package com.shopping.service;

import com.shopping.entity.User;
import com.shopping.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;

    //BCrypt 이용해 패스워드 암호화 + 신규회원 Role 값 적용
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CartService cartService;
    private final OrderService orderService;
    private final SaleService saleService;

    @Transactional
    public User signup(User user){
        String rawPassword=user.getPassword();
        String encPassword=bCryptPasswordEncoder.encode(rawPassword);

        user.setPassword(encPassword);
        user.setRole(user.getRole());

        User userEntity = userRepository.save(user);

        if(Object.equals(userEntity.getRole(),"ROLE_SELLER")){
            saleService.createSale(user);
        } else if(Objects.equals(user.getRole(),"ROLE_USER")){
            cartService.createCart(user);
            orderService.createOrder(user);
        }
        return userEntity;
    }
}
