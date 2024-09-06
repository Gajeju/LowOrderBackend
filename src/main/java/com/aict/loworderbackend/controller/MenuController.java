package com.aict.loworderbackend.controller;

import com.aict.loworderbackend.entity.Menu;
import com.aict.loworderbackend.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    // 모든 메뉴 가져오기
    @GetMapping
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }

    // 특정 가게의 메뉴 가져오기
    @GetMapping("/store/{storeId}")
    public List<Menu> getMenusByStoreId(@PathVariable Long storeId) {
        return menuService.getMenusByStoreId(storeId);
    }

    // 메뉴 생성
    @PostMapping
    public ResponseEntity<?> createMenu(@RequestBody Menu menu) {
        System.out.println("Log: Insert Menu: " + menu);  // 수신된 데이터를 확인하기 위한 로그
        if (menu.getMenuName() == null || menu.getMenuName().isEmpty()) {
            return ResponseEntity.badRequest().body("Menu name is required.");
        }

        Menu savedMenu = menuService.createMenu(menu);
        return ResponseEntity.ok(savedMenu);
    }

    // 메뉴 수정
    @PutMapping("/{menuId}")
    public Menu updateMenu(@PathVariable Long menuId, @RequestBody Menu menuDetails) {
        return menuService.updateMenu(menuId, menuDetails);
    }

    // 메뉴 삭제
    @DeleteMapping("/{menuId}")
    public ResponseEntity<?> deleteMenu(@PathVariable Long menuId) {
        menuService.deleteMenu(menuId);
        return ResponseEntity.ok().build();
    }
}