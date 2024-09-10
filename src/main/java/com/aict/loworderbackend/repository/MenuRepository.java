package com.aict.loworderbackend.repository;


import com.aict.loworderbackend.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByStoreId(Long storeId);  // 특정 store의 메뉴를 가져오기 위한 메서드

}