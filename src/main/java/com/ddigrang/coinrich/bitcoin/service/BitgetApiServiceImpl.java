package com.ddigrang.coinrich.bitcoin.service;

import com.ddigrang.coinrich.bitcoin.dto.BitgetCoin;
import com.ddigrang.coinrich.bitcoin.dto.Candle;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class BitgetApiServiceImpl implements BitgetApiService {

    private OkHttpClient client = new OkHttpClient();

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public BitgetCoin tickerCall(String target) throws Exception {

        String url = "https://capi.bitget.com/api/mix/v1/market/ticker?symbol="+target;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Accept","application/json")
                .build();

        ResponseBody response = client.newCall(request).execute().body();



        JSONObject jsonObject = new JSONObject(response.string());

        response.close();

        BitgetCoin result = mapper.readValue(jsonObject.get("data").toString(), BitgetCoin.class);

        return result;
    }

    @Override
    public ArrayList<Candle> candle(String target) throws IOException {

        long endTime = System.currentTimeMillis();
        long startTime = endTime - 3 * 60 * 60 * 1000;

        String url = "https://api.bitget.com/api/mix/v1/market/candles?symbol="+target+"&granularity=1m&startTime="+startTime+"&endTime="+endTime;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Accept","application/json")
                .build();

        ResponseBody response = client.newCall(request).execute().body();

        String result = response.string();
        result = result.replace("\"","");

        String[] rows = result.split("],\\[");

        for(int i = 0; i < rows.length; i++)
            rows[i] = rows[i].replace("[[","").replace("]]","").replace(" ","");

        int numberOfColumns = rows[0].split(",").length;

        String[][] matrix = new String[rows.length][numberOfColumns];

        for(int i = 0; i < matrix.length; i++)
            matrix[i] = rows[i].split(",");

        ArrayList<Candle> chartData = new ArrayList<>();

        for(int i = 0; i < rows.length; i++)
        {
            chartData.add(
                    Candle.builder()
                            .time(Double.valueOf(matrix[i][0].substring(0, matrix[i][0].length()-3 )))
                            .open(Double.valueOf(matrix[i][1]))
                            .high(Double.valueOf(matrix[i][2]))
                            .low(Double.valueOf(matrix[i][3]))
                            .close(Double.valueOf(matrix[i][4]))
                            .build());
        }

        response.close();

        return chartData;
    }
}
