package com.shaowei.food.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.shaowei.food.security.AuthoritiesConstants;
import com.shaowei.food.service.ProviderService;
import com.shaowei.food.service.dto.ProviderDTO;
import com.shaowei.food.web.rest.errors.BadRequestAlertException;
import com.shaowei.food.web.rest.util.HeaderUtil;
import com.shaowei.food.web.rest.util.PaginationUtil;
import com.shaowei.food.web.rest.vm.ProviderAndAdminVM;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Provider.
 */
@RestController
@RequestMapping("/api")
public class ProviderResource {

    private final Logger log = LoggerFactory.getLogger(ProviderResource.class);

    private static final String ENTITY_NAME = "provider";

    private final ProviderService providerService;

    public ProviderResource(ProviderService providerService) {
        this.providerService = providerService;
    }

    /**
     * POST  /providers : Create a new provider.
     *
     * @param providerDTO the providerDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new providerDTO, or with status 400 (Bad Request) if the provider has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/providers")
    @Timed
    public ResponseEntity<ProviderDTO> createProvider(@RequestBody ProviderDTO providerDTO) throws URISyntaxException {
        log.debug("REST request to save Provider : {}", providerDTO);
        if (providerDTO.getId() != null) {
            throw new BadRequestAlertException("A new provider cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProviderDTO result = providerService.save(providerDTO);
        return ResponseEntity.created(new URI("/api/providers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /providers : Updates an existing provider.
     *
     * @param providerDTO the providerDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated providerDTO,
     * or with status 400 (Bad Request) if the providerDTO is not valid,
     * or with status 500 (Internal Server Error) if the providerDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/providers")
    @Timed
    public ResponseEntity<ProviderDTO> updateProvider(@RequestBody ProviderDTO providerDTO) throws URISyntaxException {
        log.debug("REST request to update Provider : {}", providerDTO);
        if (providerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProviderDTO result = providerService.save(providerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, providerDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /providers : get all the providers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of providers in body
     */
    @GetMapping("/providers")
    @Timed
    public ResponseEntity<List<ProviderDTO>> getAllProviders(Pageable pageable) {
        log.debug("REST request to get a page of Providers");
        Page<ProviderDTO> page = providerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/providers");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /providers/:id : get the "id" provider.
     *
     * @param id the id of the providerDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the providerDTO, or with status 404 (Not Found)
     */
    @GetMapping("/providers/{id}")
    @Timed
    public ResponseEntity<ProviderDTO> getProvider(@PathVariable String id) {
        log.debug("REST request to get Provider : {}", id);
        Optional<ProviderDTO> providerDTO = providerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(providerDTO);
    }

    /**
     * DELETE  /providers/:id : delete the "id" provider.
     *
     * @param id the id of the providerDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/providers/{id}")
    @Timed
    public ResponseEntity<Void> deleteProvider(@PathVariable String id) {
        log.debug("REST request to delete Provider : {}", id);
        providerService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
    
    /**
     * POST  /providers : Create a new provider.
     *
     * @param providerDTO the providerDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new providerDTO, or with status 400 (Bad Request) if the provider has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/providers-with-admin")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.PROVIDER_ADMINISTRATOR + "\")")
    @Timed
    public ResponseEntity<ProviderDTO> createProviderwithAdmin(@RequestBody ProviderAndAdminVM providerAndAdminVM) throws URISyntaxException {
        log.debug("REST request to save Provider with administrator: {}", providerAndAdminVM);
        if (providerAndAdminVM.getId() != null) {
            throw new BadRequestAlertException("A new provider cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProviderDTO result = providerService.save(providerAndAdminVM, providerAndAdminVM.getAdministratorId());
        return ResponseEntity.created(new URI("/api/providers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

}
