import Ultima.infrastructure.config.ApplicationConfiguration;
import Ultima.service.managment.InputData;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringJUnitConfig(classes = ApplicationConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UltimaSportServiceTest {

private final InputData inputData;


@BeforeAll
void readInput(InputData inputData){

}

    @Test
    @Order(1)
    void clearDataBase(){


    }

}
