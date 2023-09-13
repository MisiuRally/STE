package STE;

import STE.service.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class STETest {
    @InjectMocks
    private STE ste;
    @Mock
    private EmailService emailService;


    @BeforeEach
    void setUp() {
        ste = new STE(emailService);
    }

    @Test
    void sendEmailWorksCorrectly() {

//given when
        ste.runEmail();

//then
        verify(emailService).emailSenderBirthdayWish();
        verify(emailService).emailReminderAboutStart();


    }


}