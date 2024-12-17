package dao;

import tables.Supplements;

public class SupplementsDAO extends DAO<Supplements> {
	@Override
	public Supplements create(Supplements sup) {
		return sup;
	}
	
	@Override
	public Supplements update(Supplements sup) {
		return sup;
	}
	
	@Override
	public void delete(Supplements sup) {
		
	}
	
	public Supplements read(Supplements sup) {
		return sup;
	}
}
