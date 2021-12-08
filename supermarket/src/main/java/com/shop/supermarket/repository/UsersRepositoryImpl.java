package com.shop.supermarket.repository;

import com.shop.supermarket.entity.Items;
import com.shop.supermarket.entity.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class UsersRepositoryImpl {

    @Autowired
    EntityManager entityManager;
    
    @Override
    public List<Items> getOrdersList(String username) {
        return null;
    }

    @Override
    public void addItem(String username, int itemId) {

    }

    @Override
    public void deleteItem(String username, int itemId) {

    }

    @Override
    public void updateData(String username, String password, String email, String phoneNumber, String address) {

    }

    @Override
    public void saveRole(String user, Roles role) {

    }
}
