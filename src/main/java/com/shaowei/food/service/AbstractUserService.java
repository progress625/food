package com.shaowei.food.service;

import com.shaowei.food.domain.AbstractUser;
import com.shaowei.food.service.dto.AbstractUserDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing AbstractUser.
 */
public interface AbstractUserService {

    /**
     * Save a abstractUser.
     *
     * @param abstractUserDTO the entity to save
     * @return the persisted entity
     */
    AbstractUserDTO save(AbstractUserDTO abstractUserDTO);

    /**
     * Get all the abstractUsers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AbstractUserDTO> findAll(Pageable pageable);


    /**
     * Get the "id" abstractUser.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AbstractUserDTO> findOne(String id);

    /**
     * Delete the "id" abstractUser.
     *
     * @param id the id of the entity
     */
    void delete(String id);

	Optional<AbstractUser> findOneEntity(String id);
	
    /**
     * Save a abstractUser.
     *
     * @param abstractUser the entity to save
     * @return the persisted entity
     */
    AbstractUser save(AbstractUser abstractUser);
}
