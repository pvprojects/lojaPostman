package br.com.pvprojects.loja.domain.form;

import java.util.Objects;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.pvprojects.loja.util.enums.Type;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentChange {

    @NotBlank(message = "O número não pode ser vazio ou nulo")
    private String number;

    @NotBlank(message = "O  tipo do documento não pode ser vazio ou nulo")
    private Type type;

    public DocumentChange() {
    }

    public DocumentChange(String number, Type type) {
        this.number = number;
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentChange that = (DocumentChange) o;
        return Objects.equals(number, that.number) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, type);
    }

    @Override
    public String toString() {
        return "DocumentChange{" +
                "number='" + number + '\'' +
                ", type=" + type +
                '}';
    }
}