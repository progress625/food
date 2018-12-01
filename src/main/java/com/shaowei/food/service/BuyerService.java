package com.shaowei.food.service;

import com.shaowei.food.service.dto.BuyerDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Buyer.
 */
public interface BuyerService {

    /**
     * Save a buyer.
     *
     * @param buyerDTO the entity to save
     * @return the persisted entity
     */
    BuyerDTO save(BuyerDTO buyerDTO);

    /**
     * Get all the buyers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<BuyerDTO> findAll(Pageable pageable);


    /**
     * Get the "id" buyer.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BuyerDTO> findOne(String id);

    /**
     * Delete the "id" buyer.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
