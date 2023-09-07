package com.tidavid1.FindEternal.domain.player.controller;

import com.tidavid1.FindEternal.domain.player.entity.Player;
import com.tidavid1.FindEternal.domain.player.service.PlayerService;
import com.tidavid1.FindEternal.domain.stats.entity.Season;
import com.tidavid1.FindEternal.domain.stats.service.PlayerStatsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final PlayerService playerService;
    private final PlayerStatsService playerStatsService;

    @GetMapping("")
    public String userPage(@RequestParam String nickname, Model model){
        Player player = playerService.findByNickname(nickname);
        model.addAttribute("nickname", nickname);
        model.addAttribute("playerStats", playerStatsService.findByUserNumAndSeason(player.getUserNum(), Season.RegularSeason01));
        // TODO: 대전 기록 View
        return "user";
    }
}
