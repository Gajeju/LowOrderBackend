package com.aict.loworderbackend.service;

import com.aict.loworderbackend.entity.Store;
import com.aict.loworderbackend.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public String findStoreTypeById(Long storeId) {
        return storeRepository.findById(storeId)
                .map(Store::getStoreType)
                .orElseThrow(() -> new IllegalArgumentException("Store not found"));
    }

    public List<Store> findStoresByStoreType(String storeType) {
        return storeRepository.findByStoreType(storeType);
    }

    public Store findById(Long storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("Store not found with id: " + storeId));
    }

}
