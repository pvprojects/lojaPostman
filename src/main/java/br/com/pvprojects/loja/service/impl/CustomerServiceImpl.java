package br.com.pvprojects.loja.service.impl;

import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pvprojects.loja.domain.Customer;
import br.com.pvprojects.loja.domain.data.CustomerData;
import br.com.pvprojects.loja.repository.CustomerRepository;
import br.com.pvprojects.loja.service.CredentialService;
import br.com.pvprojects.loja.service.CustomerService;
import br.com.pvprojects.loja.util.enums.Gender;
import br.com.pvprojects.loja.util.enums.PersonType;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;
    private final CredentialService credentialService;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CredentialService credentialService) {
        this.customerRepository = customerRepository;
        this.credentialService = credentialService;
    }


    @Override
    @Transactional
    public Customer create(Customer customer) {
        if (null == customer)
            log.error("Invalid form");

        Customer newCustomer = this.createCustomerHelper(customer);
        this.customerRepository.saveAndFlush(newCustomer);

        this.credentialService.create(newCustomer);

        return newCustomer;
    }

    @Override
    @Transactional
    public CustomerData updateCustomer(String customerId, Customer customer) {

        final Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if (!optionalCustomer.isPresent())
            return null;

        Customer customerPersisted = optionalCustomer.get();

        customerPersisted.setFullName(customer.getFullName());
        customerPersisted.setPersonType(customer.getPersonType() != null ? customer.getPersonType() : PersonType.F);
        customerPersisted.setNickName(customer.getNickName());
        customerPersisted.setBirthDate(customer.getBirthDate());
        customerPersisted.setCountry(customer.getCountry());
        customerPersisted.setGender(customer.getGender() != null ? customer.getGender() : Gender.I);
        customerPersisted.setMotherName(customer.getMotherName());
        customerPersisted.setFatherName(customer.getFatherName());
        customerPersisted.setNumberOfChildren(customer.getNumberOfChildren());
        customerPersisted.setParentId(customer.getParentId());
        this.customerRepository.saveAndFlush(customerPersisted);

        CustomerData customerData = this.customerDto(customerPersisted);

        return customerData;
    }

    @Override
    public CustomerData findById(String customerId) {

        if (Strings.isBlank(customerId)) {
            log.error("Invalid customerId.");
        }

        Optional<Customer> customer = customerRepository.findById(customerId);
        if (!customer.isPresent())
            return null;

        Customer currentCustomer = customer.get();
        CustomerData customerData = this.customerDto(currentCustomer);

        return customerData;
    }

    private Customer createCustomerHelper(Customer customer) {
        Customer newCustomer = new Customer();
        newCustomer.setFullName(customer.getFullName());
        newCustomer.setPersonType(customer.getPersonType() != null ? customer.getPersonType() : PersonType.F);
        newCustomer.setNickName(customer.getNickName());
        newCustomer.setBirthDate(customer.getBirthDate());
        newCustomer.setCountry(customer.getCountry());
        newCustomer.setGender(customer.getGender() != null ? customer.getGender() : Gender.I);
        newCustomer.setMotherName(customer.getMotherName());
        newCustomer.setFatherName(customer.getFatherName());
        newCustomer.setLogin(customer.getLogin());
        newCustomer.setPassword(new BCryptPasswordEncoder().encode(customer.getPassword()));
        newCustomer.setNumberOfChildren(customer.getNumberOfChildren());
        newCustomer.setParentId(customer.getParentId());
        return newCustomer;
    }

    private CustomerData customerDto(Customer customer) {
        CustomerData customerData = new CustomerData();
        customerData.setFullName(customer.getFullName());
        customerData.setPersonType(customer.getPersonType());
        customerData.setNickName(customer.getNickName());
        customerData.setBirthDate(customer.getBirthDate());
        customerData.setCountry(customer.getCountry());
        customerData.setCreated(customer.getCreated());
        customerData.setGender(customer.getGender());
        customerData.setMotherName(customer.getMotherName());
        customerData.setFatherName(customer.getFatherName());
        customerData.setLogin(customer.getLogin());
        customerData.setNumberOfChildren(customer.getNumberOfChildren());
        customerData.setParentId(customer.getParentId());
        return customerData;
    }
}