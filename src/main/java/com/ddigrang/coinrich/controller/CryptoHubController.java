package com.ddigrang.coinrich.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cryptohub")
public class CryptoHubController {

    @RequestMapping("/news")
    public String test() throws Exception{
        OkHttpClient client = new OkHttpClient();

        String url = "https://www.cryptohub.or.kr/api/v1/news";

        Gson gson = new Gson();

        JsonObject json = new JsonObject();
        json.addProperty("token","$2y$10$ok3zypALjUuz.HRtarxbseFxVzf2IoJ0w/7a20rsYTeAzKiIGLwdu");

        String sJson = json.getAsString();
        Request request = new Request.Builder()
                .url("https://www.cryptohub.or.kr/api/v1/news")
                .post(RequestBody.create(MediaType.parse("application/json"), sJson))
                .build();

        Response response = client.newCall(request).execute();

        System.out.println("result = "+response.toString());

        return response.toString();
    }
}
