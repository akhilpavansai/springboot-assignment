package com.shop.supermarket.dto;

import com.shop.supermarket.entity.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;



@Getter
@Setter
@NoArgsConstructor
public class ItemsDTO {

    private int itemId;

    private String itemName;

    private int cost;

    private String company;

    private List<Users> users;

    public ItemsDTO(String itemName, int cost, String company) {
        this.itemName = itemName;
        this.cost = cost;
        this.company = company;
    }


    public void addUser(Users theUser)
    {
        if (users==null)
        {
            users=new ArrayList<>();
        }
        users.add(theUser);
    }

}
