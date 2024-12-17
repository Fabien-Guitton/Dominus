package tables;

import java.sql.Date; 

public class ClockingIn {
	private long idClockingIn; // BIGINT AUTO_INCREMENT NOT NULL
	private Date startClockingIn; // DATETIME NOT NULL
	private Date endClockingIn; // DATETIME
	
	// PAPA
	private String userCreate; // VARCHAR(100) NOT NULL
	private Date dateCreate; // DATETIME NOT NULL
	private String userModif; // VARCHAR(100) NOT NULL
	private Date dateModif; // DATETIME NOT NULL
	
	private Employees idEmployee; // FOREIGN KEY : BIGINT

	private void initClockingIn(long idClockingIn, Date startClockingIn, Date endClockingIn, Employees idEmployee) {
		this.idClockingIn = idClockingIn;
		this.startClockingIn = startClockingIn;
		this.endClockingIn = endClockingIn;
		this.idEmployee = idEmployee;
		// Creer la methode add pour que ça marche !
//		if(this.idEmployee != null) { 
//			Afficher un message d'erreur pour dire que cet employee existe pas
//		}
	}

	// PEUT ETRE D'AUTRE A CREER SUIVANT LA DAO
	public ClockingIn(Date startClockingIn, Date endClockingIn, Employees idEmployee) {
		initClockingIn(0, startClockingIn, endClockingIn, idEmployee); // regarder si c'est bien 0
	}

	public ClockingIn(long idClockingInd, Date startClockingIn, Date endClockingIn, Employees idEmployee) {
		initClockingIn(idClockingInd, startClockingIn, endClockingIn, idEmployee);
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

	// Pour debugger
	@Override
	public String toString() {
		String result = "ClockingIn : [id long idClockingInd, Date startClockingIn, Date endClockingIn, Employees emp";
	}
}
