package br.com.pvprojects.loja.service.impl;

import static br.com.pvprojects.loja.util.ConventionsHelper.CAMPO_CUSTOMERID_INVALIDO;
import static br.com.pvprojects.loja.util.ConventionsHelper.CAMPO_LOGIN_CUSTOMERID_INVALIDO;
import static br.com.pvprojects.loja.util.ConventionsHelper.CUSTOMER_NOT_FOUND;
import static br.com.pvprojects.loja.util.ConventionsHelper.ERRO_ATUALIZAR_CUSTOMER;
import static br.com.pvprojects.loja.util.ConventionsHelper.ERRO_CRIAR_CUSTOMER;
import static br.com.pvprojects.loja.util.ConventionsHelper.ERRO_CUSTOMER_ENUM;
import static br.com.pvprojects.loja.util.ConventionsHelper.ERRO_PARSE_RESPONSE;
import static br.com.pvprojects.loja.util.ConventionsHelper.INVALID_REQUEST;
import static br.com.pvprojects.loja.util.ConventionsHelper.LOGIN_UNIQUE;
import static br.com.pvprojects.loja.util.Helper.createPasswordByBCrypt;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

        Helper.checkIfObjectIsNull(customerResquest, INVALID_REQUEST);
        this.loginIsUnique(customerResquest.getLogin());

        CustomerResponse customerResponse;

        Customer customer = this.createCustomerHelper(customerResquest);

        try {
            this.customerRepository.saveAndFlush(customer);
        } catch (Exception e) {
            log.error(ERRO_CRIAR_CUSTOMER);
            throw new DefaultException(ERRO_CRIAR_CUSTOMER);
        }

        this.credentialService.createWithCustomer(customer);

        customerResponse = this.customerToCustomerResponse(customer);

        return customerResponse;
    }

    @Override
    @Transactional
    public CustomerResponse updateCustomer(String customerId, CustomerResquest customerResquest) {

        Helper.checkIfObjectIsNull(customerResquest, INVALID_REQUEST);
        Helper.checkIfStringIsBlank(customerId, CAMPO_CUSTOMERID_INVALIDO);
        this.loginIsUnique(customerResquest.getLogin());

        final Customer customerPersisted = customerRepository.findOne(customerId);

        Helper.checkIfObjectIsNull(customerPersisted, CUSTOMER_NOT_FOUND);

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
            throw new DefaultException(ERRO_CUSTOMER_ENUM);
        } catch (Exception e) {
            log.error(ERRO_ATUALIZAR_CUSTOMER);
            throw new DefaultException(ERRO_ATUALIZAR_CUSTOMER);
        }

        CustomerResponse customerResponse = this.customerToCustomerResponse(customerPersisted);

        credentialService.updateLoginWithCustomer(oldLogin, customerResponse);

        return customerResponse;
    }

    @Override
    public CustomerResponse findByIdOrLogin(String customerId) {
        Helper.checkIfStringIsBlank(customerId, CAMPO_LOGIN_CUSTOMERID_INVALIDO);

        Customer customerById = customerRepository.findOne(customerId);

        if (null != customerById && !customerById.getId().isEmpty()) {
            return this.customerToCustomerResponse(customerById);
        }

        Customer customrByLogin = customerRepository.findByLogin(customerId.toLowerCase());
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
            customer.setPassword(createPasswordByBCrypt(customerResquest.getPassword()));
            customer.setNumberOfChildren(customerResquest.getNumberOfChildren() != null ?
                    customerResquest.getNumberOfChildren() : 0);
            customer.setParentId(customerResquest.getParentId());
        } catch (IllegalArgumentException e) {
            throw new DefaultException(ERRO_CUSTOMER_ENUM);
        } catch (Exception e) {
            throw new DefaultException(ERRO_PARSE_RESPONSE);
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
        Customer customerOptional = this.customerRepository.findByLogin(login.toLowerCase());

        if (null != customerOptional)
            Helper.checkIfObjectIsNull(customerOptional, LOGIN_UNIQUE);

        return true;
    }
}