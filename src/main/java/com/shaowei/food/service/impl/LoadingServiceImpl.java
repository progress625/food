package com.shaowei.food.service.impl;

import com.shaowei.food.service.LoadingService;
import com.shaowei.food.domain.Loading;
import com.shaowei.food.repository.LoadingRepository;
import com.shaowei.food.service.dto.LoadingDTO;
import com.shaowei.food.service.mapper.LoadingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing Loading.
 */
@Service
public class LoadingServiceImpl implements LoadingService {

    private final Logger log = LoggerFactory.getLogger(LoadingServiceImpl.class);

    private final LoadingRepository loadingRepository;

    private final LoadingMapper loadingMapper;

    public LoadingServiceImpl(LoadingRepository loadingRepository, LoadingMapper loadingMapper) {
        this.loadingRepository = loadingRepository;
        this.loadingMapper = loadingMapper;
    }

    /**
     * Save a loading.
     *
     * @param loadingDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public LoadingDTO save(LoadingDTO loadingDTO) {
        log.debug("Request to save Loading : {}", loadingDTO);

        Loading loading = loadingMapper.toEntity(loadingDTO);
        loading = loadingRepository.save(loading);
        return loadingMapper.toDto(loading);
    }

    /**
     * Get all the loadings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<LoadingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Loadings");
        return loadingRepository.findAll(pageable)
            .map(loadingMapper::toDto);
    }


    /**
     * Get one loading by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<LoadingDTO> findOne(String id) {
        log.debug("Request to get Loading : {}", id);
        return loadingRepository.findById(id)
            .map(loadingMapper::toDto);
    }

    /**
     * Delete the loading by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Loading : {}", id);
        loadingRepository.deleteById(id);
    }
}
