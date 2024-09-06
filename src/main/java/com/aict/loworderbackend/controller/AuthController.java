package com.aict.loworderbackend.controller;

import com.aict.loworderbackend.entity.Store;
import com.aict.loworderbackend.repository.StoreRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private StoreRepository storeRepository;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginData, HttpSession session) {
        String loginId = loginData.get("loginId");
        String password = loginData.get("password");

        // 프론트에서 받은 로그인 정보 출력
        System.out.println("Received Login Data - loginId: " + loginId + ", password: " + password);

        Store store = storeRepository.findByLoginId(loginId);

        if (store != null) {
            // DB에서 찾은 store 정보 출력 (보안을 위해 비밀번호는 제외)
            System.out.println("Found Store in DB - loginId: " + store.getLoginId() + ", storeName: " + store.getStoreName());

            if (store.getPassword().equals(password)) {
                // 로그인 성공 시점 출력
                System.out.println("Login successful for store: " + store.getLoginId());

                session.setAttribute("store", store);  // 세션에 전체 Store 객체 저장

                // 응답으로 storeId와 성공 메시지 전송
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Login successful");
                response.put("storeId", store.getStoreId()); // store_id 반환

                return ResponseEntity.ok(response);
            } else {
                // 비밀번호가 일치하지 않을 때
                System.out.println("Invalid password for store: " + store.getLoginId());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        } else {
            // 해당 loginId가 없을 때
            System.out.println("No store found with loginId: " + loginId);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return ResponseEntity.ok("Logout successful");
    }
}