package STE.service.managment;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailSender {

    private final JavaMailSenderImpl javaMailSender;

    public void sendEmail(SimpleMailMessage message) {
        javaMailSender.send(message);
    }

}
