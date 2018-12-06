package br.com.pvprojects.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pvprojects.loja.domain.request.CustomerResquest;
import br.com.pvprojects.loja.domain.response.CustomerResponse;
import br.com.pvprojects.loja.service.CustomerService;
import br.com.pvprojects.loja.util.Helper;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')  and #oauth2" +
            ".hasScope('write')")
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerResquest customerResquest) {

        CustomerResponse customerResponse = this.customerService.create(customerResquest);

        return new ResponseEntity<>(new CustomerResponse(customerResponse.getId()), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{customerId}")
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')  and #oauth2" +
            ".hasScope('read')")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable(name = "customerId") String customerId) {

        CustomerResponse customerResponse = this.customerService.findByIdOrLogin(customerId);
        Helper.checkIfObjectIsNull(customerResponse, "Usuário não encontrado.");

        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }

    @PutMapping(path = "/{customerId}")
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')  and #oauth2" +
            ".hasScope('write')")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable(name = "customerId") String customerId,
            @RequestBody CustomerResquest customerResquest) {

        CustomerResponse customerResponse = this.customerService.updateCustomer(customerId, customerResquest);

        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }
}