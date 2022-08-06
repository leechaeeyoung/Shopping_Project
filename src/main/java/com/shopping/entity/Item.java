package com.shopping.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Table(name="item")
@Setter
@ToString
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //키 값 생성방법
    @Column(name="item_id") //필드 컬럼 매핑
    private Long id; //상품코드

    @Column(nullable = false, length = 50)
    private String itemName;    //상품명

    @Column(name = "price", nullable = false)
    private int price;      // 상품 가격

    @Lob
    @Column(nullable = false)
    private String itemDetail;

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;
}
