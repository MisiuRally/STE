package Ultima.infrastructure.database.entity;

import Ultima.domain.Address;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "")
@ToString(of = {""})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "organizer")
public class OrganizerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organizer_id")
    private Integer organizerId;

    @Column(name = "name_of_organizer ")
    private String nameOfOrganizer;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;


}
