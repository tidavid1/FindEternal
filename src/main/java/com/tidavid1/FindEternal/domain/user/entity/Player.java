package com.tidavid1.FindEternal.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "player")
@Entity
public class Player {
    @Id
    private Integer userNum;

    @Column(nullable = false)
    private String nickname;

    public void updateNickname(String nickname){
        this.nickname = nickname;
    }
}
