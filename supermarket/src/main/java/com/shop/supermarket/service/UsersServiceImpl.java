package com.shop.supermarket.service;

import com.shop.supermarket.converter.RolesConverter;
import com.shop.supermarket.converter.UsersConverter;
import com.shop.supermarket.dto.RolesDTO;
import com.shop.supermarket.dto.UsersDTO;
import com.shop.supermarket.entity.Users;
import com.shop.supermarket.dao.UsersDAO;
import com.shop.supermarket.entity.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService{

    private UsersDAO usersDAO;


    @Autowired
    private RolesConverter rolesConverter;

    @Autowired
    public UsersServiceImpl(UsersDAO theUsersDAO)
    {
        usersDAO=theUsersDAO;
    }


    @Override
    public List<Items> getOrdersList(String username) {
        return usersDAO.getOrdersList(username);
    }

    @Override
    public void addItem(String username,int itemId) {
        usersDAO.addItem(username,itemId);
    }

    @Override
    public void deleteItem(String username, int itemId) {
        usersDAO.deleteItem(username,itemId);
    }

    @Override
    public void updateData(String username,String password, String email,String phoneNumber,String address) {
        usersDAO.updateData(username,password,email,phoneNumber,address);
    }

    @Override
    public void save(Users theUser) {
        usersDAO.registerUser(theUser);
    }

    @Override
    public void saveRole(String user, RolesDTO role) {
        usersDAO.saveRole(user,rolesConverter.dtoToEntity(role));
    }

    @Override
    public Users findByUsername(String username) {
        return usersDAO.getUser(username);
    }
}
