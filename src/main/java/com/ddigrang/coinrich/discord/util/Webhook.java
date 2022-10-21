package com.ddigrang.coinrich.discord.util;

import com.google.gson.JsonObject;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Webhook {

    /**
     * 디스코드 Text Channel 메시지
     * @param channelName
     * @param message
     * @param tts
     * @throws IOException
     */
    public void discordWebhook(String channelName, String message, boolean tts) throws IOException {
        OkHttpClient client = new OkHttpClient();

        JsonObject data = new JsonObject();

        // Text Channel - 비트코인 100틱 알림
        String webhookUrl = "https://discord.com/api/webhooks/996313509283500052/Wm5lMYrql1eqiQ6-SLhgYQ__k6egts7DWeD6h3EXqPQ9g-rHItYO1jwcOZOz6Cgf1Ccp";

        data.addProperty("content", message);
        data.addProperty("tts", (tts == true) ? "true" : "false");

        Request request = new Request.Builder()
                .url(webhookUrl)
                .post(RequestBody.create(MediaType.parse("application/json"), data.toString()))
                .build();

        client.newCall(request).execute();
    }
}
