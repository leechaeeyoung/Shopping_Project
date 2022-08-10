package com.shopping.entity;

import lombok.*;
import org.hibernate.query.criteria.internal.OrderImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;      // 구매자

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate createDate;
    @PrePersist
    public void createDate(){
        this.createDate=LocalDate.now();
    }
    public void addOrderItem(OrderItem orderItem){
        orderItem.add(orderItem);
        orderItem.setOrder(this);
    }

    public static Order createOrder(User user, List<OrderItem> orderItemList){
        Order order = new Order();
        order.setUser(user);
        for(OrderItem orderItem:orderItemList){
            order.addOrderItem(orderItem);
        }
        order.setCreateDate(order.createDate);
        return order;
    }
    public static Order createOrder(User user){
        Order order = new Order();
        order.setUser(user);
        order.setCreateDate(order.createDate);
        return order;
    }
}
