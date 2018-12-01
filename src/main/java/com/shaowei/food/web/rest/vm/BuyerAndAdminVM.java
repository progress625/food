package com.shaowei.food.web.rest.vm;

import com.shaowei.food.service.dto.BuyerDTO;

/**
 * View Model extending the UserDTO, which is meant to be used in the user management UI.
 */
public class BuyerAndAdminVM extends BuyerDTO {

    private String administratorId;

    public BuyerAndAdminVM() {
        // Empty constructor needed for Jackson.
    }

	public String getAdministratorId() {
		return administratorId;
	}

	public void setAdministratorId(String administratorId) {
		this.administratorId = administratorId;
	}

	@Override
	public String toString() {
		return "ProviderAndAdminVM [administratorId=" + administratorId + "]";
	}


}
