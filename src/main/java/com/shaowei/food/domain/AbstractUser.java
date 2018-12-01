package com.shaowei.food.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.Objects;

import com.shaowei.food.domain.enumeration.Role;

/**
 * A AbstractUser.
 */
@Document(collection = "abstract_user")
public class AbstractUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("firstname")
    private String firstname;

    @Field("lastname")
    private String lastname;

    @Field("gender")
    private String gender;

    @Field("position")
    private String position;

    @Field("telephone")
    private String telephone;

    @Field("mobilephone")
    private String mobilephone;

    @Field("email")
    private String email;

    @Field("wechat")
    private String wechat;

    @Field("skype")
    private String skype;

    @Field("company_name")
    private String companyName;

    @Field("role")
    private Role role;

    @DBRef
    @Field("address")
    private Address address;

    @DBRef
    @Field("buyer")
    @JsonIgnoreProperties("users")
    private Buyer buyer;

    @DBRef
    @Field("provider")
    @JsonIgnoreProperties("users")
    private Provider provider;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public AbstractUser firstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public AbstractUser lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public AbstractUser gender(String gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPosition() {
        return position;
    }

    public AbstractUser position(String position) {
        this.position = position;
        return this;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTelephone() {
        return telephone;
    }

    public AbstractUser telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public AbstractUser mobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
        return this;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getEmail() {
        return email;
    }

    public AbstractUser email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWechat() {
        return wechat;
    }

    public AbstractUser wechat(String wechat) {
        this.wechat = wechat;
        return this;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getSkype() {
        return skype;
    }

    public AbstractUser skype(String skype) {
        this.skype = skype;
        return this;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getCompanyName() {
        return companyName;
    }

    public AbstractUser companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Role getRole() {
        return role;
    }

    public AbstractUser role(Role role) {
        this.role = role;
        return this;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Address getAddress() {
        return address;
    }

    public AbstractUser address(Address address) {
        this.address = address;
        return this;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public AbstractUser buyer(Buyer buyer) {
        this.buyer = buyer;
        return this;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Provider getProvider() {
        return provider;
    }

    public AbstractUser provider(Provider provider) {
        this.provider = provider;
        return this;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractUser abstractUser = (AbstractUser) o;
        if (abstractUser.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), abstractUser.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AbstractUser{" +
            "id=" + getId() +
            ", firstname='" + getFirstname() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", gender='" + getGender() + "'" +
            ", position='" + getPosition() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", mobilephone='" + getMobilephone() + "'" +
            ", email='" + getEmail() + "'" +
            ", wechat='" + getWechat() + "'" +
            ", skype='" + getSkype() + "'" +
            ", companyName='" + getCompanyName() + "'" +
            ", role='" + getRole() + "'" +
            "}";
    }
}
