package com.elektra.orchestrator_service;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class OrchestratorControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void addUserTest() throws Exception {
        JSONObject request = new JSONObject();
        request.put("name", "Jorge Muñiz");
        request.put("email", "jorge.muniz@gmail.com");
        request.put("image", "https://parispasoapasotours.com/wp-content/uploads/2022/12/tour-paris-nocturno.jpg");
        request.put("password", "password");
    
        JSONObject response = request;
        response.put("id", 1);
    
        mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                .content(request.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(response.toString()))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
    
    @Test
    void validateUserTest() throws Exception {
        JSONObject request = new JSONObject();
        request.put("name", "");
        request.put("email", "jorge.muniz");
        request.put("image", "https://parispasoapasotours.com/wp-content/uploads/2022/12/tour-paris-nocturno.jpg");
        request.put("password", "password");
    
        mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                .content(request.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Nombre no debe estar vacío"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("Correo debe tener un formato válido"))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}