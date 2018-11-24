package br.com.pvprojects.loja.domain.request;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class CredentialRequest implements Serializable {

    @NotBlank(message = "O customerId não pode ser vazio ou nulo")
    private String customerId;

    @NotBlank(message = "O login não pode ser vazio ou nulo")
    @Email(message = "O login informado é inválido.")
    private String login;

    @NotBlank(message = "O password não pode ser vazio ou nulo")
    @Pattern(regexp = "\\d{6}$", message = "Password inválido. O password deve conter 6 dígitos.")
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
