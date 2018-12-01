package com.shaowei.food.service.mapper;

import com.shaowei.food.domain.*;
import com.shaowei.food.service.dto.ProviderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Provider and its DTO ProviderDTO.
 */
@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface ProviderMapper extends EntityMapper<ProviderDTO, Provider> {

    @Mapping(source = "address.id", target = "addressId")
    ProviderDTO toDto(Provider provider);

    @Mapping(source = "addressId", target = "address")
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "photos", ignore = true)
    @Mapping(target = "loadings", ignore = true)
    Provider toEntity(ProviderDTO providerDTO);

    default Provider fromId(String id) {
        if (id == null) {
            return null;
        }
        Provider provider = new Provider();
        provider.setId(id);
        return provider;
    }
}
