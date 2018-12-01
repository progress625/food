package com.shaowei.food.web.rest;

import com.shaowei.food.FoodApp;

import com.shaowei.food.domain.Buyer;
import com.shaowei.food.repository.BuyerRepository;
import com.shaowei.food.service.BuyerService;
import com.shaowei.food.service.dto.BuyerDTO;
import com.shaowei.food.service.mapper.BuyerMapper;
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

import java.math.BigDecimal;
import java.util.List;


import static com.shaowei.food.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.shaowei.food.domain.enumeration.Membership;
import com.shaowei.food.domain.enumeration.Status;
/**
 * Test class for the BuyerResource REST controller.
 *
 * @see BuyerResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FoodApp.class)
public class BuyerResourceIntTest {

    private static final String DEFAULT_COMPANY_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESS_LICENSE = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_LICENSE = "BBBBBBBBBB";

    private static final Integer DEFAULT_CONTAINER_AMOUNT_ESTIMATED = 1;
    private static final Integer UPDATED_CONTAINER_AMOUNT_ESTIMATED = 2;

    private static final Integer DEFAULT_CREDIBILITY_LEVEL = 1;
    private static final Integer UPDATED_CREDIBILITY_LEVEL = 2;

    private static final BigDecimal DEFAULT_GUARANTEE_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_GUARANTEE_AMOUNT = new BigDecimal(2);

    private static final Float DEFAULT_PREPAYMENT_PERCENT = 1F;
    private static final Float UPDATED_PREPAYMENT_PERCENT = 2F;

    private static final String DEFAULT_PAYMENT_METHOD = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_METHOD = "BBBBBBBBBB";

    private static final Membership DEFAULT_MEMBERSHIP = Membership.NORMAL;
    private static final Membership UPDATED_MEMBERSHIP = Membership.VIP;

    private static final Status DEFAULT_STATUS = Status.DISABLE;
    private static final Status UPDATED_STATUS = Status.VALIDATED;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private BuyerMapper buyerMapper;

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restBuyerMockMvc;

    private Buyer buyer;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BuyerResource buyerResource = new BuyerResource(buyerService);
        this.restBuyerMockMvc = MockMvcBuilders.standaloneSetup(buyerResource)
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
    public static Buyer createEntity() {
        Buyer buyer = new Buyer()
            .companyNumber(DEFAULT_COMPANY_NUMBER)
            .companyName(DEFAULT_COMPANY_NAME)
            .businessLicense(DEFAULT_BUSINESS_LICENSE)
            .containerAmountEstimated(DEFAULT_CONTAINER_AMOUNT_ESTIMATED)
            .credibilityLevel(DEFAULT_CREDIBILITY_LEVEL)
            .guaranteeAmount(DEFAULT_GUARANTEE_AMOUNT)
            .prepaymentPercent(DEFAULT_PREPAYMENT_PERCENT)
            .paymentMethod(DEFAULT_PAYMENT_METHOD)
            .membership(DEFAULT_MEMBERSHIP)
            .status(DEFAULT_STATUS);
        return buyer;
    }

    @Before
    public void initTest() {
        buyerRepository.deleteAll();
        buyer = createEntity();
    }

    @Test
    public void createBuyer() throws Exception {
        int databaseSizeBeforeCreate = buyerRepository.findAll().size();

        // Create the Buyer
        BuyerDTO buyerDTO = buyerMapper.toDto(buyer);
        restBuyerMockMvc.perform(post("/api/buyers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buyerDTO)))
            .andExpect(status().isCreated());

        // Validate the Buyer in the database
        List<Buyer> buyerList = buyerRepository.findAll();
        assertThat(buyerList).hasSize(databaseSizeBeforeCreate + 1);
        Buyer testBuyer = buyerList.get(buyerList.size() - 1);
        assertThat(testBuyer.getCompanyNumber()).isEqualTo(DEFAULT_COMPANY_NUMBER);
        assertThat(testBuyer.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testBuyer.getBusinessLicense()).isEqualTo(DEFAULT_BUSINESS_LICENSE);
        assertThat(testBuyer.getContainerAmountEstimated()).isEqualTo(DEFAULT_CONTAINER_AMOUNT_ESTIMATED);
        assertThat(testBuyer.getCredibilityLevel()).isEqualTo(DEFAULT_CREDIBILITY_LEVEL);
        assertThat(testBuyer.getGuaranteeAmount()).isEqualTo(DEFAULT_GUARANTEE_AMOUNT);
        assertThat(testBuyer.getPrepaymentPercent()).isEqualTo(DEFAULT_PREPAYMENT_PERCENT);
        assertThat(testBuyer.getPaymentMethod()).isEqualTo(DEFAULT_PAYMENT_METHOD);
        assertThat(testBuyer.getMembership()).isEqualTo(DEFAULT_MEMBERSHIP);
        assertThat(testBuyer.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    public void createBuyerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = buyerRepository.findAll().size();

        // Create the Buyer with an existing ID
        buyer.setId("existing_id");
        BuyerDTO buyerDTO = buyerMapper.toDto(buyer);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBuyerMockMvc.perform(post("/api/buyers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buyerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Buyer in the database
        List<Buyer> buyerList = buyerRepository.findAll();
        assertThat(buyerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllBuyers() throws Exception {
        // Initialize the database
        buyerRepository.save(buyer);

        // Get all the buyerList
        restBuyerMockMvc.perform(get("/api/buyers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(buyer.getId())))
            .andExpect(jsonPath("$.[*].companyNumber").value(hasItem(DEFAULT_COMPANY_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].companyName").value(hasItem(DEFAULT_COMPANY_NAME.toString())))
            .andExpect(jsonPath("$.[*].businessLicense").value(hasItem(DEFAULT_BUSINESS_LICENSE.toString())))
            .andExpect(jsonPath("$.[*].containerAmountEstimated").value(hasItem(DEFAULT_CONTAINER_AMOUNT_ESTIMATED)))
            .andExpect(jsonPath("$.[*].credibilityLevel").value(hasItem(DEFAULT_CREDIBILITY_LEVEL)))
            .andExpect(jsonPath("$.[*].guaranteeAmount").value(hasItem(DEFAULT_GUARANTEE_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].prepaymentPercent").value(hasItem(DEFAULT_PREPAYMENT_PERCENT.doubleValue())))
            .andExpect(jsonPath("$.[*].paymentMethod").value(hasItem(DEFAULT_PAYMENT_METHOD.toString())))
            .andExpect(jsonPath("$.[*].membership").value(hasItem(DEFAULT_MEMBERSHIP.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    public void getBuyer() throws Exception {
        // Initialize the database
        buyerRepository.save(buyer);

        // Get the buyer
        restBuyerMockMvc.perform(get("/api/buyers/{id}", buyer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(buyer.getId()))
            .andExpect(jsonPath("$.companyNumber").value(DEFAULT_COMPANY_NUMBER.toString()))
            .andExpect(jsonPath("$.companyName").value(DEFAULT_COMPANY_NAME.toString()))
            .andExpect(jsonPath("$.businessLicense").value(DEFAULT_BUSINESS_LICENSE.toString()))
            .andExpect(jsonPath("$.containerAmountEstimated").value(DEFAULT_CONTAINER_AMOUNT_ESTIMATED))
            .andExpect(jsonPath("$.credibilityLevel").value(DEFAULT_CREDIBILITY_LEVEL))
            .andExpect(jsonPath("$.guaranteeAmount").value(DEFAULT_GUARANTEE_AMOUNT.intValue()))
            .andExpect(jsonPath("$.prepaymentPercent").value(DEFAULT_PREPAYMENT_PERCENT.doubleValue()))
            .andExpect(jsonPath("$.paymentMethod").value(DEFAULT_PAYMENT_METHOD.toString()))
            .andExpect(jsonPath("$.membership").value(DEFAULT_MEMBERSHIP.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    public void getNonExistingBuyer() throws Exception {
        // Get the buyer
        restBuyerMockMvc.perform(get("/api/buyers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateBuyer() throws Exception {
        // Initialize the database
        buyerRepository.save(buyer);

        int databaseSizeBeforeUpdate = buyerRepository.findAll().size();

        // Update the buyer
        Buyer updatedBuyer = buyerRepository.findById(buyer.getId()).get();
        updatedBuyer
            .companyNumber(UPDATED_COMPANY_NUMBER)
            .companyName(UPDATED_COMPANY_NAME)
            .businessLicense(UPDATED_BUSINESS_LICENSE)
            .containerAmountEstimated(UPDATED_CONTAINER_AMOUNT_ESTIMATED)
            .credibilityLevel(UPDATED_CREDIBILITY_LEVEL)
            .guaranteeAmount(UPDATED_GUARANTEE_AMOUNT)
            .prepaymentPercent(UPDATED_PREPAYMENT_PERCENT)
            .paymentMethod(UPDATED_PAYMENT_METHOD)
            .membership(UPDATED_MEMBERSHIP)
            .status(UPDATED_STATUS);
        BuyerDTO buyerDTO = buyerMapper.toDto(updatedBuyer);

        restBuyerMockMvc.perform(put("/api/buyers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buyerDTO)))
            .andExpect(status().isOk());

        // Validate the Buyer in the database
        List<Buyer> buyerList = buyerRepository.findAll();
        assertThat(buyerList).hasSize(databaseSizeBeforeUpdate);
        Buyer testBuyer = buyerList.get(buyerList.size() - 1);
        assertThat(testBuyer.getCompanyNumber()).isEqualTo(UPDATED_COMPANY_NUMBER);
        assertThat(testBuyer.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testBuyer.getBusinessLicense()).isEqualTo(UPDATED_BUSINESS_LICENSE);
        assertThat(testBuyer.getContainerAmountEstimated()).isEqualTo(UPDATED_CONTAINER_AMOUNT_ESTIMATED);
        assertThat(testBuyer.getCredibilityLevel()).isEqualTo(UPDATED_CREDIBILITY_LEVEL);
        assertThat(testBuyer.getGuaranteeAmount()).isEqualTo(UPDATED_GUARANTEE_AMOUNT);
        assertThat(testBuyer.getPrepaymentPercent()).isEqualTo(UPDATED_PREPAYMENT_PERCENT);
        assertThat(testBuyer.getPaymentMethod()).isEqualTo(UPDATED_PAYMENT_METHOD);
        assertThat(testBuyer.getMembership()).isEqualTo(UPDATED_MEMBERSHIP);
        assertThat(testBuyer.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    public void updateNonExistingBuyer() throws Exception {
        int databaseSizeBeforeUpdate = buyerRepository.findAll().size();

        // Create the Buyer
        BuyerDTO buyerDTO = buyerMapper.toDto(buyer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBuyerMockMvc.perform(put("/api/buyers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buyerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Buyer in the database
        List<Buyer> buyerList = buyerRepository.findAll();
        assertThat(buyerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteBuyer() throws Exception {
        // Initialize the database
        buyerRepository.save(buyer);

        int databaseSizeBeforeDelete = buyerRepository.findAll().size();

        // Get the buyer
        restBuyerMockMvc.perform(delete("/api/buyers/{id}", buyer.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Buyer> buyerList = buyerRepository.findAll();
        assertThat(buyerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Buyer.class);
        Buyer buyer1 = new Buyer();
        buyer1.setId("id1");
        Buyer buyer2 = new Buyer();
        buyer2.setId(buyer1.getId());
        assertThat(buyer1).isEqualTo(buyer2);
        buyer2.setId("id2");
        assertThat(buyer1).isNotEqualTo(buyer2);
        buyer1.setId(null);
        assertThat(buyer1).isNotEqualTo(buyer2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BuyerDTO.class);
        BuyerDTO buyerDTO1 = new BuyerDTO();
        buyerDTO1.setId("id1");
        BuyerDTO buyerDTO2 = new BuyerDTO();
        assertThat(buyerDTO1).isNotEqualTo(buyerDTO2);
        buyerDTO2.setId(buyerDTO1.getId());
        assertThat(buyerDTO1).isEqualTo(buyerDTO2);
        buyerDTO2.setId("id2");
        assertThat(buyerDTO1).isNotEqualTo(buyerDTO2);
        buyerDTO1.setId(null);
        assertThat(buyerDTO1).isNotEqualTo(buyerDTO2);
    }
}
