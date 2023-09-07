package com.tidavid1.FindEternal.domain.stats.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "player_stats")
@Entity
public class PlayerStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statsId;

    @Column
    private Integer userNum;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private Season season;

    @Column
    private Integer rankPercent;

    @Column
    private Double averageRank;

    @Column
    private Double top1;

    @Column
    private Integer totalGames;

    @Column
    private Double averageKills;

    @Column
    private Double top2;

    @Column
    private Integer mmr;

    @Column
    private Double averageAssistants;

    @Column
    private Double top3;

    @Column
    private Double averageHunts;

    public void updateStats(PlayerStats playerStats){
        this.rankPercent = playerStats.getRankPercent();
        this.averageRank = playerStats.getAverageRank();
        this.top1 = playerStats.getTop1();
        this.totalGames = playerStats.getTotalGames();
        this.averageKills = playerStats.getAverageKills();
        this.top2 = playerStats.getTop2();
        this.mmr = playerStats.getMmr();
        this.averageAssistants = playerStats.getAverageAssistants();
        this.top3 = playerStats.getTop3();
        this.averageHunts = playerStats.getAverageHunts();
    }
}
