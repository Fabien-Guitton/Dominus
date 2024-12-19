package tables;

import java.sql.Timestamp;

public class Supplements {
	private long idSupplement; // BIGINT
	private Ingredients idIngredient; // FOREIGN KEY : BIGINT
	private LineBasket idLineBasket; // FOREIGN KEY : BIGINT
	private int qtySup; // INT NOT NULL
	private boolean addSupON; // BOOLEAN NOT NULL
	private String userCreate; // VARCHAR(100) NOT NULL
	private Timestamp dateCreate; // DATETIME NOT NULL
	private String userModif; // VARCHAR(100) NOT NULL
	private Timestamp dateModif; // DATETIME NOT NULL
	
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
	public Supplements(Ingredients idIngredient, LineBasket idLineBasket, int qtySup, boolean addSupON,
			String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		initSupplements(0, idIngredient, idLineBasket, qtySup, addSupON, userCreate, dateCreate, userModif, dateModif);
	}
	
	public Supplements(long idSupplement, Ingredients idIngredient, LineBasket idLineBasket, int qtySup, boolean addSupON,
			String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		initSupplements(idSupplement, idIngredient, idLineBasket, qtySup, addSupON, userCreate, dateCreate, userModif, dateModif);
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
	
	// Pour debugger
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
