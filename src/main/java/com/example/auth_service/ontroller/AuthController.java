package com.example.auth_service.ontroller;

import com.example.auth_service.dto.AccessToken;
import com.example.auth_service.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.concurrent.CompletableFuture;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final HttpServletRequest  request;
    private final AuthService service;

    @GetMapping("/accessToken")
    public ResponseEntity<AccessToken> getAccessToken(){
        String payLoad=request.getHeader("session");
        CompletableFuture<AccessToken> token= CompletableFuture.supplyAsync(()->service.generateAccessToken(payLoad));
        return ResponseEntity.ok(token.join());
    }

}
