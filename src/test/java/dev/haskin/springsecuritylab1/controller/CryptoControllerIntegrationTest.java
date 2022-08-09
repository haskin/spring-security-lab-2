package dev.haskin.springsecuritylab1.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class CryptoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGetBitcoinPrice() throws Exception {
        mockMvc.perform(get("/crypto"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")))
                .andExpect(status().isOk());
    }
}
