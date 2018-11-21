package br.com.pvprojects.loja.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.pvprojects.loja.base.BaseServiceTests;
import br.com.pvprojects.loja.domain.Customer;
import br.com.pvprojects.loja.domain.data.CustomerData;
import br.com.pvprojects.loja.repository.CustomerRepository;
import br.com.pvprojects.loja.service.CredentialService;
import br.com.pvprojects.loja.util.enums.Gender;
import br.com.pvprojects.loja.util.enums.PersonType;

public class CustomerServiceImplTest extends BaseServiceTests {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CredentialService credentialService;

    private String customerId;
    private Optional<Customer> customerOptional;

    @Before
    public void setUp() throws Exception {

        customerId = "ab3de519-20a1-4e40-a997-e55b010d4a79";

        customerOptional = Optional.of(new Customer());
        Customer customer = customerOptional.get();
        customer.setId(customerId);
        customer.setFullName("Paulo Vieira");
        customer.setNickName("Mestre");
        customer.setCountry("BR");
        customer.setBirthDate("22041993");
        customer.setGender(Gender.M);
        customer.setMotherName("Marlene");
        customer.setFatherName("Paulo");
        customer.setLogin("eng.paulovieiraa@gmail.com");
        customer.setPassword("123456");
        customer.setNumberOfChildren(0);
        customer.setPersonType(PersonType.F);
        customer.setCreated(LocalDateTime.now());
    }

    @Test
    public void findById() {
        when(customerRepository.findById(anyString())).thenReturn(customerOptional);

        CustomerData customerData = customerService.findById(customerId);
        assertNotNull(customerData);
    }
}