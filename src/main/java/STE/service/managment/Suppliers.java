package STE.service.managment;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class Suppliers {
    public  LocalDateTime localDateTimeSupplier(){
        return LocalDateTime.now();
    }

}
