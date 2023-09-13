package STE.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@With
@EqualsAndHashCode(of = "tournamentId")
@ToString(of = {"nameOfTournament", "competitor", "tournamentId"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tournament")
public class TournamentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer tournamentId;
    @Column(name = "name_of_tournament")
    private String nameOfTournament;
    @Column(name = "number_of_start_plates")
    private Integer numberOfStartPlates;
    @Column(name = "sport_category")
    private String sportCategory;
    @Column(name = "start_of_tournament")
    private String startOfTournament;
    @Column(name = "end_of_tournament")
    private String endOfTournament;
    @Column(name = "distance")
    private Integer distance;
    @Column(name = "buy_in")
    private BigDecimal buyIn;
    @Column(name="start_of_competitor")
    private String startOfCompetitors;
    @OneToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE)
    @JoinColumn(name = "organizer_id")
    private OrganizerEntity organizer;
    @OneToMany(fetch =FetchType.LAZY,mappedBy = "tournamentEntity",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<CompetitorEntity> competitor;


}
