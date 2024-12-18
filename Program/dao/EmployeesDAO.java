package dao;

import tables.Employees;

public class EmployeesDAO extends DAO<Employees> {
	@Override
	public Employees create(Employees emp) {
		return emp;
	}
	
	@Override
	public Employees update(Employees emp) {
		return emp;
	}
	
	@Override
	public void delete(Employees emp) {
		
	}
	
	public Employees read(long l) {
		Employees e = null;
		return e;
	}
}
