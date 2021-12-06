package com.shop.supermarket.service;

public interface StaffService {
    public void deleteItem(int itemId);

    void addNewItem(String itemName, int cost, String company);
}
