package com.shaowei.food.service.impl;

import com.shaowei.food.service.BuyerService;
import com.shaowei.food.domain.Buyer;
import com.shaowei.food.repository.BuyerRepository;
import com.shaowei.food.service.dto.BuyerDTO;
import com.shaowei.food.service.mapper.BuyerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing Buyer.
 */
@Service
public class BuyerServiceImpl implements BuyerService {

    private final Logger log = LoggerFactory.getLogger(BuyerServiceImpl.class);

    private final BuyerRepository buyerRepository;

    private final BuyerMapper buyerMapper;

    public BuyerServiceImpl(BuyerRepository buyerRepository, BuyerMapper buyerMapper) {
        this.buyerRepository = buyerRepository;
        this.buyerMapper = buyerMapper;
    }

    /**
     * Save a buyer.
     *
     * @param buyerDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BuyerDTO save(BuyerDTO buyerDTO) {
        log.debug("Request to save Buyer : {}", buyerDTO);

        Buyer buyer = buyerMapper.toEntity(buyerDTO);
        buyer = buyerRepository.save(buyer);
        return buyerMapper.toDto(buyer);
    }

    /**
     * Get all the buyers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<BuyerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Buyers");
        return buyerRepository.findAll(pageable)
            .map(buyerMapper::toDto);
    }


    /**
     * Get one buyer by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<BuyerDTO> findOne(String id) {
        log.debug("Request to get Buyer : {}", id);
        return buyerRepository.findById(id)
            .map(buyerMapper::toDto);
    }

    /**
     * Delete the buyer by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Buyer : {}", id);
        buyerRepository.deleteById(id);
    }
}
