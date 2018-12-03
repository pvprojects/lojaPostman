package br.com.pvprojects.loja.domain.form;

import java.util.Objects;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PasswordRequest {

    @NotBlank(message = "O login não pode ser vazio ou nulo")
    private String login;

    public PasswordRequest() {
    }

    public PasswordRequest(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PasswordRequest that = (PasswordRequest) o;
        return Objects.equals(login, that.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

    @Override
    public String toString() {
        return "PasswordRequest{" +
                "login='" + login + '\'' +
                '}';
    }
}