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
        JSONObject result = webClientProvider.requestNicknameAPI(nickname);
        if (!result.get("code").equals(200)){
            throw new CUserNotFoundException();
        }
        if(userService.findByUserNum(result.getJSONObject("user").getInt("userNum")).isEmpty()){
            userService.addUser(User.builder()
                    .userNum(result.getJSONObject("user").getInt("userNum"))
                    .nickname(nickname)
                    .build());
        }else{
            User user = userService.findByUserNum(result.getJSONObject("user").getInt("userNum")).get();
            if(!user.getNickname().equals(nickname)){
                user.updateNickname(nickname);
            }
        }
        return "user";
    }
}
