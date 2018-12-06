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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.pvprojects.loja.util.dateHelper.LocalDateDeserializer;
import br.com.pvprojects.loja.util.dateHelper.LocalDateSerializer;
import br.com.pvprojects.loja.util.enums.Type;

@Entity
@Table(name = "DOCUMENT")
@SequenceGenerator(name = "DOCUMENT_SEQ", sequenceName = "DOCUMENT_SEQ", allocationSize = 1)
public class Document {

    public Document() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOCUMENT_SEQ")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CUSTOMERID")
    private String customerId;

    @Column(name = "DOCUMENT_TYPE")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "DOCUMENT_NUMBER")
    private String number;

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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
        Document document = (Document) o;
        return Objects.equals(id, document.id) &&
                Objects.equals(customerId, document.customerId) &&
                type == document.type &&
                Objects.equals(number, document.number) &&
                Objects.equals(created, document.created) &&
                Objects.equals(updated, document.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, type, number, created, updated);
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", customerId='" + customerId + '\'' +
                ", type=" + type +
                ", number='" + number + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }

    @PrePersist
    public void prePersist() {
        if (created == null)
            this.created = LocalDateTime.now();
    }
}