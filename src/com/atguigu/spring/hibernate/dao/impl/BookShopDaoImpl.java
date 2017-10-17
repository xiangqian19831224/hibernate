package com.atguigu.spring.hibernate.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.spring.hibernate.dao.BookShopDao;
import com.atguigu.spring.hibernate.exception.BookStockException;
import com.atguigu.spring.hibernate.exception.UserAccountException;

@Service
public class BookShopDaoImpl implements BookShopDao {

	@Autowired
	private SessionFactory sessionFactory;

	// 获取和当前线程绑定的session
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public int findBookPriceByIsbn(String isbn) {
		String hql = "SELECT b.price FROM Book b WHERE b.isbn=?";
		Query query = getSession().createQuery(hql).setString(0, isbn);
		System.out.println(query.toString());
		return (Integer) query.uniqueResult();
	}

	@Override
	public void updateBookStock(String isbn) {
		// 验证图书库存是否充足
		String hql2 = "SELECT b.stock FROM Book b WHERE b.isbn=?";
		int stock = (Integer) getSession().createQuery(hql2).setString(0, isbn).uniqueResult();
		if (stock == 0) {
			throw new BookStockException("库存不足");
		}

		String hql = "UPDATE Book b SET b.stock=b.stock-1 WHERE b.isbn = ?";
		getSession().createQuery(hql).setString(0, isbn).executeUpdate();
	}

	@Override
	public void updateUserAccount(String userName, int price) {
		String hql2 = "SELECT a.balance FROM Account a WHERE a.username=?";
		int balance = (int) getSession().createQuery(hql2).setString(0, userName).uniqueResult();
		if (balance < price) {
			throw new UserAccountException("余额不足");
		}
		String hql = "UPDATE Account a SET a.balance = a.balance - ? " + "WHERE a.username=?";
		getSession().createQuery(hql).setInteger(0, price).setString(1, userName).executeUpdate();

	}

}
