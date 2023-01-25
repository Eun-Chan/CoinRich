package com.ddigrang.coinrich.bitcoin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class BitgetCoin {
    private String symbol;

    private double last;

    private double bestAsk;

    private double bestBid;

    private double bidSz;

    private double askSz;

    private double high24h;

    private double low24h;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Timestamp timestamp;

    private double priceChangePercent;

    private double baseVolume;

    private double quoteVolume;

    private double usdtVolume;

    private double openUtc;

    private double chgUtc;
}
