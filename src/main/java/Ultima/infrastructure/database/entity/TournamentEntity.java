package Ultima.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@With
@EqualsAndHashCode(of = "tournamentId")
@ToString(of = {"nameOfTournament"})
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
    @Column(name ="name_of_tournament" )
    private String nameOfTournament;
    @Column(name = "number_of_start_plates")
    private Integer numberOfStartPlates;
    @Column(name = "sport_category" )
    private String sportCategory;
    @Column(name = "start_of_tournament")
    private  OffsetDateTime startOfTournament;
    @Column(name = "end_of_tournament")
    private OffsetDateTime endOfTournament;
    @OneToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE)
    @JoinColumn(name = "organizer_id")
    private OrganizerEntity organizer;

    @OneToMany
    @JoinColumn(name = "competitor_id")
    private Set<CompetitorEntity> competitorEntity;










//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "competitor_id")
//    private Set<CompetitorEntity> competitorEntities=new HashSet<>();

}
