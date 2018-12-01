package com.shaowei.food.web.rest;

import com.shaowei.food.FoodApp;

import com.shaowei.food.domain.Loading;
import com.shaowei.food.repository.LoadingRepository;
import com.shaowei.food.service.LoadingService;
import com.shaowei.food.service.dto.LoadingDTO;
import com.shaowei.food.service.mapper.LoadingMapper;
import com.shaowei.food.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;


import static com.shaowei.food.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the LoadingResource REST controller.
 *
 * @see LoadingResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FoodApp.class)
public class LoadingResourceIntTest {

    @Autowired
    private LoadingRepository loadingRepository;

    @Autowired
    private LoadingMapper loadingMapper;

    @Autowired
    private LoadingService loadingService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restLoadingMockMvc;

    private Loading loading;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LoadingResource loadingResource = new LoadingResource(loadingService);
        this.restLoadingMockMvc = MockMvcBuilders.standaloneSetup(loadingResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Loading createEntity() {
        Loading loading = new Loading();
        return loading;
    }

    @Before
    public void initTest() {
        loadingRepository.deleteAll();
        loading = createEntity();
    }

    @Test
    public void createLoading() throws Exception {
        int databaseSizeBeforeCreate = loadingRepository.findAll().size();

        // Create the Loading
        LoadingDTO loadingDTO = loadingMapper.toDto(loading);
        restLoadingMockMvc.perform(post("/api/loadings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loadingDTO)))
            .andExpect(status().isCreated());

        // Validate the Loading in the database
        List<Loading> loadingList = loadingRepository.findAll();
        assertThat(loadingList).hasSize(databaseSizeBeforeCreate + 1);
        Loading testLoading = loadingList.get(loadingList.size() - 1);
    }

    @Test
    public void createLoadingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = loadingRepository.findAll().size();

        // Create the Loading with an existing ID
        loading.setId("existing_id");
        LoadingDTO loadingDTO = loadingMapper.toDto(loading);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLoadingMockMvc.perform(post("/api/loadings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loadingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Loading in the database
        List<Loading> loadingList = loadingRepository.findAll();
        assertThat(loadingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllLoadings() throws Exception {
        // Initialize the database
        loadingRepository.save(loading);

        // Get all the loadingList
        restLoadingMockMvc.perform(get("/api/loadings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(loading.getId())));
    }
    
    @Test
    public void getLoading() throws Exception {
        // Initialize the database
        loadingRepository.save(loading);

        // Get the loading
        restLoadingMockMvc.perform(get("/api/loadings/{id}", loading.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(loading.getId()));
    }

    @Test
    public void getNonExistingLoading() throws Exception {
        // Get the loading
        restLoadingMockMvc.perform(get("/api/loadings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateLoading() throws Exception {
        // Initialize the database
        loadingRepository.save(loading);

        int databaseSizeBeforeUpdate = loadingRepository.findAll().size();

        // Update the loading
        Loading updatedLoading = loadingRepository.findById(loading.getId()).get();
        LoadingDTO loadingDTO = loadingMapper.toDto(updatedLoading);

        restLoadingMockMvc.perform(put("/api/loadings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loadingDTO)))
            .andExpect(status().isOk());

        // Validate the Loading in the database
        List<Loading> loadingList = loadingRepository.findAll();
        assertThat(loadingList).hasSize(databaseSizeBeforeUpdate);
        Loading testLoading = loadingList.get(loadingList.size() - 1);
    }

    @Test
    public void updateNonExistingLoading() throws Exception {
        int databaseSizeBeforeUpdate = loadingRepository.findAll().size();

        // Create the Loading
        LoadingDTO loadingDTO = loadingMapper.toDto(loading);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLoadingMockMvc.perform(put("/api/loadings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loadingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Loading in the database
        List<Loading> loadingList = loadingRepository.findAll();
        assertThat(loadingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteLoading() throws Exception {
        // Initialize the database
        loadingRepository.save(loading);

        int databaseSizeBeforeDelete = loadingRepository.findAll().size();

        // Get the loading
        restLoadingMockMvc.perform(delete("/api/loadings/{id}", loading.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Loading> loadingList = loadingRepository.findAll();
        assertThat(loadingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Loading.class);
        Loading loading1 = new Loading();
        loading1.setId("id1");
        Loading loading2 = new Loading();
        loading2.setId(loading1.getId());
        assertThat(loading1).isEqualTo(loading2);
        loading2.setId("id2");
        assertThat(loading1).isNotEqualTo(loading2);
        loading1.setId(null);
        assertThat(loading1).isNotEqualTo(loading2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LoadingDTO.class);
        LoadingDTO loadingDTO1 = new LoadingDTO();
        loadingDTO1.setId("id1");
        LoadingDTO loadingDTO2 = new LoadingDTO();
        assertThat(loadingDTO1).isNotEqualTo(loadingDTO2);
        loadingDTO2.setId(loadingDTO1.getId());
        assertThat(loadingDTO1).isEqualTo(loadingDTO2);
        loadingDTO2.setId("id2");
        assertThat(loadingDTO1).isNotEqualTo(loadingDTO2);
        loadingDTO1.setId(null);
        assertThat(loadingDTO1).isNotEqualTo(loadingDTO2);
    }
}
