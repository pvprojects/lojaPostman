package br.com.pvprojects.loja.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "CREDIT_CARD")
@SequenceGenerator(name = "CREDIT_CARD_SEQ", sequenceName = "CREDIT_CARD_SEQ", allocationSize = 1)
public class CreditCard {

    public CreditCard() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CREDIT_CARD_SEQ")
    @Column(name = "ID")
    private Integer id;

    @NotEmpty(message = "O customerId não pode ser vazio")
    @NotNull(message = "O customerId não pode ser nulo")
    @Column(name = "CUSTOMERID")
    private String customerId;

    @NotEmpty(message = "O number não pode ser vazio")
    @NotNull(message = "O number não pode ser nulo")
    @Column(name = "NUMBER")
    @Pattern(regexp = "[0-9]+", message = "Apenas números [0-9]")
    private String number;

    @NotEmpty(message = "O brand não pode ser vazio")
    @NotNull(message = "O brand não pode ser nulo")
    @Column(name = "BRAND")
    private String brand;

    @NotEmpty(message = "O expirationMonth não pode ser vazio")
    @NotNull(message = "O expirationMonth não pode ser nulo")
    @Pattern(regexp = "[0-9]{2}", message = "Apenas 2 números")
    @Column(name = "EXPIRATION_MONTH")
    private String expirationMonth;

    @NotEmpty(message = "O expirationYear não pode ser vazio")
    @NotNull(message = "O expirationYear não pode ser nulo")
    @Pattern(regexp = "[0-9]{2,4}", message = "Apenas números")
    @Column(name = "EXPIRATION_YEAR")
    private String expirationYear;

    @NotEmpty(message = "O holder não pode ser vazio")
    @NotNull(message = "O holder não pode ser nulo")
    @Column(name = "HOLDER")
    private String holder;

    @NotEmpty(message = "O cvv não pode ser vazio")
    @NotNull(message = "O cvv não pode ser nulo")
    @Pattern(regexp = "[0-9]{3}", message = "Apenas números [0-9]")
    @Column(name = "CVV")
    private String cvv;

    @Column(name = "MAIN")
    private Boolean main;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public String getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(String expirationYear) {
        this.expirationYear = expirationYear;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Boolean getMain() {
        return main;
    }

    public void setMain(Boolean main) {
        this.main = main;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(number, that.number) &&
                Objects.equals(brand, that.brand) &&
                Objects.equals(expirationMonth, that.expirationMonth) &&
                Objects.equals(expirationYear, that.expirationYear) &&
                Objects.equals(holder, that.holder) &&
                Objects.equals(cvv, that.cvv) &&
                Objects.equals(main, that.main);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, number, brand, expirationMonth, expirationYear, holder, cvv, main);
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + id +
                ", customerId='" + customerId + '\'' +
                ", number='" + number + '\'' +
                ", brand='" + brand + '\'' +
                ", expirationMonth='" + expirationMonth + '\'' +
                ", expirationYear='" + expirationYear + '\'' +
                ", holder='" + holder + '\'' +
                ", cvv='" + cvv + '\'' +
                ", main=" + main +
                '}';
    }
}