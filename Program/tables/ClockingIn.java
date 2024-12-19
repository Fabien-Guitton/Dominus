package tables;

import java.sql.Timestamp;

public class ClockingIn {
	private long idClockingIn; // PRIMARY KEY : BIGINT AUTO_INCREMENT NOT NULL
	private Timestamp startClockingIn; // DATETIME NOT NULL
	private Timestamp endClockingIn; // DATETIME
	private Employees idEmployee; // FOREIGN KEY : BIGINT
	private String userCreate; // VARCHAR(100) NOT NULL
	private Timestamp dateCreate; // DATETIME NOT NULL
	private String userModif; // VARCHAR(100) NOT NULL
	private Timestamp dateModif; // DATETIME NOT NULL

	private void initClockingIn(long idClockingIn, Timestamp startClockingIn, Timestamp endClockingIn, Employees idEmployee, String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		this.idClockingIn = idClockingIn;
		this.startClockingIn = startClockingIn;
		this.endClockingIn = endClockingIn;
		this.idEmployee = idEmployee;
		this.userCreate = userCreate;
		this.dateCreate = dateCreate;
		this.userModif = userModif;
		this.dateModif = dateModif;
//		For debug	
//		if(this.idEmployee == null) { 
//			System.out.println("WARNING: There are no linked employees.");
//		}
	}

	// PEUT ETRE D'AUTRE A CREER SUIVANT LA DAO
	public ClockingIn(long idClockingInd, Timestamp startClockingIn, Timestamp endClockingIn, Employees idEmployee, String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		initClockingIn(idClockingInd, startClockingIn, endClockingIn, idEmployee, userCreate, dateCreate, userModif, dateModif);
	}
	
	public ClockingIn(Timestamp startClockingIn, Timestamp endClockingIn, Employees idEmployee, String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		initClockingIn(0, startClockingIn, endClockingIn, idEmployee, userCreate, dateCreate, userModif, dateModif);
	}
	
	public ClockingIn(long idClockingInd, Timestamp startClockingIn, Timestamp endClockingIn, Employees idEmployee) {
		initClockingIn(idClockingInd, startClockingIn, endClockingIn, idEmployee, 
				Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()), Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()));
	}
	
	public ClockingIn(Timestamp startClockingIn, Timestamp endClockingIn, Employees idEmployee) {
		initClockingIn(0, startClockingIn, endClockingIn, idEmployee, 
				Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()), Constants.JAVA_USER.getUser(), new Timestamp(System.currentTimeMillis()));
	}

	public long getIdClockingIn() {
		return idClockingIn;
	}

	public void setIdClockingIn(long idClockingIn) {
		this.idClockingIn = idClockingIn;
	}

	public Timestamp getStartClockingIn() {
		return startClockingIn;
	}

	public void setStartClockingIn(Timestamp startClockingIn) {
		this.startClockingIn = startClockingIn;
	}

	public Timestamp getEndClockingIn() {
		return endClockingIn;
	}

	public void setEndClockingIn(Timestamp endClockingIn) {
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
		String result = "ClockingIn: [idClockinIn = " + idClockingIn + ", startClockingIn = " + startClockingIn + ", endClockingIn = " + endClockingIn + 
				", idEmployee = ";
		if(idEmployee != null) {
			result += idEmployee.resume();
		}
		result += ", userCreate = " + userCreate + ", dateCreate = " + dateCreate + ", userModif = " + userModif + ", dateModif = " + dateModif + "]";
		return result;
	}
}
