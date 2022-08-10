package com.shopping.entity;

import jdk.jfr.Enabled;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Order order;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    
    private int itemId;     // 주문 상품 번호
    private String itemName; // 주문 상품 이름
    private int itemPrice;   // 주문 상품 가격
    private int itemCnt;    // 주문 상품 수량
    private int itemTotalPrice; // 가격 x 수량

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "saleItem_id")
    private SaleItem saleItem;
    private int isCancel;       // 취소여부 (0:주문완료/1:주문취소)

    //장바구니 전체 주문
    public static OrderItem createOrderItem(int itemId,User user,CartItem cartItem,SaleItem saleItem){
        OrderItem orderItem = new OrderItem();
        orderItem.setItemId(itemId);
        orderItem.setUser(user);
        orderItem.setItemName(cartItem.getItem().getName());
        orderItem.setItemPrice(cartItem.getItem().getPrice());
        orderItem.setItemCnt(cartItem.getCartCnt());
        orderItem.setItemTotalPrice(cartItem.getItem().getPrice()*cartItem.getCartCnt());
        orderItem.setSaleItem(saleItem);
        return orderItem;
    }
    // 상품 개별 주문
    public static OrderItem createOrderItem(int itemId,User user,Item item,int itemCnt,Order order,SaleItem saleItem){
        OrderItem orderItem = new OrderItem();
        orderItem.setItemId(itemId);
        orderItem.setUser(user);
        orderItem.setOrder(order);
        orderItem.setItemName(item.getName());
        orderItem.setItemPrice(item.getPrice());
        orderItem.setItemCnt(itemCnt);
        orderItem.setItemTotalPrice(item.getPrice()*itemCnt);
        orderItem.setSaleItem(saleItem);
        return orderItem;
    }
}
