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
import br.com.pvprojects.loja.domain.data.CredentialData;
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
    public Credential create(Credential credential) {

        Helper.checkIfObjectIsNull(credential, "Informações inválidas.");
        this.loginIsUnique(credential.getLogin());

        Credential newCretial = new Credential();
        newCretial.setCustomerId(credential.getCustomerId());
        newCretial.setLogin(credential.getPassword());
        newCretial.setPassword(new BCryptPasswordEncoder().encode(credential.getPassword()));
        newCretial.setPerfil(Perfil.CLIENTE);

        try {
            this.credentialRepository.saveAndFlush(newCretial);
        } catch (Exception e) {
            log.error("Erro ao criar credential");
            throw new DefaultException("Erro ao criar credential.");
        }

        return newCretial;
    }

    @Override
    public Credential create(Customer customer) {

        Helper.checkIfObjectIsNull(customer, "Informações inválidas.");
        this.loginIsUnique(customer.getLogin());

        Credential newCretial = new Credential();
        newCretial.setCustomerId(customer.getId());
        newCretial.setLogin(customer.getLogin());
        newCretial.setPassword(customer.getPassword());
        newCretial.setPerfil(Perfil.CLIENTE);

        try {
            this.credentialRepository.saveAndFlush(newCretial);
        } catch (Exception e) {
            log.error("Erro ao criar credential");
            throw new DefaultException("Erro ao criar credential.");
        }

        return newCretial;
    }

    @Override
    public CredentialData findByLogin(String login) {
        Credential credential = this.credentialRepository.findByLoginIgnoreCase(login);
        Helper.checkIfObjectIsNull(credential, "Credential não encontrada.");

        CredentialData credentialData = new CredentialData();
        credentialData.setId(credential.getId());
        credentialData.setCustomerId(credential.getCustomerId());
        credentialData.setLogin(credential.getLogin());
        credentialData.setPassword("***");
        credentialData.setPerfil(credential.getPerfil().name());
        credentialData.setCreated(credential.getCreated());
        credentialData.setUpdated(credential.getUpdated());

        return credentialData;
    }

    @Override
    public void updateLoginWithCustomer(Customer customer) {
        Helper.checkIfObjectIsNull(customer, "Informações inválidas.");
        this.loginIsUnique(customer.getLogin());

        Credential credential = this.credentialRepository.findByLoginIgnoreCase(customer.getLogin());
        Helper.checkIfObjectIsNull(credential, "Credential não encontrada.");

        try {
            credential.setLogin(customer.getLogin());

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

            credential.setPassword(new BCryptPasswordEncoder().encode(newPassord));
            this.credentialRepository.saveAndFlush(credential);

            customer.setPassword(new BCryptPasswordEncoder().encode(newPassord));
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