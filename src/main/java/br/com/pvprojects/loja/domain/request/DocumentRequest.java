package br.com.pvprojects.loja.domain.request;

import static br.com.pvprojects.loja.util.ConventionsHelper.CAMPO_NUMERO_DOCUMENTO;
import static br.com.pvprojects.loja.util.ConventionsHelper.CAMPO_NUMERO_DOCUMENTO_SIZE;
import static br.com.pvprojects.loja.util.ConventionsHelper.CAMPO_TIPO_DOCUMENTO;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class DocumentRequest implements Serializable {

    private String customerId;

    @NotBlank(message = CAMPO_TIPO_DOCUMENTO)
    private String type;

    @Length(min = 5, message = CAMPO_NUMERO_DOCUMENTO_SIZE)
    @NotBlank(message = CAMPO_NUMERO_DOCUMENTO)
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