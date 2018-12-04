package br.com.pvprojects.loja.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.pvprojects.loja.util.dateHelper.LocalDateDeserializer;
import br.com.pvprojects.loja.util.dateHelper.LocalDateSerializer;

@Entity
@Table(name = "HISTORIC")
@SequenceGenerator(name = "HISTORIC_SEQ", sequenceName = "HISTORIC_SEQ", allocationSize = 1)
public class Historic {

    public Historic() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HISTORIC_SEQ")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "CUSTOMERID")
    private String customerId;

    @Column(name = "ITEM")
    private String item;

    @Column(name = "PRICE")
    private String price;

    @Column(name = "CREATED_AT")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime created;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Historic historic = (Historic) o;
        return Objects.equals(id, historic.id) &&
                Objects.equals(login, historic.login) &&
                Objects.equals(customerId, historic.customerId) &&
                Objects.equals(item, historic.item) &&
                Objects.equals(price, historic.price) &&
                Objects.equals(created, historic.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, customerId, item, price, created);
    }

    @Override
    public String toString() {
        return "Historic{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", customerId='" + customerId + '\'' +
                ", item='" + item + '\'' +
                ", price='" + price + '\'' +
                ", created=" + created +
                '}';
    }

    @PrePersist
    public void prePersist() {
        if (created == null)
            this.created = LocalDateTime.now();
    }
}