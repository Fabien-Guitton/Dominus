package dao;

import tables.ClockingIn;

public class ClockingInDAO extends DAO<ClockingIn> {
	@Override
	public ClockingIn create(ClockingIn clo) {
		return clo;
	}
	
	@Override
	public ClockingIn update(ClockingIn clo) {
		return clo;
	}
	
	@Override
	public void delete(ClockingIn clo) {
		
	}
	
	// Il faudra en faire plusieurs
	public ClockingIn read(ClockingIn clo) {
		return clo;
	}
}
