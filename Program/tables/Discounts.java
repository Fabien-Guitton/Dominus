package tables;

import java.sql.Timestamp;

public class Discounts {
	private long idDiscount; // BIGINT AUTO_INCREMENT NOT NULL
	private String nameDist; // VARCHAR(50) NOT NULL
	private double valueDist; // DOUBLE NOT NULL
	private String codeDist; // CHAR(6) NOT NULL
	private boolean accredDistON; // BOOLEAN NOT NULL
	private String userCreate; // VARCHAR(100) NOT NULL
	private Timestamp dateCreate; // DATETIME NOT NULL
	private String userModif; // VARCHAR(100) NOT NULL
	private Timestamp dateModif; // DATETIME NOT NULL
	   
	public void initDiscounts(long idDiscount, String nameDist, double valueDist, String codeDist, boolean accredDistON,
			String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		this.idDiscount = idDiscount;
		this.nameDist = nameDist;
		this.valueDist = valueDist;
		this.codeDist = codeDist;
		this.accredDistON = accredDistON;
		this.userCreate = userCreate;
		this.dateCreate = dateCreate;
		this.userModif = userModif;
		this.dateModif = dateModif;
	}
	
	// PEUT ETRE D'AUTRE A CREER SUIVANT LA DAO
	public Discounts(String nameDist, double valueDist, String codeDist, boolean accredDistON,
			String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		initDiscounts(0, nameDist, valueDist, codeDist, accredDistON, userCreate, dateCreate, userModif, dateModif); // 0 à vérifier
	}
	
	public Discounts(long idDiscount, String nameDist, double valueDist, String codeDist, boolean accredDistON,
			String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		initDiscounts(idDiscount, nameDist, valueDist, codeDist, accredDistON, userCreate, dateCreate, userModif, dateModif);
	}

	public long getIdDiscount() {
		return idDiscount;
	}

	public void setIdDiscount(long idDiscount) {
		this.idDiscount = idDiscount;
	}

	public String getNameDist() {
		return nameDist;
	}

	public void setNameDist(String nameDist) {
		this.nameDist = nameDist;
	}

	public double getValueDist() {
		return valueDist;
	}

	public void setValueDist(double valueDist) {
		this.valueDist = valueDist;
	}

	public String getCodeDist() {
		return codeDist;
	}

	public void setCodeDist(String codeDist) {
		this.codeDist = codeDist;
	}

	public boolean isAccredDistON() {
		return accredDistON;
	}

	public void setAccredDistON(boolean accredDistON) {
		this.accredDistON = accredDistON;
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
		return "Discounts: [idDiscount = " + idDiscount + "]";
	}

	// Pour debugger
	@Override
	public String toString() {
		return "Discounts: [idDiscount = " + idDiscount + ", nameDist = " + nameDist + ", valueDist = " + valueDist
				+ ", codeDist = " + codeDist + ", accredDistON = " + accredDistON 
				+ ", userCreate = " + userCreate + ", dateCreate = " + dateCreate + ", userModif = " + userModif + ", dateModif = " + dateModif + "]";
	}	   
}
