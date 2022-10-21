package com.ddigrang.coinrich.controller;

import com.ddigrang.coinrich.dto.BitgetCoin;
import com.ddigrang.coinrich.service.BitgetApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bitget")
public class BitgetController {

    @Autowired
    BitgetApiService bitgetApiService;

    @RequestMapping("/market/ticker")
    public BitgetCoin btcTest(
            @RequestParam(value="target", required = true, defaultValue = "BTCUSDT_UMCBL") String target)
                throws Exception {

        return bitgetApiService.tickerCall(target);
    }
}
