package Ultima.API.DTO.mapper;

import Ultima.API.DTO.TournamentsDTOs;
import Ultima.infrastructure.database.entity.TournamentEntity;
import Ultima.infrastructure.database.repository.mapper.OrganizerEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

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
                .city(tournamentEntity.getOrganizer().getAddress().getCity())
                .build();
    }
}
