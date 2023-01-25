package com.ddigrang.coinrich.bitcoin.service;

import com.ddigrang.coinrich.bitcoin.dto.MyUpbitAccount;
import com.ddigrang.coinrich.bitcoin.dto.WithDraw;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UpbitApiService {
    String upbitTicker(String coin) throws IOException;

    List<MyUpbitAccount> getAccount();

    List<WithDraw> getProfit() throws NoSuchAlgorithmException, UnsupportedEncodingException;
}
