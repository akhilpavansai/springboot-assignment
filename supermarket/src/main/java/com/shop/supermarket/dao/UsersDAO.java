package com.shop.supermarket.dao;



import com.shop.supermarket.entity.Items;
import com.shop.supermarket.entity.Roles;
import com.shop.supermarket.entity.Users;

import java.util.List;

public interface UsersDAO {
    public List<Items> getOrdersList(String username);

    public void addItem(String username,int itemId);

    public void deleteItem(String username,int itemId);

    public Users getUser(String username);

    public void updateData(String username,String password, String email,String phoneNumber,String address);

    public void registerUser(Users theUser);

    public void saveRole(String user, Roles role);
}