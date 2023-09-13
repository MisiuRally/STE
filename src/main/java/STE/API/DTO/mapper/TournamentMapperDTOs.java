package STE.API.DTO.mapper;

import STE.API.DTO.TournamentsDTOs;
import STE.infrastructure.database.entity.TournamentEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TournamentMapperDTOs {


    public TournamentsDTOs mapFromEntity(TournamentEntity tournamentEntity) {
        return TournamentsDTOs.builder()
                .id(tournamentEntity.getTournamentId())
                .namOfTournament(tournamentEntity.getNameOfTournament())
                .numbersOfStartPlates(tournamentEntity.getNumberOfStartPlates())
                .startOfTournament(tournamentEntity.getStartOfTournament())
                .endOfTournament(tournamentEntity.getEndOfTournament())
                .sportCategories(tournamentEntity.getSportCategory())
                .nameOfOrganizer(tournamentEntity.getOrganizer().getNameOfOrganizer())
                .distance(tournamentEntity.getDistance())
                .buyIn(tournamentEntity.getBuyIn())
                .city(tournamentEntity.getOrganizer().getAddress().getCity())
                .startOfCompetitors(tournamentEntity.getStartOfCompetitors())
                .build();
    }
}
