package com.ddigrang.coinrich.service;

import com.ddigrang.coinrich.dto.CoinGecko;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Arrays;

@SpringBootTest
class CoinGeckoApiServiceImplTest {

    @Autowired
    CoinGeckoApiService coinGeckoApiService;

    @Test
    void tickers() throws IOException {
        System.out.println(coinGeckoApiService.tickers());
    }
}