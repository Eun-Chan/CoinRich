package com.ddigrang.coinrich.service;

import com.ddigrang.coinrich.bitcoin.dto.Candle;
import com.ddigrang.coinrich.bitcoin.service.UpbitApiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.TimeZone;

@SpringBootTest
class UpbitApiServiceImplTest {

    @Autowired
    UpbitApiService upbitApiService;

    private OkHttpClient client = new OkHttpClient();

    private ObjectMapper mapper = new ObjectMapper();

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
    void test() throws IOException {
        long endTime = System.currentTimeMillis();
        long startTime = endTime - 60 * 60 * 1000;

        String url = "https://api.bitget.com/api/mix/v1/market/candles?symbol=BTCUSDT_UMCBL&granularity=1m&startTime=" + startTime + "&endTime=" + endTime;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Accept", "application/json")
                .build();

        ResponseBody response = client.newCall(request).execute().body();

        System.out.println("response length = "+response.contentLength());

        String result = response.string();
        result = result.replace("\"","");
        System.out.println(result);

        String[] rows = result.split("],\\[");

        for(int i = 0; i < rows.length; i++)
            rows[i] = rows[i].replace("[[","").replace("]]","").replace(" ","");

        int numberOfColumns = rows[0].split(",").length;

        String[][] matrix = new String[rows.length][numberOfColumns];

        for(int i = 0; i < matrix.length; i++)
            matrix[i] = rows[i].split(",");

        System.out.println(Arrays.deepToString(matrix));

        ArrayList<Candle> chartData = new ArrayList<>();

        for(int i = 0; i < rows.length; i++)
        {

            chartData.add(
                    Candle.builder()
                            .time(Double.valueOf(matrix[i][0].substring(0, matrix[i][0].length()-3 )))
                            .open(Double.valueOf(matrix[i][1]))
                            .high(Double.valueOf(matrix[i][3]))
                            .low(Double.valueOf(matrix[i][4]))
                            .close(Double.valueOf(matrix[i][5]))
                        .build());
        }


        for(int i = 0; i < chartData.size(); i++) {
            System.out.println(chartData.get(i).getTime());
            System.out.println(chartData.get(i).getOpen());
            System.out.println(chartData.get(i).getHigh());
            System.out.println(chartData.get(i).getLow());
            System.out.println(chartData.get(i).getClose());
        }


        System.out.println(Arrays.toString(TimeZone.getAvailableIDs()));

//        String[] rows = result.sp

//        result = result.replaceFirst("\\[", "{");
//        result = StringUtils.removeEnd(result, "}");
//
//        result = result.replace("[","{").replace("]","}");
//
//        String[][] res = result.
////        Candle result = mapper.readValue(jsonObject.get("data").toString(), Candle.class);
//
//        System.out.println("result = " + result);
    }
}