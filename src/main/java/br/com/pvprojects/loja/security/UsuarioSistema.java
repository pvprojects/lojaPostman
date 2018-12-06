package br.com.pvprojects.loja.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.pvprojects.loja.domain.Credential;

public class UsuarioSistema extends User {

    private static final long serialVersionUID = 1L;

    private Credential credential;

    public UsuarioSistema(Credential credential, Collection<? extends GrantedAuthority> authorities) {
        super(credential.getLogin(), credential.getPassword(), authorities);
        this.credential = credential;
    }

    public Credential getCredential() {
        return credential;
    }
}
