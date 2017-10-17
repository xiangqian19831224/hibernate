package com.atguigu.spring.hibernate.dao;

public interface BookShopDao {
	//������Ż�ȡ��ĵ���
	public int findBookPriceByIsbn(String isbn);
	
	//�������ݵĿ�棬ʹ����Ŷ�Ӧ�Ŀ��
	public void updateBookStock(String isbn);
	
	//�����û����˻�����ȥ���
	public void updateUserAccount(String userName, int price);
}
