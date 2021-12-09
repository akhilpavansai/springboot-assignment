package com.shop.supermarket.service;

import com.shop.supermarket.entity.Items;
import com.shop.supermarket.entity.Roles;
import com.shop.supermarket.entity.Users;

import java.util.List;

public interface UsersService {
    public List<Items> getOrdersList(String username);

    public void addItem(String username,int itemId);

    public void deleteItem(String username,int itemId);

    public Users findByUsername(String username);

    public void updateUser(String username, String password, String email, String phoneNumber, String address);

    public void deleteUser(Users user);

    public void saveUser(Users theUser);

    public void saveRole(String user, Roles role);
}
