package ru.alfabank.exchangerates.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alfabank.exchangerates.client.GiphyClient;
import ru.alfabank.exchangerates.client.OpenExchangeRateClient;
import ru.alfabank.exchangerates.payload.dto.GiphyDataDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Log4j2
@Controller
@AllArgsConstructor
public class MainController {

    private final GiphyClient giphyClient;
    private final OpenExchangeRateClient openExchangeRateClient;

    @GetMapping("/currency")
    public String index(Model model) {
        model.addAttribute("code", "");
        model.addAttribute("success", false);
        model.addAttribute("currencies", openExchangeRateClient.getCurrencyInfo());
        return "index";
    }

    @PostMapping("/currency/compare")
    public String compare(Model model, @RequestParam String code) {
        var today = openExchangeRateClient.getExchangeRateByToday();
        var yesterday = openExchangeRateClient.getExchangeRateByDate(
                LocalDate.now().minusDays(1).toString()
        );

        float currencyToday = today.getRates().get(code);
        float currencyYesterday = yesterday.getRates().get(code);
        boolean rich = currencyToday > currencyYesterday;

        log.info("Yesterday USD to {} - {}", code, currencyYesterday);
        log.info("Today USD to {} - {}", code, currencyToday);
        log.info("Is rich - {}", rich);

        List<String> embedUrls = giphyClient.getGif(rich ? "rich" : "broke").getData()
                .stream().map(GiphyDataDto::getEmbedUrl).collect(Collectors.toList());
        String embedUrl = embedUrls.get(new Random().nextInt(embedUrls.size() - 1));

        model.addAttribute("code", code);
        model.addAttribute("gif", embedUrl);
        model.addAttribute("success", true);
        model.addAttribute("currencies", openExchangeRateClient.getCurrencyInfo());

        return "index";
    }

}
