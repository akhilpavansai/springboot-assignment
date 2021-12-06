package com.shop.supermarket.service;

import com.shop.supermarket.dao.ItemsDAO;
import com.shop.supermarket.entity.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsServiceImpl implements ItemsService{
    ItemsDAO itemsDAO;

    @Autowired
    public ItemsServiceImpl(ItemsDAO theItemsDAO)
    {
        itemsDAO=theItemsDAO;
    }

    @Override
    public List<Items> getAllItemsList() {
        return itemsDAO.getAllItemsList();
    }
}
