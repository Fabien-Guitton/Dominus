package tables;

import java.sql.Timestamp;

public class Ingredients { 
	private long idIngredient; // PRIMARY KEY: BIGINT AUTO_INCREMENT NOT NULL
	private String nameIng; // VARCHAR(100) NOT NULL
	private long stockIng; // BIGINT NOT NULL
	private String unityIng; // VARCHAR(25) NOT NULL
	private Boolean supplementPossibleON;
	private double priceETIng; // DOUBLE NOT NULL
	private double priceITIng; // DOUBLE NOT NULL
	private String userCreate; // VARCHAR(100) NOT NULL
	private Timestamp dateCreate; // DATETIME NOT NULL
	private String userModif; // VARCHAR(100) NOT NULL
	private Timestamp dateModif; // DATETIME NOT NULL
	
	public void initIngredients(long idIngredient, String nameIng, long stockIng, String unityIng, Boolean supplementPossibleON, double priceETIng, double priceITIng,
			String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		this.idIngredient = idIngredient;
		this.nameIng = nameIng;
		this.stockIng = stockIng;
		this.unityIng = unityIng;
		this.supplementPossibleON = supplementPossibleON;
		this.priceETIng = priceETIng;
		this.priceITIng = priceITIng;
		this.userCreate = userCreate;
		this.dateCreate = dateCreate;
		this.userModif = userModif;
		this.dateModif = dateModif;
	}
	
	// PEUT ETRE D'AUTRE A CREER SUIVANT LA DAO
	public Ingredients(long idIngredient, String nameIng, long stockIng, String unityIng, Boolean supplementPossibleON, double priceETIng, double priceITIng,
			String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		initIngredients(idIngredient, nameIng, stockIng, unityIng, supplementPossibleON, priceETIng, priceITIng, userCreate, dateCreate, userModif, dateModif);
	}
	
	public Ingredients(String nameIng, long stockIng, String unityIng, Boolean supplementPossibleON, double priceETIng, double priceITIng,
			String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		initIngredients(0, nameIng, stockIng, unityIng, supplementPossibleON, priceETIng, priceITIng, userCreate, dateCreate, userModif, dateModif);
	}
	
	public Ingredients(long idIngredient, String nameIng, long stockIng, String unityIng, Boolean supplementPossibleON, double priceETIng, double priceITIng) {
		initIngredients(idIngredient, nameIng, stockIng, unityIng, supplementPossibleON, priceETIng, priceITIng, 
				Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()), Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()));
	}
	
	public Ingredients(String nameIng, long stockIng, String unityIng, Boolean supplementPossibleON, double priceETIng, double priceITIng) {
		initIngredients(0, nameIng, stockIng, unityIng, supplementPossibleON, priceETIng, priceITIng, 
				Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()), Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()));
	}


	public long getidIngredient() {
		return idIngredient;
	}

	public void setidIngredient(long idIngredient) {
		this.idIngredient = idIngredient;
	}

	public String getNameIng() {
		return nameIng;
	}

	public void setNameIng(String nameIng) {
		this.nameIng = nameIng;
	}

	public long getStockIng() {
		return stockIng;
	}

	public void setStockIng(long stockIng) {
		this.stockIng = stockIng;
	}

	public String getUnityIng() {
		return unityIng;
	}

	public void setUnityIng(String unityIng) {
		this.unityIng = unityIng;
	}
	
	public Boolean isSupplementPossibleON() {
		return supplementPossibleON;
	}

	public void setUnityIng(Boolean supplementPossibleON) {
		this.supplementPossibleON = supplementPossibleON;
	}

	public double getPriceETIng() {
		return priceETIng;
	}

	public void setPriceETIng(double priceETIng) {
		this.priceETIng = priceETIng;
	}

	public double getPriceITIng() {
		return priceITIng;
	}

	public void setPriceITIng(double priceITIng) {
		this.priceITIng = priceITIng;
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
	
	public String resume() {
		return "Ingredients: [idIngredient=" + idIngredient + "]";
	}

// 	For debug
	@Override
	public String toString() {
		return "Ingredients: [idIngredient = " + idIngredient + ", nameIng = " + nameIng + ", stockIng = " + stockIng
				+ ", unityIng = " + unityIng + ", priceETIng = " + priceETIng + ", priceITIng = " + priceITIng
				+ ", userCreate = " + userCreate + ", dateCreate = " + dateCreate + ", userModif = " + userModif + ", dateModif = " + dateModif + "]";
	}
}