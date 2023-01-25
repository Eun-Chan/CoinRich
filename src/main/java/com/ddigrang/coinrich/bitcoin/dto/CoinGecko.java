package com.ddigrang.coinrich.bitcoin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CoinGecko {

    private String name;

    private String symbol;

    private String image;

    @JsonProperty("current_price")
    private double currentPrice;

    // 시가총액
    @JsonProperty("market_cap")
    private long marketCap;

    @JsonProperty("price_change_percentage_1h_in_currency")
    private double priceChangePercentage1hInCurrency;

    @JsonProperty("price_change_percentage_24h_in_currency")
    private double priceChangePercentage24hInCurrency;

    @JsonProperty("price_change_percentage_7d_in_currency")
    private double priceChangePercentage7dInCurrency;
}

