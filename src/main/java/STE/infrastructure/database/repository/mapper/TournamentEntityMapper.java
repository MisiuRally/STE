package STE.infrastructure.database.repository.mapper;

import STE.domain.Tournament;
import STE.infrastructure.database.entity.TournamentEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TournamentEntityMapper {
    private final OrganizerEntityMapper organizerEntityMapper;


    public Tournament mapperFromEntity(TournamentEntity tournamentEntity) {
        return Tournament.builder()
                .tournamentId(tournamentEntity.getTournamentId())
                .namOfTournament(tournamentEntity.getNameOfTournament())
                .numbersOfStartPlates(tournamentEntity.getNumberOfStartPlates())
                .organizer(organizerEntityMapper.mapperFromEntity(tournamentEntity.getOrganizer()))
                .sportCategories(tournamentEntity.getSportCategory())
                .startOfTournament(tournamentEntity.getStartOfTournament())
                .endOfTournament(tournamentEntity.getEndOfTournament())
                .distance(tournamentEntity.getDistance())
                .buyIn(tournamentEntity.getBuyIn())
                .startOfCompetitors(tournamentEntity.getStartOfCompetitors())
                .build();
    }



    public TournamentEntity mapperToEntity(Tournament tournament) {
        return TournamentEntity.builder()
                .tournamentId(tournament.getTournamentId())
                .nameOfTournament(tournament.getNamOfTournament())
                .numberOfStartPlates(tournament.getNumbersOfStartPlates())
                .organizer(organizerEntityMapper.mapperToEntity(tournament.getOrganizer()))
                .sportCategory(tournament.getSportCategories())
                .startOfTournament(tournament.getStartOfTournament())
                .endOfTournament(tournament.getEndOfTournament())
                .distance(tournament.getDistance())
                .buyIn(tournament.getBuyIn())
                .startOfCompetitors(tournament.getStartOfCompetitors())
                .build();
    }


}

