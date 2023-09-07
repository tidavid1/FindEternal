package com.tidavid1.FindEternal.domain.stats.repository;

import com.tidavid1.FindEternal.domain.stats.entity.PlayerStats;
import com.tidavid1.FindEternal.domain.stats.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerStatsRepository extends JpaRepository<PlayerStats, Long> {
    @Query("select s from PlayerStats s where s.userNum = :userNum and s.season = :season")
    Optional<PlayerStats> findByUserNumAndSeason(@Param("userNum")Integer userNum, @Param("season")Season season);
}
