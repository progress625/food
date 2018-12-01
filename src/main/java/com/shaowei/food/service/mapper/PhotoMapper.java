package com.shaowei.food.service.mapper;

import com.shaowei.food.domain.*;
import com.shaowei.food.service.dto.PhotoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Photo and its DTO PhotoDTO.
 */
@Mapper(componentModel = "spring", uses = {ProviderMapper.class})
public interface PhotoMapper extends EntityMapper<PhotoDTO, Photo> {

    @Mapping(source = "provider.id", target = "providerId")
    PhotoDTO toDto(Photo photo);

    @Mapping(source = "providerId", target = "provider")
    Photo toEntity(PhotoDTO photoDTO);

    default Photo fromId(String id) {
        if (id == null) {
            return null;
        }
        Photo photo = new Photo();
        photo.setId(id);
        return photo;
    }
}
