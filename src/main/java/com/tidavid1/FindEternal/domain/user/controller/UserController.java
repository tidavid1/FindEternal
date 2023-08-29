package com.tidavid1.FindEternal.domain.user.controller;

import com.tidavid1.FindEternal.common.config.WebClientProvider;
import com.tidavid1.FindEternal.domain.user.entity.User;
import com.tidavid1.FindEternal.domain.user.exception.CUserNotFoundException;
import com.tidavid1.FindEternal.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final WebClientProvider webClientProvider;
    private final UserService userService;

    @GetMapping("")
    public String userPage(@RequestParam String nickname){
        User user = userService.findByNickname(nickname).orElseGet(()->{
            JSONObject result = webClientProvider.requestNicknameAPI(nickname);
            if(!(result.getInt("code")==200)){
                throw new CUserNotFoundException();
            }
            result = result.getJSONObject("user");
            User temp = User.builder().userNum(result.getInt("userNum")).nickname(nickname).build();
            userService.addUser(temp);
            return temp;
        });
        if(!userService.findByUserNum(user.getUserNum()).getNickname().equals(nickname)){
            user = userService.findByUserNum(user.getUserNum());
            user.updateNickname(nickname);
        }
        // TODO: 평균 수치 View
        // TODO: 대전 기록 View
        return "user";
    }
}
