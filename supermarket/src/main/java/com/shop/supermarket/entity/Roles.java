package com.shop.supermarket.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Roles {

    @Id
    @Column(name = "authority")
    private String authority;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinTable(
            name = "authorities",
            joinColumns = @JoinColumn(name = "authority"),
            inverseJoinColumns = @JoinColumn(name = "username")
    )
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


    public Roles(String authority)
    {
        this.authority=authority;
    }

}
