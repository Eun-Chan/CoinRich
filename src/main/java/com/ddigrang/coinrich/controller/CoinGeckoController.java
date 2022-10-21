package com.ddigrang.coinrich.controller;

import com.ddigrang.coinrich.dto.CoinGecko;

import java.io.IOException;
import java.util.*;

import com.ddigrang.coinrich.service.CoinGeckoApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/coinpaprika")
public class CoinGeckoController {

    @Autowired
    CoinGeckoApiService coinGeckoApiService;

    @RequestMapping("/tickers")
    public List<CoinGecko> tickers() throws IOException {
        return coinGeckoApiService.tickers();
    }
}
