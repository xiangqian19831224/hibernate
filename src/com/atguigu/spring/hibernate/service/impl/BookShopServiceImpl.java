package com.atguigu.spring.hibernate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.spring.hibernate.dao.BookShopDao;
import com.atguigu.spring.hibernate.service.BookShopService;

@Service
public class BookShopServiceImpl implements BookShopService {
	
	@Autowired
	private BookShopDao bookShopDao;
	
	/*
	 * Spring hibernate事务的流程
	 * 1. 在方法开始之前
	 *   获取session
	 *   session和当前线程绑定，可以在dao中使用SessionFactor。getCurrentSession()获取session
	 *   开启事务
	 *  2. 正常结束
	 *    提交事务
	 *    解除和当前线程绑定的session
	 *    关闭session
	 *  3.异常
	 *    回滚事务
	 *    使和当前线程绑定的Session解除
	 *    关闭Session 
	 */
	@Override
	public void purchase(String username, String isbn) {
		int price = bookShopDao.findBookPriceByIsbn(isbn);
		
		bookShopDao.updateBookStock(isbn);
		
		bookShopDao.updateUserAccount(username, price);
	}

}
