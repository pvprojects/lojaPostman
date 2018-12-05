package br.com.pvprojects.loja.domain.request;

import static br.com.pvprojects.loja.util.ConventionsHelper.CAMPO_CUSTOMERID_NAO_INFORMADO;
import static br.com.pvprojects.loja.util.ConventionsHelper.EMPTY_NULL_LOGIN;
import static br.com.pvprojects.loja.util.ConventionsHelper.EMPTY_NULL_PASSWORD;
import static br.com.pvprojects.loja.util.ConventionsHelper.INVALID_LOGIN;
import static br.com.pvprojects.loja.util.ConventionsHelper.INVALID_PASSWORD_SIZE;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class CredentialRequest implements Serializable {

    @NotBlank(message = CAMPO_CUSTOMERID_NAO_INFORMADO)
    private String customerId;

    @NotBlank(message = EMPTY_NULL_LOGIN)
    @Email(message = INVALID_LOGIN)
    private String login;

    @NotBlank(message = EMPTY_NULL_PASSWORD)
    @Pattern(regexp = "\\d{6}$", message = INVALID_PASSWORD_SIZE)
    private String password;

    private String perfil;

    public CredentialRequest() {
    }

    public CredentialRequest(String customerId, String login, String password, String perfil) {
        this.customerId = customerId;
        this.login = login;
        this.password = password;
        this.perfil = perfil;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CredentialRequest that = (CredentialRequest) o;
        return Objects.equals(customerId, that.customerId) &&
                Objects.equals(login, that.login) &&
                Objects.equals(password, that.password) &&
                Objects.equals(perfil, that.perfil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, login, password, perfil);
    }

    @Override
    public String toString() {
        return "CredentialRequest{" +
                "customerId='" + customerId + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", perfil='" + perfil + '\'' +
                '}';
    }
}