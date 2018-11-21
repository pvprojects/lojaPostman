package br.com.pvprojects.loja.domain.data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.pvprojects.loja.util.enums.Gender;
import br.com.pvprojects.loja.util.enums.PersonType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerData implements Serializable {

    private String id;
    private String fullName;
    private PersonType personType;
    private String nickName;
    private String birthDate;
    private String country;
    private LocalDateTime created;
    private Gender gender;
    private String motherName;
    private String fatherName;
    private String login;
    private Integer numberOfChildren;
    private String parentId;

    public CustomerData() {
    }

    public CustomerData(String id) {
        this.id = id;
    }

    public CustomerData(String id, String fullName, PersonType personType, String nickName, String birthDate,
            String country, LocalDateTime created, Gender gender, String motherName, String fatherName,
            String login, Integer numberOfChildren, String parentId) {
        this.id = id;
        this.fullName = fullName;
        this.personType = personType;
        this.nickName = nickName;
        this.birthDate = birthDate;
        this.country = country;
        this.created = created;
        this.gender = gender;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.login = login;
        this.numberOfChildren = numberOfChildren;
        this.parentId = parentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public void setPersonType(PersonType personType) {
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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
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
        CustomerData that = (CustomerData) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(fullName, that.fullName) &&
                personType == that.personType &&
                Objects.equals(nickName, that.nickName) &&
                Objects.equals(birthDate, that.birthDate) &&
                Objects.equals(country, that.country) &&
                Objects.equals(created, that.created) &&
                gender == that.gender &&
                Objects.equals(motherName, that.motherName) &&
                Objects.equals(fatherName, that.fatherName) &&
                Objects.equals(login, that.login) &&
                Objects.equals(numberOfChildren, that.numberOfChildren) &&
                Objects.equals(parentId, that.parentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, personType, nickName, birthDate, country, created, gender, motherName,
                fatherName,
                login, numberOfChildren, parentId);
    }

    @Override
    public String toString() {
        return "CustomerData{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", personType=" + personType +
                ", nickName='" + nickName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", country='" + country + '\'' +
                ", created=" + created +
                ", gender=" + gender +
                ", motherName='" + motherName + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", login='" + login + '\'' +
                ", numberOfChildren=" + numberOfChildren +
                ", parentId='" + parentId + '\'' +
                '}';
    }
}