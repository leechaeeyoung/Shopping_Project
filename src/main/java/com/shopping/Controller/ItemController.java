//package com.shopping.controller;
//
//import com.shopping.config.PrincipalDetail;
//import com.shopping.entity.Item;
//import com.shopping.service.ItemService;
//import com.shopping.service.UserPageService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Repository;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.List;
//
//@Controller
//@RequiredArgsConstructor
//public class ItemController {
//    private final ItemService itemService;
//    private final UserPageService userPageService;
//    private final CartService cartService;
//
//    // 메인페이지 html (로그인 유저/localhost:8080)
//    @GetMapping("/")
//    public String mainPageNoneLogin(Model model){
//        //로그인 x일 때
//        List<Item> items = itemService.allItemView();
//        model.addAttribute("items",items);
//        return "main";
//    }
//    // 메인 페이지(로그인)-> 판매자 / 구매자로 로그인
//    @GetMapping("/main")
//    public String mainPage(Model model, @AuthenticationPrincipal PrincipalDetail principalDetail){
//        if(principalDetail.getUser().getRole().equals("ROLE_SELLER")){
//            // 판매자
//            int sellerid = principalDetail.getUser().getId();
//            List<Item> items = itemService.allItemView();
//            model.addAttribute("items",items);
//            model.addAttribute("user",userPageService.findUser(sellerid));
//            return "/main";
//        } else{
//            // 구매자
//            int userId = principalDetail.getUser().getId();
//            List<Item> items = itemService.allItemView();
//            model.addAttribute("items",items);
//            model.addAttribute("user",userPageService.findUser(userId));
//            return "/main";
//        }
//    }
//}
