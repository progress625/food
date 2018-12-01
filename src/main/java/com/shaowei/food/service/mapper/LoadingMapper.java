package com.shaowei.food.service.mapper;

import com.shaowei.food.domain.*;
import com.shaowei.food.service.dto.LoadingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Loading and its DTO LoadingDTO.
 */
@Mapper(componentModel = "spring", uses = {ProviderMapper.class, AddressMapper.class})
public interface LoadingMapper extends EntityMapper<LoadingDTO, Loading> {

    @Mapping(source = "provider.id", target = "providerId")
    @Mapping(source = "address.id", target = "addressId")
    LoadingDTO toDto(Loading loading);

    @Mapping(source = "providerId", target = "provider")
    @Mapping(source = "addressId", target = "address")
    Loading toEntity(LoadingDTO loadingDTO);

    default Loading fromId(String id) {
        if (id == null) {
            return null;
        }
        Loading loading = new Loading();
        loading.setId(id);
        return loading;
    }
}
