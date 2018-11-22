package br.com.pvprojects.loja.controller;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pvprojects.loja.domain.Address;
import br.com.pvprojects.loja.domain.data.AddressData;
import br.com.pvprojects.loja.domain.data.DocumentsData;
import br.com.pvprojects.loja.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping(path = "/create")
    public ResponseEntity<AddressData> createAddress(@RequestBody Address address,
            @QueryParam("login") String login) {

        Address newAddress = this.addressService.create(address, login);
        AddressData addressData = new AddressData(newAddress.getId());

        return new ResponseEntity<>(addressData, HttpStatus.CREATED);
    }
}