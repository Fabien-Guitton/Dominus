package dao;

import tables.Products;

public class ProductsDAO extends DAO<Products> {
	@Override
	public Products create(Products prod) {
		return prod;
	}
	
	@Override
	public Products update(Products prod) {
		return prod;
	}
	
	@Override
	public void delete(Products prod) {
		
	}
	
	public Products read(Products prod) {
		return prod;
	}
}
