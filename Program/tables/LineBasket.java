package tables;

import java.sql.Date;

public class LineBasket {
	private long idLineBasket; // BIGINT AUTO_INCREMENT NOT NULL
	private int qtyProductLB; // INT NOT NULL
	private double priceHTLB; // DOUBLE NOT NULL
	private double priceTTCLB; // DOUBLE NOT NULL
	private Products idProduct; // BIGINT
	private Orders idOrder; // BIGINT
	private String userCreate; // VARCHAR(100) NOT NULL
	private Date dateCreate; // DATETIME NOT NULL
	private String userModif; // VARCHAR(100) NOT NULL
	private Date dateModif; // DATETIME NOT NULL
	
	public void initLineBasket(long idLineBasket, int qtyProductLB, double priceHTLB, double priceTTCLB, Products idProduct, Orders idOrder,
			String userCreate, Date dateCreate, String userModif, Date dateModif) {
		this.idLineBasket = idLineBasket;
		this.qtyProductLB = qtyProductLB;
		this.priceHTLB = priceHTLB;
		this.priceTTCLB = priceTTCLB;
		this.idProduct = idProduct;
		this.idOrder = idOrder;
		this.userCreate = userCreate;
		this.dateCreate = dateCreate;
		this.userModif = userModif;
		this.dateModif = dateModif;
	}
	
	// PEUT ETRE D'AUTRE A CREER SUIVANT LA DAO
	public LineBasket(int qtyProductLB, double priceHTLB, double priceTTCLB, Products idProduct, Orders idOrder,
			String userCreate, Date dateCreate, String userModif, Date dateModif) {
		initLineBasket(0, qtyProductLB, priceHTLB, priceTTCLB, idProduct, idOrder, userCreate, dateCreate, userModif, dateModif);
	}
	
	public LineBasket(long idLineBasket, int qtyProductLB, double priceHTLB, double priceTTCLB, Products idProduct, Orders idOrder,
			String userCreate, Date dateCreate, String userModif, Date dateModif) {
		
	}
	
	// A VOIR POUR CELA
	
	// Pour debugger
}
