package com.shop.supermarket;


import com.shop.supermarket.controller.StaffDataController;
import com.shop.supermarket.repository.ItemsRepository;
import com.shop.supermarket.service.ItemsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringRunner.class)
@SpringBootTest
class StaffDataControllerTest {
    private MockMvc mockMvc;

    StaffDataController staffDataController;

    @Autowired
    ItemsService itemsService;

    @MockBean
    ItemsRepository itemsRepository;

    @BeforeEach
    void setup()
    {
        staffDataController = new StaffDataController();
        mockMvc= MockMvcBuilders.standaloneSetup(staffDataController).build();
    }

    @Test
    void showFormForAddItem() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/staff/addItem"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(model().attribute("item",StaffDataController.itemsDTO))
                .andExpect(view().name("item-form"));
    }




}