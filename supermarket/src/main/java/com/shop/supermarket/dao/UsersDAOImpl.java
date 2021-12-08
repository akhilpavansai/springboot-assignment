package com.shop.supermarket.dao;


import com.shop.supermarket.entity.Items;
import com.shop.supermarket.entity.Roles;
import com.shop.supermarket.entity.Users;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UsersDAOImpl implements UsersDAO {

    EntityManager entityManager;

    @Autowired
    public UsersDAOImpl(EntityManager theEntityManager)
    {
        entityManager=theEntityManager;
    }


    @Override
    @Transactional
    public List<Items> getOrdersList(String username) {
        Session currentSession = entityManager.unwrap(Session.class);
        Users tempUser = currentSession.get(Users.class,username);
        currentSession.close();
        return tempUser.getItems();
    }

    @Override
    @Transactional
    public void addItem(String username,int itemId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Items tempItem = currentSession.get(Items.class,itemId);
        Users tempUser = currentSession.get(Users.class,username);
        tempItem.addUser(tempUser);
        currentSession.saveOrUpdate(tempItem);

    }

    @Override
    @Transactional
    public void deleteItem(String username, int itemId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Users tempUser = currentSession.get(Users.class,username);
        Items tempItem = currentSession.get(Items.class,itemId);
        tempUser.getItems().remove(tempItem);
        currentSession.saveOrUpdate(tempUser);
    }

    @Override
    @Transactional
    public Users getUser(String username) {
        return entityManager.unwrap(Session.class).find(Users.class,username);
    }

    @Override
    @Transactional
    public void updateData(String username,String password, String email,String phoneNumber,String address) {
        Session currentSession = entityManager.unwrap(Session.class);
        Users tempUser = currentSession.get(Users.class,username);
        tempUser.setPassword(password);
        tempUser.setEmail(email);
        tempUser.setPhoneNumber(phoneNumber);
        tempUser.setAddress(address);
        currentSession.saveOrUpdate(tempUser);
    }

    @Override
    @Transactional
    public void registerUser(Users theUser) {
        Session currentSession = entityManager.unwrap(Session.class);
        theUser.setId(0);
        currentSession.save(theUser);
        currentSession.close();
    }

    @Override
    @Transactional
    public void saveRole(String user, Roles role) {
        Session currentSession = entityManager.unwrap(Session.class);
        Roles tempRole = currentSession.get(Roles.class,role.getAuthority());
        Users tempUser = currentSession.get(Users.class,user);
        tempUser.addRole(tempRole);
        currentSession.saveOrUpdate(tempUser);
    }
}
