package Ultima.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@With
@EqualsAndHashCode(of = "competitorId")
@ToString(of = {"competitorId", "person"})
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
    private String ageCategories;
    @Column(name = "start_number")
    private Integer startNumber;
    @Column(name = "result")
    private String result;
    @Column(name = "average_speed")
    private String averageSpeed;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private PersonEntity person;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "tournament")
    private TournamentEntity tournamentEntity;


}
