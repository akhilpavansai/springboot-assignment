package com.shop.supermarket.dto;


import com.shop.supermarket.entity.Items;
import com.shop.supermarket.entity.Roles;
import lombok.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class UsersDTO {

    private int id;

    private String username;

    private String password;

    private String email;

    private String phoneNumber;

    private String address;


    private short enabled;

    private List<Items> items;


    private List<Roles> roles;

    public void addRole(Roles theRole)
    {
        if(roles==null)
        {
            roles=new ArrayList<>();
        }
        roles.add(theRole);
    }

    public void addItem(Items theItem)
    {
        if(items==null)
        {
            items=new ArrayList<>();
        }
        items.add(theItem);
    }


    public UsersDTO(String username, String password, String email, String phoneNumber, String address, short enabled) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.enabled = enabled;
    }

}
