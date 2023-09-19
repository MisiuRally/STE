package STE;

import STE.service.EmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@AutoConfiguration
@SpringBootApplication
@AllArgsConstructor
@EnableScheduling
@ComponentScan(basePackageClasses = _Marker.class)
public class STE {

    private final EmailService emailService;


    public static void main(String[] args) {

        SpringApplication.run(STE.class, args);
        log.info("Start");
    }

    @Transactional
    @Scheduled(fixedDelayString = "PT24H")
    public void runEmail() {
        emailService.emailSenderBirthdayWish();
        emailService.emailReminderAboutStart();
        log.info("Emails sent: " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE));
    }


}

