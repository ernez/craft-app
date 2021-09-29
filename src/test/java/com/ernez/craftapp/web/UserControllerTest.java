package com.ernez.craftapp.web;

import com.ernez.craftapp.domain.UserDtoUtil;
import com.ernez.craftapp.domain.UserUtil;
import com.ernez.craftapp.dto.UserDto;
import com.ernez.craftapp.repository.AppUserRepository;
import com.ernez.craftapp.service.AppUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class UserControllerTest {
    //Important ! These appUserRepository, appUserService MUST be annotated with @MockBean, in order to be included in Spring Application Context
    @MockBean
    private AppUserRepository appUserRepository;
    @MockBean
    private AppUserService appUserService;

    @Autowired
    private MockMvc mvc;

    // This object will be magically initialized by the initFields method below.
    private JacksonTester<UserDto> json;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    void findAll() {
    }

    @Test
    void findAllActiveUsers() throws Exception {
        // given
        given(appUserRepository.findAllByEnabled(true))
                .willReturn(UserUtil.createDummyUserList());
        //when
        MockHttpServletResponse response = mvc.perform(
                get("/api/users/active")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
                .andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void findById() {
    }

    @Test
    void create() {
    }

    @Test
    void delete() {
    }
}
