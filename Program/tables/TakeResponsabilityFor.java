package tables;

import java.sql.Date;

public class TakeResponsabilityFor {   
	private Orders idOrder; // FOREIGN KEY : BIGINT
	private Employees idEmployee; // FOREIGN KEY : BIGINT
	private boolean deliveryTakeON; // BOOLEAN NOT NULL
	private boolean paymentTakeON; // BOOLEAN NOT NULL
	private String userCreate; // VARCHAR(100) NOT NULL
	private Date dateCreate; // DATETIME NOT NULL
	private String userModif; // VARCHAR(100) NOT NULL
	private Date dateModif; // DATETIME NOT NULL
	
	public void initTakeResponsabilityFor(Orders idOrder, Employees idEmployee, boolean deliveryTakeON, boolean paymentTakeON,
			String userCreate, Date dateCreate, String userModif, Date dateModif) {
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
	public TakeResponsabilityFor(Orders idOrder, Employees idEmployee, boolean deliveryTakeON, boolean paymentTakeON,
			String userCreate, Date dateCreate, String userModif, Date dateModif) {
		initTakeResponsabilityFor(idOrder, idEmployee, deliveryTakeON, paymentTakeON, userCreate, dateCreate, userModif, dateModif);
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

	// Pour debugger
	@Override
	public String toString() {
		String result = "TakeResponsabilityFor: [idOrder = ";
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
