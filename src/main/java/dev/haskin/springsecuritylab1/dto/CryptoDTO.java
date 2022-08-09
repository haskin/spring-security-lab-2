package dev.haskin.springsecuritylab1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CryptoDTO {
    @JsonProperty("data")
    private DataDTO data;
}
/*
 * https://api.coincap.io/v2/assets/bitcoin
 * {
 * "data": {
 * "id": "bitcoin",
 * "rank": "1",
 * "symbol": "BTC",
 * "name": "Bitcoin",
 * "supply": "19116268.0000000000000000",
 * "maxSupply": "21000000.0000000000000000",
 * "marketCapUsd": "455680046586.3372987436934280",
 * "volumeUsd24Hr": "11984663615.4585699600446033",
 * "priceUsd": "23837.2911797604688710",
 * "changePercent24Hr": "2.4317278187050277",
 * "vwap24Hr": "23923.2697219982267273",
 * "explorer": "https://blockchain.info/"
 * },
 * "timestamp": 1660006292824
 * }
 *
 */
