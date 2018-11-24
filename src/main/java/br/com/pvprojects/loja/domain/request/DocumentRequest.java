package br.com.pvprojects.loja.domain.request;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class DocumentRequest implements Serializable {

    private String customerId;

    @NotBlank(message = "O tipo do documento não pode ser vazio ou nulo")
    private String type;

    @Length(min = 5, message = "O número não pode ter menos de 5 caracteres")
    @NotBlank(message = "O número do documento não pode ser vazio ou nulo")
    private String number;

    public DocumentRequest() {
    }

    public DocumentRequest(String customerId, String type, String number) {
        this.customerId = customerId;
        this.type = type;
        this.number = number;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentRequest that = (DocumentRequest) o;
        return Objects.equals(customerId, that.customerId) &&
                Objects.equals(type, that.type) &&
                Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, type, number);
    }

    @Override
    public String toString() {
        return "DocumentRequest{" +
                "customerId='" + customerId + '\'' +
                ", type='" + type + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}