package STE.API.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {""},
        allowGetters = true)
public class WeatherDTO {

    String date;
    float temperature;
    float pressure;
    float  humidity;
    float windSpeed;


}
