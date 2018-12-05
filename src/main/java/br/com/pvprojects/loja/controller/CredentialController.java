package br.com.pvprojects.loja.controller;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pvprojects.loja.domain.form.CredentialChangeLogin;
import br.com.pvprojects.loja.domain.form.CredentialChangeLoginAndPassword;
import br.com.pvprojects.loja.domain.form.CredentialResetPassword;
import br.com.pvprojects.loja.domain.form.PasswordRequest;
import br.com.pvprojects.loja.domain.response.CredentialResponse;
import br.com.pvprojects.loja.service.CredentialService;

@RestController
@RequestMapping("/credential")
public class CredentialController {

    @Autowired
    private CredentialService credentialService;

    @GetMapping(path = "/login")
    public ResponseEntity<CredentialResponse> getCredentialByLogin(@QueryParam("email") String email) {

        CredentialResponse credentialResponse = this.credentialService.findByLogin(email);

        return new ResponseEntity<>(credentialResponse, HttpStatus.OK);
    }

    @PutMapping(path = "/login")
    public ResponseEntity<Void> changeLogin(@QueryParam("email") String email,
            @RequestBody CredentialChangeLogin credentialChangeLogin) {

        this.credentialService.changeLogin(credentialChangeLogin.getNewLogin(), email);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/login/password")
    public ResponseEntity<Void> changePassword(@QueryParam("email") String email,
            @RequestBody CredentialResetPassword credentialResetPassword) {

        this.credentialService.changePassword(credentialResetPassword.getPassword(), email);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/login/change")
    public ResponseEntity<Void> changeLoginAndPassword(@QueryParam("email") String email,
            @RequestBody CredentialChangeLoginAndPassword credentialChangeLoginAndPassword) {

        this.credentialService.changeLogingAndPassword(credentialChangeLoginAndPassword.getNewLogin(),
                credentialChangeLoginAndPassword.getNewPassword(), email);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login/recovery/password")
    public ResponseEntity<Void> recoveryPassword(@RequestBody PasswordRequest passwordRequest) {
        this.credentialService.recoveryPassword(passwordRequest.getLogin());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}