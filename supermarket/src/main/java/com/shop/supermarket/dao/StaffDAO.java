package com.shop.supermarket.dao;

public interface StaffDAO {
    public void deleteOrder(int itemId);

    void addNewItem(String itemName, int cost, String company);
}
