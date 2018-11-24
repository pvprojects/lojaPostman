package br.com.pvprojects.loja.service;

import br.com.pvprojects.loja.domain.request.CustomerResquest;
import br.com.pvprojects.loja.domain.response.CustomerResponse;

public interface CustomerService {

    CustomerResponse create(CustomerResquest customerResquest);

    CustomerResponse updateCustomer(String customerId, CustomerResquest customerResquest);

    CustomerResponse findByIdOrLogin(String customerId);
}