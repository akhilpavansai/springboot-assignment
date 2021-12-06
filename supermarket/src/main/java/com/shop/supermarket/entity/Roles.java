package com.shop.supermarket.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
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
    private List<Users> users;

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public void addUser(Users theUser)
    {
        if(users==null)
        {
            users=new ArrayList<>();
        }
        users.add(theUser);
    }

    public Roles()
    {

    }

    public Roles(String authority)
    {
        this.authority=authority;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "authority='" + authority + '\'' +
                '}';
    }
}
