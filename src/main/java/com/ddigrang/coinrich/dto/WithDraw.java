package com.ddigrang.coinrich.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@ToString
public class WithDraw {

    private String type;

    private String uuid;

    private String currency;

    private String txid;

    private String state;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Timestamp created_at;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Timestamp done_at;

    private double amount;

    private double fee;

    private String transaction_type;
}
