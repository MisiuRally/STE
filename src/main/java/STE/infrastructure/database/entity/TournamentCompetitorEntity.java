package STE.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@With
@EqualsAndHashCode(of = "tournamentCompetitorId")
@ToString(of = {"tournamentCompetitorId"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Entity
@Table(name = "tournament_competitor")
public class TournamentCompetitorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tournament_competitor_id")
    private Integer tournamentCompetitorId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tournament_entity_id")
    private TournamentEntity tournamentEntity;

    @OneToMany(fetch = FetchType.EAGER
            ,cascade = CascadeType.ALL)
    @JoinColumn(name = "competitor_id")
    private Set<CompetitorEntity> competitorEntities;

}
