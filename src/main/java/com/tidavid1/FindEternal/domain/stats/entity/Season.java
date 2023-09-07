package com.tidavid1.FindEternal.domain.stats.entity;

import lombok.Getter;

@Getter
public enum Season {

    Normal(0), RegularSeason01(19);
    final private Integer seasonId;
    Season(int seasonId){
        this.seasonId = seasonId;
    }
}
