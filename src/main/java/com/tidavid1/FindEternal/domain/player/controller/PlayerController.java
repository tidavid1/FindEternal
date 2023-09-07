package com.tidavid1.FindEternal.domain.player.controller;

import com.tidavid1.FindEternal.common.config.WebClientProvider;
import com.tidavid1.FindEternal.domain.player.entity.Player;
import com.tidavid1.FindEternal.domain.player.exception.CPlayerNotFoundException;
import com.tidavid1.FindEternal.domain.player.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/player")
public class PlayerController {
    private final WebClientProvider webClientProvider;
    private final PlayerService playerService;

    @GetMapping("")
    public String userPage(@RequestParam String nickname, Model model){
        Player player = playerService.findByNickname(nickname).orElseGet(()->{
            JSONObject result = webClientProvider.requestNicknameAPI(nickname);
            if(!(result.getInt("code")==200)){
                throw new CPlayerNotFoundException();
            }
            result = result.getJSONObject("user");
            Player temp = Player.builder().userNum(result.getInt("userNum")).nickname(nickname).build();
            playerService.addUser(temp);
            return temp;
        });
        if(!playerService.findByUserNum(player.getUserNum()).getNickname().equals(nickname)){
            player = playerService.findByUserNum(player.getUserNum());
            player.updateNickname(nickname);
        }
        model.addAttribute("nickname", nickname);
        // TODO: 평균 수치 View
        JSONObject stats = webClientProvider.requestUserStats(player.getUserNum(), 19).getJSONArray("userStats").getJSONObject(0);
        model.addAttribute("stats", stats);
        // TODO: 대전 기록 View
        return "user";
    }
}
