package dev.haskin.springsecuritylab1.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import dev.haskin.springsecuritylab1.dto.CryptoDTO;
import dev.haskin.springsecuritylab1.dto.DataDTO;
import dev.haskin.springsecuritylab1.service.CryptoService;

@ExtendWith(MockitoExtension.class)
public class CryptoControllerTest {

    @Mock
    private CryptoService cryptoService;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CryptoController cryptoController;

    @Test
    void testCryptoByName_invalidName() throws Exception {
        String gibberish = "adjlafjdfoqejjadsfhg";
        when(restTemplate.getForObject(cryptoService.getApiUrl(), CryptoDTO.class))
                .thenThrow(new RestClientException("404"));
        try {
            cryptoController.getCryptoByName(gibberish);
        } catch (ResponseStatusException e) {
            HttpStatus status = Optional.ofNullable(e.getStatus()).orElse(null);
            assertEquals(HttpStatus.BAD_REQUEST, status);
        }
    }

    @Test
    void testCryptoByName_name() throws Exception {
        String cryptoName = "ethereum";
        DataDTO dataDTO = new DataDTO();
        dataDTO.setName(cryptoName);
        CryptoDTO cryptDTO = new CryptoDTO(dataDTO);
        when(restTemplate.getForObject(cryptoService.getApiUrl(), CryptoDTO.class)).thenReturn(cryptDTO);
        assertEquals(cryptoName, cryptoController.getCryptoByName(cryptoName).getData().getName());
    }

    @Test
    void testGetCostOfOneBitcoin() {
        // System.out.println(cryptoService.getApiUrl());
        String expectedPrice = "20_000";
        DataDTO dataDTO = new DataDTO();
        dataDTO.setPriceUsd(expectedPrice);
        CryptoDTO bitcoinDTO = new CryptoDTO(dataDTO);
        when(restTemplate.getForObject(cryptoService.getApiUrl(), CryptoDTO.class)).thenReturn(bitcoinDTO);
        assertEquals(expectedPrice, cryptoController.getCostOfOneBitcoin());
        // verify(bitcoinDTO).getData();
    }

    @Test
    void testGetCostOfOneBitcoin_null() {
        when(restTemplate.getForObject(cryptoService.getApiUrl(), CryptoDTO.class))
                .thenReturn(null);
        assertThrows(ResponseStatusException.class,
                () -> {
                    cryptoController.getCostOfOneBitcoin();
                });
    }

    @Test
    void testGetCostOfOneBitcoin_restTemplateException() {
        when(restTemplate.getForObject(cryptoService.getApiUrl(), CryptoDTO.class))
                .thenThrow(new RestClientException(""));
        assertThrows(RestClientException.class,
                () -> {
                    cryptoController.getCostOfOneBitcoin();
                });
    }
}
