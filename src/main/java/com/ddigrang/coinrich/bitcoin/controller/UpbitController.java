package com.ddigrang.coinrich.bitcoin.controller;

import com.ddigrang.coinrich.bitcoin.dto.MyUpbitAccount;
import com.ddigrang.coinrich.bitcoin.dto.WithDraw;
import com.ddigrang.coinrich.bitcoin.service.UpbitApiService;
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
