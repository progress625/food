package com.shaowei.food.web.rest;

import com.shaowei.food.FoodApp;

import com.shaowei.food.domain.AbstractUser;
import com.shaowei.food.repository.AbstractUserRepository;
import com.shaowei.food.service.AbstractUserService;
import com.shaowei.food.service.dto.AbstractUserDTO;
import com.shaowei.food.service.mapper.AbstractUserMapper;
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

import com.shaowei.food.domain.enumeration.Role;
/**
 * Test class for the AbstractUserResource REST controller.
 *
 * @see AbstractUserResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FoodApp.class)
public class AbstractUserResourceIntTest {

    private static final String DEFAULT_FIRSTNAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRSTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_LASTNAME = "AAAAAAAAAA";
    private static final String UPDATED_LASTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_GENDER = "BBBBBBBBBB";

    private static final String DEFAULT_POSITION = "AAAAAAAAAA";
    private static final String UPDATED_POSITION = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILEPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_WECHAT = "AAAAAAAAAA";
    private static final String UPDATED_WECHAT = "BBBBBBBBBB";

    private static final String DEFAULT_SKYPE = "AAAAAAAAAA";
    private static final String UPDATED_SKYPE = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NAME = "BBBBBBBBBB";

    private static final Role DEFAULT_ROLE = Role.PROVIDER_ADMINISTRATOR;
    private static final Role UPDATED_ROLE = Role.PROVIDER_SALES;

    @Autowired
    private AbstractUserRepository abstractUserRepository;

    @Autowired
    private AbstractUserMapper abstractUserMapper;

    @Autowired
    private AbstractUserService abstractUserService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restAbstractUserMockMvc;

    private AbstractUser abstractUser;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AbstractUserResource abstractUserResource = new AbstractUserResource(abstractUserService);
        this.restAbstractUserMockMvc = MockMvcBuilders.standaloneSetup(abstractUserResource)
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
    public static AbstractUser createEntity() {
        AbstractUser abstractUser = new AbstractUser()
            .firstname(DEFAULT_FIRSTNAME)
            .lastname(DEFAULT_LASTNAME)
            .gender(DEFAULT_GENDER)
            .position(DEFAULT_POSITION)
            .telephone(DEFAULT_TELEPHONE)
            .mobilephone(DEFAULT_MOBILEPHONE)
            .email(DEFAULT_EMAIL)
            .wechat(DEFAULT_WECHAT)
            .skype(DEFAULT_SKYPE)
            .companyName(DEFAULT_COMPANY_NAME)
            .role(DEFAULT_ROLE);
        return abstractUser;
    }

    @Before
    public void initTest() {
        abstractUserRepository.deleteAll();
        abstractUser = createEntity();
    }

    @Test
    public void createAbstractUser() throws Exception {
        int databaseSizeBeforeCreate = abstractUserRepository.findAll().size();

        // Create the AbstractUser
        AbstractUserDTO abstractUserDTO = abstractUserMapper.toDto(abstractUser);
        restAbstractUserMockMvc.perform(post("/api/abstract-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(abstractUserDTO)))
            .andExpect(status().isCreated());

        // Validate the AbstractUser in the database
        List<AbstractUser> abstractUserList = abstractUserRepository.findAll();
        assertThat(abstractUserList).hasSize(databaseSizeBeforeCreate + 1);
        AbstractUser testAbstractUser = abstractUserList.get(abstractUserList.size() - 1);
        assertThat(testAbstractUser.getFirstname()).isEqualTo(DEFAULT_FIRSTNAME);
        assertThat(testAbstractUser.getLastname()).isEqualTo(DEFAULT_LASTNAME);
        assertThat(testAbstractUser.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testAbstractUser.getPosition()).isEqualTo(DEFAULT_POSITION);
        assertThat(testAbstractUser.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testAbstractUser.getMobilephone()).isEqualTo(DEFAULT_MOBILEPHONE);
        assertThat(testAbstractUser.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testAbstractUser.getWechat()).isEqualTo(DEFAULT_WECHAT);
        assertThat(testAbstractUser.getSkype()).isEqualTo(DEFAULT_SKYPE);
        assertThat(testAbstractUser.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testAbstractUser.getRole()).isEqualTo(DEFAULT_ROLE);
    }

    @Test
    public void createAbstractUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = abstractUserRepository.findAll().size();

        // Create the AbstractUser with an existing ID
        abstractUser.setId("existing_id");
        AbstractUserDTO abstractUserDTO = abstractUserMapper.toDto(abstractUser);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAbstractUserMockMvc.perform(post("/api/abstract-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(abstractUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AbstractUser in the database
        List<AbstractUser> abstractUserList = abstractUserRepository.findAll();
        assertThat(abstractUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllAbstractUsers() throws Exception {
        // Initialize the database
        abstractUserRepository.save(abstractUser);

        // Get all the abstractUserList
        restAbstractUserMockMvc.perform(get("/api/abstract-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(abstractUser.getId())))
            .andExpect(jsonPath("$.[*].firstname").value(hasItem(DEFAULT_FIRSTNAME.toString())))
            .andExpect(jsonPath("$.[*].lastname").value(hasItem(DEFAULT_LASTNAME.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION.toString())))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE.toString())))
            .andExpect(jsonPath("$.[*].mobilephone").value(hasItem(DEFAULT_MOBILEPHONE.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].wechat").value(hasItem(DEFAULT_WECHAT.toString())))
            .andExpect(jsonPath("$.[*].skype").value(hasItem(DEFAULT_SKYPE.toString())))
            .andExpect(jsonPath("$.[*].companyName").value(hasItem(DEFAULT_COMPANY_NAME.toString())))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE.toString())));
    }
    
    @Test
    public void getAbstractUser() throws Exception {
        // Initialize the database
        abstractUserRepository.save(abstractUser);

        // Get the abstractUser
        restAbstractUserMockMvc.perform(get("/api/abstract-users/{id}", abstractUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(abstractUser.getId()))
            .andExpect(jsonPath("$.firstname").value(DEFAULT_FIRSTNAME.toString()))
            .andExpect(jsonPath("$.lastname").value(DEFAULT_LASTNAME.toString()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.position").value(DEFAULT_POSITION.toString()))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE.toString()))
            .andExpect(jsonPath("$.mobilephone").value(DEFAULT_MOBILEPHONE.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.wechat").value(DEFAULT_WECHAT.toString()))
            .andExpect(jsonPath("$.skype").value(DEFAULT_SKYPE.toString()))
            .andExpect(jsonPath("$.companyName").value(DEFAULT_COMPANY_NAME.toString()))
            .andExpect(jsonPath("$.role").value(DEFAULT_ROLE.toString()));
    }

    @Test
    public void getNonExistingAbstractUser() throws Exception {
        // Get the abstractUser
        restAbstractUserMockMvc.perform(get("/api/abstract-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateAbstractUser() throws Exception {
        // Initialize the database
        abstractUserRepository.save(abstractUser);

        int databaseSizeBeforeUpdate = abstractUserRepository.findAll().size();

        // Update the abstractUser
        AbstractUser updatedAbstractUser = abstractUserRepository.findById(abstractUser.getId()).get();
        updatedAbstractUser
            .firstname(UPDATED_FIRSTNAME)
            .lastname(UPDATED_LASTNAME)
            .gender(UPDATED_GENDER)
            .position(UPDATED_POSITION)
            .telephone(UPDATED_TELEPHONE)
            .mobilephone(UPDATED_MOBILEPHONE)
            .email(UPDATED_EMAIL)
            .wechat(UPDATED_WECHAT)
            .skype(UPDATED_SKYPE)
            .companyName(UPDATED_COMPANY_NAME)
            .role(UPDATED_ROLE);
        AbstractUserDTO abstractUserDTO = abstractUserMapper.toDto(updatedAbstractUser);

        restAbstractUserMockMvc.perform(put("/api/abstract-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(abstractUserDTO)))
            .andExpect(status().isOk());

        // Validate the AbstractUser in the database
        List<AbstractUser> abstractUserList = abstractUserRepository.findAll();
        assertThat(abstractUserList).hasSize(databaseSizeBeforeUpdate);
        AbstractUser testAbstractUser = abstractUserList.get(abstractUserList.size() - 1);
        assertThat(testAbstractUser.getFirstname()).isEqualTo(UPDATED_FIRSTNAME);
        assertThat(testAbstractUser.getLastname()).isEqualTo(UPDATED_LASTNAME);
        assertThat(testAbstractUser.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testAbstractUser.getPosition()).isEqualTo(UPDATED_POSITION);
        assertThat(testAbstractUser.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testAbstractUser.getMobilephone()).isEqualTo(UPDATED_MOBILEPHONE);
        assertThat(testAbstractUser.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testAbstractUser.getWechat()).isEqualTo(UPDATED_WECHAT);
        assertThat(testAbstractUser.getSkype()).isEqualTo(UPDATED_SKYPE);
        assertThat(testAbstractUser.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testAbstractUser.getRole()).isEqualTo(UPDATED_ROLE);
    }

    @Test
    public void updateNonExistingAbstractUser() throws Exception {
        int databaseSizeBeforeUpdate = abstractUserRepository.findAll().size();

        // Create the AbstractUser
        AbstractUserDTO abstractUserDTO = abstractUserMapper.toDto(abstractUser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAbstractUserMockMvc.perform(put("/api/abstract-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(abstractUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AbstractUser in the database
        List<AbstractUser> abstractUserList = abstractUserRepository.findAll();
        assertThat(abstractUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteAbstractUser() throws Exception {
        // Initialize the database
        abstractUserRepository.save(abstractUser);

        int databaseSizeBeforeDelete = abstractUserRepository.findAll().size();

        // Get the abstractUser
        restAbstractUserMockMvc.perform(delete("/api/abstract-users/{id}", abstractUser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AbstractUser> abstractUserList = abstractUserRepository.findAll();
        assertThat(abstractUserList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AbstractUser.class);
        AbstractUser abstractUser1 = new AbstractUser();
        abstractUser1.setId("id1");
        AbstractUser abstractUser2 = new AbstractUser();
        abstractUser2.setId(abstractUser1.getId());
        assertThat(abstractUser1).isEqualTo(abstractUser2);
        abstractUser2.setId("id2");
        assertThat(abstractUser1).isNotEqualTo(abstractUser2);
        abstractUser1.setId(null);
        assertThat(abstractUser1).isNotEqualTo(abstractUser2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AbstractUserDTO.class);
        AbstractUserDTO abstractUserDTO1 = new AbstractUserDTO();
        abstractUserDTO1.setId("id1");
        AbstractUserDTO abstractUserDTO2 = new AbstractUserDTO();
        assertThat(abstractUserDTO1).isNotEqualTo(abstractUserDTO2);
        abstractUserDTO2.setId(abstractUserDTO1.getId());
        assertThat(abstractUserDTO1).isEqualTo(abstractUserDTO2);
        abstractUserDTO2.setId("id2");
        assertThat(abstractUserDTO1).isNotEqualTo(abstractUserDTO2);
        abstractUserDTO1.setId(null);
        assertThat(abstractUserDTO1).isNotEqualTo(abstractUserDTO2);
    }
}
