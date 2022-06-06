package ru.alfabank.exchangerates;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.alfabank.exchangerates.payload.dto.GiphyDto;
import ru.alfabank.exchangerates.payload.dto.OpenExchangeRateDto;

import java.nio.file.Paths;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
public class ExchangeRatesApplicationTests {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper mapper;

    @Test
    void contextLoads() {
    }


    @SneakyThrows
    protected Map<String, String> getCurrencies() {
        return mapper.readValue(Paths.get("src/test/resources/currencies.json").toFile(), Map.class);
    }

    @SneakyThrows
    protected OpenExchangeRateDto getExchangeRateByToday() {
        return mapper.readValue(Paths.get("src/test/resources/latest.json").toFile(), OpenExchangeRateDto.class);
    }

    @SneakyThrows
    protected OpenExchangeRateDto getExchangeRateByDate() {
        return mapper.readValue(Paths.get("src/test/resources/2022-06-05.json").toFile(), OpenExchangeRateDto.class);
    }

    @SneakyThrows
    protected GiphyDto getGif() {
        return mapper.readValue(Paths.get("src/test/resources/giphy.json").toFile(), GiphyDto.class);
    }

}
