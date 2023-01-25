package com.ddigrang.coinrich.service;

import com.ddigrang.coinrich.bitcoin.service.CoinGeckoApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class CoinGeckoApiServiceImplTest {

    @Autowired
    CoinGeckoApiService coinGeckoApiService;

    @Test
    void tickers() throws IOException {
        System.out.println(coinGeckoApiService.tickers());
    }
}