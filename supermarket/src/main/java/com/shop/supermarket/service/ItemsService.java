package com.shop.supermarket.service;

import com.shop.supermarket.entity.Items;

import java.util.List;

public interface ItemsService {
    public List<Items> getAllItemsList();

    public void deleteItemById(int itemId);

    public void addNewItem(Items items);
}
