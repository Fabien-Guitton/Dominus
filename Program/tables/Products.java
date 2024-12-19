package tables;

import java.sql.Timestamp;

public class Products {
	private long idProduct; // PRIMARY KEY: BIGINT AUTO_INCREMENT NOT NULL
	private String nameProduct; // VARCHAR(100) NOT NULL
	private String sizeProduct; // VARCHAR(50) NOT NULL
	private String categoryProduct; // VARCHAR(100) NOT NULL
	private double priceETProduct; // DOUBLE NOT NULL
	private double priceITProduct; // DOUBLE NOT NULL
	private String userCreate; // VARCHAR(100) NOT NULL
	private Timestamp dateCreate; // DATETIME NOT NULL
	private String userModif; // VARCHAR(100) NOT NULL
	private Timestamp dateModif; // DATETIME NOT NULL

	public void initProducts(long idProduct, String nameProduct, String sizeProduct, String categoryProduct, double priceETProduct, double priceITProduct, 
			String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		this.idProduct = idProduct;
		this.nameProduct = nameProduct;
		this.sizeProduct = sizeProduct;
		this.categoryProduct = categoryProduct;
		this.priceETProduct = priceETProduct;
		this.priceITProduct = priceITProduct;
		this.userCreate = userCreate;
		this.dateCreate = dateCreate;
		this.userModif = userModif;
		this.dateModif = dateModif;
	}

	// PEUT ETRE D'AUTRE A CREER SUIVANT LA DAO
	public Products(long idProduct, String nameProduct, String sizeProduct, String categoryProduct, double priceETProduct, double priceITProduct, 
			String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		initProducts(idProduct, nameProduct, sizeProduct, categoryProduct, priceETProduct, priceITProduct, userCreate, dateCreate, userModif, dateModif);
	}
	
	public Products(String nameProduct, String sizeProduct, String categoryProduct, double priceETProduct, double priceITProduct, 
			String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		initProducts(0, nameProduct, sizeProduct, categoryProduct, priceETProduct, priceITProduct, userCreate, dateCreate, userModif, dateModif);
	}
	
	public Products(long idProduct, String nameProduct, String sizeProduct, String categoryProduct, double priceETProduct, double priceITProduct) {
		initProducts(idProduct, nameProduct, sizeProduct, categoryProduct, priceETProduct, priceITProduct, 
				Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()), Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()));
	}
	
	public Products(String nameProduct, String sizeProduct, String categoryProduct, double priceETProduct, double priceITProduct) {
		initProducts(0, nameProduct, sizeProduct, categoryProduct, priceETProduct, priceITProduct, 
				Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()), Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()));
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

	public double getPriceETProduct() {
		return priceETProduct;
	}

	public void setPriceETProduct(double priceETProduct) {
		this.priceETProduct = priceETProduct;
	}

	public double getPriceITProduct() {
		return priceITProduct;
	}

	public void setPriceITProduct(double priceITProduct) {
		this.priceITProduct = priceITProduct;
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
		return "Products: [idProduct = " + idProduct + "]";
	}

//	For debug
	@Override
	public String toString() {
		return "Products: [idProduct = " + idProduct + ", nameProduct = " + nameProduct + ", sizeProduct = " + sizeProduct
				+ ", categoryProduct = " + categoryProduct + ", priceETProduct = " + priceETProduct + ", priceITProduct = " + priceITProduct 
				+ ", userCreate = " + userCreate + ", dateCreate = " + dateCreate + ", userModif = " + userModif + ", dateModif = " + dateModif + "]";
	}
}
