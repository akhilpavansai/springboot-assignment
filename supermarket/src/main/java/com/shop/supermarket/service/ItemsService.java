package com.shop.supermarket.service;

import com.shop.supermarket.entity.Items;

import java.util.List;

public interface ItemsService {

    //create in CRUD
    //update in CRUD
    public void saveItem(Items items);

    //read in CRUD
    public List<Items> getAllItemsList();

    //read in CRUD
    public Items getItemById(int id);

    //delete in CRUD
    public void deleteItemById(int itemId);

}
