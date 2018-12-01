package com.shaowei.food.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import com.shaowei.food.domain.enumeration.Membership;
import com.shaowei.food.domain.enumeration.Status;

/**
 * A DTO for the Buyer entity.
 */
public class BuyerDTO implements Serializable {

    private String id;

    private String companyNumber;

    private String companyName;

    private String businessLicense;

    private Integer containerAmountEstimated;

    private Integer credibilityLevel;

    private BigDecimal guaranteeAmount;

    private Float prepaymentPercent;

    private String paymentMethod;

    private Membership membership;

    private Status status;

    private String addressId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public Integer getContainerAmountEstimated() {
        return containerAmountEstimated;
    }

    public void setContainerAmountEstimated(Integer containerAmountEstimated) {
        this.containerAmountEstimated = containerAmountEstimated;
    }

    public Integer getCredibilityLevel() {
        return credibilityLevel;
    }

    public void setCredibilityLevel(Integer credibilityLevel) {
        this.credibilityLevel = credibilityLevel;
    }

    public BigDecimal getGuaranteeAmount() {
        return guaranteeAmount;
    }

    public void setGuaranteeAmount(BigDecimal guaranteeAmount) {
        this.guaranteeAmount = guaranteeAmount;
    }

    public Float getPrepaymentPercent() {
        return prepaymentPercent;
    }

    public void setPrepaymentPercent(Float prepaymentPercent) {
        this.prepaymentPercent = prepaymentPercent;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BuyerDTO buyerDTO = (BuyerDTO) o;
        if (buyerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), buyerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BuyerDTO{" +
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
            ", address=" + getAddressId() +
            "}";
    }
}
