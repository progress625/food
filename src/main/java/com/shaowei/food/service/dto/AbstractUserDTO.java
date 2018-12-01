package com.shaowei.food.service.dto;

import java.io.Serializable;
import java.util.Objects;
import com.shaowei.food.domain.enumeration.Role;

/**
 * A DTO for the AbstractUser entity.
 */
public class AbstractUserDTO implements Serializable {

    private String id;

    private String firstname;

    private String lastname;

    private String gender;

    private String position;

    private String telephone;

    private String mobilephone;

    private String email;

    private String wechat;

    private String skype;

    private String companyName;

    private Role role;

    private String addressId;

    private String buyerId;

    private String providerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbstractUserDTO abstractUserDTO = (AbstractUserDTO) o;
        if (abstractUserDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), abstractUserDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AbstractUserDTO{" +
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
            ", address=" + getAddressId() +
            ", buyer=" + getBuyerId() +
            ", provider=" + getProviderId() +
            "}";
    }
}
