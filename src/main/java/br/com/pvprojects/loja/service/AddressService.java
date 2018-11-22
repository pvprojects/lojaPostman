package br.com.pvprojects.loja.service;

import br.com.pvprojects.loja.domain.Address;

public interface AddressService {

    Address create(Address address, String login);
}
