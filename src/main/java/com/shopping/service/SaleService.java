package com.shopping.service;

import com.shopping.entity.*;
import com.shopping.repository.ItemRepository;
import com.shopping.repository.SaleItemRepository;
import com.shopping.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService  {
    private final SaleRepository saleRepository;
    private final UserPageService userPageService;
    private final SaleItemRepository saleItemRepository;
    private final ItemRepository itemRepository;

    //회원가입 시 판매자 당 판매내역 생성
    public void createSale(User user){
        Sale sale = Sale.createSale(user);
        saleRepository.save(sale);
    }
    // id에 해당하는 판매아이템 찾기
    public List<SaleItem> findSellerSaleItems (int sellerId) {
        return saleItemRepository.findSaleItemBySellerId(sellerId);
    }
    // 판매자 id에 해당하는 Sale 찾기
    public Sale findSaleById (int sellerId) {
        return saleRepository.findBySellerId(sellerId);
    }
    // 판매내역에 저장(장바구니 전체 주문)
    @Transactional
    public SaleItem addSale(int itemId, int sellerId,CartItem cartItem){
        User seller = userPageService.findUser(sellerId);
        Sale sale = saleRepository.findBySellerId(sellerId);
        sale.setTotalCnt(sale.getTotalCnt()+cartItem.getCartCnt());
        saleRepository.save(sale);
        SaleItem saleItem=SaleItem.createSaleItem(itemId,sale,seller,cartItem);
        saleItemRepository.save(saleItem);
        return saleItem;
    }
    // 판매내역에 저장(상품 개별 주문)
    @Transactional
    public SaleItem addSale(int sellerId, Item item,int count){
        User seller = userPageService.findUser(sellerId);
        Sale sale = saleRepository.findBySellerId(sellerId);
        sale.setTotalCnt(sale.getTotalCnt()+count);
        saleRepository.save(sale);
        SaleItem saleItem = SaleItem.createSaleItem(item.getId(),sale,seller,item,count);
        saleItemRepository.save(saleItem);

        return saleItem;
    }
}
