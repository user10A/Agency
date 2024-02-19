package peaksoft.service.Impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.enamus.HouseType;
import peaksoft.model.Agency;
import peaksoft.model.Customer;
import peaksoft.repo.AgencyRepository;
import peaksoft.service.AgencyService;

import java.util.List;
import java.util.Map;
@Service
public class AgencyServiceImpl implements AgencyService {
    @Autowired
    AgencyRepository agencyRepository;
    @Override
    public void saveAgency(Agency agency) {
        agencyRepository.saveAgency(agency);
    }

    @Override
    public void updateAgency(Long agencyId, Agency agency) {
        agencyRepository.updateAgency(agencyId,agency);
    }

    @Override
    public void deleteAgency(Long agencyId) {
        agencyRepository.deleteAgency(agencyId);
    }

    @Override
    public Agency getAgencyById(Long id) {
        return agencyRepository.getAgencyById(id);
    }

    @Override
    public List<Agency> getAllAgencies() {
        return agencyRepository.getAllAgencies();
    }

    @Override
    public Map<HouseType, Long> getNumberOfHousesByType(Long agencyId) {
        return agencyRepository.getNumberOfHousesByType(agencyId);
    }

    @Override
    public int getNumberOfClients(Long agencyId) {
        return agencyRepository.getNumberOfClients(agencyId);
    }

    @Override
    public void customerAssignAgency(Long agencyId, Long customerId) {
        agencyRepository.customerAssignAgency(agencyId,customerId);
    }

    @Override
    public List<Customer> getNotAssignedCustomerTOAgency(Long agencyId) {
        return agencyRepository.getNotAssignedCustomerTOAgency(agencyId);
    }

    @Override
    public void assignCustomerToAgency(Long agencyId, List<Long> customerId) {
        agencyRepository.assignCustomerToAgency(agencyId,customerId);
    }

    @Override
    public List<Customer> CustomersFindAgencyById(Long id) {
        return null;
    }
}
