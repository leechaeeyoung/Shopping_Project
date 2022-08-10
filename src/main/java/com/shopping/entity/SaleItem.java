package com.shopping.entity;

import lombok.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class SaleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_id")
    private User seller;    // 판매자

    private int itemId;     // 주문 상품 번호
    private String itemName; // 주문 상품 이름
    private int itemPrice;   // 주문 상품 가격
    private int itemCnt;     // 주문 상품 수량
    private int itemTotalPrice; // 가격 x 수량

    @OneToOne(mappedBy = "saleItem")
    private OrderItem orderItem;    // 판매 상품에 매핑되는 주문 상품
    private int isCancel;           // 판매 취소 여부(0:완료/1:취소)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate createDate;
    @PrePersist
    public void createDate(){
        this.createDate=LocalDate.now();
    }

    // 장바구니 전체 주문
    public static SaleItem createSaleItem(int itemId,Sale sale,User seller,CartItem cartItem){
        SaleItem saleItem = new SaleItem();
        saleItem.setItemId(itemId);
        saleItem.setSale(sale);
        saleItem.setSeller(seller);
        saleItem.setItemName(cartItem.getItem().getName());
        saleItem.setItemPrice(cartItem.getItem().getPrice());
        saleItem.setItemCnt(cartItem.getCartCnt());
        saleItem.setItemTotalPrice(cartItem.getItem().getPrice()*cartItem.getCartCnt());
        return saleItem;
    }

    // 상품 개별 주문
    public static SaleItem createSaleItem(int itemId,Sale sale,User seller,Item item, int itemCnt){
        SaleItem saleItem = new SaleItem();
        saleItem.setItemId(itemId);
        saleItem.setSale(sale);
        saleItem.setSeller(seller);
        saleItem.setItemName(item.getName());
        saleItem.setItemPrice(item.getPrice());
        saleItem.setItemCnt(itemCnt);
        saleItem.setItemTotalPrice(item.getPrice()*itemCnt);
        return saleItem;
    }
}
