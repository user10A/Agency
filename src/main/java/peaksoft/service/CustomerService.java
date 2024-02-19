package peaksoft.service;

import peaksoft.model.Customer;

import java.util.List;

public interface CustomerService {
    //IV - Customer сакталып жатканда озу эле сакталсын, анан agency гe assign болот
    //VIII - Customer save кылып жатканда жашы 18 чон болуш керек
    void saveCustomer(Customer customer);
    void updateCustomer (Long id, Customer customer);
    void deleteCustomer(Long id);
    Customer getCustomerById(Long id);
    List<Customer>getAllCustomers();
    Customer findByUsername(String name);
    List<Customer>getAllCustomersByAgencyId(Long agencyId);

}
