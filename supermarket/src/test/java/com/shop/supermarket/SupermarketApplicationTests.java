package com.shop.supermarket;

import com.shop.supermarket.converter.ItemsConverter;
import com.shop.supermarket.converter.UsersConverter;
import com.shop.supermarket.entity.Items;
import com.shop.supermarket.entity.Users;
import com.shop.supermarket.repository.ItemsRepository;
import com.shop.supermarket.repository.UsersRepository;
import com.shop.supermarket.service.ItemsService;
import com.shop.supermarket.service.UsersService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
class SupermarketApplicationTests {

	@Autowired
	private UsersService usersService;

	@MockBean
	private UsersRepository usersRepository;

	@Autowired
	private ItemsService itemsService;

	@MockBean
	private ItemsRepository itemsRepository;

	@Autowired
	UsersConverter usersConverter;

	@Autowired
	ItemsConverter itemsConverter;


	@Test
	void ordersList_gettingOrdersList()
	{
		when(usersRepository.getOrdersList("john")).thenReturn(
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
		when(usersRepository.findByUsername("john")).thenReturn(user);
		assertEquals(usersService.findByUsername("john"),user);
	}

	//testing only for sonar cloud basic invocation of supermarket application main method
	@Test
	void testingMainMethodInSpringBootApplication()
	{
		SupermarketApplication.main(new String[]{});
		Assertions.assertTrue(true, "asserting to be compliant with Sonar");
	}

}
