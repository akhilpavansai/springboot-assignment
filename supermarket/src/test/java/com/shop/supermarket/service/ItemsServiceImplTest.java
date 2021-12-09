package com.shop.supermarket.service;

import com.shop.supermarket.entity.Items;
import com.shop.supermarket.repository.ItemsRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ItemsServiceImplTest {

    @MockBean
    ItemsRepository itemsRepository;

    @Autowired
    ItemsService itemsService;

    @Test
    void saveItem() {
        Items item = new Items(1,"kurkure",5,"kurkure");
        when(itemsRepository.save(item)).thenReturn(item);
        itemsService.saveItem(item);
        verify(itemsRepository,times(1)).save(item);
    }

    @Test
    void getAllItemsList() {
        when(itemsRepository.findAll()).thenReturn(Stream.of(new Items(1,"kurkure",5,"kurkure"),new Items(2,"goodday",10,"britania")).collect(Collectors.toList()));
        assertEquals(2,itemsService.getAllItemsList().size());
    }

    @Test
    void getItemById() {
        Items item = new Items(1,"kurkure",5,"kurkure");
        when(itemsRepository.getById(1)).thenReturn(item);
        assertEquals(item,itemsService.getItemById(1));
    }


    @Test
    void deleteItemById() {
        itemsService.deleteItemById(1);
        verify(itemsRepository,times(1)).deleteById(1);
    }

}