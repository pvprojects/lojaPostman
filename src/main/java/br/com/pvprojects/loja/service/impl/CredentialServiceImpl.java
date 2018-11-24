package br.com.pvprojects.loja.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pvprojects.loja.domain.Credential;
import br.com.pvprojects.loja.domain.Customer;
import br.com.pvprojects.loja.domain.request.CredentialRequest;
import br.com.pvprojects.loja.domain.response.CredentialResponse;
import br.com.pvprojects.loja.domain.response.CustomerResponse;
import br.com.pvprojects.loja.infra.handle.exceptions.DefaultException;
import br.com.pvprojects.loja.repository.CredentialRepository;
import br.com.pvprojects.loja.repository.CustomerRepository;
import br.com.pvprojects.loja.service.CredentialService;
import br.com.pvprojects.loja.util.Helper;
import br.com.pvprojects.loja.util.enums.Perfil;

@Service(value = "userService")
public class CredentialServiceImpl implements CredentialService, UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CredentialRepository credentialRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public CredentialResponse create(CredentialRequest credentialRequest) {

        Helper.checkIfObjectIsNull(credentialRequest, "Informações inválidas.");
        this.loginIsUnique(credentialRequest.getLogin());

        Credential cretial = new Credential();
        CredentialResponse credentialResponse;

        try {

            cretial.setCustomerId(credentialRequest.getCustomerId());
            cretial.setLogin(credentialRequest.getPassword());
            cretial.setPassword(new BCryptPasswordEncoder().encode(credentialRequest.getPassword()));
            cretial.setPerfil(credentialRequest.getPerfil() != null ? Perfil.valueOf(credentialRequest.getPerfil()) :
                    Perfil.CLIENTE);
            this.credentialRepository.saveAndFlush(cretial);

        } catch (IllegalArgumentException e) {
            throw new DefaultException("Perfil inválido.");
        } catch (Exception e) {
            log.error("Erro ao criar credential");
            throw new DefaultException("Erro ao criar credential.");
        }

        credentialResponse = this.credentialToCredentialResponse(cretial);

        return credentialResponse;
    }

    private CredentialResponse credentialToCredentialResponse(Credential credential) {
        CredentialResponse credentialResponse = new CredentialResponse();

        credentialResponse.setId(credential.getId());
        credentialResponse.setCustomerId(credential.getCustomerId());
        credentialResponse.setLogin(credential.getLogin());
        credentialResponse.setPassword("* * *");
        credentialResponse.setPerfil(credential.getPerfil());
        credentialResponse.setCreated(credential.getCreated());
        credentialResponse.setUpdated(credential.getUpdated());
        return credentialResponse;
    }

    @Override
    @Transactional
    public Credential createWithCustomer(Customer customer) {

        Helper.checkIfObjectIsNull(customer, "Informações inválidas.");
        this.loginIsUnique(customer.getLogin());

        Credential cretial = new Credential();
        cretial.setCustomerId(customer.getId());
        cretial.setLogin(customer.getLogin());
        cretial.setPassword(customer.getPassword());
        cretial.setPerfil(Perfil.CLIENTE);

        try {
            this.credentialRepository.saveAndFlush(cretial);
        } catch (Exception e) {
            log.error("Erro ao criar credential");
            throw new DefaultException("Erro ao criar credential.");
        }

        return cretial;
    }

    @Override
    public CredentialResponse findByLogin(String login) {
        Credential credential = this.credentialRepository.findByLoginIgnoreCase(login);
        Helper.checkIfObjectIsNull(credential, "Credential não encontrada.");

        CredentialResponse credentialResponse = new CredentialResponse();
        credentialResponse.setId(credential.getId());
        credentialResponse.setCustomerId(credential.getCustomerId());
        credentialResponse.setLogin(credential.getLogin());
        credentialResponse.setPassword("* * *");
        credentialResponse.setPerfil(credential.getPerfil());
        credentialResponse.setCreated(credential.getCreated());
        credentialResponse.setUpdated(credential.getUpdated());

        return credentialResponse;
    }

    @Override
    @Transactional
    public void updateLoginWithCustomer(String oldLogin, CustomerResponse customerResponse) {
        Helper.checkIfObjectIsNull(customerResponse, "Informações inválidas.");
        this.loginIsUnique(customerResponse.getLogin());

        Credential credential = this.credentialRepository.findByLoginIgnoreCase(oldLogin);
        Helper.checkIfObjectIsNull(credential, "Credential não encontrada.");

        try {
            credential.setLogin(customerResponse.getLogin());

            credentialRepository.saveAndFlush(credential);
        } catch (Exception e) {
            log.error("Erro ao atualizar a credential");
            throw new DefaultException("Erro ao atualizar a credential.");
        }
    }

    @Override
    @Transactional
    public void changeLogin(String newLogin, String oldLogin) {
        Helper.checkIfStringIsBlank(oldLogin, "Usuário inválido.");
        Helper.checkIfStringIsBlank(newLogin, "Novo login inválido.");

        this.loginIsUnique(newLogin);

        Customer customer = this.customerRepository.findByLoginIgnoreCase(oldLogin);
        Helper.checkIfObjectIsNull(customer, "Customer não encontrado.");

        Credential credential = this.credentialRepository.findByLoginIgnoreCase(oldLogin);
        Helper.checkIfObjectIsNull(credential, "Credential não encontrada.");

        try {
            customer.setLogin(newLogin);
            this.customerRepository.saveAndFlush(customer);

            credential.setLogin(newLogin);
            this.credentialRepository.saveAndFlush(credential);
        } catch (Exception e) {
            log.error("Erro ao atualizar o login.");
            throw new DefaultException("Erro ao atualizar o login.");
        }
    }

    @Override
    @Transactional
    public void changePassword(String newPassord, String login) {
        Helper.checkIfStringIsBlank(login, "Usuário inválido.");
        Helper.checkIfStringIsBlank(newPassord, "Nova senha inválido.");

        Customer customer = this.customerRepository.findByLoginIgnoreCase(login);
        Helper.checkIfObjectIsNull(customer, "Customer não encontrado.");

        Credential credential = this.credentialRepository.findByLoginIgnoreCase(login);
        Helper.checkIfObjectIsNull(credential, "Credential não encontrada.");

        try {
            String passwordEncoder = new BCryptPasswordEncoder().encode(newPassord);

            credential.setPassword(passwordEncoder);
            this.credentialRepository.saveAndFlush(credential);

            customer.setPassword(passwordEncoder);
            this.customerRepository.saveAndFlush(customer);
        } catch (Exception e) {
            log.error("Erro ao atualizar a senha.");
            throw new DefaultException("Erro ao atualizar a senha.");
        }
    }

    @Override
    @Transactional
    public void changeLogingAndPassword(String newLogin, String newPassord, String login) {
        this.changeLogin(newLogin, login);
        this.changePassword(newPassord, newLogin);
    }

    public UserDetails loadUserByUsername(String login) {
        Credential credential = this.credentialRepository.findByLoginIgnoreCase(login);
        if (null == credential) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(credential.getLogin(), credential.getPassword(),
                getAuthority(credential));
    }

    private List getAuthority(Credential user) {
        return Arrays.asList(new SimpleGrantedAuthority(user.getPerfil().name()));
    }

    private boolean loginIsUnique(String login) {
        Optional<Credential> credentialptional = Optional.ofNullable(
                this.credentialRepository.findByLoginIgnoreCase(login));

        if (credentialptional.isPresent())
            throw new DefaultException("O login informado já existe.");

        return true;
    }
}