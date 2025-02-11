package tables;

import java.sql.Timestamp;

public class DefaultIngredients {
	private long idDefaultIngredient; // PRIMARY KEY: BIGINT AUTO_INCREMENT NOT NULL
	private Ingredients idIngredient; // PRIMARY KEY + FOREIGN KEY: BIGINT
	private Products idProduct; // PRIMARY KEY + FOREIGN KEY: BIGINT
	private String userCreate; // VARCHAR(100) NOT NULL
	private Timestamp dateCreate; // DATETIME NOT NULL
	private String userModif; // VARCHAR(100) NOT NULL
	private Timestamp dateModif; // DATETIME NOT NULL
//	For debug	
//	if(this.idIngredient == null) { 
//		System.out.println("WARNING: There are no linked ingredients.");
//	}	
//	if(this.idProduct == null) { 
//		System.out.println("WARNING: There are no linked products.");
//	}
	
	public void initDefaultIngredients(long idDefaultIngredient, Ingredients idIngredient, Products idProduct, String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		this.idDefaultIngredient = idDefaultIngredient;
		this.idIngredient = idIngredient;
		this.idProduct = idProduct;
		this.userCreate = userCreate;
		this.dateCreate = dateCreate;
		this.userModif = userModif;
		this.dateModif = dateModif;
	}
	
	// PEUT ETRE D'AUTRE A CREER SUIVANT LA DAO
	public DefaultIngredients(long idDefaultIngredient, Ingredients idIngredient, Products idProduct, String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		initDefaultIngredients(idDefaultIngredient, idIngredient, idProduct, userCreate, dateCreate, userModif, dateModif);
	}
	
	public DefaultIngredients(Ingredients idIngredient, Products idProduct, String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		initDefaultIngredients(0, idIngredient, idProduct, userCreate, dateCreate, userModif, dateModif);
	}
	
	public DefaultIngredients(long idDefaultIngredient, Ingredients idIngredient, Products idProduct) {
		initDefaultIngredients(idDefaultIngredient, idIngredient, idProduct, 
				Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()), Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()));
	}
	
	public DefaultIngredients(Ingredients idIngredient, Products idProduct) {
		initDefaultIngredients(0, idIngredient, idProduct, 
				Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()), Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()));
	}
	
	public long getidDefaultIngredient() {
		return idDefaultIngredient;
	}

	public void setidDefaultIngredient(long idDefaultIngredient) {
		this.idDefaultIngredient = idDefaultIngredient;
	}

	public Ingredients getidIngredient() {
		return idIngredient;
	}

	public void setidIngredient(Ingredients idIngredient) {
		this.idIngredient = idIngredient;
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

//	For debug
	@Override
	public String toString() {
		String result = "DefaultIngredients: [idDefaultIngredient = " + idDefaultIngredient + ", idIngredient = "; 
		if(idIngredient != null) {
			result += idIngredient.resume(); 
		}
		result +=  ", idProduct = ";
		if(idProduct != null) {
			result += idProduct.resume();
		}
		result += ", userCreate = " + userCreate + ", dateCreate = " + dateCreate + ", userModif = " + userModif + ", dateModif = " + dateModif + "]";
		return result;
	}
}
