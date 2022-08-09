package com.shopping.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
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
    private int id;            //상품코드
    private String name;        //상품명
    private int price;          // 상품 가격
    private String text;       // 상세 설명
    private int stock;          // 재고
    private int isSold;      // 상품 상태(0: 판매/ 1: 품절)
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="seller_id")
    private User seller;         // 판매자 아이디
    
    @OneToMany(mappedBy = "item")
    private List<CartItem> cart_items = new ArrayList<>();

    private String imgName; // 파일명
    private String imgPath; // 경로 조회

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate createDate; // 상품 등록 날짜
    
    @PrePersist   // insert 직전 실행
    public void createDate(){
        this.createDate = LocalDate.now();
    }

}
