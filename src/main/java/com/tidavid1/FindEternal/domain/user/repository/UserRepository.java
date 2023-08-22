package com.tidavid1.FindEternal.domain.user.repository;

import com.tidavid1.FindEternal.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.nickname = :nickname")
    Optional<User> findByNickname(@Param("nickname") String nickname);

    @Query("select u from User u where u.userNum = :userNum")
    Optional<User> findByUserNum(@Param("userNum") Integer userNum);
}
