package br.com.pvprojects.loja.domain.form;

import java.util.Objects;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CredentialChangeLogin {

    @NotBlank(message = "O login não pode ser vazio ou nulo")
    private String newLogin;

    public CredentialChangeLogin() {
    }

    public CredentialChangeLogin(String newLogin) {
        this.newLogin = newLogin;
    }

    public String getNewLogin() {
        return newLogin;
    }

    public void setNewLogin(String newLogin) {
        this.newLogin = newLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CredentialChangeLogin that = (CredentialChangeLogin) o;
        return Objects.equals(newLogin, that.newLogin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newLogin);
    }

    @Override
    public String toString() {
        return "CredentialChangeLogin{" +
                "newLogin='" + newLogin + '\'' +
                '}';
    }
}
