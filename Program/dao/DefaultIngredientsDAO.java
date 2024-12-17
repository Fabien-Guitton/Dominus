package dao;

import tables.DefaultIngredients;

public class DefaultIngredientsDAO extends DAO<DefaultIngredients> {
	@Override
	public DefaultIngredients create(DefaultIngredients defaultIng) {
		return defaultIng;
	}
	
	@Override
	public DefaultIngredients update(DefaultIngredients defaultIng) {
		return defaultIng;
	}
	
	@Override
	public void delete(DefaultIngredients defaultIng) {
		
	}
	
	public DefaultIngredients read(DefaultIngredients defaultIng) {
		return defaultIng;
	}
}
