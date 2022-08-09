package dev.haskin.springsecuritylab1.service;

import org.springframework.stereotype.Service;

@Service
public class CryptoService {

    private static final String API_URL = "https://api.coincap.io/v2/assets/%s";

    public String getApiUrl(String cryptoName) {
        return String.format(CryptoService.API_URL, cryptoName);
    }

    public String getApiUrl() {
        return String.format(CryptoService.API_URL, "bitcoin");
    }
}
