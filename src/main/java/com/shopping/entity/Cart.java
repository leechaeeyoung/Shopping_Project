package com.shopping.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    User user; // 구매자

    private int cnt; // 카트 담긴 상품 수

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cart_items = new ArrayList<>();

    public static Cart createCart(User user){
        Cart cart = new Cart();
        cart.setCnt(0);
        cart.setUser(user);
        return cart;
    }
}
