package com.shop.supermarket;

import com.shop.supermarket.controller.BasicController;

import org.junit.jupiter.api.Test;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;



class BasicControllerTest {

    private MockMvc mockMvc;

    @org.junit.jupiter.api.BeforeEach
    public void setup()
    {
        BasicController basicController = new BasicController();
        mockMvc= MockMvcBuilders.standaloneSetup(basicController).build();
    }

    @Test
    void shouldReturnIndexView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/loginPage"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("login-page"));
    }

    @Test
    void failHandlerShouldReturnView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/access-denied"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("denied"));
    }

    @Test
    void shouldReturnRolePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/role"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("role-page"));
    }

}