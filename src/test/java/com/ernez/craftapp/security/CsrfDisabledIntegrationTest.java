package com.ernez.craftapp.security;

import com.ernez.craftapp.service.RegistrationService;
import com.ernez.craftapp.web.RegistrationController;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test-SecurityWithoutCsrfConfig")
@WebMvcTest(value = RegistrationController.class)
@AutoConfigureMockMvc(addFilters = true)
public class CsrfDisabledIntegrationTest extends CsrfAbstractIntegrationTesting {
    @MockBean
    private RegistrationService registrationService;

    @Test
    public void givenNotAuth_whenRegister_thenUnauthorized() throws Exception {
        mvc.perform(
                post("/api/v1/registration").contentType(MediaType.APPLICATION_JSON)
                        .content(createRegistrationRequest())
        ).andExpect(status().isUnauthorized()); // Could not ger isUnauthorized
    }

    @Test
    public void givenAuth_whenRegister_thenCreated() throws Exception {
        mvc.perform(
                post("/api/v1/registration").contentType(MediaType.APPLICATION_JSON)
                        .content(createRegistrationRequest())
                        .with(testUser())
        ).andExpect(status().isOk());
    }
}
