package com.tidavid1.FindEternal.domain.user.service;

import com.tidavid1.FindEternal.domain.user.entity.Player;
import com.tidavid1.FindEternal.domain.user.exception.CPlayerNotFoundException;
import com.tidavid1.FindEternal.domain.user.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Transactional
    public Player findByUserNum(Integer userNum){
        return playerRepository.findByUserNum(userNum).orElseThrow(CPlayerNotFoundException::new);
    }

    @Transactional
    public Optional<Player> findByNickname(String nickname){return playerRepository.findByNickname(nickname);}

    @Transactional
    public void addUser(Player player){
        playerRepository.save(player);
    }
}
