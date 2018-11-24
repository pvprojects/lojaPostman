package br.com.pvprojects.loja.domain.form;

import java.util.Objects;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CredentialResetPassword {

    @NotBlank(message = "O password não pode ser vazio ou nulo")
    private String password;

    public CredentialResetPassword() {
    }

    public CredentialResetPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CredentialResetPassword that = (CredentialResetPassword) o;
        return Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password);
    }

    @Override
    public String toString() {
        return "CredentialResetPassword{" +
                "password='" + password + '\'' +
                '}';
    }
}