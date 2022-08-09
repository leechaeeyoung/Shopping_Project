package com.shopping.Controller;

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

    @GetMapping("/signin")
    public String SignupForm(){
        return "signup";
    }
    @PostMapping("/signup")
    public String signUp(User user){
        User newUser = user;
        User userEntity = authService.signup(user);

        System.out.println(userEntity);
        return "signin";
    }

    //로그아웃 버튼
    @GetMapping("/logout")
    public String logout(HttpSession session) throws Exception{
        authService.logout(session);
        return "redirect:/signin";
    }
}
