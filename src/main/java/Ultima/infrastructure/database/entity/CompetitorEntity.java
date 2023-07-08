package Ultima.infrastructure.database.entity;

import Ultima.domain.Person;
import Ultima.service.managment.Categories;
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
@Table(name = "competitor")
public class CompetitorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "competitor_id")
    private Integer competitorId;
    @Column(name = "age_category")
    private Categories.AgeCategories ageCategories;
    @Column(name = "start_number")
    private Integer startNumber;
    @JoinColumn(name = "person_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Person person;


}
