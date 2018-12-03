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

    @GetMapping(path = "/login/{email}")
    public ResponseEntity<CredentialResponse> getCredentialByLogin(@PathVariable(name = "email") String email) {

        CredentialResponse credentialResponse = this.credentialService.findByLogin(email);

        return new ResponseEntity<>(credentialResponse, HttpStatus.OK);
    }

    @PutMapping(path = "/login/{email}")
    public ResponseEntity<Void> changeLogin(@PathVariable(name = "email") String email,
            @RequestBody CredentialChangeLogin credentialChangeLogin) {

        this.credentialService.changeLogin(credentialChangeLogin.getNewLogin(), email);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/login/password/{email}")
    public ResponseEntity<Void> changePassword(@PathVariable(name = "email") String email,
            @RequestBody CredentialResetPassword credentialResetPassword) {

        this.credentialService.changePassword(credentialResetPassword.getPassword(), email);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/login/change/{email}")
    public ResponseEntity<Void> changeLoginAndPassword(@PathVariable(name = "email") String email,
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
