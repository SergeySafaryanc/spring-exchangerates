package ru.alfabank.exchangerates.payload.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GiphyDto {
    private List<GiphyDataDto> data;
}
