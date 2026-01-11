package com.alchemist.framework.utils;

import com.alchemist.framework.clients.OAuthClient;
import com.alchemist.framework.models.TokenResponse;

public class TokenManager {

    private TokenManager() {}

    private static String token;
    private static long expiryTimeMs;

    public static synchronized String getToken() {
        long now = System.currentTimeMillis();

        if (token == null || now >= expiryTimeMs) {
            TokenResponse tr = new OAuthClient().generateToken();
            token = tr.access_token;
            expiryTimeMs = now + (tr.expires_in - 30) * 1000L; // 30 sec buffer
        }
        return token;
    }
}
