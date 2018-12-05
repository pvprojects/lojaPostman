package br.com.pvprojects.loja.service.impl;

import static br.com.pvprojects.loja.util.ConventionsHelper.CAMPO_CUSTOMERID_INVALIDO;
import static br.com.pvprojects.loja.util.ConventionsHelper.ERRO_SALVAR_ENDERECO;
import static br.com.pvprojects.loja.util.ConventionsHelper.INVALID_REQUEST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pvprojects.loja.domain.Address;
import br.com.pvprojects.loja.domain.Customer;
import br.com.pvprojects.loja.domain.request.AddressRequest;
import br.com.pvprojects.loja.domain.response.AddressResponse;
import br.com.pvprojects.loja.infra.handle.exceptions.DefaultException;
import br.com.pvprojects.loja.repository.AddressRepository;
import br.com.pvprojects.loja.repository.CustomerRepository;
import br.com.pvprojects.loja.service.AddressService;
import br.com.pvprojects.loja.util.Helper;

@Service
public class AddressServiceImpl implements AddressService {

    private static final Logger log = LoggerFactory.getLogger(AddressServiceImpl.class);

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public AddressResponse create(AddressRequest addressRequest, String login) {
        Helper.checkIfObjectIsNull(addressRequest, INVALID_REQUEST);
        Helper.checkIfStringIsBlank(login, CAMPO_CUSTOMERID_INVALIDO);

        Customer customer = customerRepository.findByLogin(login.toLowerCase());

        Address address = new Address();
        AddressResponse addressResponse;

        try {

            address.setName(addressRequest.getName());
            address.setZipCode(addressRequest.getZipCode());
            address.setNumber(addressRequest.getNumber());
            address.setStreet(addressRequest.getStreet());
            address.setDistrict(addressRequest.getDistrict());
            address.setCity(addressRequest.getCity());
            address.setState(addressRequest.getState());
            address.setComplement(addressRequest.getComplement());
            address.setCountry(addressRequest.getCountry() != null ? addressRequest.getCountry() : "BR");
            address.setCustomerId(customer.getId());

            addressRepository.saveAndFlush(address);
        } catch (Exception e) {
            log.error(ERRO_SALVAR_ENDERECO);
            throw new DefaultException(ERRO_SALVAR_ENDERECO);
        }

        addressResponse = this.addressToAddressResponse(address);

        return addressResponse;
    }

    private AddressResponse addressToAddressResponse(Address address) {
        AddressResponse addressResponse = new AddressResponse();

        addressResponse.setId(address.getId());
        addressResponse.setName(address.getName());
        addressResponse.setZipCode(address.getZipCode());
        addressResponse.setNumber(address.getNumber());
        addressResponse.setStreet(address.getStreet());
        addressResponse.setDistrict(address.getDistrict());
        addressResponse.setCity(address.getCity());
        address.setState(address.getState());
        addressResponse.setComplement(address.getComplement());
        addressResponse.setCountry(address.getCountry());
        addressResponse.setCustomerId(address.getCustomerId());
        addressResponse.setCreated(address.getCreated());
        addressResponse.setUpdated(address.getUpdated());
        return addressResponse;
    }
}