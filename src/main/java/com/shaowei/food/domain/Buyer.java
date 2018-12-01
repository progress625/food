package com.shaowei.food.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.shaowei.food.domain.enumeration.Membership;

import com.shaowei.food.domain.enumeration.Status;

/**
 * A Buyer.
 */
@Document(collection = "buyer")
public class Buyer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("company_number")
    private String companyNumber;

    @Field("company_name")
    private String companyName;

    @Field("business_license")
    private String businessLicense;

    @Field("container_amount_estimated")
    private Integer containerAmountEstimated;

    @Field("credibility_level")
    private Integer credibilityLevel;

    @Field("guarantee_amount")
    private BigDecimal guaranteeAmount;

    @Field("prepayment_percent")
    private Float prepaymentPercent;

    @Field("payment_method")
    private String paymentMethod;

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

    public Buyer companyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
        return this;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Buyer companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public Buyer businessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
        return this;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public Integer getContainerAmountEstimated() {
        return containerAmountEstimated;
    }

    public Buyer containerAmountEstimated(Integer containerAmountEstimated) {
        this.containerAmountEstimated = containerAmountEstimated;
        return this;
    }

    public void setContainerAmountEstimated(Integer containerAmountEstimated) {
        this.containerAmountEstimated = containerAmountEstimated;
    }

    public Integer getCredibilityLevel() {
        return credibilityLevel;
    }

    public Buyer credibilityLevel(Integer credibilityLevel) {
        this.credibilityLevel = credibilityLevel;
        return this;
    }

    public void setCredibilityLevel(Integer credibilityLevel) {
        this.credibilityLevel = credibilityLevel;
    }

    public BigDecimal getGuaranteeAmount() {
        return guaranteeAmount;
    }

    public Buyer guaranteeAmount(BigDecimal guaranteeAmount) {
        this.guaranteeAmount = guaranteeAmount;
        return this;
    }

    public void setGuaranteeAmount(BigDecimal guaranteeAmount) {
        this.guaranteeAmount = guaranteeAmount;
    }

    public Float getPrepaymentPercent() {
        return prepaymentPercent;
    }

    public Buyer prepaymentPercent(Float prepaymentPercent) {
        this.prepaymentPercent = prepaymentPercent;
        return this;
    }

    public void setPrepaymentPercent(Float prepaymentPercent) {
        this.prepaymentPercent = prepaymentPercent;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Buyer paymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Membership getMembership() {
        return membership;
    }

    public Buyer membership(Membership membership) {
        this.membership = membership;
        return this;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public Status getStatus() {
        return status;
    }

    public Buyer status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Address getAddress() {
        return address;
    }

    public Buyer address(Address address) {
        this.address = address;
        return this;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<AbstractUser> getUsers() {
        return users;
    }

    public Buyer users(Set<AbstractUser> abstractUsers) {
        this.users = abstractUsers;
        return this;
    }

    public Buyer addUser(AbstractUser abstractUser) {
        this.users.add(abstractUser);
        abstractUser.setBuyer(this);
        return this;
    }

    public Buyer removeUser(AbstractUser abstractUser) {
        this.users.remove(abstractUser);
        abstractUser.setBuyer(null);
        return this;
    }

    public void setUsers(Set<AbstractUser> abstractUsers) {
        this.users = abstractUsers;
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
        Buyer buyer = (Buyer) o;
        if (buyer.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), buyer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Buyer{" +
            "id=" + getId() +
            ", companyNumber='" + getCompanyNumber() + "'" +
            ", companyName='" + getCompanyName() + "'" +
            ", businessLicense='" + getBusinessLicense() + "'" +
            ", containerAmountEstimated=" + getContainerAmountEstimated() +
            ", credibilityLevel=" + getCredibilityLevel() +
            ", guaranteeAmount=" + getGuaranteeAmount() +
            ", prepaymentPercent=" + getPrepaymentPercent() +
            ", paymentMethod='" + getPaymentMethod() + "'" +
            ", membership='" + getMembership() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
