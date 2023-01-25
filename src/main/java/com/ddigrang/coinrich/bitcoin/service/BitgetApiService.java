package com.ddigrang.coinrich.bitcoin.service;

import com.ddigrang.coinrich.bitcoin.dto.BitgetCoin;
import com.ddigrang.coinrich.bitcoin.dto.Candle;

import java.io.IOException;
import java.util.ArrayList;

public interface BitgetApiService {

    /**
     * 코인가격 API 요청
     * @param target
     * @return
     */
    BitgetCoin tickerCall(String target) throws Exception;

    /**
     * 차트 데이터 API 요청
     */
    ArrayList<Candle> candle(String target) throws IOException;
}