package br.com.pvprojects.loja.service.impl;

import java.util.Arrays;
import java.util.List;

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
import br.com.pvprojects.loja.repository.CredentialRepository;
import br.com.pvprojects.loja.repository.CustomerRepository;
import br.com.pvprojects.loja.service.CredentialService;
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

        Credential newCretial = new Credential();
        newCretial.setCustomerId(credential.getCustomerId());
        newCretial.setLogin(credential.getPassword());
        newCretial.setPassword(new BCryptPasswordEncoder().encode(credential.getPassword()));
        newCretial.setPerfil(Perfil.CLIENTE);
        this.credentialRepository.saveAndFlush(newCretial);

        return newCretial;
    }

    @Override
    public Credential create(Customer customer) {

        Credential newCretial = new Credential();
        newCretial.setCustomerId(customer.getId());
        newCretial.setLogin(customer.getLogin());
        newCretial.setPassword(customer.getPassword());
        newCretial.setPerfil(Perfil.CLIENTE);
        this.credentialRepository.saveAndFlush(newCretial);

        return newCretial;
    }

    @Override
    public CredentialData findByLogin(String login) {
        Credential credential = this.credentialRepository.findByLoginIgnoreCase(login);

        CredentialData credentialData = new CredentialData();
        credentialData.setId(credential.getId());
        credentialData.setCustomerId(credential.getCustomerId());
        credentialData.setLogin(credential.getLogin());
        credentialData.setPerfil(credential.getPerfil().name());
        credentialData.setCreated(credential.getCreated());
        credentialData.setUpdated(credential.getUpdated());

        return credentialData;
    }

    @Override
    @Transactional
    public void changeLogin(String newLogin, String oldLogin) {
        Customer customer = this.customerRepository.findByLoginIgnoreCase(oldLogin);
        Credential credential = this.credentialRepository.findByLoginIgnoreCase(oldLogin);

        Customer customerExist = this.customerRepository.findByLoginIgnoreCase(newLogin);
        if (null != customerExist)
            log.error("Email ja existe em uma conta");
        //TODO exception

        customer.setLogin(newLogin);
        this.customerRepository.saveAndFlush(customer);

        credential.setLogin(newLogin);
        this.credentialRepository.saveAndFlush(credential);
    }

    @Override
    @Transactional
    public void changePassword(String newPassord, String login) {
        Credential credential = this.credentialRepository.findByLoginIgnoreCase(login);
        Customer customer = this.customerRepository.findByLoginIgnoreCase(login);

        credential.setPassword(new BCryptPasswordEncoder().encode(newPassord));
        this.credentialRepository.saveAndFlush(credential);

        customer.setPassword(new BCryptPasswordEncoder().encode(newPassord));
        this.customerRepository.saveAndFlush(customer);
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
}