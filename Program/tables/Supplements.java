package tables;

import java.sql.Timestamp;

public class Supplements {
	private Ingredients idIgredient; // FOREIGN KEY : BIGINT
	private LineBasket idLineBasket; // FOREIGN KEY : BIGINT
	private int qtySup; // INT NOT NULL
	private boolean addSupON; // BOOLEAN NOT NULL
	private String userCreate; // VARCHAR(100) NOT NULL
	private Timestamp dateCreate; // DATETIME NOT NULL
	private String userModif; // VARCHAR(100) NOT NULL
	private Timestamp dateModif; // DATETIME NOT NULL
	
	public void initSupplements(Ingredients idIgredient, LineBasket idLineBasket, int qtySup, boolean addSupON,
			String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		this.idIgredient = idIgredient;
		this.idLineBasket = idLineBasket;
		this.qtySup = qtySup;
		this.addSupON = addSupON;
		this.userCreate = userCreate;
		this.dateCreate = dateCreate;
		this.userModif = userModif;
		this.dateModif = dateModif;
	}

	// PEUT ETRE D'AUTRE A CREER SUIVANT LA DAO
	public Supplements(Ingredients idIgredient, LineBasket idLineBasket, int qtySup, boolean addSupON,
			String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		initSupplements(idIgredient, idLineBasket, qtySup, addSupON, userCreate, dateCreate, userModif, dateModif);
	}
	
	public Ingredients getIdIgredient() {
		return idIgredient;
	}

	public void setIdIgredient(Ingredients idIgredient) {
		this.idIgredient = idIgredient;
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
		String result = "Supplements: [idIgredient = ";
		if(idIgredient != null) {
			result += idIgredient.resume();
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
