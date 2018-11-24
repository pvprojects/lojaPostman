package br.com.pvprojects.loja.domain.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.pvprojects.loja.util.LocalDateDeserializer;
import br.com.pvprojects.loja.util.LocalDateSerializer;
import br.com.pvprojects.loja.util.enums.Perfil;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CredentialResponse implements Serializable {

    private Integer id;
    private String customerId;
    private String login;
    private String password;
    private Perfil perfil;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime created;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime updated;

    public CredentialResponse() {
    }

    public CredentialResponse(Integer id, String customerId, String login, String password, Perfil perfil,
            LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.customerId = customerId;
        this.login = login;
        this.password = password;
        this.perfil = perfil;
        this.created = created;
        this.updated = updated;
    }

    public CredentialResponse(Integer id, String customerId, String login, Perfil perfil, LocalDateTime created,
            LocalDateTime updated) {
        this.id = id;
        this.customerId = customerId;
        this.login = login;
        this.perfil = perfil;
        this.created = created;
        this.updated = updated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CredentialResponse that = (CredentialResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(login, that.login) &&
                Objects.equals(password, that.password) &&
                perfil == that.perfil &&
                Objects.equals(created, that.created) &&
                Objects.equals(updated, that.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, login, password, perfil, created, updated);
    }

    @Override
    public String toString() {
        return "CredentialResponse{" +
                "id=" + id +
                ", customerId='" + customerId + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", perfil=" + perfil +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}