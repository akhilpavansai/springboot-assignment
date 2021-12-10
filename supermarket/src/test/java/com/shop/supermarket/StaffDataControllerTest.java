package com.shop.supermarket;


import com.shop.supermarket.controller.StaffDataController;
import com.shop.supermarket.converter.ItemsConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringRunner.class)
@SpringBootTest
class StaffDataControllerTest {
    private MockMvc mockMvc;

    StaffDataController staffDataController;

    @Autowired
    ItemsConverter itemsConverter;

    @BeforeEach
    void setup()
    {
        staffDataController = new StaffDataController();
        mockMvc= MockMvcBuilders.standaloneSetup(staffDataController).build();
    }

    @Test
    void contextLoads()
    {
        assertThat(staffDataController).isNotNull();
    }


    @Test
    void showFormForAddItem() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/staff/addItem"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("item-form"));
    }

}