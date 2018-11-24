package br.com.pvprojects.loja.controller;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pvprojects.loja.domain.request.AddressRequest;
import br.com.pvprojects.loja.domain.response.AddressResponse;
import br.com.pvprojects.loja.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping(path = "/create")
    public ResponseEntity<AddressResponse> createAddress(@RequestBody @Valid AddressRequest addressRequest,
            @QueryParam("login") String login) {

        AddressResponse address = this.addressService.create(addressRequest, login);

        return new ResponseEntity<>(new AddressResponse(address.getId()), HttpStatus.CREATED);
    }
}