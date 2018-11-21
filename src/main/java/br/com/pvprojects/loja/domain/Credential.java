package br.com.pvprojects.loja.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.pvprojects.loja.util.LocalDateDeserializer;
import br.com.pvprojects.loja.util.LocalDateSerializer;
import br.com.pvprojects.loja.util.enums.Perfil;

@Entity
@Table(name = "CREDENTIAL")
@SequenceGenerator(name = "CREDENTIAL_SEQ", sequenceName = "CREDENTIAL_SEQ", allocationSize = 1)
public class Credential {

    public Credential() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CREDENTIAL_SEQ")
    @Column(name = "ID")
    private Integer id;

    @NotEmpty(message = "O customerId não pode ser vazio")
    @NotNull(message = "O customerId não pode ser nulo")
    @Column(name = "CUSTOMERID")
    private String customerId;

    @NotEmpty(message = "O login não pode ser vazio")
    @NotNull(message = "O login não pode ser nulo")
    @Column(name = "LOGIN")
    private String login;

    @NotEmpty(message = "O password não pode ser vazio")
    @NotNull(message = "O password não pode ser nulo")
    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "PERFIL")
    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    @Column(name = "CREATED_AT")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime created;

    @Column(name = "UPDATED_AT")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime updated;

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
        Credential that = (Credential) o;
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
        return "Credential{" +
                "id=" + id +
                ", customerId='" + customerId + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", perfil=" + perfil +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }

    @PrePersist
    public void prePersist() {
        if (created == null) {
            this.created = LocalDateTime.now();
        }
        updated = LocalDateTime.now();
    }
}