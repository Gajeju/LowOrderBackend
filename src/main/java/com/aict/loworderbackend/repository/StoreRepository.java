package com.aict.loworderbackend.repository;

import com.aict.loworderbackend.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findByLoginId(String loginId);  // 메서드 이름은 필드 이름에 맞춰 작성
    List<Store> findByStoreType(String storeType);
}