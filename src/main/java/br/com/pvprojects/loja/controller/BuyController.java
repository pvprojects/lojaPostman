package br.com.pvprojects.loja.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.pvprojects.loja.integration.MockApiService;
import br.com.pvprojects.loja.integration.response.MockRequestData;
import br.com.pvprojects.loja.integration.response.MockResponseData;

@RestController
public class BuyController {

    @Autowired
    private MockApiService mockApiService;

    @PostMapping(path = "/buy/{number}")
    public ResponseEntity<MockResponseData> buy(@PathVariable(name = "number") String number,
            @RequestBody MockRequestData requestData) {

        MockResponseData responseData = mockApiService.responseBuy(number, requestData);

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}