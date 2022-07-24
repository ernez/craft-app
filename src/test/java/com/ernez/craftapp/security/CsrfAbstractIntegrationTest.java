package com.ernez.craftapp.security;

import com.ernez.craftapp.dto.RegistrationRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@RunWith(SpringRunner.class)
public class CsrfAbstractIntegrationTest {

    @Autowired
    protected MockMvc mvc;

    protected RequestPostProcessor testUser() {
        return user("user").password("userPass").roles("USER");
    }

    protected String createRegistrationRequest() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(new RegistrationRequest(
            "firstName",
            "lastName",
                "email",
            "pass"));
    }
}
