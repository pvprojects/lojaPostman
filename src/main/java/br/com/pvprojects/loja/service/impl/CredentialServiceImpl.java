package br.com.pvprojects.loja.service.impl;

import static br.com.pvprojects.loja.util.ConventionsHelper.AUTH_ERROR;
import static br.com.pvprojects.loja.util.ConventionsHelper.CAMPO_EMAIL_INVALIDO;
import static br.com.pvprojects.loja.util.ConventionsHelper.CAMPO_NOVO_EMAIL_INVALIDO;
import static br.com.pvprojects.loja.util.ConventionsHelper.CAMPO_NOVO_PASSWORD_INVALIDO;
import static br.com.pvprojects.loja.util.ConventionsHelper.CREDENTIAL_NOT_FOUND;
import static br.com.pvprojects.loja.util.ConventionsHelper.CUSTOMER_NOT_FOUND;
import static br.com.pvprojects.loja.util.ConventionsHelper.ERRO_ATUALIZAR_CREDENTIAL;
import static br.com.pvprojects.loja.util.ConventionsHelper.ERRO_ATUALIZAR_LOGIN;
import static br.com.pvprojects.loja.util.ConventionsHelper.ERRO_ATUALIZAR_PASSWORD;
import static br.com.pvprojects.loja.util.ConventionsHelper.ERRO_CREDENTIAL_NAO_ENCONTRADA;
import static br.com.pvprojects.loja.util.ConventionsHelper.ERRO_CRIAR_CREDENTIAL;
import static br.com.pvprojects.loja.util.ConventionsHelper.ERRO_RECUPERAR_PASSWORD;
import static br.com.pvprojects.loja.util.ConventionsHelper.INVALID_PERFIL;
import static br.com.pvprojects.loja.util.ConventionsHelper.INVALID_REQUEST;
import static br.com.pvprojects.loja.util.ConventionsHelper.LOGIN_UNIQUE;
import static br.com.pvprojects.loja.util.Helper.createPasswordByBCrypt;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pvprojects.loja.domain.Credential;
import br.com.pvprojects.loja.domain.Customer;
import br.com.pvprojects.loja.domain.Permissao;
import br.com.pvprojects.loja.domain.request.CredentialRequest;
import br.com.pvprojects.loja.domain.response.CredentialResponse;
import br.com.pvprojects.loja.domain.response.CustomerResponse;
import br.com.pvprojects.loja.infra.handle.exceptions.DefaultException;
import br.com.pvprojects.loja.infra.mail.SendEmail;
import br.com.pvprojects.loja.repository.CredentialRepository;
import br.com.pvprojects.loja.repository.CustomerRepository;
import br.com.pvprojects.loja.security.UsuarioSistema;
import br.com.pvprojects.loja.service.CredentialService;
import br.com.pvprojects.loja.util.Helper;

