package com.ddigrang.coinrich.bitcoin.controller;

import com.ddigrang.coinrich.bitcoin.dto.BitgetCoin;
import com.ddigrang.coinrich.bitcoin.dto.Candle;
import com.ddigrang.coinrich.bitcoin.service.BitgetApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/bitget")
public class BitgetController {

    @Autowired
    BitgetApiService bitgetApiService;

    @RequestMapping("/market/ticker")
    public BitgetCoin ticker(
            @RequestParam(value="target", required = true, defaultValue = "BTCUSDT_UMCBL") String target)
                throws Exception {

        return bitgetApiService.tickerCall(target);
    }

    @RequestMapping("/market/candle")
    public ArrayList<Candle> candle(
            @RequestParam(value="target", required = false, defaultValue = "BTCUSDT_UMCBL") String target)
                throws IOException {
        return bitgetApiService.candle(target);
    }
}
