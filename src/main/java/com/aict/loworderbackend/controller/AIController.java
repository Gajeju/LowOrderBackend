package com.aict.loworderbackend.controller;

import com.aict.loworderbackend.entity.Menu;
import com.aict.loworderbackend.entity.Order;
import com.aict.loworderbackend.entity.OrderDetail;
import com.aict.loworderbackend.entity.Store;
import com.aict.loworderbackend.service.AIService;
import com.aict.loworderbackend.service.MenuService;
import com.aict.loworderbackend.service.OrderService;
import com.aict.loworderbackend.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AIService aiService;

    @PostMapping("/suggestions")
    public ResponseEntity<String> getAiSuggestions(@RequestBody Map<String, Long> requestData) {
        Long storeId = requestData.get("storeId");

        Store currentStore = storeService.findById(storeId);
        String currentStoreName = currentStore.getStoreName();
        String storeType = storeService.findStoreTypeById(storeId);
        List<Store> equalTypeStores = storeService.findStoresByStoreType(storeType);

        List<String> storeOrderDetailsWithNames = new ArrayList<>();
        for (Store store : equalTypeStores) {
            List<Order> storeOrders = orderService.findOrdersByStore(store.getStoreId());

            for (Order order : storeOrders) {
                for (OrderDetail orderDetail : order.getOrderDetails()) {
                    String detailWithStoreName = "Store: " + store.getStoreName() + ", Menu: " + orderDetail.getMenuName() +
                            ", Price: " + orderDetail.getPrice() + ", Quantity: " + orderDetail.getQuantity();
                    storeOrderDetailsWithNames.add(detailWithStoreName);
                }
            }
        }
        String prompt = "당신은 "
                + currentStoreName
                + " 가게 매출 및 주변 가게와 비교하는 마케팅 분석가입니다. 아래의 데이터를 보고 \"가격경쟁력\"  측면으로 제안해줘"
                + String.join("\n", storeOrderDetailsWithNames);
        String aiResponse = aiService.getAiSuggestions(prompt);

        return ResponseEntity.ok(aiResponse);
    }
}