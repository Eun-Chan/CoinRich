package com.ddigrang.coinrich.bitcoin.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Candle {

    private Double time;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
}
