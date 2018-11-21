package br.com.pvprojects.loja.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.pvprojects.loja.util.LocalDateDeserializer;
import br.com.pvprojects.loja.util.LocalDateSerializer;
import br.com.pvprojects.loja.util.enums.Gender;
import br.com.pvprojects.loja.util.enums.PersonType;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

    public Customer() {
    }

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    @Column(name = "ID")
    private String id;

    @NotEmpty(message = "O nome não pode ser vazio")
    @NotNull(message = "O nome não pode ser nulo")
    @Length(min = 3, message = "O nome não pode ter menos de 3 caracteres")
    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "PERSON_TYPE")
    @Enumerated(EnumType.STRING)
    private PersonType personType;

    @NotEmpty(message = "O nickName não pode ser vazio")
    @NotNull(message = "O nickName não pode ser nulo")
    @Length(min = 3, message = "O nickName não pode ter menos de 3 caracteres")
    @Column(name = "NICKNAME")
    private String nickName;

    @NotEmpty(message = "A data de aniversário não pode ser vazia")
    @NotNull(message = "A data de aniversário não pode ser nula")
    @Column(name = "BIRTH_DATE")
    private String birthDate;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "CREATED_AT")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime created;

    @Column(name = "UPDATED_AT")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime updated;

    @Column(name = "GENDER")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotEmpty(message = "A nome da mãe não pode ser vazio")
    @NotNull(message = "A nome da mãe não pode ser nulo")
    @Column(name = "MOTHER_NAME")
    private String motherName;

    @Column(name = "FATHER_NAME")
    private String fatherName;

    @NotEmpty(message = "A login não pode ser vazio")
    @NotNull(message = "A login não pode ser nulo")
    @Column(name = "LOGIN", unique = true)
    private String login;

    @NotEmpty(message = "O password não pode ser vazio")
    @NotNull(message = "O password não pode ser nulo")
    @Column(name = "PASSWORD")
    private String password;


    @Column(name = "NUMBER_OF_CHILDREN")
    private Integer numberOfChildren;

    @Column(name = "PARENT_ID")
    private String parentId;

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

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
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
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(fullName, customer.fullName) &&
                personType == customer.personType &&
                Objects.equals(nickName, customer.nickName) &&
                Objects.equals(birthDate, customer.birthDate) &&
                Objects.equals(country, customer.country) &&
                Objects.equals(created, customer.created) &&
                Objects.equals(updated, customer.updated) &&
                gender == customer.gender &&
                Objects.equals(motherName, customer.motherName) &&
                Objects.equals(fatherName, customer.fatherName) &&
                Objects.equals(login, customer.login) &&
                Objects.equals(password, customer.password) &&
                Objects.equals(numberOfChildren, customer.numberOfChildren) &&
                Objects.equals(parentId, customer.parentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, personType, nickName, birthDate, country, created, updated, gender,
                motherName,
                fatherName, login, password, numberOfChildren, parentId);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", personType=" + personType +
                ", nickName='" + nickName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", country='" + country + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", gender=" + gender +
                ", motherName='" + motherName + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", numberOfChildren=" + numberOfChildren +
                ", parentId='" + parentId + '\'' +
                '}';
    }

    @PrePersist
    public void prePersist() {
        if (created == null) {
            this.created = LocalDateTime.now();
        }
        updated = LocalDateTime.now();
    }
}