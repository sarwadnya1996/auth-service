package com.example.auth_service.ontroller;

import ch.qos.logback.core.util.StringUtil;
import com.example.auth_service.dto.AccessToken;
import com.example.auth_service.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final HttpServletRequest request;
    private final AuthService service;

    @PostMapping("/accessToken")
    public ResponseEntity<AccessToken> getAccessToken() {
        String payLoad = request.getHeader("session");
        if (StringUtil.isNullOrEmpty(payLoad)) {
            throw new RuntimeException("session header not found ");
        }
        CompletableFuture<AccessToken> token = CompletableFuture.supplyAsync(() -> service.generateAccessToken(payLoad));
        return ResponseEntity.ok(token.join());
    }

}
