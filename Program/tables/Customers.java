package tables;

import java.sql.Timestamp;

public class Customers {
	private long idCustomer; // PRIMARY KEY : BIGINT AUTO_INCREMENT NOT NULL
	private String nameCst; // VARCHAR(100) NOT NULL
	private String telCst; // CHAR(10) NOT NULL
	private String streetNumberCst; // VARCHAR(50) NOT NULL
	private String streetNameCst; // VARCHAR(255) NOT NULL
	private String postcodeCst; // VARCHAR(50) NOT NULL
	private String instructionsCst; // VARCHAR(100)
	private String internalComCst; // VARCHAR(100)
	private String userCreate; // VARCHAR(100) NOT NULL
	private Timestamp dateCreate; // DATETIME NOT NULL
	private String userModif; // VARCHAR(100) NOT NULL
	private Timestamp dateModif; // DATETIME NOT NULL
	
	public void initCustomers(long idCustomer, String nameCst, String telCst, String streetNumberCst, String streetNameCst,
			String postcodeCst, String instructionsCst, String internalComCst, 
			String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		this.idCustomer = idCustomer;
		this.nameCst = nameCst;
		this.telCst = telCst;
		this.streetNumberCst = streetNumberCst;
		this.streetNameCst = streetNameCst;
		this.postcodeCst = postcodeCst;
		this.instructionsCst = instructionsCst;
		this.internalComCst = internalComCst;
		this.userCreate = userCreate;
		this.dateCreate = dateCreate;
		this.userModif = userModif;
		this.dateModif = dateModif;
	}
	
	// PEUT ETRE D'AUTRE A CREER SUIVANT LA DAO
	public Customers(long idCustomer, String nameCst, String telCst, String streetNumberCst, String streetNameCst, 
			String postcodeCst, String instructionsCst, String internalComCst, 
			String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		initCustomers(idCustomer, nameCst, telCst, streetNumberCst, streetNameCst, postcodeCst, instructionsCst, internalComCst, 
				userCreate, dateCreate, userModif, dateModif);
	}
	
	public Customers(String nameCst, String telCst, String streetNumberCst, String streetNameCst, 
			String postcodeCst, String instructionsCst, String internalComCst, 
			String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		initCustomers(0, nameCst, telCst, streetNumberCst, streetNameCst, postcodeCst, instructionsCst, internalComCst, 
				userCreate, dateCreate, userModif, dateModif);
	}
	
	public Customers(long idCustomer, String nameCst, String telCst, String streetNumberCst, String streetNameCst, 
			String postcodeCst, String instructionsCst, String internalComCst) {
		initCustomers(idCustomer, nameCst, telCst, streetNumberCst, streetNameCst, postcodeCst, instructionsCst, internalComCst, 
				Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()), Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()));
	}
	
	public Customers(String nameCst, String telCst, String streetNumberCst, String streetNameCst, 
			String postcodeCst, String instructionsCst, String internalComCst) {
		initCustomers(0, nameCst, telCst, streetNumberCst, streetNameCst, postcodeCst, instructionsCst, internalComCst, 
				Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()), Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()));
	}
	
	public long getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(long idCustomer) {
		this.idCustomer = idCustomer;
	}

	public String getNameCst() {
		return nameCst;
	}

	public void setNameCst(String nameCst) {
		this.nameCst = nameCst;
	}

	public String getTelCst() {
		return telCst;
	}

	public void setTelCst(String telCst) {
		this.telCst = telCst;
	}

	public String getStreetNumberCst() {
		return streetNumberCst;
	}

	public void setStreetNumberCst(String streetNumberCst) {
		this.streetNumberCst = streetNumberCst;
	}

	public String getStreetNameCst() {
		return streetNameCst;
	}

	public void setStreetNameCst(String streetNameCst) {
		this.streetNameCst = streetNameCst;
	}

	public String getPostcodeCst() {
		return postcodeCst;
	}

	public void setPostcodeCst(String postcodeCst) {
		this.postcodeCst = postcodeCst;
	}

	public String getInstructionsCst() {
		return instructionsCst;
	}

	public void setInstructionsCst(String instructionsCst) {
		this.instructionsCst = instructionsCst;
	}

	public String getInternalComCst() {
		return internalComCst;
	}

	public void setInternalComCst(String internalComCst) {
		this.internalComCst = internalComCst;
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
		return "Customers: [idCustomer = " + idCustomer + "]";
	}

//	For debug
	@Override
	public String toString() {
		return "Customers: [idCustomer = " + idCustomer + ", nameCst = " + nameCst + ", telCst = " + telCst + ", streetNumberCst = " + streetNumberCst 
				+ ", streetNameCst = " + streetNameCst + ", postcodeCst = " + postcodeCst + ", instructionsCst = " + instructionsCst + ", internalComCst = " + internalComCst
				+ ", userCreate = " + userCreate + ", dateCreate = " + dateCreate + ", userModif = " + userModif + ", dateModif = " + dateModif + "]";
	}
	
}
