package com.shopping.controller;

import com.shopping.dto.SignupDto;
import com.shopping.entity.User;
import com.shopping.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class AuthController {
    private final AuthService authService;

    @GetMapping("/signup")
    public String SignupForm(){
        return "signup";
    }
    @PostMapping("/signin")
    public String signinForm(){
        return "signin";
    }
    @PostMapping("/signup")
    public String signup(SignupDto signupDto){
        // user 에 signupDto 삽입
        User user = signupDto.toEntity();
        User userEntity = authService.signup(user);
        System.out.println(userEntity);
        return "signin";
    }
}
