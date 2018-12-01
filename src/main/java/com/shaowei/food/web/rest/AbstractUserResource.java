package com.shaowei.food.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.shaowei.food.service.AbstractUserService;
import com.shaowei.food.web.rest.errors.BadRequestAlertException;
import com.shaowei.food.web.rest.util.HeaderUtil;
import com.shaowei.food.web.rest.util.PaginationUtil;
import com.shaowei.food.service.dto.AbstractUserDTO;
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
 * REST controller for managing AbstractUser.
 */
@RestController
@RequestMapping("/api")
public class AbstractUserResource {

    private final Logger log = LoggerFactory.getLogger(AbstractUserResource.class);

    private static final String ENTITY_NAME = "abstractUser";

    private final AbstractUserService abstractUserService;

    public AbstractUserResource(AbstractUserService abstractUserService) {
        this.abstractUserService = abstractUserService;
    }

    /**
     * POST  /abstract-users : Create a new abstractUser.
     *
     * @param abstractUserDTO the abstractUserDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new abstractUserDTO, or with status 400 (Bad Request) if the abstractUser has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/abstract-users")
    @Timed
    public ResponseEntity<AbstractUserDTO> createAbstractUser(@RequestBody AbstractUserDTO abstractUserDTO) throws URISyntaxException {
        log.debug("REST request to save AbstractUser : {}", abstractUserDTO);
        if (abstractUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new abstractUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AbstractUserDTO result = abstractUserService.save(abstractUserDTO);
        return ResponseEntity.created(new URI("/api/abstract-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /abstract-users : Updates an existing abstractUser.
     *
     * @param abstractUserDTO the abstractUserDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated abstractUserDTO,
     * or with status 400 (Bad Request) if the abstractUserDTO is not valid,
     * or with status 500 (Internal Server Error) if the abstractUserDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/abstract-users")
    @Timed
    public ResponseEntity<AbstractUserDTO> updateAbstractUser(@RequestBody AbstractUserDTO abstractUserDTO) throws URISyntaxException {
        log.debug("REST request to update AbstractUser : {}", abstractUserDTO);
        if (abstractUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AbstractUserDTO result = abstractUserService.save(abstractUserDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, abstractUserDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /abstract-users : get all the abstractUsers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of abstractUsers in body
     */
    @GetMapping("/abstract-users")
    @Timed
    public ResponseEntity<List<AbstractUserDTO>> getAllAbstractUsers(Pageable pageable) {
        log.debug("REST request to get a page of AbstractUsers");
        Page<AbstractUserDTO> page = abstractUserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/abstract-users");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /abstract-users/:id : get the "id" abstractUser.
     *
     * @param id the id of the abstractUserDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the abstractUserDTO, or with status 404 (Not Found)
     */
    @GetMapping("/abstract-users/{id}")
    @Timed
    public ResponseEntity<AbstractUserDTO> getAbstractUser(@PathVariable String id) {
        log.debug("REST request to get AbstractUser : {}", id);
        Optional<AbstractUserDTO> abstractUserDTO = abstractUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(abstractUserDTO);
    }

    /**
     * DELETE  /abstract-users/:id : delete the "id" abstractUser.
     *
     * @param id the id of the abstractUserDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/abstract-users/{id}")
    @Timed
    public ResponseEntity<Void> deleteAbstractUser(@PathVariable String id) {
        log.debug("REST request to delete AbstractUser : {}", id);
        abstractUserService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
