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
public class CsrfAbstractIntegrationTesting {

    @Autowired
    protected MockMvc mvc;

    protected RequestPostProcessor testUser() {
        return user("user1").password("user1Pass").roles("USER");
    }

    protected String createRegistrationRequest() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(new RegistrationRequest(
            "firstName",
            "lastName",
                "enacatovis@gmail.com",
            "pass"));
    }
}
