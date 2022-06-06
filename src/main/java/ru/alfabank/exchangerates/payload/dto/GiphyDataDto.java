package ru.alfabank.exchangerates.payload.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GiphyDataDto {
    @JsonProperty(value = "embed_url")
    private String embedUrl;
}
