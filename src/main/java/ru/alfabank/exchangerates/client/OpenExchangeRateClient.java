package ru.alfabank.exchangerates.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.alfabank.exchangerates.payload.dto.OpenExchangeRateDto;

import java.util.Map;

@FeignClient(url = "${openexchangerate.url}", name = "openExchangeRateClient")
public interface OpenExchangeRateClient {

    @GetMapping(path = "${openexchangerate.latest.path}")
    OpenExchangeRateDto getExchangeRateByToday();

    @GetMapping(path = "${openexchangerate.history.path}")
    OpenExchangeRateDto getExchangeRateByDate(@PathVariable String date);

    @GetMapping(path = "${openexchangerate.currencies.path}")
    Map<String, String> getCurrencyInfo();

}
