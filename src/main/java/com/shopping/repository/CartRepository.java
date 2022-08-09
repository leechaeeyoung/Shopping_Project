package com.shopping.repository;

import com.shopping.entity.Cart;
import groovy.transform.RecordBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByUserId(int id);
    Cart findCartById(int id);
    Cart findCartByUserID(int id);
}
