package com.shopping.service;

import com.shopping.entity.Cart;
import com.shopping.entity.CartItem;
import com.shopping.entity.Item;
import com.shopping.entity.User;
import com.shopping.repository.CartItemRepository;
import com.shopping.repository.CartRepository;
import com.shopping.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final CartItemRepository cartItemRepository;

    //회원가입 후 카트 하나 생성
    public void createCart(User user){
        Cart cart = Cart.createCart(user);
        cartRepository.save(cart);
    }
    @Transactional
    // 장바구니 담기
    public void addCart(User user, Item newItem, int amount){
        // id로 장바구니 찾기
        Cart cart = cartRepository.findByUserId(user.getId());
        // 장바구니가 없다면
        if(cart==null){
            cartRepository.save(cart);
        }
        Item item = itemRepository.findItemById(newItem.getId());
        CartItem cartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());

        //장바구니에 상품 없을 때 카트상품 추가
        if(cartItem==null){
            cartItem = CartItem.createCartItem(cart,item,amount);
            cartItemRepository.save(cartItem);
        }
        // 장바구니에 이미 존재한다면 수량만 증가
        else{
            CartItem update = cartItem;
            update.setCart(cartItem.getCart());
            update.setItem(cartItem.getItem());
            update.addCnt(amount);
            update.setCartCnt(update.getCartCnt());
            cartItemRepository.save(update);
        }
        // 카트 상품 총 개수 증기
        cart.setCnt(cart.getCnt());
    }
    // 유저 id로 장바구니 찾기
    public Cart findUserCart(int userId){
        return cartRepository.findCartByUserID(userId);
    }
    // user 카트 id와 카트상품 id가 가튼 상품만 반환
    public List<CartItem> allUserCartView(Cart userCart){
        // 유저의 카트 id
        int userCartId = userCart.getId();
        // id에 해당하는 유저의 상품 넣어둘 곳
        List<CartItem> userCartItem = new ArrayList<>();
        // 유저 상관없이 카트에 있는 상품 모두 불러오기
        List<CartItem> CartItems = cartItemRepository.findAll();
        for(CartItem cartItem:CartItems){
            if(cartItem.getCart().getId()==userCartId){
                userCartItem.add(cartItem);
            }
        }
        return userCartItem;
    }
    // 카트 상품 리스트 중 해당하는 상품 id만 반환
    public List<CartItem> findCartItemByItemId(int id){
        List<CartItem> cartItems = (List<CartItem>) cartItemRepository.findCartItemById(id);
        return cartItems;
    }
    // 카트 상품 중 해당 상품 id만 반환
    public CartItem findCartItemById(int id){
        CartItem cartItem = cartItemRepository.findCartItemById(id);
        return cartItem;
    }
    // 장바구니 상품 개별 삭제
    public void cartItemDelete(int id){
        cartItemRepository.deleteById(id);
    }
    // 전체 삭제
    public void allCartItemDelete(int id){
        // 전체 cartItem
        List<CartItem> cartItems = cartItemRepository.findAll();
        // 해당 user 의 cart item 만 찾아 삭제
        for(CartItem cartItem:cartItems){
            if(cartItem.getCart().getUser().getId()==id)
                cartItem.getCart().setCnt(0);
                cartItemRepository.deleteById(cartItem.getId());
        }
    }
}
