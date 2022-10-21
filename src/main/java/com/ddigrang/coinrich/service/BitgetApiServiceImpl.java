package com.ddigrang.coinrich.service;

import com.ddigrang.coinrich.dto.BitgetCoin;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

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
}
