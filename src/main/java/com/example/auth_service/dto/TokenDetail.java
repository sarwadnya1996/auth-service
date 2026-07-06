package com.example.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDetail {
    private String sessionId;
    private String issuedAt;
    private AuthRequest authRequest;
    private String expireAt;
}
