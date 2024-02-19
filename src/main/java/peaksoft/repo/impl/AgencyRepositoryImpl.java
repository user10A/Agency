package peaksoft.repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.enamus.HouseType;
import peaksoft.model.Agency;
import peaksoft.model.Customer;
import peaksoft.model.House;
import peaksoft.repo.AgencyRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Transactional
@Repository
public class AgencyRepositoryImpl implements AgencyRepository {
    @PersistenceContext
    private EntityManager entityManager;

    // II -  Agency сакталып жатканда озу эле сакталсын
    //VI - Agency сакталып жатканда окшош ат мн сакталбасын. Эгер ат бирдей болуп калса анда Exception класс ыргытсын.
    //IX - Agency' де полелерине жараша search болсун
    //X - House тибине жараша фильтрация болсун
    @Override
    public void saveAgency(Agency agency) {
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(a) FROM Agency a WHERE a.name = :agencyName", Long.class);
        query.setParameter("agencyName", agency.getName());
        Long existingAgencyCount = query.getSingleResult();
        if (existingAgencyCount > 0) {
            throw new IllegalArgumentException("Агентство с таким именем уже существует");
        }
        Agency managedAgency = entityManager.merge(agency);
        entityManager.persist(managedAgency);
    }


    @Override
    public void updateAgency(Long agencyId, Agency agency) {
        Agency existingAgency = entityManager.find(Agency.class, agencyId);

        if (existingAgency == null) {
            throw new IllegalArgumentException("Агентство с ID " + agencyId + " не найдено");
        } else {
            existingAgency.setName(agency.getName());
            existingAgency.setImage(agency.getImage());
            existingAgency.setEmail(agency.getEmail());
            existingAgency.setCountry(agency.getCountry());
            existingAgency.setPhoneNumber(agency.getPhoneNumber());
            existingAgency.setAgencyHouses(agency.getAgencyHouses());
            existingAgency.setAgencyCustomers(agency.getAgencyCustomers());
            entityManager.merge(existingAgency);
        }
    }


    @Override
    public void deleteAgency(Long agencyId) {
        Agency agency = entityManager.find(Agency.class, agencyId);
        if (agency != null) {
            for (House house : agency.getAgencyHouses()) {
                house.setHouseAgency(null);
            }
            for (Customer customer : agency.getAgencyCustomers()) {
                customer.setCustomerAgency(null);
            }
            entityManager.remove(agency);
        } else {
            System.out.println("Агентство с ID " + agencyId + " не найдено.");
        }
        if (agency==null){
            entityManager.remove(agency);
        }
    }

    @Override
    public Agency getAgencyById(Long id) {
        Agency agency = entityManager.find(Agency.class, id);
        if (agency == null) {
            throw new IllegalArgumentException("Агентство с ID " + id + " не найдено");
        }

        return agency;
    }

    @Override
    public List<Agency> getAllAgencies() {
        return entityManager.createQuery("select l from Agency l ", Agency.class).getResultList();
    }

    @Override
    public Map<HouseType, Long> getNumberOfHousesByType(Long agencyId) {
        Agency agency = entityManager.find(Agency.class, agencyId);

        List<House> houses = agency.getAgencyHouses();

        Map<HouseType, Long> houseCountByType = houses.stream()
                .collect(Collectors.groupingBy(House::getHouseType, Collectors.counting()));

        return houseCountByType;
    }

    @Override
    public int getNumberOfClients(Long agencyId) {
        Agency agency = entityManager.find(Agency.class, agencyId);
        if (agency != null && agency.getAgencyCustomers() != null) {
            return agency.getAgencyCustomers().size();
        } else {
            return 0;
        }
    }


    @Override
    public void customerAssignAgency(Long agencyId, Long customerId) {
//        Agency agency = entityManager.find(Agency.class,agencyId);
//        if (agency == null) {
//            throw new IllegalArgumentException("Агентство с ID " + agencyId + " не найдено");
//        }
//        Customer customer = entityManager.find(Customer.class,customerId);
//        if (customer == null){
//            throw new IllegalArgumentException("Клиент с ID "+ customerId + "не найдено");
//        }
//        if (!customer.getCustomerAgencies().contains(agency)){
//            customer.getCustomerAgencies().add(agency);
//            agency.getAgencyCustomers().add(customer);
//            entityManager.merge(customer);
//        }
    }

    @Override
    public List<Customer> getNotAssignedCustomerTOAgency(Long agencyId) {
        Agency agency = entityManager.find(Agency.class, agencyId);
        List<Customer> customers = entityManager.createQuery("SELECT e FROM Customer e WHERE e NOT IN (SELECT se FROM Agency s JOIN s.agencyCustomers se WHERE s.id = :agencyId)")
                .setParameter("agencyId", agencyId)
                .getResultList();
        return customers;
    }

    @Override
    public void assignCustomerToAgency(Long agencyId, List<Long> customerId) {
        Agency agency = entityManager.find(Agency.class, agencyId);
        entityManager.createQuery("UPDATE Customer e SET e.customerAgency.id = :agencyId WHERE e.id IN :customerId")
                .setParameter("agencyId", agencyId)
                .setParameter("customerId", customerId)
                .executeUpdate();
    }


}
