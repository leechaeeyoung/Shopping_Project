package com.shopping.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private String address;
    private String phone;
    private String role; //권한 (회원/관리자)

    // 판매자 갖고있는 상품들
    @OneToMany(mappedBy = "seller")
    private List<Item> items = new ArrayList<>();
    // 판매자의 판매상품
    @OneToMany(mappedBy = "seller")
    private List<SaleItem> sellerSaleItem = new ArrayList<>();
    //판매자의 판매
    @OneToMany(mappedBy = "seller")
    private List<SaleItem> sellerSale;

    // 구매자 장바구니
    @OneToOne(mappedBy = "user")
    private Cart cart;

    // 구매자 주문
    @OneToMany(mappedBy = "user")
    private List<Order> userOrder = new ArrayList<>();

    // 구매자 주문상품
    @OneToMany(mappedBy = "user")
    private List<OrderItem> userOrderItem = new ArrayList<>();

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime createDate;
    @PrePersist  //DB에 INSERT 되기 전 실행
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }
}
