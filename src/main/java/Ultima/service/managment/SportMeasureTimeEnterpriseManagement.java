package Ultima.service.managment;

import Ultima.infrastructure.database.repository.SportMeasureTimeEnterpriseManagementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SportMeasureTimeEnterpriseManagement  {

    private final SportMeasureTimeEnterpriseManagementRepository sportMeasureTimeEnterpriseManagementRepository;


    public void clearDataBase() {
        sportMeasureTimeEnterpriseManagementRepository.clearDataBase();

    }
}
