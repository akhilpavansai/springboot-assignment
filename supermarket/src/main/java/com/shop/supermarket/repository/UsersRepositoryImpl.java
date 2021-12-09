package com.shop.supermarket.repository;

import com.shop.supermarket.entity.Items;
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
    public Users findByUsername(String user) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(Users.class,user);
    }
}
