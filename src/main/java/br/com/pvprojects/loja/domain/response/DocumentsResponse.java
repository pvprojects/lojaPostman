package br.com.pvprojects.loja.domain.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.pvprojects.loja.util.LocalDateDeserializer;
import br.com.pvprojects.loja.util.LocalDateSerializer;
import br.com.pvprojects.loja.util.enums.Type;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentsResponse implements Serializable {

    private Integer id;
    private String customerId;
    private Type type;
    private String number;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime created;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime updated;

    public DocumentsResponse() {
    }

    public DocumentsResponse(Integer id) {
        this.id = id;
    }

    public DocumentsResponse(Integer id, String customerId, Type type, String number, LocalDateTime created,
            LocalDateTime updated) {
        this.id = id;
        this.customerId = customerId;
        this.type = type;
        this.number = number;
        this.created = created;
        this.updated = updated;
    }

    public DocumentsResponse(Type type, String number) {
        this.type = type;
        this.number = number;
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
        DocumentsResponse that = (DocumentsResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(customerId, that.customerId) &&
                type == that.type &&
                Objects.equals(number, that.number) &&
                Objects.equals(created, that.created) &&
                Objects.equals(updated, that.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, type, number, created, updated);
    }

    @Override
    public String toString() {
        return "DocumentsResponse{" +
                "id=" + id +
                ", customerId='" + customerId + '\'' +
                ", type=" + type +
                ", number='" + number + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}