package STE.API.DTO.apiDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherMainDTO {


    private float temp;
    private float pressure;
    private float humidity;



}
