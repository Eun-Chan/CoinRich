package com.ddigrang.coinrich.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ddigrang.coinrich.dto.MyUpbitAccount;
import com.ddigrang.coinrich.dto.UpbitCoin;
import com.ddigrang.coinrich.dto.WithDraw;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import okhttp3.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.UUID;

import java.util.*;

@Service
public class UpbitApiServiceImpl implements UpbitApiService {

    @Value("${apikey.upbit.accesskey}")
    private String accessKey;

    @Value("${apikey.upbit.secretkey}")
    private String secretKey;

    @Override
    public String upbitTicker(String coin) throws IOException {

        OkHttpClient client = new OkHttpClient();
        String result;

        String url = "https://api.upbit.com/v1/ticker?markets=KRW-" + coin;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Accept", "application/json")
                .build();

        Response response = client.newCall(request).execute();

        ResponseBody body = response.body();
        result = body.string();

        // ticker가 List 형태로 출력이 되기때문에 parser 시 불편하여 replace 함
        result = result.replace("[", "").replace("]","");

        body.close();

        return result;
    }

    @Override
    public List<MyUpbitAccount> getAccount() {

        String url = "https://api.upbit.com/v1/accounts";

        List<UpbitCoin> upbitCoins = new ArrayList<>();
        List<MyUpbitAccount> myUpbitAccounts = new ArrayList<>();

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        String jwtToken = JWT.create()
                .withClaim("access_key", accessKey)
                .withClaim("nonce", UUID.randomUUID().toString())
                .sign(algorithm);

        String authenticationToken = "Bearer " + jwtToken;

        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);
            request.setHeader("Content-Type", "application/json");
            request.addHeader("Authorization", authenticationToken);

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            ObjectMapper mapper = new ObjectMapper();
            upbitCoins = Arrays.asList(mapper.readValue(EntityUtils.toString(entity, "UTF-8"), UpbitCoin[].class));

            myUpbitAccounts = createAccount(upbitCoins);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return myUpbitAccounts;
    }

    @Override
    public List<WithDraw> getProfit() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String url = "https://api.upbit.com/v1/withdraws?";

        List<WithDraw> withDraws = new ArrayList<>();

        HashMap<String, String> params = new HashMap<>();
        params.put("currency", "KRW");

        ArrayList<String> queryElements = new ArrayList<>();

        for (Map.Entry<String, String> entity : params.entrySet())
            queryElements.add(entity.getKey() + "=" + entity.getValue());

        String queryString = String.join("&", queryElements.toArray(new String[1]));

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(queryString.getBytes("UTF-8"));

        String queryHash = String.format("%0128x", new BigInteger(1, md.digest()));

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        String jwtToken = JWT.create()
                .withClaim("access_key", accessKey)
                .withClaim("nonce", UUID.randomUUID().toString())
                .withClaim("query_hash", queryHash)
                .withClaim("query_hash_alg", "SHA512")
                .sign(algorithm);

        String authenticationToken = "Bearer " + jwtToken;

        try {
            System.out.println(url + queryString);

            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url + queryString);
            request.setHeader("Content-Type", "application/json");
            request.addHeader("Authorization", authenticationToken);

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            ObjectMapper mapper = new ObjectMapper();
            withDraws = Arrays.asList(mapper.readValue(EntityUtils.toString(entity, "UTF-8"), WithDraw[].class));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return withDraws;
    }

    private List<MyUpbitAccount> createAccount(List<UpbitCoin> upbitCoins) throws IOException{
        List<MyUpbitAccount> myUpbitAccounts = new ArrayList<>();

        for(int i = 0; i < upbitCoins.size(); i++) {
            UpbitCoin upbitCoin = upbitCoins.get(i);

            if(upbitCoin.getAvg_buy_price().equals("0"))
                continue;

            double myPrice = Double.valueOf(upbitCoin.getAvg_buy_price());
            double nowPrice = Double.valueOf(new JSONObject(upbitTicker(upbitCoin.getCurrency())).get("trade_price").toString());
            String profit = String.format("%.2f", ((nowPrice / myPrice) - 1.0) * 100);
            MyUpbitAccount myUpbitAccount = new MyUpbitAccount();

            myUpbitAccount.setCurruncy(upbitCoin.getCurrency());
            DecimalFormat df = new DecimalFormat("###,###");

            myUpbitAccount.setMyPrice(df.format(myPrice));
            myUpbitAccount.setNowPrice(df.format(nowPrice));
            myUpbitAccount.setProfit(profit+"%");

            myUpbitAccounts.add(myUpbitAccount);
        }

        return myUpbitAccounts;
    }
}