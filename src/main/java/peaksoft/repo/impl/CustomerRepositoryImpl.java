package peaksoft.repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.model.Agency;
import peaksoft.model.Booking;
import peaksoft.model.Customer;
import peaksoft.repo.CustomerRepository;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
@Transactional
@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    @PersistenceContext
    private EntityManager entityManager;
    //IV - Customer сакталып жатканда озу эле сакталсын, анан agency гe assign болот
    //VIII - Customer save кылып жатканда жашы 18 чон болуш керек
    @Override
    public void saveCustomer(Customer customer) {
        LocalDate birthDate = customer.getDateOfBirth();
        if (birthDate == null) {
            throw new IllegalArgumentException("Дата рождения не установлена");
        }

        int age = Period.between(birthDate, LocalDate.now()).getYears();

        if (age < 18) {
            throw new IllegalArgumentException("Вы несовершенно летний по закону КР");
        }

        entityManager.persist(customer);
    }




    @Override
    public void updateCustomer(Long id, Customer customer) {
        Customer customer1 = entityManager.find(Customer.class,id);
        if (customer1==null){
            throw  new IllegalArgumentException("Клиент с ID"+id+ "не найден");
        }
        customer1.setName(customer.getName());
        customer1.setSurname(customer.getSurname());
        customer1.setEmail(customer.getEmail());
        customer1.setGender(customer.getGender());
        customer1.setImage(customer.getImage());
        customer1.setPhoneNumber(customer.getPhoneNumber());
//        customer1.setCustomerAgencies(customer.getCustomerAgencies());
//        customer1.setCustomerBookings(customer.getCustomerBookings());
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = entityManager.find(Customer.class,id);
        if (customer == null){
            throw new IllegalArgumentException("Клиент с ID"+ id + "не найден");
        }
        customer.setCustomerAgency(null);
        for (Booking b : customer.getBookings()){
            b.setCustomer(null);
        }
        entityManager.remove(customer);
    }
    @Override
    public Customer getCustomerById(Long id) {
        Customer customer = entityManager.find(Customer.class,id);
        if (customer == null){
            throw new IllegalArgumentException("Клиент с ID"+ id + "не найден");
        }
        return customer;
    }

    @Override
    public Customer findByUsername(String username) {
        try {
            return (Customer) entityManager.createQuery("SELECT c FROM Customer c WHERE c.name = :username")
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    @Override
    public List<Customer> getAllCustomers() {
        return entityManager.createQuery("select l from Customer l ", Customer.class).getResultList();
    }
    @Override
    public List<Customer> getAllCustomersByAgencyId(Long agencyId) {
        return entityManager.createQuery("SELECT c FROM Customer c JOIN c.customerAgency a WHERE a.id = :agencyId", Customer.class)
                .setParameter("agencyId", agencyId)
                .getResultList();
    }
}
