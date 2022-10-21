package com.ddigrang.coinrich.controller;

import com.ddigrang.coinrich.dto.MyUpbitAccount;
import com.ddigrang.coinrich.dto.UpbitCoin;
import com.ddigrang.coinrich.dto.WithDraw;
import com.ddigrang.coinrich.service.UpbitApiService;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;


@RestController
@RequestMapping("/api/v1/upbit")
public class UpbitController {

    @Autowired
    UpbitApiService upbitApiService;

    @RequestMapping("/ticker")
    public String ticker(
            @RequestParam(value = "target", required = false, defaultValue = "BTC") String target) throws Exception {

        return upbitApiService.upbitTicker(target);
    }

    @RequestMapping("/account")
    public List<MyUpbitAccount> getAccount() {
        return upbitApiService.getAccount();
    }

    @RequestMapping("/profit")
    public List<WithDraw> getProfit() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return upbitApiService.getProfit();
    }
}
