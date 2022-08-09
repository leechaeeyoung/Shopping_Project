package com.shopping.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //키 값 생성방법
    private Long id;            //상품코드
    private String itemName;    //상품명
    private int price;          // 상품 가격
    private String itemDetail;  // 상세 설명
    private int stock;          // 재고
    private String photo;       // 상품사진
    
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;         // 판매자 아이디
    
    @OneToMany
    private List<CartItem> cart_items = new ArrayList<>();

    private String imgName; // 파일명
    private String imgPath; // 경로 조회

}
