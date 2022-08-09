package com.shopping.repository;

import com.shopping.entity.Cart;
import com.shopping.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface ItemRepository extends JpaRepository<Cart, Integer>{
    Item findItemById(int id);
    Page<Item> findByNameContaining(String searchKeyword, Pageable pageable);
}
