package br.com.pvprojects.loja.domain.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.pvprojects.loja.util.dateHelper.LocalDateDeserializer;
import br.com.pvprojects.loja.util.dateHelper.LocalDateSerializer;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HistoricResponse implements Serializable {

    private String customerId;

    private String item;

    private String price;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime created;

    public HistoricResponse() {
    }

    public HistoricResponse(String customerId, String item, String price, LocalDateTime created) {
        this.customerId = customerId;
        this.item = item;
        this.price = price;
        this.created = created;
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
        HistoricResponse that = (HistoricResponse) o;
        return Objects.equals(customerId, that.customerId) &&
                Objects.equals(item, that.item) &&
                Objects.equals(price, that.price) &&
                Objects.equals(created, that.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, item, price, created);
    }

    @Override
    public String toString() {
        return "HistoricResponse{" +
                "customerId='" + customerId + '\'' +
                ", item='" + item + '\'' +
                ", price='" + price + '\'' +
                ", created=" + created +
                '}';
    }
}