package com.shop.supermarket.service;

import com.shop.supermarket.entity.Items;
import com.shop.supermarket.entity.Users;

import java.util.List;

public interface UsersService {
    //create in CRUD
    public void saveUser(Users theUser);

    //read in CRUD
    public List<Items> getOrdersList(String username);

    //read in CRUD
    public Users findByUsername(String username);

    //update in CRUD
    public void updateUser(String username, String password, String email, String phoneNumber, String address);

    //delete in CRUD
    public void deleteUser(Users user);

}
