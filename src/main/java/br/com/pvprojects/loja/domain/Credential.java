package br.com.pvprojects.loja.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.pvprojects.loja.util.dateHelper.LocalDateDeserializer;
import br.com.pvprojects.loja.util.dateHelper.LocalDateSerializer;

@Entity
@Table(name = "CREDENTIAL")
@SequenceGenerator(name = "CREDENTIAL_SEQ", sequenceName = "CREDENTIAL_SEQ", allocationSize = 1)
public class Credential {

    public Credential() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CREDENTIAL_SEQ")
    @Column(name = "CODIGO")
    private Integer codigo;

    @Column(name = "CUSTOMERID")
    private String customerId;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "PASSWORD")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "credential_permissao", joinColumns = @JoinColumn(name = "codigo_credential")
            , inverseJoinColumns = @JoinColumn(name = "codigo_permissao"))
    private List<Permissao> permissoes;

    @Column(name = "CREATED_AT")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime created;

    @Column(name = "UPDATED_AT")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime updated;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
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

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
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

    @PrePersist
    public void prePersist() {

        if (created == null)
            this.created = LocalDateTime.now();
    }
}