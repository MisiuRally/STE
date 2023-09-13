package STE.service.managment;

import STE.infrastructure.database.repository.SportMeasureTimeEnterpriseManagementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;


@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SportMeasureTimeEnterpriseManagement  {

    private final SportMeasureTimeEnterpriseManagementRepository sportMeasureTimeEnterpriseManagementRepository;


//    public void clearDataBase() {
//        sportMeasureTimeEnterpriseManagementRepository.clearDataBase();
//
//    }
}
