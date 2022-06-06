package ru.alfabank.exchangerates.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.alfabank.exchangerates.payload.dto.GiphyDto;

@FeignClient(url = "${giphy.url}", name = "giphyClient")
public interface GiphyClient {

    @GetMapping(path = "${giphy.path}")
    GiphyDto getGif(@PathVariable String query);

}
