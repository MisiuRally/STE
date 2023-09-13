package STE.service;

import STE.API.DTO.LocalizationDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LocalizationService {

    private final RestTemplate restTemplate = new RestTemplate();

    public LocalizationDTO prepareLocalizationData(String city){
        LocalizationDTO[] forObject = restTemplate.getForObject("http://api.openweathermap.org/geo/1.0/direct?q={city}&limit=1&appid=0698b586077ea70d12342dafe84f3958",
                LocalizationDTO[].class, city);
        List<LocalizationDTO> list = Arrays.stream(forObject).toList();
        LocalizationDTO collect = list.stream().collect(toSingleton());


        return collect;
    }

    public static <T> Collector<T, ?, T> toSingleton() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    if (list.size() != 1) {
                        throw new IllegalStateException();
                    }
                    return list.get(0);
                }
        );
    }

}
