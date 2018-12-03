package com.shaowei.food.service.impl;

import com.shaowei.food.service.AbstractUserService;
import com.shaowei.food.domain.AbstractUser;
import com.shaowei.food.repository.AbstractUserRepository;
import com.shaowei.food.security.SecurityUtils;
import com.shaowei.food.service.dto.AbstractUserDTO;
import com.shaowei.food.service.mapper.AbstractUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing AbstractUser.
 */
@Service
public class AbstractUserServiceImpl implements AbstractUserService {

    private final Logger log = LoggerFactory.getLogger(AbstractUserServiceImpl.class);

    private final AbstractUserRepository abstractUserRepository;

    private final AbstractUserMapper abstractUserMapper;

    public AbstractUserServiceImpl(AbstractUserRepository abstractUserRepository, AbstractUserMapper abstractUserMapper) {
        this.abstractUserRepository = abstractUserRepository;
        this.abstractUserMapper = abstractUserMapper;
    }

    /**
     * Save a abstractUser.
     *
     * @param abstractUserDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AbstractUserDTO save(AbstractUserDTO abstractUserDTO) {
        log.debug("Request to save AbstractUser : {}", abstractUserDTO);

        AbstractUser abstractUser = abstractUserMapper.toEntity(abstractUserDTO);
        abstractUser = abstractUserRepository.save(abstractUser);
        return abstractUserMapper.toDto(abstractUser);
    }

    /**
     * Get all the abstractUsers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<AbstractUserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AbstractUsers");
        return abstractUserRepository.findAll(pageable)
            .map(abstractUserMapper::toDto);
    }


    /**
     * Get one abstractUser by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<AbstractUserDTO> findOne(String id) {
        log.debug("Request to get AbstractUser : {}", id);
        return abstractUserRepository.findById(id)
            .map(abstractUserMapper::toDto);
    }

    /**
     * Delete the abstractUser by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete AbstractUser : {}", id);
        abstractUserRepository.deleteById(id);
    }
    
    /**
    * Get one abstractUser by id.
    *
    * @param id the id of the entity
    * @return the entity
    */
   @Override
   public Optional<AbstractUser> findOneEntity(String id) {
       log.debug("Request to get AbstractUser : {}", id);
       return abstractUserRepository.findById(id);
   }

	@Override
	public AbstractUser save(AbstractUser abstractUser) {
        log.debug("Request to save AbstractUser : {}", abstractUser);

        abstractUser = abstractUserRepository.save(abstractUser);
        return abstractUser;
	}

	@Override
	public Optional<AbstractUser> getCurrentAbstractUser() {
		return  SecurityUtils.getCurrentUserLogin().flatMap(abstractUserRepository::findByEmail);
	}
}
