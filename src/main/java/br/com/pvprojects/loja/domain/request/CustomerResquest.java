package br.com.pvprojects.loja.domain.request;

import static br.com.pvprojects.loja.util.ConventionsHelper.CAMPO_DATA_ANIVERSARIO_NAO_INFORMADO;
import static br.com.pvprojects.loja.util.ConventionsHelper.CAMPO_EMAIL_INVALIDO;
import static br.com.pvprojects.loja.util.ConventionsHelper.CAMPO_EMAIL_NAO_INFORMADO;
import static br.com.pvprojects.loja.util.ConventionsHelper.CAMPO_NICK_NAME;
import static br.com.pvprojects.loja.util.ConventionsHelper.CAMPO_NICK_NAME_SIZE;
import static br.com.pvprojects.loja.util.ConventionsHelper.CAMPO_NOME;
import static br.com.pvprojects.loja.util.ConventionsHelper.CAMPO_NOME_MAE_NAO_INFORMADO;
import static br.com.pvprojects.loja.util.ConventionsHelper.CAMPO_NOME_SIZE;
import static br.com.pvprojects.loja.util.ConventionsHelper.EMPTY_NULL_PASSWORD;
import static br.com.pvprojects.loja.util.ConventionsHelper.INVALID_PASSWORD_SIZE;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class CustomerResquest implements Serializable {

    @NotBlank(message = CAMPO_NOME)
    @Length(min = 3, message = CAMPO_NOME_SIZE)
    private String fullName;

    private String personType;

    @NotBlank(message = CAMPO_NICK_NAME)
    @Length(min = 3, message = CAMPO_NICK_NAME_SIZE)
    private String nickName;

    @NotBlank(message = CAMPO_DATA_ANIVERSARIO_NAO_INFORMADO)
    private String birthDate;

    private String country;

    private String gender;

    @NotBlank(message = CAMPO_NOME_MAE_NAO_INFORMADO)
    private String motherName;

    private String fatherName;

    @NotBlank(message = CAMPO_EMAIL_NAO_INFORMADO)
    @Email(message = CAMPO_EMAIL_INVALIDO)
    private String login;

    @NotBlank(message = EMPTY_NULL_PASSWORD)
    @Pattern(regexp = "\\d{6}$", message = INVALID_PASSWORD_SIZE)
    private String password;

    private Integer numberOfChildren;

    private String parentId;

    public CustomerResquest() {
    }

    public CustomerResquest(String fullName, String personType, String nickName, String birthDate, String country,
            String gender, String motherName, String fatherName, String login, String password,
            Integer numberOfChildren, String parentId) {
        this.fullName = fullName;
        this.personType = personType;
        this.nickName = nickName;
        this.birthDate = birthDate;
        this.country = country;
        this.gender = gender;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.login = login;
        this.password = password;
        this.numberOfChildren = numberOfChildren;
        this.parentId = parentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(Integer numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerResquest that = (CustomerResquest) o;
        return Objects.equals(fullName, that.fullName) &&
                Objects.equals(personType, that.personType) &&
                Objects.equals(nickName, that.nickName) &&
                Objects.equals(birthDate, that.birthDate) &&
                Objects.equals(country, that.country) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(motherName, that.motherName) &&
                Objects.equals(fatherName, that.fatherName) &&
                Objects.equals(login, that.login) &&
                Objects.equals(password, that.password) &&
                Objects.equals(numberOfChildren, that.numberOfChildren) &&
                Objects.equals(parentId, that.parentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, personType, nickName, birthDate, country, gender, motherName, fatherName, login,
                password, numberOfChildren, parentId);
    }

    @Override
    public String toString() {
        return "CustomerResquest{" +
                "fullName='" + fullName + '\'' +
                ", personType='" + personType + '\'' +
                ", nickName='" + nickName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", country='" + country + '\'' +
                ", gender='" + gender + '\'' +
                ", motherName='" + motherName + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", numberOfChildren=" + numberOfChildren +
                ", parentId='" + parentId + '\'' +
                '}';
    }
}
