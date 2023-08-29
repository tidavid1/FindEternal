package com.tidavid1.FindEternal.domain.user.service;

import com.tidavid1.FindEternal.domain.user.entity.User;
import com.tidavid1.FindEternal.domain.user.exception.CUserNotFoundException;
import com.tidavid1.FindEternal.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User findByUserNum(Integer userNum){
        return userRepository.findByUserNum(userNum).orElseThrow(CUserNotFoundException::new);
    }

    @Transactional
    public Optional<User> findByNickname(String nickname){return userRepository.findByNickname(nickname);}

    @Transactional
    public void addUser(User user){
        userRepository.save(user);
    }
}
