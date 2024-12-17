package dao;

import tables.Orders;

public class OrdersDAO extends DAO<Orders> {
	@Override
	public Orders create(Orders ord) {
		return ord;
	}
	
	@Override
	public Orders update(Orders ord) {
		return ord;
	}
	
	@Override
	public void delete(Orders ord) {
		
	}
	
	public Orders read(Orders ord) {
		return ord;
	}
}
