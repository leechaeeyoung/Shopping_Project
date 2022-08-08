package com.shopping.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true) // username 중복x
    private String username;
    private String password;
    private String name;
    private String email;
    private String role; //권한
    private LocalDateTime createDate;

    @PrePersist  //DB에 INSERT 되기 전 실행
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }
}
