package dev.haskin.springsecuritylab1.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import dev.haskin.springsecuritylab1.dto.CryptoDTO;
import dev.haskin.springsecuritylab1.service.CryptoService;

@RestController
@RequestMapping("crypto")
public class CryptoController {

    @Autowired
    private CryptoService cryptoService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{cryptoName}")
    CryptoDTO getCryptoByName(@PathVariable String cryptoName) throws Exception {
        try {
            return restTemplate.getForObject(cryptoService.getApiUrl(cryptoName), CryptoDTO.class);
        } catch (RestClientException e) {
            Optional.ofNullable(e).ifPresent(error -> {
                if (e.getMessage().contains("404"))
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid crypto name was given");
            });
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "could not connect to Crypto API");
        }
    }

    @GetMapping
    String getCostOfOneBitcoin() throws ResponseStatusException {
        CryptoDTO cryptoDTO = Optional
                .ofNullable(restTemplate.getForObject(cryptoService.getApiUrl("bitcoin"),
                        CryptoDTO.class))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                        "Could not connect to Bitcoin API"));
        return cryptoDTO.getData().getPriceUsd();
    }
}