package com.shopping.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_id")
    private User seller;    // 판매자

    @OneToMany(mappedBy = "sale")
    private List<SaleItem> saleItems = new ArrayList<>();
    private int totalCnt;   // 총 판매 개수

    public static Sale createSale(User seller){
        Sale sale = new Sale();
        sale.setSeller(seller);
        sale.setTotalCnt(0);
        return sale;
    }
}
