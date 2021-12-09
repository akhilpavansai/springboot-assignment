package com.shop.supermarket.repository;


import com.shop.supermarket.entity.Items;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
@Component
@Transactional
public class ItemsRepositoryImpl {

    EntityManager entityManager;
    public ItemsRepositoryImpl(EntityManager theEntityManager)
    {
        entityManager=theEntityManager;
    }

    @SuppressWarnings("unused")
    @Transactional
    public void updateItem(Items item) {
        Session currentSession = entityManager.unwrap(Session.class);
        Items tempItem = currentSession.get(Items.class,item.getItemId());
        tempItem.setCost(item.getCost());
        tempItem.setCompany(item.getCompany());
        currentSession.saveOrUpdate(tempItem);
    }
}
