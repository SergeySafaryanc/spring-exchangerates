package ru.alfabank.exchangerates.controller;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Matches;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.alfabank.exchangerates.ExchangeRatesApplicationTests;
import ru.alfabank.exchangerates.client.GiphyClient;
import ru.alfabank.exchangerates.client.OpenExchangeRateClient;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MainControllerTest extends ExchangeRatesApplicationTests {

    @MockBean
    GiphyClient giphyClient;
    @MockBean
    OpenExchangeRateClient openExchangeRateClient;

    @BeforeEach
    void setUp() {
        Mockito.when(openExchangeRateClient.getCurrencyInfo()).thenReturn(getCurrencies());
        Mockito.when(openExchangeRateClient.getExchangeRateByToday()).thenReturn(getExchangeRateByToday());
        Mockito.when(openExchangeRateClient.getExchangeRateByDate(LocalDate.now().minusDays(1).toString())).thenReturn(getExchangeRateByDate());
        Mockito.when(giphyClient.getGif("rich")).thenReturn(getGif());
        Mockito.when(giphyClient.getGif("broke")).thenReturn(getGif());
    }

    @Test
    void testIndex() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/currency"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(Matchers.anyOf(
                                getCurrencies().keySet().stream().map(s -> {
                                    return Matchers.containsString("<option value=\""+ s + "\"");
                                }).toArray(Matcher[]::new)
                        )));
    }

    @Test
    void testCompare() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/currency/compare").param("code", "RUB"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(Matchers.allOf(
                                Matchers.containsString("<option value=\"RUB\" selected>"),
                                Matchers.containsString("<iframe width=\"500\" height=\"500\" src=")
                        )));
    }
}