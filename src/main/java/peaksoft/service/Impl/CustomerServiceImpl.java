package peaksoft.service.Impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Customer;
import peaksoft.repo.CustomerRepository;
import peaksoft.service.CustomerService;

import java.util.List;
@Service

public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.saveCustomer(customer);
    }

    @Override
    public void updateCustomer(Long id, Customer customer) {
        customerRepository.updateCustomer(id,customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteCustomer(id);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.getCustomerById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    @Override
    public Customer findByUsername(String name) {
        return customerRepository.findByUsername(name);
    }

    @Override
    public List<Customer> getAllCustomersByAgencyId(Long agencyId) {
        return customerRepository.getAllCustomersByAgencyId(agencyId);
    }
}
