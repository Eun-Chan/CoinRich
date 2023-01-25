package com.ddigrang.coinrich.bitcoin.service;

import java.util.*;

import com.ddigrang.coinrich.bitcoin.dto.CoinGecko;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CoinGeckoApiServiceImpl implements CoinGeckoApiService {

    public List<CoinGecko> tickers() throws IOException {
        List<CoinGecko> coinGeckos = new ArrayList<>();

        OkHttpClient client = new OkHttpClient();

        String result;

        String url = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=30&page=1&sparkline=false&price_change_percentage=1h%2C24h%2C7d";

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Accept", "application/json")
                .build();

        Response response = client.newCall(request).execute();

        ResponseBody body = response.body();
        result = body.string();
        body.close();

        JSONArray jsonArray = new JSONArray(result);

        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);


            CoinGecko coinGecko = new CoinGecko();
            coinGecko.setName((String)jsonObject.get("name"));
            coinGecko.setSymbol((String)jsonObject.get("symbol"));
            coinGecko.setImage((String)jsonObject.get("image"));
            coinGecko.setCurrentPrice(Double.valueOf(jsonObject.get("current_price").toString()));
            coinGecko.setMarketCap(Long.valueOf(jsonObject.get("market_cap").toString()));
            coinGecko.setPriceChangePercentage1hInCurrency(Double.valueOf(jsonObject.get("price_change_percentage_1h_in_currency").toString()));
            coinGecko.setPriceChangePercentage24hInCurrency(Double.valueOf(jsonObject.get("price_change_percentage_24h_in_currency").toString()));
            coinGecko.setPriceChangePercentage7dInCurrency(Double.valueOf(jsonObject.get("price_change_percentage_7d_in_currency").toString()));

            coinGeckos.add(coinGecko);
        }

        return coinGeckos;

    }
}
