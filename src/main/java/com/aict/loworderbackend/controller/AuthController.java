package com.aict.loworderbackend.controller;

import com.aict.loworderbackend.entity.Store;
import com.aict.loworderbackend.repository.StoreRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private StoreRepository storeRepository;

    @PostMapping("/login")  // POST 요청을 처리하는 엔드포인트
    public ResponseEntity<String> login(@RequestBody Map<String, String> loginData, HttpSession session) {
        String loginId = loginData.get("loginId");
        String password = loginData.get("password");

        Store store = storeRepository.findByLoginId(loginId);
        if (store != null && store.getPassword().equals(password)) {
            session.setAttribute("store", store); // 세션에 사용자 정보 저장
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login credentials");
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return ResponseEntity.ok("Logout successful");
    }
}