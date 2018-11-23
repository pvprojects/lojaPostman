package br.com.pvprojects.loja.service;

import br.com.pvprojects.loja.domain.Customer;
import br.com.pvprojects.loja.domain.data.CustomerData;

public interface CustomerService {

    Customer create(Customer customer);

    CustomerData updateCustomer(String customerId, Customer customer);

    CustomerData findByIdOrLogin(String customerId);
}