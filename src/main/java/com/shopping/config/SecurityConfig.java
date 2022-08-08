package com.shopping.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Bean
    public BCryptPasswordEncoder encoder(){
        //DB 패스워드 암호화
        return new BCryptPasswordEncoder();
    }
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable(); // csrf 토큰 비활성화
        http.authorizeRequests()
                .antMatchers("/main/**").authenticated() //로그인 요청
                .anyRequest().permitAll() // 위 주소 아니라면 인증 필요x
                .and()
                .formLogin()
                .loginPage("/signin") //인증 주소 접속링크로 이동
                .loginProcessingUrl("/signin") // 스프링 시큐리티가 로그인 자동진행
                .defaultSuccessUrl("/main"); //정상 로그인
    }
}
