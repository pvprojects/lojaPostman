package br.com.pvprojects.loja.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pvprojects.loja.domain.Customer;
import br.com.pvprojects.loja.domain.data.CustomerData;
import br.com.pvprojects.loja.infra.handle.exceptions.DefaultException;
import br.com.pvprojects.loja.repository.CustomerRepository;
import br.com.pvprojects.loja.service.CredentialService;
import br.com.pvprojects.loja.service.CustomerService;
import br.com.pvprojects.loja.util.Helper;
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

        Helper.checkIfObjectIsNull(customer, "Informações inválidas.");
        this.loginIsUnique(customer.getLogin());

        Customer newCustomer = this.createCustomerHelper(customer);

        try {
            this.customerRepository.saveAndFlush(newCustomer);
        } catch (Exception e) {
            log.error("Erro ao criar customer");
            throw new DefaultException("Erro ao criar customer");
        }

        this.credentialService.create(newCustomer);

        return newCustomer;
    }

    @Override
    @Transactional
    public CustomerData updateCustomer(String customerId, Customer customer) {

        Helper.checkIfObjectIsNull(customer, "Informações inválidas.");
        Helper.checkIfStringIsBlank(customerId, "customerId inválido.");
        this.loginIsUnique(customer.getLogin());

        final Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if (!optionalCustomer.isPresent())
            throw new DefaultException("Usuário não encontrado.");

        Customer customerPersisted = optionalCustomer.get();

        try {

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

        } catch (Exception e) {
            log.error("Erro ao atualizar customer");
            throw new DefaultException("Erro ao atualizar customer");
        }

        credentialService.updateLoginWithCustomer(customerPersisted);

        CustomerData customerData = this.customerDto(customerPersisted);

        return customerData;
    }

    @Override
    public CustomerData findByIdOrLogin(String customerId) {
        Helper.checkIfStringIsBlank(customerId, "customerId ou email inválido.");

        Optional<Customer> customerById = customerRepository.findById(customerId);

        if (customerById.isPresent()) {
            return this.customerDto(customerById.get());
        }

        Customer customrByLogin = customerRepository.findByLoginIgnoreCase(customerId);
        if (null != customrByLogin) {
            return this.customerDto(customrByLogin);
        }

        return null;
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
        customerData.setId(customer.getId());
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

    private boolean loginIsUnique(String login) {
        Optional<Customer> customerOptional = Optional.ofNullable(this.customerRepository.findByLoginIgnoreCase(login));

        if (customerOptional.isPresent())
            throw new DefaultException("O login informado já existe.");

        return true;
    }
}