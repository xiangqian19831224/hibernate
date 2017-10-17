package com.atguigu.spring.hibernate.dao;

public interface BookShopDao {
	//根据书号获取书的单价
	public int findBookPriceByIsbn(String isbn);
	
	//更新数据的库存，使用书号对应的库存
	public void updateBookStock(String isbn);
	
	//更新用户的账户余额减去书价
	public void updateUserAccount(String userName, int price);
}
