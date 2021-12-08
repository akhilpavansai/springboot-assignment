package com.shop.supermarket;

import com.shop.supermarket.dao.UsersDAO;
import com.shop.supermarket.entity.Items;
import com.shop.supermarket.entity.Users;
import com.shop.supermarket.repository.ItemsRepository;
import com.shop.supermarket.service.ItemsService;
import com.shop.supermarket.service.UsersService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static javax.swing.UIManager.get;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@RunWith(SpringRunner.class)
@SpringBootTest
class SupermarketApplicationTests {

	@Autowired
	private UsersService usersService;

	@MockBean
	private UsersDAO usersDAO;

	@Autowired
	private ItemsService itemsService;

	@MockBean
	private ItemsRepository itemsRepository;



	@Test
	void ordersList_gettingOrdersList()
	{
		when(usersDAO.getOrdersList("john")).thenReturn(
				Stream.of(new Items(1,"kurkure",5,"kurkure"),new Items(2,"goodday",10,"britania")).collect(Collectors.toList()));
		assertEquals(2,usersService.getOrdersList("john").size());
	}

	@Test
	void stockList_gettingStockList()
	{
		when(itemsRepository.findAll()).thenReturn(
				Stream.of(new Items(1,"kurkure",5,"kurkure"),new Items(2,"goodday",10,"britania")).collect(Collectors.toList()));
		assertEquals(2,itemsService.getAllItemsList().size());
	}

	@Test
	void getUser_gettingUserDetails()
	{
		Users user = new Users("john","fun123","john@gmail.com","9701209751","hyderabad",(short) 1);
		when(usersDAO.getUser("john")).thenReturn(user);
		assertEquals(usersService.findByUsername("john"),user);
	}

	@Test
	void saveOrder_savingItemToUser()
	{
		Users user =new Users("john","fun123","john@gmail.com","9701209751","hyderabad",(short) 1);
		Items item1 = new Items(1,"kurkure",5,"kurkure");
		Items item2 = new Items(2,"lays",5,"lays");
		List<Items> itemsList = new ArrayList<>();
		itemsList.add(item1);
		user.setItems(itemsList);
		user.addItem(item2);
		assertEquals(2,user.getItems().size());
	}

	@Test
	void saveOrder_savingOrder()
	{
		Users user =new Users("john","fun123","john@gmail.com","9701209751","hyderabad",(short) 1);
		Items item1 = new Items(1,"kurkure",5,"kurkure");
		user.addItem(item1);
		assertEquals(1,user.getItems().size());
	}

	@Test
	void savingUser_savingUserToItem()
	{
		Users user1 =new Users("john","fun123","john@gmail.com","9701209751","hyderabad",(short) 1);
		Users user2 =new Users("susan","fun123","susan@gmail.com","9701209751","hyderabad",(short) 1);
		Items item1 = new Items(1,"kurkure",5,"kurkure");
		List<Users> usersList = new ArrayList<>();
		usersList.add(user1);
		item1.setUsers(usersList);
		item1.addUser(user2);
		assertEquals(2,item1.getUsers().size());
	}


	@Test
	void saveUser_savingUser()
	{
		Users user =new Users("susan","fun123","susan@gmail.com","9701209751","hyderabad",(short) 1);
		Items item = new Items("kurkure",5,"kurkure");
		item.addUser(user);
		assertEquals(1,item.getUsers().size());
	}


}
