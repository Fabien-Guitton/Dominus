package tables;

import java.sql.Timestamp;

public class LineBasket {
	private long idLineBasket; // PRIMARY KEY: BIGINT AUTO_INCREMENT NOT NULL
	private int qtyProductLB; // INT NOT NULL
	private double priceETLB; // DOUBLE NOT NULL
	private double priceITLB; // DOUBLE NOT NULL
	private Products idProduct; // FOREIGN KEY: BIGINT
	private Orders idOrder; // FOREIGN KEY: BIGINT
	private String userCreate; // VARCHAR(100) NOT NULL
	private Timestamp dateCreate; // DATETIME NOT NULL
	private String userModif; // VARCHAR(100) NOT NULL
	private Timestamp dateModif; // DATETIME NOT NULL
//	For debug	
//	if(this.idProduct == null) { 
//		System.out.println("WARNING: There are no linked products.");
//	}	
//	if(this.idOrder == null) { 
//		System.out.println("WARNING: There are no linked orders.");
//	}
	
	public void initLineBasket(long idLineBasket, int qtyProductLB, double priceETLB, double priceITLB, Products idProduct, Orders idOrder,
			String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		this.idLineBasket = idLineBasket;
		this.qtyProductLB = qtyProductLB;
		this.priceETLB = priceETLB;
		this.priceITLB = priceITLB;
		this.idProduct = idProduct;
		this.idOrder = idOrder;
		this.userCreate = userCreate;
		this.dateCreate = dateCreate;
		this.userModif = userModif;
		this.dateModif = dateModif;
	}
	
	// PEUT ETRE D'AUTRE A CREER SUIVANT LA DAO
	public LineBasket(long idLineBasket, int qtyProductLB, double priceETLB, double priceITLB, Products idProduct, Orders idOrder,
			String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		initLineBasket(idLineBasket, qtyProductLB, priceETLB, priceITLB, idProduct, idOrder, userCreate, dateCreate, userModif, dateModif);
	}
	
	public LineBasket(int qtyProductLB, double priceETLB, double priceITLB, Products idProduct, Orders idOrder,
			String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		initLineBasket(0, qtyProductLB, priceETLB, priceITLB, idProduct, idOrder, userCreate, dateCreate, userModif, dateModif);
	}
	
	public LineBasket(long idLineBasket, int qtyProductLB, double priceETLB, double priceITLB, Products idProduct, Orders idOrder) {
		initLineBasket(idLineBasket, qtyProductLB, priceETLB, priceITLB, idProduct, idOrder, 
				Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()), Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()));
	}
	
	public LineBasket(int qtyProductLB, double priceETLB, double priceITLB, Products idProduct, Orders idOrder) {
		initLineBasket(0, qtyProductLB, priceETLB, priceITLB, idProduct, idOrder, 
				Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()), Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()));
	}

	public long getIdLineBasket() {
		return idLineBasket;
	}

	public void setIdLineBasket(long idLineBasket) {
		this.idLineBasket = idLineBasket;
	}

	public int getQtyProductLB() {
		return qtyProductLB;
	}

	public void setQtyProductLB(int qtyProductLB) {
		this.qtyProductLB = qtyProductLB;
	}

	public double getPriceETLB() {
		return priceETLB;
	}

	public void setPriceETLB(double priceETLB) {
		this.priceETLB = priceETLB;
	}

	public double getPriceITLB() {
		return priceITLB;
	}

	public void setPriceITLB(double priceITLB) {
		this.priceITLB = priceITLB;
	}

	public Products getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Products idProduct) {
		this.idProduct = idProduct;
	}

	public Orders getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Orders idOrder) {
		this.idOrder = idOrder;
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
		return "LineBasket: [idLineBasket = " + idLineBasket + "]";
	}

//	For debug
	@Override
	public String toString() {
		return "LineBasket: [idLineBasket = " + idLineBasket + ", qtyProductLB = " + qtyProductLB + ", priceETLB = " + priceETLB 
				+ ", priceITLB = " + priceITLB + ", idProduct = " + idProduct + ", idOrder = " + idOrder
				+ ", userCreate = " + userCreate + ", dateCreate = " + dateCreate + ", userModif = " + userModif + ", dateModif = " + dateModif + "]";
	}
}
