package com.shopping.service;

import com.shopping.entity.*;
import com.shopping.repository.CartItemRepository;
import com.shopping.repository.OrderItemRepository;
import com.shopping.repository.OrderRepository;
import com.shopping.repository.SaleItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserPageService userPageService;
    private final SaleItemRepository saleItemRepository;
    private final ItemService itemService;

    // 회원가입 시 주문 하나 생성
    public void createOrder(User user){
        Order order = Order.createOrder(user);
        orderRepository.save(order);
    }
    //id 해당 주문찾기
    public List<OrderItem> findUserOrderItems(int userId){
        return orderItemRepository.findOrderItemsByUserId(userId);
    }
    // order item 하나 찾기
    public OrderItem findOrderItem(int orderItemId){
        return orderItemRepository.findOrderItemById(orderItemId);
    }
    // 장바구니 상품 주문
    @Transactional
    public OrderItem addCartOrder(int itemId, int userId, CartItem cart,SaleItem saleItem){
        User user = userPageService.findUser(userId);
        OrderItem orderItem = OrderItem.createOrderItem(itemId,user,cartItem,saleItem);
        orderItemRepository.save(orderItem);
        return orderItem;
    }
    // 주문하면 order 만들기
    @Transactional
    public void addOrder(User user,List<OrderItem> orderItemList){
        Order userOrder = Order.createOrder(user,orderItemList);
        orderRepository.save(userOrder);
    }
    // 단일 상품 주문
    @Transactional
    public void addOneItemOrder(int user)
}
