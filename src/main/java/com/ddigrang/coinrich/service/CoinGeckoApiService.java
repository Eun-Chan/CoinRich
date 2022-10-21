package com.ddigrang.coinrich.service;

import com.ddigrang.coinrich.dto.CoinGecko;

import java.io.IOException;
import java.util.List;

public interface CoinGeckoApiService {

    List<CoinGecko> tickers() throws IOException;
}
