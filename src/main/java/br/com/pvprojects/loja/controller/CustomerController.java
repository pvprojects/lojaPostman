package br.com.pvprojects.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pvprojects.loja.domain.Customer;
import br.com.pvprojects.loja.domain.data.CustomerData;
import br.com.pvprojects.loja.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerData> createCustomer(@RequestBody Customer customer) {

        Customer newCustomer = this.customerService.create(customer);
        CustomerData customerData = new CustomerData(newCustomer.getId());

        return new ResponseEntity<>(customerData, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{customerId}")
    public ResponseEntity<CustomerData> getCustomerById(@PathVariable(name = "customerId") String customerId) {

        CustomerData customerData = this.customerService.findById(customerId);

        return new ResponseEntity<>(customerData, HttpStatus.OK);
    }

    @PutMapping(path = "/{customerId}")
    private ResponseEntity<CustomerData> updateCustomer(@PathVariable(name = "customerId") String customerId,
            @RequestBody Customer customer) {

        CustomerData customerData = this.customerService.updateCustomer(customerId, customer);

        return new ResponseEntity<>(customerData, HttpStatus.OK);
    }
}