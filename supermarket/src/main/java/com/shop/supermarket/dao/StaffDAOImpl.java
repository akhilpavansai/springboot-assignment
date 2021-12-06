package com.shop.supermarket.dao;

import com.shop.supermarket.entity.Items;
import com.shop.supermarket.entity.Users;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class StaffDAOImpl implements StaffDAO{
    EntityManager entityManager;

    @Autowired
    public StaffDAOImpl(EntityManager theEntityManager)
    {
        entityManager=theEntityManager;
    }

    @Override
    @Transactional
    public void deleteOrder(int itemId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Items tempItem = currentSession.get(Items.class,itemId);
        currentSession.remove(tempItem);
    }

    @Override
    @Transactional
    public void addNewItem(String itemName, int cost, String company) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Items> theQuery =  currentSession.createQuery("from Items where itemName =: name");
        theQuery.setParameter("name",itemName);
        List<Items> tempList=theQuery.getResultList();
        if(tempList==null)
        {
            currentSession.save(new Items(itemName,cost,company));
        }
    }

}
