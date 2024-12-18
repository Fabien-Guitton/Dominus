package tables;

import java.sql.Date;

public class Orders {
	private long idOrder; // BIGINT AUTO_INCREMENT NOT NULL
	private String nameOrd; // VARCHAR(50) NOT NULL
	private String typeOrd; // VARCHAR(50) NOT NULL
	private boolean payOrdON; // BOOLEAN NOT NULL
	private double reductionOrd; // DOUBLE
	private Date takingDateOrd; // DATETIME NOT NULL
	private Date readyDateOrd; // DATETIME NOT NULL
	private double priceHTOrd; // DOUBLE NOT NULL
	private double priceTTCOrd; // DOUBLE NOT NULL
	private Discounts idDiscount; // FOREIGN KEY : BIGINT
	private Customers idCustomer; // FOREIGN KEY : BIGINT
	private String userCreate; // VARCHAR(100) NOT NULL
	private Date dateCreate; // DATETIME NOT NULL
	private String userModif; // VARCHAR(100) NOT NULL
	private Date dateModif; // DATETIME NOT NULL
	
	public void initOrders(long idOrder, String nameOrd, String typeOrd, boolean payOrdON, double reductionOrd, Date takingDateOrd, 
			Date readyDateOrd, double priceHTOrd, double priceTTCOrd, Discounts idDiscount,Customers idCustomer, 
			String userCreate, Date dateCreate, String userModif, Date dateModif) {
		this.idOrder = idOrder;
		this.nameOrd = nameOrd;
		this.typeOrd = typeOrd;
		this.payOrdON = payOrdON;
		this.reductionOrd = reductionOrd;
		this.takingDateOrd = takingDateOrd;
		this.readyDateOrd = readyDateOrd;
		this.priceHTOrd = priceHTOrd;
		this.priceTTCOrd = priceTTCOrd;
		this.idDiscount = idDiscount;
		this.idCustomer = idCustomer;
		this.userCreate = userCreate;
		this.dateCreate = dateCreate;
		this.userModif = userModif;
		this.dateModif = dateModif;
	}

	// PEUT ETRE D'AUTRE A CREER SUIVANT LA DAO
	public Orders(String nameOrd, String typeOrd, boolean payOrdON, double reductionOrd, Date takingDateOrd, 
			Date readyDateOrd, double priceHTOrd, double priceTTCOrd, Discounts idDiscount,Customers idCustomer, 
			String userCreate, Date dateCreate, String userModif, Date dateModif) {
		initOrders(0, nameOrd, typeOrd, payOrdON, reductionOrd, takingDateOrd, readyDateOrd, priceHTOrd, priceTTCOrd, idDiscount, idCustomer, 
				userCreate, dateCreate, userModif, dateModif); // 0 à vérifier
	}
	
	public Orders(Long idOrder, String nameOrd, String typeOrd, boolean payOrdON, double reductionOrd, Date takingDateOrd, 
			Date readyDateOrd, double priceHTOrd, double priceTTCOrd, Discounts idDiscount,Customers idCustomer, 
			String userCreate, Date dateCreate, String userModif, Date dateModif) {
		initOrders(getIdOrder(), nameOrd, typeOrd, payOrdON, reductionOrd, takingDateOrd, readyDateOrd, priceHTOrd, priceTTCOrd, idDiscount, idCustomer,
				userCreate, dateCreate, userModif, dateModif);
	}
	
	public long getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(long idOrder) {
		this.idOrder = idOrder;
	}

	public String getNameOrd() {
		return nameOrd;
	}

	public void setNameOrd(String nameOrd) {
		this.nameOrd = nameOrd;
	}

	public String getTypeOrd() {
		return typeOrd;
	}

	public void setTypeOrd(String typeOrd) {
		this.typeOrd = typeOrd;
	}

	public boolean isPayOrdON() {
		return payOrdON;
	}

	public void setPayOrdON(boolean payOrdON) {
		this.payOrdON = payOrdON;
	}

	public double getReductionOrd() {
		return reductionOrd;
	}

	public void setReductionOrd(double reductionOrd) {
		this.reductionOrd = reductionOrd;
	}

	public Date getTakingDateOrd() {
		return takingDateOrd;
	}

	public void setTakingDateOrd(Date takingDateOrd) {
		this.takingDateOrd = takingDateOrd;
	}

	public Date getReadyDateOrd() {
		return readyDateOrd;
	}

	public void setReadyDateOrd(Date readyDateOrd) {
		this.readyDateOrd = readyDateOrd;
	}

	public double getPriceHTOrd() {
		return priceHTOrd;
	}

	public void setPriceHTOrd(double priceHTOrd) {
		this.priceHTOrd = priceHTOrd;
	}

	public double getPriceTTCOrd() {
		return priceTTCOrd;
	}

	public void setPriceTTCOrd(double priceTTCOrd) {
		this.priceTTCOrd = priceTTCOrd;
	}

	public Discounts getIdDiscount() {
		return idDiscount;
	}

	public void setIdDiscount(Discounts idDiscount) {
		this.idDiscount = idDiscount;
	}

	public Customers getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(Customers idCustomer) {
		this.idCustomer = idCustomer;
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
		return "Orders: [idOrder = " + idOrder + "]";
	}

	// Pour debugger
	@Override
	public String toString() {
		String result = "Orders: [idOrder = " + idOrder + ", nameOrd = " + nameOrd + ", typeOrd = " + typeOrd + ", payOrdON = " + payOrdON
				+ ", reductionOrd = " + reductionOrd + ", takingDateOrd = " + takingDateOrd + ", readyDateOrd = "
				+ readyDateOrd + ", priceHTOrd = " + priceHTOrd + ", priceTTCOrd = " + priceTTCOrd + ", idDiscount = ";
		if(idDiscount != null) {
			result += idDiscount.resume();
		}
		result += ", idCustomer = ";
		if(idCustomer != null) {
			result += idCustomer.resume(); 
		}
		result += ", userCreate = " + userCreate + ", dateCreate = " + dateCreate + ", userModif = " + userModif + ", dateModif = " + dateModif + "]";
		return result;
	}
}
