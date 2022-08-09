package dev.haskin.springsecuritylab1.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
    void testGetCostOfOneBitcoin() {
        // System.out.println(cryptoService.getApiUrl());
        String expectedPrice = "20_000";
        DataDTO dataDTO = new DataDTO(expectedPrice);
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
