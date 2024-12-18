package tables;

import java.sql.Timestamp;

public class LineBasket {
	private long idLineBasket; // BIGINT AUTO_INCREMENT NOT NULL
	private int qtyProductLB; // INT NOT NULL
	private double priceETLB; // DOUBLE NOT NULL
	private double priceITLB; // DOUBLE NOT NULL
	private Products idProduct; // BIGINT
	private Orders idOrder; // BIGINT
	private String userCreate; // VARCHAR(100) NOT NULL
	private Timestamp dateCreate; // DATETIME NOT NULL
	private String userModif; // VARCHAR(100) NOT NULL
	private Timestamp dateModif; // DATETIME NOT NULL
	
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
	public LineBasket(int qtyProductLB, double priceETLB, double priceITLB, Products idProduct, Orders idOrder,
			String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		initLineBasket(0, qtyProductLB, priceETLB, priceITLB, idProduct, idOrder, userCreate, dateCreate, userModif, dateModif); // 0 à vérifier
	}
	
	public LineBasket(long idLineBasket, int qtyProductLB, double priceETLB, double priceITLB, Products idProduct, Orders idOrder,
			String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		
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

	// Pour debugger
	@Override
	public String toString() {
		return "LineBasket: [idLineBasket = " + idLineBasket + ", qtyProductLB = " + qtyProductLB + ", priceETLB = " + priceETLB 
				+ ", priceITLB = " + priceITLB + ", idProduct = " + idProduct + ", idOrder = " + idOrder
				+ ", userCreate = " + userCreate + ", dateCreate = " + dateCreate + ", userModif = " + userModif + ", dateModif = " + dateModif + "]";
	}
}
