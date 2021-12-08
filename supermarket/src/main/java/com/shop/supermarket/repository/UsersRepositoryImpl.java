package com.shop.supermarket.repository;

import com.shop.supermarket.entity.Items;
import com.shop.supermarket.entity.Roles;
import com.shop.supermarket.entity.Users;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Component
@Transactional
public class UsersRepositoryImpl {


    EntityManager entityManager;
    public UsersRepositoryImpl(EntityManager theEntityManager)
    {
        entityManager=theEntityManager;
    }

    @SuppressWarnings("unused")
    @Transactional
    public List<Items> getOrdersList(String username) {
        Session currentSession = entityManager.unwrap(Session.class);
        Users tempUser = currentSession.get(Users.class,username);
        currentSession.close();
        return tempUser.getItems();
    }

    @SuppressWarnings("unused")
    @Transactional
    public void addItem(String username, int itemId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Items tempItem = currentSession.get(Items.class,itemId);
        Users tempUser = currentSession.get(Users.class,username);
        tempItem.addUser(tempUser);
        currentSession.saveOrUpdate(tempItem);
    }

    @SuppressWarnings("unused")
    @Transactional
    public void deleteItem(String username, int itemId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Users tempUser = currentSession.get(Users.class,username);
        Items tempItem = currentSession.get(Items.class,itemId);
        tempUser.getItems().remove(tempItem);
        currentSession.saveOrUpdate(tempUser);
    }

    @SuppressWarnings("unused")
    @Transactional
    public void updateData(String username, String password, String email, String phoneNumber, String address) {
        Session currentSession = entityManager.unwrap(Session.class);
        Users tempUser = currentSession.get(Users.class,username);
        tempUser.setPassword(password);
        tempUser.setEmail(email);
        tempUser.setPhoneNumber(phoneNumber);
        tempUser.setAddress(address);
        currentSession.saveOrUpdate(tempUser);
    }

    @SuppressWarnings("unused")
    @Transactional
    public void saveRole(String user, Roles role) {
        Session currentSession = entityManager.unwrap(Session.class);
        Roles tempRole = currentSession.get(Roles.class,role.getAuthority());
        Users tempUser = currentSession.get(Users.class,user);
        tempUser.addRole(tempRole);
        currentSession.saveOrUpdate(tempUser);
    }

    @SuppressWarnings("unused")
    @Transactional
    public void saveUser(Users user) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.save(user);
    }

    @SuppressWarnings("unused")
    @Transactional
    public Users findByUsername(String user) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(Users.class,user);
    }
}
