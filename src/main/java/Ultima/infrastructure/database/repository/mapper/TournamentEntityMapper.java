package Ultima.infrastructure.database.repository.mapper;

import Ultima.domain.Address;
import Ultima.domain.Competitor;
import Ultima.domain.Person;
import Ultima.domain.Tournament;
import Ultima.infrastructure.database.entity.CompetitorEntity;
import Ultima.infrastructure.database.entity.PersonEntity;
import Ultima.infrastructure.database.entity.TournamentEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

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
                .build();
    }


}

