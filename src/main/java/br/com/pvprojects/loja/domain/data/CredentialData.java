package br.com.pvprojects.loja.domain.data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CredentialData implements Serializable {

    private Integer id;
    private String customerId;
    private String login;
    private String perfil;
    private LocalDateTime created;
    private LocalDateTime updated;

    public CredentialData() {
    }

    public CredentialData(Integer id, String customerId, String login, String perfil, LocalDateTime created,
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

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
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
        CredentialData that = (CredentialData) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(login, that.login) &&
                Objects.equals(perfil, that.perfil) &&
                Objects.equals(created, that.created) &&
                Objects.equals(updated, that.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, login, perfil, created, updated);
    }

    @Override
    public String toString() {
        return "CredentialData{" +
                "id=" + id +
                ", customerId='" + customerId + '\'' +
                ", login='" + login + '\'' +
                ", perfil='" + perfil + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}