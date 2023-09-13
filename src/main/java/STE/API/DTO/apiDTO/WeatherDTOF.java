package STE.API.DTO.apiDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDTOF {
    private WeatherMainDTO main;
    private WeatherWindDTO wind;
}
