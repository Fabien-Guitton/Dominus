package dao;

import tables.LineBasket;

public class LineBasketDAO extends DAO<LineBasket> {
	@Override
	public LineBasket create(LineBasket lb) {
		return lb;
	}
	
	@Override
	public LineBasket update(LineBasket lb) {
		return lb;
	}
	
	@Override
	public void delete(LineBasket lb) {
		
	}
	
	public LineBasket read(LineBasket lb) {
		return lb;
	}
}
