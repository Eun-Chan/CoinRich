package com.ddigrang.coinrich.bitcoin.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MyUpbitAccount {

    private String curruncy;

    private String myPrice;

    private String nowPrice;

    private String profit;
}
