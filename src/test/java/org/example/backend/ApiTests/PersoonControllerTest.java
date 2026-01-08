//package org.example.backend.ApiTests;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.example.backend.Domain.PersoonModel;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(PersoonController.class)
//class PersoonControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private PersoonService service;
//
//    @Test
//    void testRegisterEndpoint() throws Exception {
//        PersoonDTO dto = new PersoonDTO("john_doe", 30, "secret123", "john@example.com");
//
//        when(service.createPersoon(any(PersoonDTO.class)))
//                .thenReturn(new PersoonModel(1, "john_doe", 30, "hashedSecret", "john@example.com"));
//
//        mockMvc.perform(post("/api/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(dto)))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Formulier opgeslagen voor: john_doe"));
//    }
//}
