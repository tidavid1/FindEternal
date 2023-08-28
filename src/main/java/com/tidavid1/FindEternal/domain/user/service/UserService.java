package com.tidavid1.FindEternal.domain.user.service;

import com.tidavid1.FindEternal.domain.user.entity.User;
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
    public Optional<User> findByUserNum(Integer userNum){
        return userRepository.findByUserNum(userNum);
    }

    @Transactional
    public void addUser(User user){
        userRepository.save(user);
    }
}
