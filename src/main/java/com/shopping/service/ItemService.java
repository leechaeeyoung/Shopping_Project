package com.shopping.service;

import com.shopping.entity.CartItem;
import com.shopping.entity.Item;
import com.shopping.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final CartService cartService;
    private final SaleService saleService;

    // 상품 등록
    public void saveItem(Item item, MultipartFile imgFile) throws Exception{
        String orgImgName = imgFile.getOriginalFilename();
        String imgName = "";

        String project_path = System.getProperty("user.dir")+"/src/main/resources/static/files/";
        //UUID(객체구별 클래스)를 이용해 파일 새로 생성
        UUID uuid = UUID.randomUUID();

        String savedFileName = uuid + "_" + orgImgName;
        imgName=savedFileName;

        File saveFile = new File(project_path, imgName);
        imgFile.transferTo(saveFile);
        item.setImgName(imgName);
        item.setImgPath("/files/"+imgName);
        itemRepository.save(item);
    }

    // 개별 상품 가져오기
    public Item itemView(Integer id){
        return itemRepository.findBy(id).get();
    }
    // 상품 리스트 가져오기
    public List<Item> allItemView(){
        return itemRepository.findAll();
    }
    // 상품 리스트 페이지 가져오기
    public Page<Item> allItemViewPage(Pageable pageable){
        return itemRepository.findAll(pageable);
    }
    // 상품 수정
    @Transactional
    public void itemModify(Item item, Integer id, MultipartFile imgFile) throws Exception{
        String projectPath = System.getProperty("user.dir")+"/src/main/resource/static/files/";
        UUID uuid = UUID.randomUUID();
        String fileName = uuid+"_"+imgFile.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);
        imgFile.transferTo(saveFile);

        Item update = itemRepository.findItemById(id);
        update.setName(item.getName());
        update.setText(item.getText());
        update.setPrice(item.getPrice());
        update.setStock(item.getStock());
        update.setIsSoldOut(item.getIsSoldOut());
        update.setImgName(item.getImgName());
        update.setImgPath(item.getImgPath());
        itemRepository.save(update);
    }
    // 상품 삭제
    @Transactional
    public void itemDelete(Integer id){
        // cart item 중 해당 id 가진 아이템찾기
        List<CartItem> items = cartService.findCartItemByItemId(id);
        for(CartItem item: items){
            cartService.cartItemDelete(item.getId());
        }
        itemRepository.deleteById(items.getId());
    }
    // 상품 검색
    public Page<Item> itemSearchList(String searchKeyword, Pageable pageable){
        return itemRepository.findByNameContaining(searchKeyword,pageable);
    }
}
