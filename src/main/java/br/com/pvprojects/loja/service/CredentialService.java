package br.com.pvprojects.loja.service;

import br.com.pvprojects.loja.domain.Credential;
import br.com.pvprojects.loja.domain.Customer;
import br.com.pvprojects.loja.domain.request.CredentialRequest;
import br.com.pvprojects.loja.domain.response.CredentialResponse;
import br.com.pvprojects.loja.domain.response.CustomerResponse;

public interface CredentialService {

    CredentialResponse create(CredentialRequest credentialRequest);

    Credential createWithCustomer(Customer customer);

    CredentialResponse findByLogin(String login);

    void updateLoginWithCustomer(String oldLogin, CustomerResponse customerCustomerResponse);

    void changeLogin(String newLogin, String oldLogin);

    void changePassword(String newPassord, String login);

    void changeLogingAndPassword(String newLogin, String newPassord, String login);
}