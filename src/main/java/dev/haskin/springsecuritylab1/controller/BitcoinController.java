package dev.haskin.springsecuritylab1.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import dev.haskin.springsecuritylab1.dto.BitcoinDTO;

@RestController
@RequestMapping("/bitcoin")
public class BitcoinController {

    private static final String API_URL = "https://api.coincap.io/v2/assets/bitcoin";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    String getCostOfOneBitcoin() throws ResponseStatusException {
        // RestTemplate restTemplate = new RestTemplate();
        BitcoinDTO bitcoinDTO = Optional.ofNullable(restTemplate.getForObject(API_URL, BitcoinDTO.class))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                        "Could not connect to Bitcoin API"));
        return bitcoinDTO.getData().getPriceUsd();
    }

    public static String getApiUrl() {
        return API_URL;
    }
}
