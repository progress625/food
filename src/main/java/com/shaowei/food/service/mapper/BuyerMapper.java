package com.shaowei.food.service.mapper;

import com.shaowei.food.domain.*;
import com.shaowei.food.service.dto.BuyerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Buyer and its DTO BuyerDTO.
 */
@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface BuyerMapper extends EntityMapper<BuyerDTO, Buyer> {

    @Mapping(source = "address.id", target = "addressId")
    BuyerDTO toDto(Buyer buyer);

    @Mapping(source = "addressId", target = "address")
    @Mapping(target = "users", ignore = true)
    Buyer toEntity(BuyerDTO buyerDTO);

    default Buyer fromId(String id) {
        if (id == null) {
            return null;
        }
        Buyer buyer = new Buyer();
        buyer.setId(id);
        return buyer;
    }
}
