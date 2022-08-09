package dev.haskin.springsecuritylab1.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import dev.haskin.springsecuritylab1.dto.CryptoDTO;

@RestController
@RequestMapping("/crypto")
public class CryptoController {

    private static final String API_URL = "https://api.coincap.io/v2/assets/bitcoin";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    String getCryptoByName(@PathVariable String cryptoName) throws ResponseStatusException {
        CryptoDTO cryptoDTO = Optional.ofNullable(restTemplate.getForObject(API_URL, CryptoDTO.class))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                        "Could not connect to Bitcoin API"));
        return cryptoDTO.getData().getPriceUsd();
    }

    @GetMapping
    String getCostOfOneBitcoin() throws ResponseStatusException {
        CryptoDTO cryptoDTO = Optional.ofNullable(restTemplate.getForObject(API_URL, CryptoDTO.class))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                        "Could not connect to Bitcoin API"));
        return cryptoDTO.getData().getPriceUsd();
    }

    public static String getApiUrl() {
        return API_URL;
    }
}
