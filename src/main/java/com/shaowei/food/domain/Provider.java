package com.shaowei.food.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.shaowei.food.domain.enumeration.Membership;

import com.shaowei.food.domain.enumeration.Status;

/**
 * A Provider.
 */
@Document(collection = "provider")
public class Provider implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("company_number")
    private String companyNumber;

    @Field("company_name")
    private String companyName;

    @Field("approval_number")
    private String approvalNumber;

    @Field("telephone")
    private String telephone;

    @Field("website")
    private String website;

    @Field("registration_number")
    private String registrationNumber;

    @Field("v_at_number")
    private String vATNumber;

    @Field("container_amount_estimated")
    private Integer containerAmountEstimated;

    @Field("product_name")
    private String productName;

    @Field("membership")
    private Membership membership;

    @Field("status")
    private Status status;

    @DBRef
    @Field("address")
    private Address address;

    @DBRef
    @Field("user")
    private Set<AbstractUser> users = new HashSet<>();
    @DBRef
    @Field("photo")
    private Set<Photo> photos = new HashSet<>();
    @DBRef
    @Field("loading")
    private Set<Loading> loadings = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public Provider companyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
        return this;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Provider companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public Provider approvalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
        return this;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public String getTelephone() {
        return telephone;
    }

    public Provider telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getWebsite() {
        return website;
    }

    public Provider website(String website) {
        this.website = website;
        return this;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public Provider registrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
        return this;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getvATNumber() {
        return vATNumber;
    }

    public Provider vATNumber(String vATNumber) {
        this.vATNumber = vATNumber;
        return this;
    }

    public void setvATNumber(String vATNumber) {
        this.vATNumber = vATNumber;
    }

    public Integer getContainerAmountEstimated() {
        return containerAmountEstimated;
    }

    public Provider containerAmountEstimated(Integer containerAmountEstimated) {
        this.containerAmountEstimated = containerAmountEstimated;
        return this;
    }

    public void setContainerAmountEstimated(Integer containerAmountEstimated) {
        this.containerAmountEstimated = containerAmountEstimated;
    }

    public String getProductName() {
        return productName;
    }

    public Provider productName(String productName) {
        this.productName = productName;
        return this;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Membership getMembership() {
        return membership;
    }

    public Provider membership(Membership membership) {
        this.membership = membership;
        return this;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public Status getStatus() {
        return status;
    }

    public Provider status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Address getAddress() {
        return address;
    }

    public Provider address(Address address) {
        this.address = address;
        return this;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<AbstractUser> getUsers() {
        return users;
    }

    public Provider users(Set<AbstractUser> abstractUsers) {
        this.users = abstractUsers;
        return this;
    }

    public Provider addUser(AbstractUser abstractUser) {
        this.users.add(abstractUser);
        abstractUser.setProvider(this);
        return this;
    }

    public Provider removeUser(AbstractUser abstractUser) {
        this.users.remove(abstractUser);
        abstractUser.setProvider(null);
        return this;
    }

    public void setUsers(Set<AbstractUser> abstractUsers) {
        this.users = abstractUsers;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public Provider photos(Set<Photo> photos) {
        this.photos = photos;
        return this;
    }

    public Provider addPhoto(Photo photo) {
        this.photos.add(photo);
        photo.setProvider(this);
        return this;
    }

    public Provider removePhoto(Photo photo) {
        this.photos.remove(photo);
        photo.setProvider(null);
        return this;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    public Set<Loading> getLoadings() {
        return loadings;
    }

    public Provider loadings(Set<Loading> loadings) {
        this.loadings = loadings;
        return this;
    }

    public Provider addLoading(Loading loading) {
        this.loadings.add(loading);
        loading.setProvider(this);
        return this;
    }

    public Provider removeLoading(Loading loading) {
        this.loadings.remove(loading);
        loading.setProvider(null);
        return this;
    }

    public void setLoadings(Set<Loading> loadings) {
        this.loadings = loadings;
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
        Provider provider = (Provider) o;
        if (provider.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), provider.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Provider{" +
            "id=" + getId() +
            ", companyNumber='" + getCompanyNumber() + "'" +
            ", companyName='" + getCompanyName() + "'" +
            ", approvalNumber='" + getApprovalNumber() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", website='" + getWebsite() + "'" +
            ", registrationNumber='" + getRegistrationNumber() + "'" +
            ", vATNumber='" + getvATNumber() + "'" +
            ", containerAmountEstimated=" + getContainerAmountEstimated() +
            ", productName='" + getProductName() + "'" +
            ", membership='" + getMembership() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
