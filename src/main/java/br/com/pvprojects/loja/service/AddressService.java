package br.com.pvprojects.loja.service;

import br.com.pvprojects.loja.domain.request.AddressRequest;
import br.com.pvprojects.loja.domain.response.AddressResponse;

public interface AddressService {

    AddressResponse create(AddressRequest addressRequest, String login);
}
