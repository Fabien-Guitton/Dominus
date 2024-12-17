package dao;

import tables.Customers;

public class CustomersDAO extends DAO<Customers> {
	@Override
	public Customers create(Customers cst) {
		return cst;
	}
	
	@Override
	public Customers update(Customers cst) {
		return cst;
	}
	
	@Override
	public void delete(Customers cst) {
		
	}
	
	public Customers read(Customers cst) {
		return cst;
	}
}
