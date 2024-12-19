package tables;

import java.sql.Timestamp;

public class Orders {
	private long idOrder; // PRIMARY KEY: BIGINT AUTO_INCREMENT NOT NULL
	private String nameOrd; // VARCHAR(50) NOT NULL
	private String typeOrd; // VARCHAR(50) NOT NULL
	private boolean payOrdON; // BOOLEAN NOT NULL
	private double reductionOrd; // DOUBLE
	private Timestamp takingDateOrd; // DATETIME NOT NULL
	private Timestamp readyDateOrd; // DATETIME NOT NULL
	private double priceETOrd; // DOUBLE NOT NULL
	private double priceITOrd; // DOUBLE NOT NULL
	private String instructionsOrd; // VARCHAR(100)
	private Discounts idDiscount; // FOREIGN KEY: BIGINT
	private Customers idCustomer; // FOREIGN KEY: BIGINT
	private String userCreate; // VARCHAR(100) NOT NULL
	private Timestamp dateCreate; // DATETIME NOT NULL
	private String userModif; // VARCHAR(100) NOT NULL
	private Timestamp dateModif; // DATETIME NOT NULL
//	For debug	
//	if(this.idDiscount == null) { 
//		System.out.println("WARNING: There are no linked discounts.");
//	}	
//	if(this.idCustomer == null) { 
//		System.out.println("WARNING: There are no linked customers.");
//	}
	
	public void initOrders(long idOrder, String nameOrd, String typeOrd, boolean payOrdON, double reductionOrd, Timestamp takingDateOrd, 
			Timestamp readyDateOrd, double priceETOrd, double priceITOrd, String instructionsOrd, Discounts idDiscount,Customers idCustomer, 
			String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		this.idOrder = idOrder;
		this.nameOrd = nameOrd;
		this.typeOrd = typeOrd;
		this.payOrdON = payOrdON;
		this.reductionOrd = reductionOrd;
		this.takingDateOrd = takingDateOrd;
		this.readyDateOrd = readyDateOrd;
		this.priceETOrd = priceETOrd;
		this.priceITOrd = priceITOrd;
		this.instructionsOrd = instructionsOrd;
		this.idDiscount = idDiscount;
		this.idCustomer = idCustomer;
		this.userCreate = userCreate;
		this.dateCreate = dateCreate;
		this.userModif = userModif;
		this.dateModif = dateModif;
	}

	// PEUT ETRE D'AUTRE A CREER SUIVANT LA DAO
	public Orders(Long idOrder, String nameOrd, String typeOrd, boolean payOrdON, double reductionOrd, Timestamp takingDateOrd, Timestamp readyDateOrd, 
			double priceETOrd, double priceITOrd, String instructionsOrd, Discounts idDiscount,Customers idCustomer, 
			String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		initOrders(getIdOrder(), nameOrd, typeOrd, payOrdON, reductionOrd, takingDateOrd, readyDateOrd, priceETOrd, priceITOrd, 
				instructionsOrd, idDiscount, idCustomer, userCreate, dateCreate, userModif, dateModif);
	}
	
	public Orders(String nameOrd, String typeOrd, boolean payOrdON, double reductionOrd, Timestamp takingDateOrd, Timestamp readyDateOrd, 
			double priceETOrd, double priceITOrd, String instructionsOrd, Discounts idDiscount,Customers idCustomer, 
			String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		initOrders(0, nameOrd, typeOrd, payOrdON, reductionOrd, takingDateOrd, readyDateOrd, priceETOrd, priceITOrd, instructionsOrd, idDiscount, idCustomer, 
				userCreate, dateCreate, userModif, dateModif);
	}
	
	public Orders(Long idOrder, String nameOrd, String typeOrd, boolean payOrdON, double reductionOrd, Timestamp takingDateOrd, Timestamp readyDateOrd, 
			double priceETOrd, double priceITOrd, String instructionsOrd, Discounts idDiscount,Customers idCustomer) {
		initOrders(getIdOrder(), nameOrd, typeOrd, payOrdON, reductionOrd, takingDateOrd, readyDateOrd, priceETOrd, priceITOrd, 
				instructionsOrd, idDiscount, idCustomer, 
				Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()), Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()));
	}
	
	public Orders(String nameOrd, String typeOrd, boolean payOrdON, double reductionOrd, Timestamp takingDateOrd, Timestamp readyDateOrd, 
			double priceETOrd, double priceITOrd, String instructionsOrd, Discounts idDiscount,Customers idCustomer) {
		initOrders(0, nameOrd, typeOrd, payOrdON, reductionOrd, takingDateOrd, readyDateOrd, priceETOrd, priceITOrd, instructionsOrd, idDiscount, idCustomer, 
				Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()), Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()));
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

	public Timestamp getTakingDateOrd() {
		return takingDateOrd;
	}

	public void setTakingDateOrd(Timestamp takingDateOrd) {
		this.takingDateOrd = takingDateOrd;
	}

	public Timestamp getReadyDateOrd() {
		return readyDateOrd;
	}

	public void setReadyDateOrd(Timestamp readyDateOrd) {
		this.readyDateOrd = readyDateOrd;
	}

	public double getPriceETOrd() {
		return priceETOrd;
	}

	public void setPriceETOrd(double priceETOrd) {
		this.priceETOrd = priceETOrd;
	}

	public double getPriceITOrd() {
		return priceITOrd;
	}

	public void setPriceITOrd(double priceITOrd) {
		this.priceITOrd = priceITOrd;
	}
	
	public String getInstructionsOrd() {
		return instructionsOrd;
	}

	public void setInstructionsOrd(String instructionsOrd) {
		this.instructionsOrd = instructionsOrd;
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
		return "Orders: [idOrder = " + idOrder + "]";
	}

// 	For debug
	@Override
	public String toString() {
		String result = "Orders: [idOrder = " + idOrder + ", nameOrd = " + nameOrd + ", typeOrd = " + typeOrd + ", payOrdON = " + payOrdON
				+ ", reductionOrd = " + reductionOrd + ", takingDateOrd = " + takingDateOrd + ", readyDateOrd = "
				+ readyDateOrd + ", priceETOrd = " + priceETOrd + ", priceITOrd = " + priceITOrd + ", instructionsOrd = " + instructionsOrd + ", idDiscount = ";
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