@Service(value = "userService")
public class CredentialServiceImpl implements CredentialService, UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CredentialRepository credentialRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SendEmail sendEmail;

    @Override
    @Transactional
    public CredentialResponse create(CredentialRequest credentialRequest) {

        Helper.checkIfObjectIsNull(credentialRequest, INVALID_REQUEST);
        this.loginIsUnique(credentialRequest.getLogin());

        Credential cretial = new Credential();
        CredentialResponse credentialResponse;

        try {

            cretial.setCustomerId(credentialRequest.getCustomerId());
            cretial.setLogin(credentialRequest.getPassword());
            cretial.setPassword(createPasswordByBCrypt(credentialRequest.getPassword()));
            cretial.setPermissoes(Arrays.asList(createPermissoesForUser()));
            this.credentialRepository.saveAndFlush(cretial);

        } catch (IllegalArgumentException e) {
            throw new DefaultException(INVALID_PERFIL);
        } catch (Exception e) {
            log.error(ERRO_CRIAR_CREDENTIAL);
            throw new DefaultException(ERRO_CRIAR_CREDENTIAL);
        }

        credentialResponse = this.credentialToCredentialResponse(cretial);

        return credentialResponse;
    }

    private Permissao createPermissoesForUser (){
        Permissao p = new Permissao();
        p.setCodigo(5L);
        p.setDescricao("ROLE_USER");
        return p;
    }

    private Permissao createPermissoesForAdmin (){
        Permissao p = new Permissao();
        p.setCodigo(5L);
        p.setDescricao("ROLE_ADMIN");
        return p;
    }

    private CredentialResponse credentialToCredentialResponse(Credential credential) {
        CredentialResponse credentialResponse = new CredentialResponse();

        credentialResponse.setId(credential.getCodigo());
        credentialResponse.setCustomerId(credential.getCustomerId());
        credentialResponse.setLogin(credential.getLogin());
        credentialResponse.setPassword("* * *");
        credentialResponse.setCreated(credential.getCreated());
        credentialResponse.setUpdated(credential.getUpdated());
        return credentialResponse;
    }

    @Override
    @Transactional
    public Credential createWithCustomer(Customer customer) {

        Helper.checkIfObjectIsNull(customer, INVALID_REQUEST);
        this.loginIsUnique(customer.getLogin());

        Credential credential = new Credential();
        credential.setCustomerId(customer.getId());
        credential.setLogin(customer.getLogin());
        credential.setPassword(customer.getPassword());
        credential.setPermissoes(Arrays.asList(createPermissoesForUser()));

        try {
            this.credentialRepository.saveAndFlush(credential);
        } catch (Exception e) {
            log.error(ERRO_CRIAR_CREDENTIAL);
            throw new DefaultException(ERRO_CRIAR_CREDENTIAL);
        }

        return credential;
    }

    @Override
    public CredentialResponse findByLogin(String login) {
        Credential credential = this.credentialRepository.findByLoginIgnoreCase(login);
        Helper.checkIfObjectIsNull(credential, ERRO_CREDENTIAL_NAO_ENCONTRADA);

        CredentialResponse credentialResponse = new CredentialResponse();
        credentialResponse.setId(credential.getCodigo());
        credentialResponse.setCustomerId(credential.getCustomerId());
        credentialResponse.setLogin(credential.getLogin());
        credentialResponse.setPassword("* * *");
        credentialResponse.setCreated(credential.getCreated());
        credentialResponse.setUpdated(credential.getUpdated());

        return credentialResponse;
    }

    @Override
    @Transactional
    public void updateLoginWithCustomer(String oldLogin, CustomerResponse customerResponse) {
        Helper.checkIfObjectIsNull(customerResponse, INVALID_REQUEST);
        this.loginIsUnique(customerResponse.getLogin());

        Credential credential = this.credentialRepository.findByLoginIgnoreCase(oldLogin);
        Helper.checkIfObjectIsNull(credential, ERRO_CREDENTIAL_NAO_ENCONTRADA);

        try {
            credential.setLogin(customerResponse.getLogin());

            credentialRepository.saveAndFlush(credential);
        } catch (Exception e) {
            log.error(ERRO_ATUALIZAR_CREDENTIAL);
            throw new DefaultException(ERRO_ATUALIZAR_CREDENTIAL);
        }
    }

    @Override
    @Transactional
    public void changeLogin(String newLogin, String oldLogin) {
        Helper.checkIfStringIsBlank(oldLogin, CAMPO_EMAIL_INVALIDO);
        Helper.checkIfStringIsBlank(newLogin, CAMPO_NOVO_EMAIL_INVALIDO);

        this.loginIsUnique(newLogin);

        Customer customer = this.customerRepository.findByLogin(oldLogin.toLowerCase());
        Helper.checkIfObjectIsNull(customer, CUSTOMER_NOT_FOUND);

        Credential credential = this.credentialRepository.findByLoginIgnoreCase(oldLogin);
        Helper.checkIfObjectIsNull(credential, CREDENTIAL_NOT_FOUND);

        try {
            customer.setLogin(newLogin);
            this.customerRepository.saveAndFlush(customer);

            credential.setLogin(newLogin);
            this.credentialRepository.saveAndFlush(credential);
        } catch (Exception e) {
            log.error(ERRO_ATUALIZAR_LOGIN);
            throw new DefaultException(ERRO_ATUALIZAR_LOGIN);
        }
    }

    @Override
    @Transactional
    public void changePassword(String newPassord, String login) {
        Helper.checkIfStringIsBlank(login, CAMPO_EMAIL_INVALIDO);
        Helper.checkIfStringIsBlank(newPassord, CAMPO_NOVO_PASSWORD_INVALIDO);

        Customer customer = this.customerRepository.findByLogin(login.toLowerCase());
        Helper.checkIfObjectIsNull(customer, CUSTOMER_NOT_FOUND);

        Credential credential = this.credentialRepository.findByLoginIgnoreCase(login);
        Helper.checkIfObjectIsNull(credential, CREDENTIAL_NOT_FOUND);

        try {
            String passwordEncoder = createPasswordByBCrypt(newPassord);

            credential.setPassword(passwordEncoder);
            this.credentialRepository.saveAndFlush(credential);

            customer.setPassword(passwordEncoder);
            this.customerRepository.saveAndFlush(customer);
        } catch (Exception e) {
            log.error(ERRO_ATUALIZAR_PASSWORD);
            throw new DefaultException(ERRO_ATUALIZAR_PASSWORD);
        }
    }

    @Override
    @Transactional
    public void changeLogingAndPassword(String newLogin, String newPassord, String login) {
        this.changeLogin(newLogin, login);
        this.changePassword(newPassord, newLogin);
    }

    @Override
    public void recoveryPassword(String login) {
        Helper.checkIfStringIsBlank(login, CAMPO_EMAIL_INVALIDO);
        login = login.toLowerCase().trim();

        Credential credential = credentialRepository.findByLogin(login);
        Helper.checkIfObjectIsNull(credential, CREDENTIAL_NOT_FOUND);

        String senha = credential.getPassword();

        String template = "email/recuperar-senha";

        Map<String, Object> map = new HashMap<>();
        map.put("senha", senha);

        try {
            this.sendEmail.enviarEmail("eng.paulovieiraa@gmail.com", Arrays.asList(credential.getLogin()),
                    "Recuperação de senha", template, map);
        } catch (Exception e) {
            log.error(ERRO_RECUPERAR_PASSWORD);
            throw new DefaultException(ERRO_RECUPERAR_PASSWORD);
        }
    }

    public UserDetails loadUserByUsername(String login) {

        Credential credential = this.credentialRepository.findByLoginIgnoreCase(login);

        if (null == credential) {
            throw new UsernameNotFoundException(AUTH_ERROR);
        }


        return new UsuarioSistema(credential, getPermissoes(credential));
    }

    private Collection<? extends GrantedAuthority> getPermissoes(Credential credential) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        credential.getPermissoes().forEach(
                p -> authorities.add(new SimpleGrantedAuthority(p.getDescricao().toUpperCase())));
        return authorities;
    }

    private boolean loginIsUnique(String login) {
        Optional<Credential> credentialptional = Optional.ofNullable(
                this.credentialRepository.findByLoginIgnoreCase(login));

        if (credentialptional.isPresent())
            throw new DefaultException(LOGIN_UNIQUE);

        return true;
    }
}