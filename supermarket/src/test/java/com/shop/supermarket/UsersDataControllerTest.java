package com.shop.supermarket;


import com.shop.supermarket.controller.StaffDataController;
import com.shop.supermarket.controller.UsersDataController;
import com.shop.supermarket.converter.ItemsConverter;
import com.shop.supermarket.converter.UsersConverter;
import com.shop.supermarket.dto.ItemsDTO;
import com.shop.supermarket.dto.UsersDTO;
import com.shop.supermarket.entity.Items;
import com.shop.supermarket.entity.Users;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringRunner.class)
@SpringBootTest
class UsersDataControllerTest {

    UsersDataController usersDataController;

    @Autowired
    ItemsConverter itemsConverter;

    @Autowired
    UsersConverter usersConverter;

    @BeforeEach
    void setup()
    {
        usersDataController = new UsersDataController();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(usersDataController).build();
    }

    @Test
    void contextLoads() throws Exception
    {
        assertThat(usersDataController).isNotNull();
    }

    @Test
    void ordersList_printingOrdersOfUser()
    {
        
    }
}