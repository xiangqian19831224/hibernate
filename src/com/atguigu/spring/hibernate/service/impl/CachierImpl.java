package com.atguigu.spring.hibernate.service.impl;

import java.util.List;

import javax.persistence.AttributeOverride;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.spring.hibernate.service.BookShopService;
import com.atguigu.spring.hibernate.service.Cashier;

@Service
public class CachierImpl implements Cashier{

	@Autowired
	private BookShopService BookShopService;
	
	@Override
	public void checkout(String username, List<String> isbns) {
		for(String isbn: isbns){
			BookShopService.purchase(username, isbn);
		}
		
	}

}
