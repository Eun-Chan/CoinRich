package com.ddigrang.coinrich.service;

import com.ddigrang.coinrich.dto.BitgetCoin;

public interface BitgetApiService {

    /**
     * 코인가격 API 요청
     * @param target
     * @return
     */
    BitgetCoin tickerCall(String target) throws Exception;
}