package br.com.pvprojects.loja.service;

import br.com.pvprojects.loja.domain.Credential;
import br.com.pvprojects.loja.domain.Customer;
import br.com.pvprojects.loja.domain.data.CredentialData;

public interface CredentialService {

    Credential create(Credential credential);

    Credential create(Customer customer);

    CredentialData findByLogin(String login);

    void updateLoginWithCustomer(Customer customer);

    void changeLogin(String newLogin, String oldLogin);

    void changePassword(String newPassord, String login);

    void changeLogingAndPassword(String newLogin, String newPassord, String login);
}