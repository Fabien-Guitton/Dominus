package tables;

import java.sql.Timestamp;

public class Supplements {
	private long idSupplement; // PRIMARY KEY: BIGINT AUTO_INCREMENT NOT NULL
	private Ingredients idIngredient; // PRIMARY KEY + FOREIGN KEY: BIGINT
	private LineBasket idLineBasket; // PRIMARY KEY + FOREIGN KEY: BIGINT
	private int qtySup; // INT NOT NULL
	private boolean addSupON; // BOOLEAN NOT NULL
	private String userCreate; // VARCHAR(100) NOT NULL
	private Timestamp dateCreate; // DATETIME NOT NULL
	private String userModif; // VARCHAR(100) NOT NULL
	private Timestamp dateModif; // DATETIME NOT NULL
//	For debug	
//	if(this.idIngredient == null) { 
//		System.out.println("WARNING: There are no linked ingredients.");
//	}	
//	if(this.idLineBasket == null) { 
//		System.out.println("WARNING: There are no linked linebasket.");
//	}
	
	public void initSupplements(long idSupplement, Ingredients idIngredient, LineBasket idLineBasket, int qtySup, boolean addSupON,
			String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		this.idSupplement = idSupplement;
		this.idIngredient = idIngredient;
		this.idLineBasket = idLineBasket;
		this.qtySup = qtySup;
		this.addSupON = addSupON;
		this.userCreate = userCreate;
		this.dateCreate = dateCreate;
		this.userModif = userModif;
		this.dateModif = dateModif;
	}

	// PEUT ETRE D'AUTRE A CREER SUIVANT LA DAO
	public Supplements(long idSupplement, Ingredients idIngredient, LineBasket idLineBasket, int qtySup, boolean addSupON,
			String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		initSupplements(idSupplement, idIngredient, idLineBasket, qtySup, addSupON, userCreate, dateCreate, userModif, dateModif);
	}
	
	public Supplements(Ingredients idIngredient, LineBasket idLineBasket, int qtySup, boolean addSupON,
			String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		initSupplements(0, idIngredient, idLineBasket, qtySup, addSupON, userCreate, dateCreate, userModif, dateModif);
	}
	
	public Supplements(long idSupplement, Ingredients idIngredient, LineBasket idLineBasket, int qtySup, boolean addSupON) {
		initSupplements(idSupplement, idIngredient, idLineBasket, qtySup, addSupON, 
				Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()), Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()));
	}
	
	public Supplements(Ingredients idIngredient, LineBasket idLineBasket, int qtySup, boolean addSupON) {
		initSupplements(0, idIngredient, idLineBasket, qtySup, addSupON, 
				Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()), Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()));
	}
	
	public long getidSupplement() {
		return idSupplement;
	}

	public void setidSupplement(long idSupplement) {
		this.idSupplement = idSupplement;
	}
	
	public Ingredients getidIngredient() {
		return idIngredient;
	}

	public void setidIngredient(Ingredients idIngredient) {
		this.idIngredient = idIngredient;
	}

	public LineBasket getIdLineBasket() {
		return idLineBasket;
	}

	public void setIdLineBasket(LineBasket idLineBasket) {
		this.idLineBasket = idLineBasket;
	}

	public int getQtySup() {
		return qtySup;
	}

	public void setQtySup(int qtySup) {
		this.qtySup = qtySup;
	}

	public boolean isAddSupON() {
		return addSupON;
	}

	public void setAddSupON(boolean addSupON) {
		this.addSupON = addSupON;
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
		String result = "Supplements: [idSupplement = " + idSupplement + ", idIngredient = ";
		if(idIngredient != null) {
			result += idIngredient.resume();
		}
		result += ", idLineBasket = ";
		if(idLineBasket != null) {
			result += idLineBasket.resume(); 
		}
		result += ", qtySup = " + qtySup + ", addSupON = " + addSupON 
				+ ", userCreate = " + userCreate + ", dateCreate = " + dateCreate + ", userModif = " + userModif + ", dateModif = " + dateModif + "]";
		return result;
	}
}
