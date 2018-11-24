package br.com.pvprojects.loja.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pvprojects.loja.domain.Customer;
import br.com.pvprojects.loja.domain.request.CustomerResquest;
import br.com.pvprojects.loja.domain.response.CustomerResponse;
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
    public CustomerResponse create(CustomerResquest customerResquest) {

        Helper.checkIfObjectIsNull(customerResquest, "Informações inválidas.");
        this.loginIsUnique(customerResquest.getLogin());

        CustomerResponse customerResponse;

        Customer customer = this.createCustomerHelper(customerResquest);

        try {
            this.customerRepository.saveAndFlush(customer);
        } catch (Exception e) {
            log.error("Erro ao criar customer");
            throw new DefaultException("Erro ao criar customer");
        }

        this.credentialService.createWithCustomer(customer);

        customerResponse = this.customerToCustomerResponse(customer);

        return customerResponse;
    }

    @Override
    @Transactional
    public CustomerResponse updateCustomer(String customerId, CustomerResquest customerResquest) {

        Helper.checkIfObjectIsNull(customerResquest, "Informações inválidas.");
        Helper.checkIfStringIsBlank(customerId, "customerId inválido.");
        this.loginIsUnique(customerResquest.getLogin());

        final Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if (!optionalCustomer.isPresent())
            throw new DefaultException("Usuário não encontrado.");

        CustomerResponse customerResponse;

        Customer customerPersisted = optionalCustomer.get();
        String oldLogin = customerPersisted.getLogin();

        try {

            customerPersisted.setFullName(customerResquest.getFullName());
            customerPersisted.setPersonType(customerResquest.getPersonType() != null ? PersonType.valueOf(
                    customerResquest.getPersonType()) : PersonType.F);
            customerPersisted.setNickName(customerResquest.getNickName());
            customerPersisted.setBirthDate(customerResquest.getBirthDate());
            customerPersisted.setCountry(customerResquest.getCountry());
            customerPersisted.setGender(
                    customerResquest.getGender() != null ? Gender.valueOf(customerResquest.getGender()) : Gender.I);
            customerPersisted.setLogin(customerResquest.getLogin());
            customerPersisted.setMotherName(customerResquest.getMotherName());
            customerPersisted.setFatherName(customerResquest.getFatherName());
            customerPersisted.setNumberOfChildren(customerResquest.getNumberOfChildren());
            customerPersisted.setParentId(customerResquest.getParentId());
            customerPersisted.setUpdated(LocalDateTime.now());
            this.customerRepository.saveAndFlush(customerPersisted);

        } catch (IllegalArgumentException e) {
            throw new DefaultException("Genero ou PersonType inválido.");
        } catch (Exception e) {
            log.error("Erro ao atualizar customer.");
            throw new DefaultException("Erro ao atualizar customer.");
        }

        credentialService.updateLoginWithCustomer(oldLogin, customerPersisted);

        customerResponse = this.customerToCustomerResponse(customerPersisted);

        return customerResponse;
    }

    @Override
    public CustomerResponse findByIdOrLogin(String customerId) {
        Helper.checkIfStringIsBlank(customerId, "customerId ou email inválido.");

        Optional<Customer> customerById = customerRepository.findById(customerId);

        if (customerById.isPresent()) {
            return this.customerToCustomerResponse(customerById.get());
        }

        Customer customrByLogin = customerRepository.findByLoginIgnoreCase(customerId);
        if (null != customrByLogin) {
            return this.customerToCustomerResponse(customrByLogin);
        }

        return null;
    }

    private Customer createCustomerHelper(CustomerResquest customerResquest) {
        Customer customer = new Customer();

        try {
            customer.setFullName(customerResquest.getFullName());
            customer.setPersonType(customerResquest.getPersonType() != null ?
                    PersonType.valueOf(customerResquest.getPersonType()) :
                    PersonType.F);
            customer.setNickName(customerResquest.getNickName());
            customer.setBirthDate(customerResquest.getBirthDate());
            customer.setCountry(customerResquest.getCountry() != null ? customerResquest.getCountry() : "BR");
            customer.setGender(customerResquest.getGender() != null ? Gender.valueOf(customerResquest.getGender()) :
                    Gender.I);
            customer.setMotherName(customerResquest.getMotherName());
            customer.setFatherName(customerResquest.getFatherName());
            customer.setLogin(customerResquest.getLogin());
            customer.setPassword(new BCryptPasswordEncoder().encode(customerResquest.getPassword()));
            customer.setNumberOfChildren(customerResquest.getNumberOfChildren() != null ?
                    customerResquest.getNumberOfChildren() : 0);
            customer.setParentId(customerResquest.getParentId());
        } catch (IllegalArgumentException e) {
            throw new DefaultException("Genero ou PersonType inválido.");
        } catch (Exception e) {
            throw new DefaultException("Erro ao parsear response.");
        }

        return customer;
    }


    private CustomerResponse customerToCustomerResponse(Customer customer) {
        CustomerResponse customerResponse = new CustomerResponse();

        customerResponse.setId(customer.getId());
        customerResponse.setFullName(customer.getFullName());
        customerResponse.setPersonType(customer.getPersonType());
        customerResponse.setNickName(customer.getNickName());
        customerResponse.setBirthDate(customer.getBirthDate());
        customerResponse.setCountry(customer.getCountry());
        customerResponse.setGender(customer.getGender());
        customerResponse.setMotherName(customer.getMotherName());
        customerResponse.setFatherName(customer.getFatherName());
        customerResponse.setLogin(customer.getLogin());
        customerResponse.setPassword("* * *");
        customerResponse.setNumberOfChildren(customer.getNumberOfChildren());
        customerResponse.setParentId(customer.getParentId());
        customerResponse.setCreated(customer.getCreated());
        customerResponse.setUpdated(customer.getUpdated());

        return customerResponse;
    }

    private boolean loginIsUnique(String login) {
        Optional<Customer> customerOptional = Optional.ofNullable(this.customerRepository.findByLoginIgnoreCase(login));

        if (customerOptional.isPresent())
            throw new DefaultException("O login informado já existe.");

        return true;
    }
}