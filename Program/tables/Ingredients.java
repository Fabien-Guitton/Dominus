package tables;

import java.sql.Date;

public class Ingredients { 
	private long idIgredient; // BIGINT AUTO_INCREMENT NOT NULL
	private String nameIng; // VARCHAR(100) NOT NULL
	private long stockIng; // BIGINT NOT NULL
	private String unityIng; // VARCHAR(25) NOT NULL
	private double priceHTIng; // DOUBLE NOT NULL
	private double priceTTCIng; // DOUBLE NOT NULL
	private String userCreate; // VARCHAR(100) NOT NULL
	private Date dateCreate; // DATETIME NOT NULL
	private String userModif; // VARCHAR(100) NOT NULL
	private Date dateModif; // DATETIME NOT NULL
	
	public void initIngredients(long idIgredient, String nameIng, long stockIng, String unityIng, double priceHTIng, double priceTTCIng,
			String userCreate, Date dateCreate, String userModif, Date dateModif) {
		this.idIgredient = idIgredient;
		this.nameIng = nameIng;
		this.stockIng = stockIng;
		this.unityIng = unityIng;
		this.priceHTIng = priceHTIng;
		this.priceTTCIng = priceTTCIng;
		this.userCreate = userCreate;
		this.dateCreate = dateCreate;
		this.userModif = userModif;
		this.dateModif = dateModif;
	}
	
	// PEUT ETRE D'AUTRE A CREER SUIVANT LA DAO
	public Ingredients(String nameIng, long stockIng, String unityIng, double priceHTIng, double priceTTCIng,
			String userCreate, Date dateCreate, String userModif, Date dateModif) {
		initIngredients(0, nameIng, stockIng, unityIng, priceHTIng, priceTTCIng, userCreate, dateCreate, userModif, dateModif); // 0 à vérifier
	}
	
	public Ingredients(long idIgredient, String nameIng, long stockIng, String unityIng, double priceHTIng, double priceTTCIng,
			String userCreate, Date dateCreate, String userModif, Date dateModif) {
		initIngredients(idIgredient, nameIng, stockIng, unityIng, priceHTIng, priceTTCIng, userCreate, dateCreate, userModif, dateModif);
	}

	public long getIdIgredient() {
		return idIgredient;
	}

	public void setIdIgredient(long idIgredient) {
		this.idIgredient = idIgredient;
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

	public double getPriceHTIng() {
		return priceHTIng;
	}

	public void setPriceHTIng(double priceHTIng) {
		this.priceHTIng = priceHTIng;
	}

	public double getPriceTTCIng() {
		return priceTTCIng;
	}

	public void setPriceTTCIng(double priceTTCIng) {
		this.priceTTCIng = priceTTCIng;
	}

	// A VOIR POUR CELA
	public String getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(String userCreate) {
		this.userCreate = userCreate;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public String getUserModif() {
		return userModif;
	}

	public void setUserModif(String userModif) {
		this.userModif = userModif;
	}

	public Date getDateModif() {
		return dateModif;
	}

	public void setDateModif(Date dateModif) {
		this.dateModif = dateModif;
	}
	
	public String resume() {
		return "Ingredients: [idIgredient=" + idIgredient + "]";
	}

	// Pour debugger
	@Override
	public String toString() {
		return "Ingredients: [idIgredient = " + idIgredient + ", nameIng = " + nameIng + ", stockIng = " + stockIng
				+ ", unityIng = " + unityIng + ", priceHTIng = " + priceHTIng + ", priceTTCIng = " + priceTTCIng
				+ ", userCreate = " + userCreate + ", dateCreate = " + dateCreate + ", userModif = " + userModif + ", dateModif = " + dateModif + "]";
	}
}
