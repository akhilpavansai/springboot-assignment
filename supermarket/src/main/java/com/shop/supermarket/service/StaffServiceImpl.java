package com.shop.supermarket.service;

import com.shop.supermarket.dao.StaffDAO;
import com.shop.supermarket.dao.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements StaffService{

    private StaffDAO staffDAO;

    @Autowired
    public StaffServiceImpl(StaffDAO theStaffDAO)
    {
        staffDAO=theStaffDAO;
    }

    @Override
    public void deleteItem(int itemId) {
        staffDAO.deleteOrder(itemId);
    }

    @Override
    public void addNewItem(String itemName, int cost, String company) {
        staffDAO.addNewItem(itemName,cost,company);
    }
}
