package com.mybottest.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mybottest.model.CurrencyModel;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BusinessLogic {
    private List<CurrencyModel> currencyModelList;

    public BusinessLogic(String result) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            CurrencyModel[] currencyModels = objectMapper.readValue(result, CurrencyModel[].class);
            currencyModelList = Arrays.asList(currencyModels);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println(currencyModelList.size());
        currencyModelList.forEach(System.out::println);

    }

    public StringBuilder getCurrencyModelByShortName(String cc) {
        Optional<CurrencyModel> any = currencyModelList
                .stream()
                .filter(currencyModel -> currencyModel.getCc().equals(cc))
                .findAny();
        return any.map
                (currencyModel -> new StringBuilder(currencyModel.toString()))
                .orElseGet(() -> new StringBuilder("Такої валюти немає"));
    }

    public StringBuilder getAllCurrencyModelList() {
        StringBuilder stringBuilder = new StringBuilder();
        for (CurrencyModel currencyModel : currencyModelList) {
            stringBuilder.append(currencyModel);
        }
        return stringBuilder;
    }
}
