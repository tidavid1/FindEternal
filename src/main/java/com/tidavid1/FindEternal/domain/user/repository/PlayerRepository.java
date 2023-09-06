package com.tidavid1.FindEternal.domain.user.repository;

import com.tidavid1.FindEternal.domain.user.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    @Query("select p from Player p where p.nickname = :nickname")
    Optional<Player> findByNickname(@Param("nickname") String nickname);

    @Query("select p from Player p where p.userNum = :userNum")
    Optional<Player> findByUserNum(@Param("userNum") Integer userNum);
}
