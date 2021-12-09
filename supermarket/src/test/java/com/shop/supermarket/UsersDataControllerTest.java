package com.shop.supermarket;
import com.shop.supermarket.controller.UsersDataController;
import com.shop.supermarket.converter.ItemsConverter;
import com.shop.supermarket.converter.UsersConverter;
import com.shop.supermarket.entity.Items;
import com.shop.supermarket.service.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;



@RunWith(SpringRunner.class)
@SpringBootTest
class UsersDataControllerTest {

    UsersDataController usersDataController;

    @Autowired
    ItemsConverter itemsConverter;

    @Autowired
    UsersConverter usersConverter;

    @MockBean
    UsersService usersService;

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
        List<Items> itemsList = new ArrayList<>();
        Items item1 = new Items(1,"fanta",10,"cocacola");
        Items item2 = new Items(2,"goodday",5,"britania");
        itemsList.add(item1);
        itemsList.add(item2);
        when(usersService.getOrdersList("Dummy")).thenReturn(itemsList);
        List<Items> orderedItems = usersService.getOrdersList("Dummy");
        assertEquals(2,orderedItems.size());
    }

    @Test
    void ordersList_printingOrdersOfUserWithEmptyOrdersList()
    {
        List<Items> itemsList = new ArrayList<>();
        when(usersService.getOrdersList("Dummy")).thenReturn(itemsList);
        List<Items> orderedItems = usersService.getOrdersList("Dummy");
        assertEquals(0,orderedItems.size());
    }
}