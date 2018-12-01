package com.shaowei.food.web.rest;

import com.shaowei.food.FoodApp;

import com.shaowei.food.domain.Provider;
import com.shaowei.food.repository.ProviderRepository;
import com.shaowei.food.service.ProviderService;
import com.shaowei.food.service.dto.ProviderDTO;
import com.shaowei.food.service.mapper.ProviderMapper;
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

import com.shaowei.food.domain.enumeration.Membership;
import com.shaowei.food.domain.enumeration.Status;
/**
 * Test class for the ProviderResource REST controller.
 *
 * @see ProviderResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FoodApp.class)
public class ProviderResourceIntTest {

    private static final String DEFAULT_COMPANY_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_APPROVAL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_APPROVAL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_WEBSITE = "AAAAAAAAAA";
    private static final String UPDATED_WEBSITE = "BBBBBBBBBB";

    private static final String DEFAULT_REGISTRATION_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_REGISTRATION_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_V_AT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_V_AT_NUMBER = "BBBBBBBBBB";

    private static final Integer DEFAULT_CONTAINER_AMOUNT_ESTIMATED = 1;
    private static final Integer UPDATED_CONTAINER_AMOUNT_ESTIMATED = 2;

    private static final String DEFAULT_PRODUCT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_NAME = "BBBBBBBBBB";

    private static final Membership DEFAULT_MEMBERSHIP = Membership.NORMAL;
    private static final Membership UPDATED_MEMBERSHIP = Membership.VIP;

    private static final Status DEFAULT_STATUS = Status.DISABLE;
    private static final Status UPDATED_STATUS = Status.VALIDATED;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ProviderMapper providerMapper;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restProviderMockMvc;

    private Provider provider;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProviderResource providerResource = new ProviderResource(providerService);
        this.restProviderMockMvc = MockMvcBuilders.standaloneSetup(providerResource)
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
    public static Provider createEntity() {
        Provider provider = new Provider()
            .companyNumber(DEFAULT_COMPANY_NUMBER)
            .companyName(DEFAULT_COMPANY_NAME)
            .approvalNumber(DEFAULT_APPROVAL_NUMBER)
            .telephone(DEFAULT_TELEPHONE)
            .website(DEFAULT_WEBSITE)
            .registrationNumber(DEFAULT_REGISTRATION_NUMBER)
            .vATNumber(DEFAULT_V_AT_NUMBER)
            .containerAmountEstimated(DEFAULT_CONTAINER_AMOUNT_ESTIMATED)
            .productName(DEFAULT_PRODUCT_NAME)
            .membership(DEFAULT_MEMBERSHIP)
            .status(DEFAULT_STATUS);
        return provider;
    }

    @Before
    public void initTest() {
        providerRepository.deleteAll();
        provider = createEntity();
    }

    @Test
    public void createProvider() throws Exception {
        int databaseSizeBeforeCreate = providerRepository.findAll().size();

        // Create the Provider
        ProviderDTO providerDTO = providerMapper.toDto(provider);
        restProviderMockMvc.perform(post("/api/providers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(providerDTO)))
            .andExpect(status().isCreated());

        // Validate the Provider in the database
        List<Provider> providerList = providerRepository.findAll();
        assertThat(providerList).hasSize(databaseSizeBeforeCreate + 1);
        Provider testProvider = providerList.get(providerList.size() - 1);
        assertThat(testProvider.getCompanyNumber()).isEqualTo(DEFAULT_COMPANY_NUMBER);
        assertThat(testProvider.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testProvider.getApprovalNumber()).isEqualTo(DEFAULT_APPROVAL_NUMBER);
        assertThat(testProvider.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testProvider.getWebsite()).isEqualTo(DEFAULT_WEBSITE);
        assertThat(testProvider.getRegistrationNumber()).isEqualTo(DEFAULT_REGISTRATION_NUMBER);
        assertThat(testProvider.getvATNumber()).isEqualTo(DEFAULT_V_AT_NUMBER);
        assertThat(testProvider.getContainerAmountEstimated()).isEqualTo(DEFAULT_CONTAINER_AMOUNT_ESTIMATED);
        assertThat(testProvider.getProductName()).isEqualTo(DEFAULT_PRODUCT_NAME);
        assertThat(testProvider.getMembership()).isEqualTo(DEFAULT_MEMBERSHIP);
        assertThat(testProvider.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    public void createProviderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = providerRepository.findAll().size();

        // Create the Provider with an existing ID
        provider.setId("existing_id");
        ProviderDTO providerDTO = providerMapper.toDto(provider);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProviderMockMvc.perform(post("/api/providers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(providerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Provider in the database
        List<Provider> providerList = providerRepository.findAll();
        assertThat(providerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllProviders() throws Exception {
        // Initialize the database
        providerRepository.save(provider);

        // Get all the providerList
        restProviderMockMvc.perform(get("/api/providers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(provider.getId())))
            .andExpect(jsonPath("$.[*].companyNumber").value(hasItem(DEFAULT_COMPANY_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].companyName").value(hasItem(DEFAULT_COMPANY_NAME.toString())))
            .andExpect(jsonPath("$.[*].approvalNumber").value(hasItem(DEFAULT_APPROVAL_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE.toString())))
            .andExpect(jsonPath("$.[*].website").value(hasItem(DEFAULT_WEBSITE.toString())))
            .andExpect(jsonPath("$.[*].registrationNumber").value(hasItem(DEFAULT_REGISTRATION_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].vATNumber").value(hasItem(DEFAULT_V_AT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].containerAmountEstimated").value(hasItem(DEFAULT_CONTAINER_AMOUNT_ESTIMATED)))
            .andExpect(jsonPath("$.[*].productName").value(hasItem(DEFAULT_PRODUCT_NAME.toString())))
            .andExpect(jsonPath("$.[*].membership").value(hasItem(DEFAULT_MEMBERSHIP.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    public void getProvider() throws Exception {
        // Initialize the database
        providerRepository.save(provider);

        // Get the provider
        restProviderMockMvc.perform(get("/api/providers/{id}", provider.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(provider.getId()))
            .andExpect(jsonPath("$.companyNumber").value(DEFAULT_COMPANY_NUMBER.toString()))
            .andExpect(jsonPath("$.companyName").value(DEFAULT_COMPANY_NAME.toString()))
            .andExpect(jsonPath("$.approvalNumber").value(DEFAULT_APPROVAL_NUMBER.toString()))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE.toString()))
            .andExpect(jsonPath("$.website").value(DEFAULT_WEBSITE.toString()))
            .andExpect(jsonPath("$.registrationNumber").value(DEFAULT_REGISTRATION_NUMBER.toString()))
            .andExpect(jsonPath("$.vATNumber").value(DEFAULT_V_AT_NUMBER.toString()))
            .andExpect(jsonPath("$.containerAmountEstimated").value(DEFAULT_CONTAINER_AMOUNT_ESTIMATED))
            .andExpect(jsonPath("$.productName").value(DEFAULT_PRODUCT_NAME.toString()))
            .andExpect(jsonPath("$.membership").value(DEFAULT_MEMBERSHIP.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    public void getNonExistingProvider() throws Exception {
        // Get the provider
        restProviderMockMvc.perform(get("/api/providers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProvider() throws Exception {
        // Initialize the database
        providerRepository.save(provider);

        int databaseSizeBeforeUpdate = providerRepository.findAll().size();

        // Update the provider
        Provider updatedProvider = providerRepository.findById(provider.getId()).get();
        updatedProvider
            .companyNumber(UPDATED_COMPANY_NUMBER)
            .companyName(UPDATED_COMPANY_NAME)
            .approvalNumber(UPDATED_APPROVAL_NUMBER)
            .telephone(UPDATED_TELEPHONE)
            .website(UPDATED_WEBSITE)
            .registrationNumber(UPDATED_REGISTRATION_NUMBER)
            .vATNumber(UPDATED_V_AT_NUMBER)
            .containerAmountEstimated(UPDATED_CONTAINER_AMOUNT_ESTIMATED)
            .productName(UPDATED_PRODUCT_NAME)
            .membership(UPDATED_MEMBERSHIP)
            .status(UPDATED_STATUS);
        ProviderDTO providerDTO = providerMapper.toDto(updatedProvider);

        restProviderMockMvc.perform(put("/api/providers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(providerDTO)))
            .andExpect(status().isOk());

        // Validate the Provider in the database
        List<Provider> providerList = providerRepository.findAll();
        assertThat(providerList).hasSize(databaseSizeBeforeUpdate);
        Provider testProvider = providerList.get(providerList.size() - 1);
        assertThat(testProvider.getCompanyNumber()).isEqualTo(UPDATED_COMPANY_NUMBER);
        assertThat(testProvider.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testProvider.getApprovalNumber()).isEqualTo(UPDATED_APPROVAL_NUMBER);
        assertThat(testProvider.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testProvider.getWebsite()).isEqualTo(UPDATED_WEBSITE);
        assertThat(testProvider.getRegistrationNumber()).isEqualTo(UPDATED_REGISTRATION_NUMBER);
        assertThat(testProvider.getvATNumber()).isEqualTo(UPDATED_V_AT_NUMBER);
        assertThat(testProvider.getContainerAmountEstimated()).isEqualTo(UPDATED_CONTAINER_AMOUNT_ESTIMATED);
        assertThat(testProvider.getProductName()).isEqualTo(UPDATED_PRODUCT_NAME);
        assertThat(testProvider.getMembership()).isEqualTo(UPDATED_MEMBERSHIP);
        assertThat(testProvider.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    public void updateNonExistingProvider() throws Exception {
        int databaseSizeBeforeUpdate = providerRepository.findAll().size();

        // Create the Provider
        ProviderDTO providerDTO = providerMapper.toDto(provider);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProviderMockMvc.perform(put("/api/providers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(providerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Provider in the database
        List<Provider> providerList = providerRepository.findAll();
        assertThat(providerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteProvider() throws Exception {
        // Initialize the database
        providerRepository.save(provider);

        int databaseSizeBeforeDelete = providerRepository.findAll().size();

        // Get the provider
        restProviderMockMvc.perform(delete("/api/providers/{id}", provider.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Provider> providerList = providerRepository.findAll();
        assertThat(providerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Provider.class);
        Provider provider1 = new Provider();
        provider1.setId("id1");
        Provider provider2 = new Provider();
        provider2.setId(provider1.getId());
        assertThat(provider1).isEqualTo(provider2);
        provider2.setId("id2");
        assertThat(provider1).isNotEqualTo(provider2);
        provider1.setId(null);
        assertThat(provider1).isNotEqualTo(provider2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProviderDTO.class);
        ProviderDTO providerDTO1 = new ProviderDTO();
        providerDTO1.setId("id1");
        ProviderDTO providerDTO2 = new ProviderDTO();
        assertThat(providerDTO1).isNotEqualTo(providerDTO2);
        providerDTO2.setId(providerDTO1.getId());
        assertThat(providerDTO1).isEqualTo(providerDTO2);
        providerDTO2.setId("id2");
        assertThat(providerDTO1).isNotEqualTo(providerDTO2);
        providerDTO1.setId(null);
        assertThat(providerDTO1).isNotEqualTo(providerDTO2);
    }
}
