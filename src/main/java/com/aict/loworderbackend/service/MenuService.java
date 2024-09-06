package com.aict.loworderbackend.service;

import com.aict.loworderbackend.entity.Menu;
import com.aict.loworderbackend.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    // 모든 메뉴 가져오기
    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    // 특정 가게의 메뉴 가져오기
    public List<Menu> getMenusByStoreId(Long storeId) {
        return menuRepository.findByStoreId(storeId);
    }

    // 메뉴 조회 by ID
    public Optional<Menu> getMenuById(Long menuId) {
        return menuRepository.findById(menuId);
    }

    // 메뉴 저장
    public Menu createMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    // 메뉴 업데이트
    public Menu updateMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    // 메뉴 삭제
    public void deleteMenu(Long menuId) {
        menuRepository.deleteById(menuId);
    }
}