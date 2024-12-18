package tables;

import java.sql.Date;

public class Products {
	private long idProduct; // BIGINT AUTO_INCREMENT NOT NULL
	private String nameProduct; // VARCHAR(100) NOT NULL
	private String sizeProduct; // VARCHAR(50) NOT NULL
	private String categoryProduct; // VARCHAR(100) NOT NULL
	private double priceHTProduct; // DOUBLE NOT NULL
	private double priceTTCProduct; // DOUBLE NOT NULL
	private String userCreate; // VARCHAR(100) NOT NULL
	private Date dateCreate; // DATETIME NOT NULL
	private String userModif; // VARCHAR(100) NOT NULL
	private Date dateModif; // DATETIME NOT NULL

	public void initProducts(long idProduct, String nameProduct, String sizeProduct, String categoryProduct, double priceHTProduct, double priceTTCProduct, 
			String userCreate, Date dateCreate, String userModif, Date dateModif) {
		this.idProduct = idProduct;
		this.nameProduct = nameProduct;
		this.sizeProduct = sizeProduct;
		this.categoryProduct = categoryProduct;
		this.priceHTProduct = priceHTProduct;
		this.priceTTCProduct = priceTTCProduct;
		this.userCreate = userCreate;
		this.dateCreate = dateCreate;
		this.userModif = userModif;
		this.dateModif = dateModif;
	}

	// PEUT ETRE D'AUTRE A CREER SUIVANT LA DAO
	public Products(String nameProduct, String sizeProduct, String categoryProduct, double priceHTProduct, double priceTTCProduct, 
			String userCreate, Date dateCreate, String userModif, Date dateModif) {
		initProducts(0, nameProduct, sizeProduct, categoryProduct, priceHTProduct, priceTTCProduct, userCreate, dateCreate, userModif, dateModif); // 0 à vérifier
	}
	
	public Products(long idProduct, String nameProduct, String sizeProduct, String categoryProduct, double priceHTProduct, double priceTTCProduct, 
			String userCreate, Date dateCreate, String userModif, Date dateModif) {
		initProducts(idProduct, nameProduct, sizeProduct, categoryProduct, priceHTProduct, priceTTCProduct, userCreate, dateCreate, userModif, dateModif);
	}

	public long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(long idProduct) {
		this.idProduct = idProduct;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public String getSizeProduct() {
		return sizeProduct;
	}

	public void setSizeProduct(String sizeProduct) {
		this.sizeProduct = sizeProduct;
	}

	public String getCategoryProduct() {
		return categoryProduct;
	}

	public void setCategoryProduct(String categoryProduct) {
		this.categoryProduct = categoryProduct;
	}

	public double getPriceHTProduct() {
		return priceHTProduct;
	}

	public void setPriceHTProduct(double priceHTProduct) {
		this.priceHTProduct = priceHTProduct;
	}

	public double getPriceTTCProduct() {
		return priceTTCProduct;
	}

	public void setPriceTTCProduct(double priceTTCProduct) {
		this.priceTTCProduct = priceTTCProduct;
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
		return "Products: [idProduct = " + idProduct + "]";
	}

	// Pour debugger
	@Override
	public String toString() {
		return "Products: [idProduct = " + idProduct + ", nameProduct = " + nameProduct + ", sizeProduct = " + sizeProduct
				+ ", categoryProduct = " + categoryProduct + ", priceHTProduct = " + priceHTProduct + ", priceTTCProduct = " + priceTTCProduct 
				+ ", userCreate = " + userCreate + ", dateCreate = " + dateCreate + ", userModif = " + userModif + ", dateModif = " + dateModif + "]";
	}
}
