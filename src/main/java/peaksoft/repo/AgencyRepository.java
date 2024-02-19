package peaksoft.repo;

import peaksoft.enamus.HouseType;
import peaksoft.model.Agency;
import peaksoft.model.Customer;

import java.util.List;
import java.util.Map;

public interface AgencyRepository {
    // II -  Agency сакталып жатканда озу эле сакталсын
    //VI - Agency сакталып жатканда окшош ат мн сакталбасын. Эгер ат бирдей болуп калса анда Exception класс ыргытсын.
    //IX - Agency' де полелерине жараша search болсун
    //X - House тибине жараша фильтрация болсун
    void saveAgency(Agency agency);
    void updateAgency (Long agencyId, Agency agency);
    void deleteAgency(Long agencyId);
    Agency getAgencyById(Long id);
    List<Agency> getAllAgencies();
    Map<HouseType, Long> getNumberOfHousesByType(Long agencyId);
    int getNumberOfClients(Long agencyId);
    void customerAssignAgency(Long agencyId,Long customerId);
    List<Customer>getNotAssignedCustomerTOAgency(Long agencyId);
    void assignCustomerToAgency(Long agencyId, List<Long> customerId);
}
