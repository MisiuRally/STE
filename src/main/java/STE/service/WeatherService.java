package STE.service;

import STE.API.DTO.LocalizationDTO;
import STE.API.DTO.WeatherDTO;
import STE.API.DTO.apiDTO.WeatherDTOF;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
@Slf4j
public class WeatherService {

    private final RestTemplate restTemplate = new RestTemplate();


    public WeatherDTO prepareWeather(LocalizationDTO localizationDTO) {
        WeatherDTOF weatherMainDTO = prepareMainWeather(localizationDTO);

        return WeatherDTO.builder()
                .temperature(weatherMainDTO.getMain().getTemp())
                .pressure(weatherMainDTO.getMain().getPressure())
                .humidity(weatherMainDTO.getMain().getHumidity())
                .windSpeed(weatherMainDTO.getWind().getSpeed())
                .build();
    }

    public WeatherDTOF prepareMainWeather(LocalizationDTO localizationDTO) {
        WeatherDTOF forObject = restTemplate.getForObject(
                "https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid=0698b586077ea70d12342dafe84f3958&units=metric",
                WeatherDTOF.class, localizationDTO.getLat(), localizationDTO.getLon());
        log.info(forObject.toString());
        return forObject;

    }

}
