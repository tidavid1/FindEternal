package com.tidavid1.FindEternal.domain.stats.service;

import com.tidavid1.FindEternal.common.config.WebClientProvider;
import com.tidavid1.FindEternal.domain.stats.entity.PlayerStats;
import com.tidavid1.FindEternal.domain.stats.entity.Season;
import com.tidavid1.FindEternal.domain.stats.exception.CPlayerStatsNotFoundException;
import com.tidavid1.FindEternal.domain.stats.repository.PlayerStatsRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PlayerStatsService {
    private final PlayerStatsRepository playerStatsRepository;
    private final WebClientProvider webClientProvider;
    @Transactional
    public PlayerStats findByUserNumAndSeason(Integer userNum, Season season){
        return playerStatsRepository.findByUserNumAndSeason(userNum, season).orElseGet(()->{
           JSONObject result = webClientProvider.requestUserStats(userNum, season.getSeasonId());
           if(!(result.getInt("code")==200)){
               throw new CPlayerStatsNotFoundException();
           }
           result = result.getJSONArray("userStats").getJSONObject(0);
           PlayerStats playerStats = PlayerStats.builder()
                   .userNum(userNum)
                   .season(season)
                   .rankPercent(result.getInt("rank"))
                   .averageRank(result.getDouble("averageRank"))
                   .top1(result.getDouble("top1"))
                   .totalGames(result.getInt("totalGames"))
                   .averageKills(result.getDouble("averageKills"))
                   .top2(result.getDouble("top2"))
                   .mmr(result.getInt("mmr"))
                   .averageAssistants(result.getDouble("averageAssistants"))
                   .top3(result.getDouble("top3"))
                   .averageHunts(result.getDouble("averageHunts"))
                   .build();
           addPlayerStats(playerStats);
           return playerStats;
        });
    }

    @Transactional
    public void addPlayerStats(PlayerStats playerStats){
        playerStatsRepository.save(playerStats);
    }
}
