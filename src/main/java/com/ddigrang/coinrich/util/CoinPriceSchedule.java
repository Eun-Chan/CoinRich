package com.ddigrang.coinrich.util;

import com.ddigrang.coinrich.discord.util.Webhook;
import com.ddigrang.coinrich.dto.BitgetCoin;
import com.ddigrang.coinrich.service.BitgetApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class CoinPriceSchedule {

    // 현재 서버에 저장된 BTC 가격
    private double saveBtcPrice;

    private Webhook webhook;

    @Autowired
    BitgetApiService bitgetApiService;

    @Async
    @Scheduled(fixedRate = 3000)
    public void btcPriceCheck() throws Exception{
        Webhook webhook = new Webhook();
        String message = null;

        BitgetCoin bitgetCoin = bitgetApiService.
                tickerCall("BTCUSDT_UMCBL");

        // 현재 BTC 가격
        double btcPriceNow = bitgetCoin.getLast();

        // BTC 100틱 이상 차이날 시 저장된 BTC 가격 변경
        if(btcPriceCompare(saveBtcPrice, btcPriceNow)) {
            int tempSaveBtcPrice = (int)saveBtcPrice;

            int temp = (int)btcPriceNow / 100;
            saveBtcPrice = temp * 100;

            message = (tempSaveBtcPrice > btcPriceNow) ? "현재 BTC 가격은 "+saveBtcPrice+"대로 하락했습니다." : "현재 BTC 가격은 "+saveBtcPrice+"대로 상승했습니다.";

            webhook.discordWebhook("비트코인-100틱-알림", message, true);

            Thread.sleep(5000);
        }
    }

    private boolean btcPriceCompare(double price1, double price2) {
        return ((int)price1 / 100) == ((int) price2 / 100) ? false : true;
    }
}
