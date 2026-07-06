package com.example.auth_service.service;

import com.example.auth_service.dto.AccessToken;
import com.example.auth_service.dto.TokenDetail;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.HexFormat;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Value("${aes.secret.key}")
    private String secretKey;

    @Value("${aes.secret.iv}")
    private String secretIv;
    public AccessToken generateAccessToken(String payload) {
        TokenDetail tokenDetail = decryptPayload(payload);

        return null;
    }

    @SneakyThrows
    public TokenDetail decryptPayload(String payload) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(HexFormat.of().parseHex(secretKey), "AES");
        IvParameterSpec parameterSpec = new IvParameterSpec(HexFormat.of().parseHex(secretIv));
        Cipher cipher=Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, parameterSpec);
        byte[] cipherText = Base64.getDecoder().decode(payload);
        byte[] decrypted = cipher.doFinal(cipherText);
        return new ObjectMapper().readValue(new String(decrypted,"UTF-8"), TokenDetail.class);
    }
}
