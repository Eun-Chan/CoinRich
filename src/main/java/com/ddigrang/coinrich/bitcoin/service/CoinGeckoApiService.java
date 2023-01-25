package com.ddigrang.coinrich.bitcoin.service;

import com.ddigrang.coinrich.bitcoin.dto.CoinGecko;

import java.io.IOException;
import java.util.List;

public interface CoinGeckoApiService {

    List<CoinGecko> tickers() throws IOException;
}
