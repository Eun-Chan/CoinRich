package com.ddigrang.coinrich.bitcoin.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpbitCoin {

    private String currency;

    private String balance;

    private String locked;

    private String avg_buy_price;

    private boolean avg_buy_price_modified;

    private String unit_currency;
}