package com.shaowei.food.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shaowei.food.domain.AbstractUser;
import com.shaowei.food.domain.Provider;
import com.shaowei.food.repository.ProviderRepository;
import com.shaowei.food.service.AbstractUserService;
import com.shaowei.food.service.ProviderService;
import com.shaowei.food.service.dto.ProviderDTO;
import com.shaowei.food.service.mapper.ProviderMapper;


/**
 * Service Implementation for managing Provider.
 */
@Service
public class ProviderServiceImpl implements ProviderService {

    private final Logger log = LoggerFactory.getLogger(ProviderServiceImpl.class);

    private final ProviderRepository providerRepository;

    private final ProviderMapper providerMapper;
    
    private final AbstractUserService abstractUserService;

    public ProviderServiceImpl(ProviderRepository providerRepository, ProviderMapper providerMapper,
    		AbstractUserService abstractUserService) {
        this.providerRepository = providerRepository;
        this.providerMapper = providerMapper;
        this.abstractUserService = abstractUserService;
    }

    /**
     * Save a provider.
     *
     * @param providerDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProviderDTO save(ProviderDTO providerDTO) {
        log.debug("Request to save Provider : {}", providerDTO);

        Provider provider = providerMapper.toEntity(providerDTO);
        provider = providerRepository.save(provider);
        return providerMapper.toDto(provider);
    }

    /**
     * Get all the providers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<ProviderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Providers");
        return providerRepository.findAll(pageable)
            .map(providerMapper::toDto);
    }


    /**
     * Get one provider by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<ProviderDTO> findOne(String id) {
        log.debug("Request to get Provider : {}", id);
        return providerRepository.findById(id)
            .map(providerMapper::toDto);
    }

    /**
     * Delete the provider by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Provider : {}", id);
        providerRepository.deleteById(id);
    }
    
    /**
     * Save a provider with administrator id .
     *
     * @param providerDTO the entity to save
     * @param adminId the id of administrator
     * @return the persisted entity
     */
    @Override
    public ProviderDTO save(ProviderDTO providerDTO, String adminId) {
        log.debug("Request to save Provider with administrator: {}", providerDTO);
        AbstractUser admin = abstractUserService.findOneEntity(adminId).get();
        ProviderDTO result = null;
        if (admin.getProvider() == null) {
            Provider provider = providerMapper.toEntity(providerDTO);
            provider.addUser(admin);
            provider = providerRepository.save(provider);
            admin.setProvider(provider);
            abstractUserService.save(admin);
            result = providerMapper.toDto(provider);
//            providerSearchRepository.save(provider);
        }
        return result;
    }
}
