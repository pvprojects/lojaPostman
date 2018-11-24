package br.com.pvprojects.loja.service.impl;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pvprojects.loja.domain.Address;
import br.com.pvprojects.loja.domain.Customer;
import br.com.pvprojects.loja.repository.AddressRepository;
import br.com.pvprojects.loja.repository.CustomerRepository;
import br.com.pvprojects.loja.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

    private static final Logger log = LoggerFactory.getLogger(AddressServiceImpl.class);

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public Address create(Address address, String login) {

        Customer customer = customerRepository.findByLoginIgnoreCase(login);

        Address newAddress = new Address();
        newAddress.setCountry(address.getCountry());
        newAddress.setCity(address.getCity());
        newAddress.setComplement(address.getComplement());
        newAddress.setCreated(LocalDateTime.now());
        newAddress.setUpdated(LocalDateTime.now());
        newAddress.setCustomerId(customer.getId());
        newAddress.setName(address.getName());
        newAddress.setNumber(address.getNumber());
        newAddress.setState(address.getState());
        newAddress.setStreet(address.getStreet());
        newAddress.setZipCode(address.getZipCode());
        addressRepository.saveAndFlush(newAddress);

        return newAddress;
    }
}
