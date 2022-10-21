package com.ddigrang.coinrich.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

//@EnableAsync
public class WebCrawling {

//    @Async
//    @Scheduled(fixedRate = 2000)
    public void crawling(String address) {

        try {
            Connection connection = Jsoup.connect("https://coinsect.io/indicators/real-time-positions");

            Document document = connection.get();
            Elements elements = document.getElementsByClass("view-indicators");

            System.out.println(elements.html());
//            for(Element element : elements) {
//                System.out.println(element.select(".prices").select(".entry").select(".value").select("f-mono").text());
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
