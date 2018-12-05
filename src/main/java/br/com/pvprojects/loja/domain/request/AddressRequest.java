package br.com.pvprojects.loja.domain.request;

import static br.com.pvprojects.loja.util.ConventionsHelper.CAMPO_BAIRRO_NAO_INFORMADO;
import static br.com.pvprojects.loja.util.ConventionsHelper.CAMPO_BAIRRO_NAO_INVALIDO;
import static br.com.pvprojects.loja.util.ConventionsHelper.CAMPO_CEP_INVALIDO;
import static br.com.pvprojects.loja.util.ConventionsHelper.CAMPO_CEP_NAO_INFORMADO;
import static br.com.pvprojects.loja.util.ConventionsHelper.CAMPO_CEP_SIZE;
import static br.com.pvprojects.loja.util.ConventionsHelper.CAMPO_CIDADE_INVALIDO;
import static br.com.pvprojects.loja.util.ConventionsHelper.CAMPO_CIDADE_NAO_INFORMADO;
import static br.com.pvprojects.loja.util.ConventionsHelper.CAMPO_ESTADO_INVALIDO;
import static br.com.pvprojects.loja.util.ConventionsHelper.CAMPO_ESTADO_NAO_INFORMADO;
import static br.com.pvprojects.loja.util.ConventionsHelper.CAMPO_LOGRADOURO_INVALIDO;
import static br.com.pvprojects.loja.util.ConventionsHelper.CAMPO_LOGRADOURO_NAO_INFORMADO;
import static br.com.pvprojects.loja.util.ConventionsHelper.CAMPO_NOME_NAO_INFORMADO;
import static br.com.pvprojects.loja.util.ConventionsHelper.CAMPO_NUMERO_INVALIDO;
import static br.com.pvprojects.loja.util.ConventionsHelper.CAMPO_NUMERO_NAO_INFORMADO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class AddressRequest {

    @NotBlank(message = CAMPO_NOME_NAO_INFORMADO)
    private String name;

    @Length(min = 8, message = CAMPO_CEP_SIZE)
    @NotBlank(message = CAMPO_CEP_NAO_INFORMADO)
    @Pattern(regexp = "\\d{3,10}", message = CAMPO_CEP_INVALIDO)
    private String zipCode;

    @NotBlank(message = CAMPO_NUMERO_NAO_INFORMADO)
    @Pattern(regexp = "\\d*", message = CAMPO_NUMERO_INVALIDO)
    private String number;

    @NotBlank(message = CAMPO_LOGRADOURO_NAO_INFORMADO)
    @Pattern(regexp = "\\p{L}+((\\s\\p{L}+)*)", message = CAMPO_LOGRADOURO_INVALIDO)
    private String street;

    @NotBlank(message = CAMPO_BAIRRO_NAO_INFORMADO)
    @Pattern(regexp = "[a-zA-Z\\u00C0-\\u00FF0-9 ]*", message = CAMPO_BAIRRO_NAO_INVALIDO)
    private String district;

    @NotBlank(message = CAMPO_CIDADE_NAO_INFORMADO)
    @Pattern(regexp = "\\p{L}+((\\s\\p{L}+)*)", message = CAMPO_CIDADE_INVALIDO)
    private String city;

    @NotBlank(message = CAMPO_ESTADO_NAO_INFORMADO)
    @Pattern(regexp = "\\p{L}+((\\s\\p{L}+)*)", message = CAMPO_ESTADO_INVALIDO)
    private String state;

    private String complement;
    private String country;
    private String customerId;

    public AddressRequest() {
    }

    public AddressRequest(String name, String zipCode, String number, String complement, String street,
            String district, String city, String state, String country, String customerId) {
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
}