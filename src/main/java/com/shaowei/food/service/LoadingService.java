package com.shaowei.food.service;

import com.shaowei.food.service.dto.LoadingDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Loading.
 */
public interface LoadingService {

    /**
     * Save a loading.
     *
     * @param loadingDTO the entity to save
     * @return the persisted entity
     */
    LoadingDTO save(LoadingDTO loadingDTO);

    /**
     * Get all the loadings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<LoadingDTO> findAll(Pageable pageable);


    /**
     * Get the "id" loading.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<LoadingDTO> findOne(String id);

    /**
     * Delete the "id" loading.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
