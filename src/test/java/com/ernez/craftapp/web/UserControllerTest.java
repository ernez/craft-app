package com.ernez.craftapp.web;

import com.ernez.craftapp.domain.AppUserUtil;
import com.ernez.craftapp.domain.UserDtoUtil;
import com.ernez.craftapp.dto.UserDto;
import com.ernez.craftapp.repository.AppUserRepository;
import com.ernez.craftapp.service.AppUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class UserControllerTest {
    //These appUserRepository, appUserService MUST be annotated with @MockBean, in order to be included in Spring Application Context
    @MockBean
    private AppUserRepository appUserRepository;
    @MockBean
    private AppUserService appUserService;

    @Autowired
    private MockMvc mvc;

    @Test
    void findAll() {
    }

    @Test
    void findAllActiveUsers() throws Exception {
        // given
        given(appUserRepository.findAllByEnabled(true))
                .willReturn(AppUserUtil.createDummyUserList());
        //when
        MockHttpServletResponse response = mvc.perform(
                get("/api/users/active")
                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
                .andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void findById() {
    }

    @Test
    void givenInvalidUserDto_whenPut_thenInvalidRequest() throws Exception {
        //given
        long id = 1l;
        String firstName = "k";
        String lastName = "Lastname";
        UserDto userDto = UserDtoUtil.createDummyUserDto(1l, firstName, lastName);
        given(appUserRepository.findById(id))
                .willReturn(Optional.of(AppUserUtil.createDummyUser(id, firstName, lastName)));
        //when
        MockHttpServletResponse response = mvc.perform(
                put("/api/users" + "/1").contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDto))
        ).andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void delete() {
    }
}
