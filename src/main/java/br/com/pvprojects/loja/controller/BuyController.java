package br.com.pvprojects.loja.controller;

import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.pvprojects.loja.domain.response.HistoryResponse;
import br.com.pvprojects.loja.integration.request.MockRequestData;
import br.com.pvprojects.loja.integration.response.MockResponseData;
import br.com.pvprojects.loja.service.BuyService;

@RestController
public class BuyController {

    @Autowired
    private BuyService buyService;

    @PostMapping(path = "/buy/{number}")
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')  and #oauth2" +
            ".hasScope('write')")
    public ResponseEntity<MockResponseData> buy(@QueryParam("login") String login,
            @PathVariable(name = "number") String number,
            @RequestBody MockRequestData requestData) {

        MockResponseData responseData = buyService.buy(login, number, requestData);

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/buy/purchases")
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')  and #oauth2" +
            ".hasScope('read')")
    public ResponseEntity<List<HistoryResponse>> getHistory(@QueryParam("login") String login) {

        List<HistoryResponse> list = buyService.getHistoryByLogin(login);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}