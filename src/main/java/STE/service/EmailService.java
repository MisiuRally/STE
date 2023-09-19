package STE.service;

import STE.infrastructure.database.entity.PersonEntity;
import STE.infrastructure.database.entity.TournamentEntity;
import STE.infrastructure.database.repository.jpa.CompetitorJpaRepository;
import STE.infrastructure.database.repository.jpa.PersonJpaRepository;
import STE.service.managment.EmailSender;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))

public class EmailService {

    private final PersonJpaRepository personJpaRepository;
    private final TournamentService tournamentService;
    private final CompetitorJpaRepository competitorJpaRepository;
    private final EmailSender emailSender;

    public void emailSenderBirthdayWish() {
        String body = "Wszystkiego najlepszego z okazji urodzin %s!!! ";
        String subject = "Żyj nam 100 lat !!!";

        System.out.println(LocalDateTime.now());
        LocalDate localDateNow = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateNow = dateTimeFormatter.format(localDateNow);
        List<PersonEntity> personEntity = personJpaRepository.findAll();
        personEntity.stream()
                .filter(p -> p.getDateOfBirth().equals(dateNow))
                .forEach(p -> sendEmail(p.getEmail(), subject, body.formatted(p.getName().toUpperCase())));
    }

    public void emailReminderAboutStart() {
        String body = "Przypominamy o zbliżającym się terminie startu zawódów %s które odbędą się w terminie %s!!! ";
        String subject = "Przypomnienie o zawodach sportowych na które jesteś zapisany/-a!!!";


        List<TournamentEntity> allTournaments = tournamentService.findAllTournaments();
        LocalDate localDateNow = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateNow = dateTimeFormatter.format(localDateNow);
        ChronoUnit.DAYS.between(localDateNow, localDateNow);
        List<Integer> list = allTournaments.stream()
                .filter(t -> ChronoUnit.DAYS.between(
                        LocalDate.parse(t.getStartOfTournament(), dateTimeFormatter), LocalDate.parse(dateNow, dateTimeFormatter)) == 14)
                .map(t -> t.getTournamentId())
                .toList();

        for (Integer tournamentId : list) {
             competitorJpaRepository.findAllCompetitorsWithTournamentId(tournamentId)
                 .forEach(c -> sendEmail(c.getPerson().getEmail(), subject,
                          body.formatted(c.getTournamentEntity().getNameOfTournament(),
                                  c.getTournamentEntity().getStartOfTournament())));
        }



    }


    public void sendEmail(String to, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        emailSender.sendEmail(message);
    }
}
