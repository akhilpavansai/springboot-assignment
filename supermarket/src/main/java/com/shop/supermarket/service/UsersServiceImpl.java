package com.shop.supermarket.service;

import com.shop.supermarket.entity.Roles;
import com.shop.supermarket.entity.Users;
import com.shop.supermarket.entity.Items;
import com.shop.supermarket.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService{

    private UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository theUsersRepository)
    {
        usersRepository =theUsersRepository;
    }


    @Override
    public List<Items> getOrdersList(String username) {
        return usersRepository.getOrdersList(username);
    }

    @Override
    public void addItem(String username,int itemId) {
        usersRepository.addItem(username,itemId);
    }

    @Override
    public void deleteItem(String username, int itemId) {
        usersRepository.deleteItem(username,itemId);
    }

    @Override
    public void updateData(String username,String password, String email,String phoneNumber,String address) {
        usersRepository.updateData(username,password,email,phoneNumber,address);
    }

    @Override
    public void saveUser(Users theUser) {
        usersRepository.saveUser(theUser);
    }

    @Override
    public void saveRole(String user, Roles role) {
        usersRepository.saveRole(user,role);
    }

    @Override
    public Users findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }
}
