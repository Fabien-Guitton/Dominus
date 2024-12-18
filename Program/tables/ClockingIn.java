package tables;

import java.sql.Date; 

public class ClockingIn {
	private long idClockingIn; // BIGINT AUTO_INCREMENT NOT NULL
	private Date startClockingIn; // DATETIME NOT NULL
	private Date endClockingIn; // DATETIME
	private Employees idEmployee; // FOREIGN KEY : BIGINT
	private String userCreate; // VARCHAR(100) NOT NULL
	private Date dateCreate; // DATETIME NOT NULL
	private String userModif; // VARCHAR(100) NOT NULL
	private Date dateModif; // DATETIME NOT NULL

	private void initClockingIn(long idClockingIn, Date startClockingIn, Date endClockingIn, Employees idEmployee, String userCreate, Date dateCreate, String userModif, Date dateModif) {
		this.idClockingIn = idClockingIn;
		this.startClockingIn = startClockingIn;
		this.endClockingIn = endClockingIn;
		this.idEmployee = idEmployee;
		this.userCreate = userCreate;
		this.dateCreate = dateCreate;
		this.userModif = userModif;
		this.dateModif = dateModif;
//		if(this.idEmployee != null) { 
//			Afficher un message d'erreur pour dire que cet employee existe pas
//		}
	}

	// PEUT ETRE D'AUTRE A CREER SUIVANT LA DAO
	public ClockingIn(Date startClockingIn, Date endClockingIn, Employees idEmployee, String userCreate, Date dateCreate, String userModif, Date dateModif) {
		initClockingIn(0, startClockingIn, endClockingIn, idEmployee, userCreate, dateCreate, userModif, dateModif); // regarder si c'est bien 0
	}

	public ClockingIn(long idClockingInd, Date startClockingIn, Date endClockingIn, Employees idEmployee, String userCreate, Date dateCreate, String userModif, Date dateModif) {
		initClockingIn(idClockingInd, startClockingIn, endClockingIn, idEmployee, userCreate, dateCreate, userModif, dateModif);
	}

	public long getIdClockingIn() {
		return idClockingIn;
	}

	public void setIdClockingIn(long idClockingIn) {
		this.idClockingIn = idClockingIn;
	}

	public Date getStartClockingIn() {
		return startClockingIn;
	}

	public void setStartClockingIn(Date startClockingIn) {
		this.startClockingIn = startClockingIn;
	}

	public Date getEndClockingIn() {
		return endClockingIn;
	}

	public void setEndClockingIn(Date endClockingIn) {
		this.endClockingIn = endClockingIn;
	}

	public Employees getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(Employees idEmployee) {
		this.idEmployee = idEmployee;
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
		String result = "ClockingIn: [idClockinIn = " + idClockingIn + ", startClockingIn = " + startClockingIn + ", endClockingIn = " + endClockingIn + 
				", idEmployee = ";
		if(idEmployee != null) {
			result += idEmployee.resume();
		}
		result += ", userCreate = " + userCreate + ", dateCreate = " + dateCreate + ", userModif = " + userModif + ", dateModif = " + dateModif + "]";
		return result;
	}
}
