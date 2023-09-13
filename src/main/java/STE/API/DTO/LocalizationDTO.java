package STE.API.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {""},
        allowGetters = true)
public class LocalizationDTO {

    String name;
    double lat;
    double lon;
    String country;
    String state;

}
