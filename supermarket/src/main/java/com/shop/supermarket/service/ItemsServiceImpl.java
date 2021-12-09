package com.shop.supermarket.service;

import com.shop.supermarket.entity.Items;
import com.shop.supermarket.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsServiceImpl implements ItemsService{

    ItemsRepository itemsRepository;

    @Autowired
    public ItemsServiceImpl(ItemsRepository theItemsRepository)
    {
        itemsRepository=theItemsRepository;
    }

    @Override
    public List<Items> getAllItemsList() {
        return itemsRepository.findAll();
    }

    @Override
    public Items getItemById(int id) {
        return itemsRepository.getById(id);
    }

    @Override
    public void deleteItemById(int itemId) {
        itemsRepository.deleteById(itemId);
    }

    @Override
    public void addNewItem(Items items) {
        itemsRepository.save(items);
    }

    @Override
    public void updateItem(Items item) {
        itemsRepository.updateItem(item);
    }
}
