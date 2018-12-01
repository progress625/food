package com.shaowei.food.service.mapper;

import com.shaowei.food.domain.*;
import com.shaowei.food.service.dto.AbstractUserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AbstractUser and its DTO AbstractUserDTO.
 */
@Mapper(componentModel = "spring", uses = {AddressMapper.class, BuyerMapper.class, ProviderMapper.class})
public interface AbstractUserMapper extends EntityMapper<AbstractUserDTO, AbstractUser> {

    @Mapping(source = "address.id", target = "addressId")
    @Mapping(source = "buyer.id", target = "buyerId")
    @Mapping(source = "provider.id", target = "providerId")
    AbstractUserDTO toDto(AbstractUser abstractUser);

    @Mapping(source = "addressId", target = "address")
    @Mapping(source = "buyerId", target = "buyer")
    @Mapping(source = "providerId", target = "provider")
    AbstractUser toEntity(AbstractUserDTO abstractUserDTO);

    default AbstractUser fromId(String id) {
        if (id == null) {
            return null;
        }
        AbstractUser abstractUser = new AbstractUser();
        abstractUser.setId(id);
        return abstractUser;
    }
}
