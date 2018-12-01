package com.shaowei.food.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.shaowei.food.service.LoadingService;
import com.shaowei.food.web.rest.errors.BadRequestAlertException;
import com.shaowei.food.web.rest.util.HeaderUtil;
import com.shaowei.food.web.rest.util.PaginationUtil;
import com.shaowei.food.service.dto.LoadingDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Loading.
 */
@RestController
@RequestMapping("/api")
public class LoadingResource {

    private final Logger log = LoggerFactory.getLogger(LoadingResource.class);

    private static final String ENTITY_NAME = "loading";

    private final LoadingService loadingService;

    public LoadingResource(LoadingService loadingService) {
        this.loadingService = loadingService;
    }

    /**
     * POST  /loadings : Create a new loading.
     *
     * @param loadingDTO the loadingDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new loadingDTO, or with status 400 (Bad Request) if the loading has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/loadings")
    @Timed
    public ResponseEntity<LoadingDTO> createLoading(@RequestBody LoadingDTO loadingDTO) throws URISyntaxException {
        log.debug("REST request to save Loading : {}", loadingDTO);
        if (loadingDTO.getId() != null) {
            throw new BadRequestAlertException("A new loading cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LoadingDTO result = loadingService.save(loadingDTO);
        return ResponseEntity.created(new URI("/api/loadings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /loadings : Updates an existing loading.
     *
     * @param loadingDTO the loadingDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated loadingDTO,
     * or with status 400 (Bad Request) if the loadingDTO is not valid,
     * or with status 500 (Internal Server Error) if the loadingDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/loadings")
    @Timed
    public ResponseEntity<LoadingDTO> updateLoading(@RequestBody LoadingDTO loadingDTO) throws URISyntaxException {
        log.debug("REST request to update Loading : {}", loadingDTO);
        if (loadingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LoadingDTO result = loadingService.save(loadingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, loadingDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /loadings : get all the loadings.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of loadings in body
     */
    @GetMapping("/loadings")
    @Timed
    public ResponseEntity<List<LoadingDTO>> getAllLoadings(Pageable pageable) {
        log.debug("REST request to get a page of Loadings");
        Page<LoadingDTO> page = loadingService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/loadings");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /loadings/:id : get the "id" loading.
     *
     * @param id the id of the loadingDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the loadingDTO, or with status 404 (Not Found)
     */
    @GetMapping("/loadings/{id}")
    @Timed
    public ResponseEntity<LoadingDTO> getLoading(@PathVariable String id) {
        log.debug("REST request to get Loading : {}", id);
        Optional<LoadingDTO> loadingDTO = loadingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(loadingDTO);
    }

    /**
     * DELETE  /loadings/:id : delete the "id" loading.
     *
     * @param id the id of the loadingDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/loadings/{id}")
    @Timed
    public ResponseEntity<Void> deleteLoading(@PathVariable String id) {
        log.debug("REST request to delete Loading : {}", id);
        loadingService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
