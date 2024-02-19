package peaksoft.repo;

import peaksoft.model.Customer;

import java.util.List;

public interface CustomerRepository {
    //IV - Customer сакталып жатканда озу эле сакталсын, анан agency гe assign болот
    //VIII - Customer save кылып жатканда жашы 18 чон болуш керек
    void saveCustomer(Customer customer);
    void updateCustomer (Long id, Customer customer);
    void deleteCustomer(Long id);
    Customer getCustomerById(Long id);
    Customer findByUsername(String name);
    List<Customer>getAllCustomers();
    List<Customer>getAllCustomersByAgencyId(Long agencyId);


}
