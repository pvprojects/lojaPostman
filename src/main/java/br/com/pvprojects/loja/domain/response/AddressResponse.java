package br.com.pvprojects.loja.domain.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.pvprojects.loja.util.LocalDateDeserializer;
import br.com.pvprojects.loja.util.LocalDateSerializer;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressResponse implements Serializable {

    private Integer id;
    private String name;
    private String zipCode;
    private String number;
    private String complement;
    private String street;
    private String district;
    private String city;
    private String state;
    private String country;
    private String customerId;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime created;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime updated;

    public AddressResponse() {
    }

    public AddressResponse(Integer id, String name, String zipCode, String number, String complement, String street,
            String district, String city, String state, String country, String customerId, LocalDateTime created,
            LocalDateTime updated) {
        this.id = id;
        this.name = name;
        this.zipCode = zipCode;
        this.number = number;
        this.complement = complement;
        this.street = street;
        this.district = district;
        this.city = city;
        this.state = state;
        this.country = country;
        this.customerId = customerId;
        this.created = created;
        this.updated = updated;
    }

    public AddressResponse(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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
        AddressResponse that = (AddressResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(zipCode, that.zipCode) &&
                Objects.equals(number, that.number) &&
                Objects.equals(complement, that.complement) &&
                Objects.equals(street, that.street) &&
                Objects.equals(district, that.district) &&
                Objects.equals(city, that.city) &&
                Objects.equals(state, that.state) &&
                Objects.equals(country, that.country) &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(created, that.created) &&
                Objects.equals(updated, that.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, zipCode, number, complement, street, district, city, state, country, customerId,
                created, updated);
    }

    @Override
    public String toString() {
        return "AddressResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", number='" + number + '\'' +
                ", complement='" + complement + '\'' +
                ", street='" + street + '\'' +
                ", district='" + district + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", customerId='" + customerId + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}