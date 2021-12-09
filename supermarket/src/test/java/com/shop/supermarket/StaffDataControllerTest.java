package com.shop.supermarket;


import com.shop.supermarket.controller.StaffDataController;
import com.shop.supermarket.converter.ItemsConverter;
import com.shop.supermarket.dto.ItemsDTO;
import com.shop.supermarket.entity.Items;
import com.shop.supermarket.entity.Users;
import com.shop.supermarket.repository.ItemsRepository;
import com.shop.supermarket.service.ItemsService;
import com.shop.supermarket.service.UsersService;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringRunner.class)
@SpringBootTest
class StaffDataControllerTest {
    private MockMvc mockMvc;

    StaffDataController staffDataController;

    @MockBean
    private ItemsService itemsService;

    @MockBean
    private UsersService usersService;

    @MockBean
    private ItemsRepository itemsRepository;

    @Autowired
    ItemsConverter itemsConverter;

    @BeforeEach
    void setup()
    {
        staffDataController = new StaffDataController();
        mockMvc= MockMvcBuilders.standaloneSetup(staffDataController).build();
    }

    @Test
    void contextLoads() throws Exception
    {
        assertThat(staffDataController).isNotNull();
    }


    @Test
    void showFormForAddItem() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/staff/addItem"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("item-form"));
    }


    @Test
    void deleteItemFromStockList()
    {
        Items items = new Items(1,"green lays",5,"lays");
        doNothing().when(itemsService).addNewItem(items);
        doNothing().when(itemsService).deleteItemById(1);
        itemsService.deleteItemById(1);
        verify(itemsService,times(1)).deleteItemById(1);
    }

    @Test
    void deleteStaffUser()
    {
        Users user = new Users("akhil","fun123","akhil@gmail.com","9701209751","hyderabad",(short)1);
        doNothing().when(usersService).saveUser(user);
        doNothing().when(usersService).deleteUser(user);
        usersService.deleteUser(user);
        verify(usersService,times(1)).deleteUser(user);
    }
}