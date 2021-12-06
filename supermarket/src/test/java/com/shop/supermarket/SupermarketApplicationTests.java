package com.shop.supermarket;

import com.shop.supermarket.dao.UsersDAO;
import com.shop.supermarket.entity.Items;
import com.shop.supermarket.service.UsersService;
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
	private UsersDAO usersDAO;

	@Test
	public void ordersList_gettingOrdersList()
	{
		when(usersDAO.getItemsList("john")).thenReturn(
				Stream.of(new Items(1,"kurkure",5,"kurkure"),new Items(2,"goodday",10,"britania")).collect(Collectors.toList()));
		assertEquals(2,usersService.getItemsList("john").size());
	}




}
