package tables;

import java.sql.Timestamp;

public class DefaultIngredients {
	private Ingredients idIgredient; // FOREIGN KEY : BIGINT
	private Products idProduct; // FOREIGN KEY : BIGINT
	private String userCreate; // VARCHAR(100) NOT NULL
	private Timestamp dateCreate; // DATETIME NOT NULL
	private String userModif; // VARCHAR(100) NOT NULL
	private Timestamp dateModif; // DATETIME NOT NULL
	
	public void initDefaultIngredients(Ingredients idIgredient, Products idProduct, String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		this.idIgredient = idIgredient;
		this.idProduct = idProduct;
		this.userCreate = userCreate;
		this.dateCreate = dateCreate;
		this.userModif = userModif;
		this.dateModif = dateModif;
	}
	
	// PEUT ETRE D'AUTRE A CREER SUIVANT LA DAO
	public DefaultIngredients(Ingredients idIgredient, Products idProduct, String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		initDefaultIngredients(idIgredient, idProduct, userCreate, dateCreate, userModif, dateModif);
	}

	public Ingredients getIdIgredient() {
		return idIgredient;
	}

	public void setIdIgredient(Ingredients idIgredient) {
		this.idIgredient = idIgredient;
	}

	public Products getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Products idProduct) {
		this.idProduct = idProduct;
	}

	// A VOIR POUR CELA
	public String getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(String userCreate) {
		this.userCreate = userCreate;
	}

	public Timestamp getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Timestamp dateCreate) {
		this.dateCreate = dateCreate;
	}

	public String getUserModif() {
		return userModif;
	}

	public void setUserModif(String userModif) {
		this.userModif = userModif;
	}

	public Timestamp getDateModif() {
		return dateModif;
	}

	public void setDateModif(Timestamp dateModif) {
		this.dateModif = dateModif;
	}

	// Pour debugger
	@Override
	public String toString() {
		String result = "DefaultIngredients: [idIgredient = "; 
		if(idIgredient != null) {
			result += idIgredient.resume(); 
		}
		result +=  ", idProduct = ";
		if(idProduct != null) {
			result += idProduct.resume();
		}
		result += ", userCreate = " + userCreate + ", dateCreate = " + dateCreate + ", userModif = " + userModif + ", dateModif = " + dateModif + "]";
		return result;
	}
}
