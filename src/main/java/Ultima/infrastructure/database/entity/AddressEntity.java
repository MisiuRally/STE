package Ultima.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
//@EqualsAndHashCode(of = "")
//@ToString(of = {""})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer addressId;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "street")
    private String street;

}
