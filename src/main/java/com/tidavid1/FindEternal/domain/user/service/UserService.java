package com.tidavid1.FindEternal.domain.user.service;

import com.tidavid1.FindEternal.domain.user.entity.User;
import com.tidavid1.FindEternal.domain.user.exception.CUserNotFoundException;
import com.tidavid1.FindEternal.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User findByNickname(String nickName){
        return userRepository.findByNickname(nickName).orElseThrow(CUserNotFoundException::new);
    }

    @Transactional
    public User findByUserNum(Integer userNum){
        return userRepository.findByUserNum(userNum).orElseThrow(CUserNotFoundException::new);
    }

    @Transactional
    public void updateNickName(Integer userNum, String nickName){
        User user = findByUserNum(userNum);
        user.updateNickname(nickName);
    }
}