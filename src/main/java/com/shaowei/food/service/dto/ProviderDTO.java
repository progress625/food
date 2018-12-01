package com.shaowei.food.service.dto;

import java.io.Serializable;
import java.util.Objects;
import com.shaowei.food.domain.enumeration.Membership;
import com.shaowei.food.domain.enumeration.Status;

/**
 * A DTO for the Provider entity.
 */
public class ProviderDTO implements Serializable {

    private String id;

    private String companyNumber;

    private String companyName;

    private String approvalNumber;

    private String telephone;

    private String website;

    private String registrationNumber;

    private String vATNumber;

    private Integer containerAmountEstimated;

    private String productName;

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

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getvATNumber() {
        return vATNumber;
    }

    public void setvATNumber(String vATNumber) {
        this.vATNumber = vATNumber;
    }

    public Integer getContainerAmountEstimated() {
        return containerAmountEstimated;
    }

    public void setContainerAmountEstimated(Integer containerAmountEstimated) {
        this.containerAmountEstimated = containerAmountEstimated;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

        ProviderDTO providerDTO = (ProviderDTO) o;
        if (providerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), providerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProviderDTO{" +
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
            ", address=" + getAddressId() +
            "}";
    }
}
