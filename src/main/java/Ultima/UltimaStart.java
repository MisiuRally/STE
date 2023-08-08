package Ultima;

import Ultima.infrastructure.database.entity._EntityMarker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Slf4j
@SpringBootApplication
@EntityScan(basePackageClasses = _EntityMarker.class)
public class UltimaStart {

    public static void main(String[] args) {

            SpringApplication.run(UltimaStart.class, args);
            log.info("Start");

        }
    }

