package com.shopping.service;

import com.shopping.entity.Item;
import com.shopping.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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

        String saveFilename = uuid + "_" + orgImgName;
        imgName = saveFilename;
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

}
