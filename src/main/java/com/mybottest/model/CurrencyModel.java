package com.mybottest.model;

import lombok.Data;

@Data
public class CurrencyModel {

    int r030;
    String txt;
    Double rate;
    String cc;
    String exchangedate;

    @Override
    public String toString() {
        return  txt + "\n" +
                cc + "\n" +
                "курс=" + rate + "\n" +
                "дата=" + exchangedate + "\n" +
                " \n";
    }
}
