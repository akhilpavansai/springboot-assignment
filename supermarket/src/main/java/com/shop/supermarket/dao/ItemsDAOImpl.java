package com.shop.supermarket.dao;


import com.shop.supermarket.entity.Items;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ItemsDAOImpl implements ItemsDAO{
    EntityManager entityManager;

    @Autowired
    public  ItemsDAOImpl(EntityManager theEntityManager)
    {
        entityManager=theEntityManager;
    }

    @Override
    @Transactional
    public List<Items> getAllItemsList() {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.createQuery("from Items ").getResultList();
    }
}
