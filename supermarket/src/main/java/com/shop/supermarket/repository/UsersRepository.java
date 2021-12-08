package com.shop.supermarket.repository;

import com.shop.supermarket.entity.Items;
import com.shop.supermarket.entity.Roles;
import com.shop.supermarket.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users,String> {
    public List<Items> getOrdersList(String username);

    public void addItem(String username,int itemId);

    public void deleteItem(String username,int itemId);

    public void updateData(String username,String password, String email,String phoneNumber,String address);

    public void saveRole(String user, Roles role);
}
