package com.shaowei.food.service;

import com.shaowei.food.service.dto.ProviderDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Provider.
 */
public interface ProviderService {

    /**
     * Save a provider.
     *
     * @param providerDTO the entity to save
     * @return the persisted entity
     */
    ProviderDTO save(ProviderDTO providerDTO);

    /**
     * Get all the providers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ProviderDTO> findAll(Pageable pageable);


    /**
     * Get the "id" provider.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ProviderDTO> findOne(String id);

    /**
     * Delete the "id" provider.
     *
     * @param id the id of the entity
     */
    void delete(String id);

	ProviderDTO save(ProviderDTO providerDTO, String adminId);

}
