package br.com.pvprojects.loja.domain.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class AddressRequest {

    @NotBlank(message = "O nome do endereço não pode ser vazio ou nulo")
    private String name;

    @Length(min = 8, message = "O zipCode não pode ter menos de 8 caracteres")
    @NotBlank(message = "O cep não pode ser vazio ou nulo")
    @Pattern(regexp = "\\d{3,10}", message = "O cep é inválido")
    private String zipCode;

    @NotBlank(message = "O número não pode ser vazio ou nulo")
    @Pattern(regexp = "\\d*", message = "Número inválido.")
    private String number;

    @NotBlank(message = "O logradouro não pode ser vazio ou nulo")
    @Pattern(regexp = "\\p{L}+((\\s\\p{L}+)*)", message = "Password inválido. O password deve conter 6 dígitos.")
    private String street;

    @NotBlank(message = "O bairro não pode ser vazio ou nulo")
    @Pattern(regexp = "[a-zA-Z\\u00C0-\\u00FF0-9 ]*", message = "Bairro inválido.")
    private String district;

    @NotBlank(message = "O nome da cidade não pode ser vazio ou nulo")
    @Pattern(regexp = "\\p{L}+((\\s\\p{L}+)*)", message = "Cidade inválida.")
    private String city;

    @NotBlank(message = "O estado não pode ser vazio ou nulo")
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
