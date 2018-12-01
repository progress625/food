package com.shaowei.food.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Loading entity.
 */
public class LoadingDTO implements Serializable {

    private String id;

    private String providerId;

    private String addressId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
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

        LoadingDTO loadingDTO = (LoadingDTO) o;
        if (loadingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), loadingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LoadingDTO{" +
            "id=" + getId() +
            ", provider=" + getProviderId() +
            ", address=" + getAddressId() +
            "}";
    }
}
