package STE.API.controller;

import STE.API.DTO.LocalizationDTO;
import STE.API.DTO.WeatherDTO;
import STE.service.LocalizationService;
import STE.service.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class WeatherController {
    private final LocalizationService localizationService;
    private final WeatherService weatherService;

    @PostMapping("/weather/{city}")
    public String getWeather(Model model,
                             @PathVariable("city") String city
    ) {
        LocalizationDTO localizationDTO = localizationService.prepareLocalizationData(city);
        model.addAttribute("localization", localizationDTO);

        WeatherDTO weatherDTO = weatherService.prepareWeather(localizationDTO);
        model.addAttribute("weather", weatherDTO);

        return "/weather";
    }
}
