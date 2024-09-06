package com.aict.loworderbackend.controller;

import com.aict.loworderbackend.entity.Menu;
import com.aict.loworderbackend.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    // 메뉴 수정
    @PutMapping("/{menuId}")
    public ResponseEntity<Menu> updateMenu(@PathVariable Long menuId, @RequestBody Menu menuDetails) {
        Optional<Menu> optionalMenu = menuService.getMenuById(menuId);

        if (optionalMenu.isPresent()) {
            Menu existingMenu = optionalMenu.get();
            // 기존 메뉴를 업데이트
            existingMenu.setMenuName(menuDetails.getMenuName());
            existingMenu.setMenuType(menuDetails.getMenuType());
            existingMenu.setPrice(menuDetails.getPrice());
            existingMenu.setStoreId(menuDetails.getStoreId()); // storeId 변경 가능 여부에 따라 이 부분을 결정

            Menu updatedMenu = menuService.updateMenu(existingMenu);
            return ResponseEntity.ok(updatedMenu);
        } else {
            // 메뉴를 찾을 수 없는 경우
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // 메뉴 삭제
    // 메뉴 삭제
    @DeleteMapping("/{menuId}")
    public ResponseEntity<?> deleteMenu(@PathVariable Long menuId) {
        Optional<Menu> optionalMenu = menuService.getMenuById(menuId);

        if (optionalMenu.isPresent()) {
            menuService.deleteMenu(menuId);
            return ResponseEntity.ok().build();
        } else {
            // 메뉴를 찾을 수 없는 경우
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 메뉴를 찾을 수 없습니다.");
        }
    }
}