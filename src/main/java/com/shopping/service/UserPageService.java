package com.shopping.service;

import com.shopping.entity.User;
import com.shopping.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserPageService {
    private final UserRepository userRepository;

    // id로 찾기
    public User findUser(Integer id){
        return userRepository.findById(id).get();
    }
    // 회원 정보 수정
    public void userModify(User user){
        User update = userRepository.findById(user.getId());
        update.setUsername(user.getUsername());
        update.setEmail(user.getEmail());
        update.setAddress(user.getAddress());
        update.setPhone(user.getPhone());
        userRepository.save(update);
    }
}
