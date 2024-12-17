package dao;

import tables.Discounts;

public class DiscountsDAO extends DAO<Discounts> {
	@Override
	public Discounts create(Discounts dist) {
		return dist;
	}
	
	@Override
	public Discounts update(Discounts dist) {
		return dist;
	}
	
	@Override
	public void delete(Discounts dist) {
		
	}
	
	public Discounts read(Discounts dist) {
		return dist;
	}
}
