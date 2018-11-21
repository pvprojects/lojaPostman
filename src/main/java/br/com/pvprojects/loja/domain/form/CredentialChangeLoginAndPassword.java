package br.com.pvprojects.loja.domain.form;

import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CredentialChangeLoginAndPassword {

    @NotEmpty(message = "O login não pode ser vazio")
    @NotNull(message = "O login não pode ser nulo")
    private String newLogin;

    @NotEmpty(message = "O password não pode ser vazio")
    @NotNull(message = "O password não pode ser nulo")
    private String newPassword;

    public CredentialChangeLoginAndPassword() {
    }

    public CredentialChangeLoginAndPassword(String newLogin, String newPassword) {
        this.newLogin = newLogin;
        this.newPassword = newPassword;
    }

    public String getNewLogin() {
        return newLogin;
    }

    public void setNewLogin(String newLogin) {
        this.newLogin = newLogin;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CredentialChangeLoginAndPassword that = (CredentialChangeLoginAndPassword) o;
        return Objects.equals(newLogin, that.newLogin) &&
                Objects.equals(newPassword, that.newPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newLogin, newPassword);
    }

    @Override
    public String toString() {
        return "CredentialChangeLoginAndPassword{" +
                "newLogin='" + newLogin + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
