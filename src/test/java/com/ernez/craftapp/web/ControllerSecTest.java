//package com.ernez.craftapp.web;
//
//import org.infobip.security.model.v2.User;
//import org.infobip.security.model.v2.user.AuthData;
//import org.infobip.voice.sbc.call.tracer.remote.TracerServiceProxy;
//import org.infobip.voice.sbc.call.tracer.service.PaginationTracerService;
//import org.infobip.voice.sbc.call.tracer.service.UserContextHolder;
//import org.infobip.voice.sbc.call.tracer.service.impl.AuditLogService;
//import org.junit.Ignore;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ExtendWith(SpringExtension.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@ActiveProfiles(profiles = "test")
//@Ignore
//class ControllerSecTest {
//    @Autowired
//    private WebApplicationContext context;
//
//    private MockMvc mvc;
//
//    @MockBean
//    private TracerServiceProxy tracerServiceProxy;
//    @MockBean
//    private PaginationTracerService paginationTracerService;
//    @MockBean
//    private UserContextHolder userContextHolder;
//    @MockBean
//    private AuditLogService auditLogService;
//
//    @BeforeAll
//    public void setup() {
//        mvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .apply(springSecurity())
//                .build();
//    }
//
//    // ... other methods
//
//    @WithMockUser(username = "ecatovic", authorities = {"RVoiceTracer", "RVoiceTracerAudit"})
//    @Test
//    public void givenProperAuthorities_shouldSucceedWith200() throws Exception {
//        mvc.perform(get("/api/voice-tracer/locations").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @WithMockUser(username = "ecatovic", authorities = {"WrongAuthority1", "WrongAuthority2"})
//    @Test
//    public void givenProperAuthorities_shouldSucceedWithError() throws Exception {
//        mvc.perform(get("/api/voice-tracer/locations").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    private User buildTestUser() {
//        User user = new User();
//        user.setFirstName("TestName");
//        user.setLastName("TestLastName");
//
//        AuthData authData = new AuthData();
//        authData.setUsername("ecatovic");
//        user.setAuthData(authData);
//        return user;
//    }
//}
