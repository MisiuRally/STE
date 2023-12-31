package STE.API.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizerDTO {

    Integer organizerId;
    String nameOfOrganizer;
    String email;
    String phone;
    AddressDTO address;



}
