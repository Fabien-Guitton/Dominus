package tables;

import java.sql.Timestamp;

public class TakeResponsabilityFor {   
	private long idTakeResponsabilityFor; // PRIMARY KEY: BIGINT AUTO_INCREMENT NOT NULL
	private Orders idOrder; // PRIMARY KEY + FOREIGN KEY: BIGINT
	private Employees idEmployee; // PRIMARY KEY + FOREIGN KEY: BIGINT
	private boolean deliveryTakeON; // BOOLEAN NOT NULL
	private boolean paymentTakeON; // BOOLEAN NOT NULL
	private String userCreate; // VARCHAR(100) NOT NULL
	private Timestamp dateCreate; // DATETIME NOT NULL
	private String userModif; // VARCHAR(100) NOT NULL
	private Timestamp dateModif; // DATETIME NOT NULL
//	For debug	
//	if(this.idOrder == null) { 
//		System.out.println("WARNING: There are no linked orders.");
//	}	
//	if(this.idEmployee == null) { 
//		System.out.println("WARNING: There are no linked employees.");
//	}

	public void initTakeResponsabilityFor(long idTakeResponsabilityFor, Orders idOrder, Employees idEmployee, boolean deliveryTakeON, boolean paymentTakeON,
			String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		this.idTakeResponsabilityFor = idTakeResponsabilityFor;
		this.idOrder = idOrder;
		this.idEmployee = idEmployee;
		this.deliveryTakeON = deliveryTakeON;
		this.paymentTakeON = paymentTakeON;
		this.userCreate = userCreate;
		this.dateCreate = dateCreate;
		this.userModif = userModif;
		this.dateModif = dateModif;
	}

	// PEUT ETRE D'AUTRE A CREER SUIVANT LA DAO
	public TakeResponsabilityFor(long idTakeResponsabilityFor, Orders idOrder, Employees idEmployee, boolean deliveryTakeON, boolean paymentTakeON,
			String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		initTakeResponsabilityFor(idTakeResponsabilityFor, idOrder, idEmployee, deliveryTakeON, paymentTakeON, userCreate, dateCreate, userModif, dateModif);
	}
	
	public TakeResponsabilityFor(Orders idOrder, Employees idEmployee, boolean deliveryTakeON, boolean paymentTakeON,
			String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		initTakeResponsabilityFor(0, idOrder, idEmployee, deliveryTakeON, paymentTakeON, userCreate, dateCreate, userModif, dateModif);
	}
	
	public TakeResponsabilityFor(long idTakeResponsabilityFor, Orders idOrder, Employees idEmployee, boolean deliveryTakeON, boolean paymentTakeON) {
		initTakeResponsabilityFor(idTakeResponsabilityFor, idOrder, idEmployee, deliveryTakeON, paymentTakeON, 
				Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()), Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()));
	}
	
	public TakeResponsabilityFor(Orders idOrder, Employees idEmployee, boolean deliveryTakeON, boolean paymentTakeON) {
		initTakeResponsabilityFor(0, idOrder, idEmployee, deliveryTakeON, paymentTakeON, 
				Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()), Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()));
	}
	
	public long getidTakeResponsabilityFor() {
		return idTakeResponsabilityFor;
	}

	public void setidTakeResponsabilityFor(long idTakeResponsabilityFor) {
		this.idTakeResponsabilityFor = idTakeResponsabilityFor;
	}

	public Orders getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Orders idOrder) {
		this.idOrder = idOrder;
	}

	public Employees getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(Employees idEmployee) {
		this.idEmployee = idEmployee;
	}

	public boolean isDeliveryTakeON() {
		return deliveryTakeON;
	}

	public void setDeliveryTakeON(boolean deliveryTakeON) {
		this.deliveryTakeON = deliveryTakeON;
	}

	public boolean isPaymentTakeON() {
		return paymentTakeON;
	}

	public void setPaymentTakeON(boolean paymentTakeON) {
		this.paymentTakeON = paymentTakeON;
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
		String result = "TakeResponsabilityFor: [idTakeResponsabilityFor = " + idTakeResponsabilityFor + ", idOrder = ";
		if(idOrder != null) {
			result += idOrder.resume();
		}
		result += ", idEmployee = ";
		if(idEmployee != null) {
			result += idEmployee.resume();
		}
		result += ", deliveryTakeON = " + deliveryTakeON + ", paymentTakeON = " + paymentTakeON + 
				", userCreate = " + userCreate + ", dateCreate = " + dateCreate + ", userModif = " + userModif + ", dateModif = " + dateModif + "]";
		return result; 
	}
}
