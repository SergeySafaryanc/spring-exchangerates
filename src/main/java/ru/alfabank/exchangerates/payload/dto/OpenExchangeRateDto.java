package ru.alfabank.exchangerates.payload.dto;

import lombok.Data;

import java.util.Map;

@Data
public class OpenExchangeRateDto {
    private String disclaimer;
    private String license;
    private String timestamp;
    private String base;
    private Map<String, Float> rates;
}
