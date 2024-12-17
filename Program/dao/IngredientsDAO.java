package dao;

import tables.Ingredients;

public class IngredientsDAO extends DAO<Ingredients> {
	@Override
	public Ingredients create(Ingredients ing) {
		return ing;
	}
	
	@Override
	public Ingredients update(Ingredients ing) {
		return ing;
	}
	
	@Override
	public void delete(Ingredients ing) {
		
	}
	
	public Ingredients read(Ingredients ing) {
		return ing;
	}
}
