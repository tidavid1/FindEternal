package com.tidavid1.FindEternal.domain.player.service;

import com.tidavid1.FindEternal.common.config.WebClientProvider;
import com.tidavid1.FindEternal.domain.player.entity.Player;
import com.tidavid1.FindEternal.domain.player.exception.CPlayerNotFoundException;
import com.tidavid1.FindEternal.domain.player.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final WebClientProvider webClientProvider;

    @Transactional
    public Player findByNickname(String nickname){
        return playerRepository.findByNickname(nickname).orElseGet(()->{
            JSONObject result = webClientProvider.requestNicknameAPI(nickname);
            if(!(result.getInt("code")==200)){
                throw new CPlayerNotFoundException();
            }
            result = result.getJSONObject("user");
            JSONObject finalResult = result;
            Player temp = playerRepository.findByUserNum(result.getInt("userNum")).orElseGet(()->{
                Player newPlayer = Player.builder().userNum(finalResult.getInt("userNum")).nickname(nickname).build();
                addUser(newPlayer);
                return newPlayer;}
            );
            if (!temp.getNickname().equals(nickname)){
                temp.updateNickname(nickname);
            }
            return temp;
        });
    }

    @Transactional
    public void addUser(Player player){
        playerRepository.save(player);
    }
}
