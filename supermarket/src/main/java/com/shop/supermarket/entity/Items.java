package com.shop.supermarket.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "items")
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private int itemId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "cost")
    private int cost;

    @Column(name = "company")
    private String company;


    @ManyToMany(fetch = FetchType.LAZY,
    cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "orders",
            joinColumns = @JoinColumn(name = "item_id"),
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
        if (users==null)
        {
            users=new ArrayList<>();
        }
        users.add(theUser);
    }

    public Items()
    {

    }

    public Items(String itemName, int cost, String company) {
        this.itemName = itemName;
        this.cost = cost;
        this.company = company;
    }

    public Items(int itemId, String itemName, int cost, String company) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.cost = cost;
        this.company = company;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Items{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", cost=" + cost +
                ", company=" + company +
                '}';
    }
}
