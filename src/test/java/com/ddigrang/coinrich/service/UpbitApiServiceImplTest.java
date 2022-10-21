package com.ddigrang.coinrich.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UpbitApiServiceImplTest {

    @Autowired
    UpbitApiService upbitApiService;

    @Test
    void upbitTicker() throws IOException {
        System.out.println(upbitApiService.upbitTicker("KRW-BTC"));
    }

    @Test
    void getAccount() {
        System.out.println("getAccount Test");
        upbitApiService.getAccount();
    }

    @Test
    void getProfit() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        System.out.println("getProfit Test");
        upbitApiService.getProfit();
    }

    @Test
    void test() {
        double nowPrice = 27370000;
        double buyPrice = 27934000;

        System.out.println(String.format("%.2f", ((nowPrice / buyPrice) - 1.0) * 100));
        System.out.println(String.format("%.2f", ((buyPrice / nowPrice) - 1.0) * 100));
    }
}