package com.shop.supermarket.dto;


import com.shop.supermarket.entity.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class RolesDTO {

    private String authority;

    @ToString.Exclude
    private List<Users> users;


    public void addUser(Users theUser)
    {
        if(users==null)
        {
            users=new ArrayList<>();
        }
        users.add(theUser);
    }

    public RolesDTO(String authority)
    {
        this.authority=authority;
    }

}
