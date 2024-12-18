package tables;

import java.sql.Timestamp;

public class Employees {
	private long idEmployee; // BIGINT AUTO_INCREMENT NOT NULL
	private String nameEmp; // VARCHAR(100) NOT NULL
	private String codeEmp; // CHAR(4) NOT NULL
	private String roleEmp; // VARCHAR(50) NOT NULL
	private String telEmp; // CHAR(10) NOT NULL
	private String userCreate; // VARCHAR(100) NOT NULL
	private Timestamp dateCreate; // DATETIME NOT NULL
	private String userModif; // VARCHAR(100) NOT NULL
	private Timestamp dateModif; // DATETIME NOT NULL
	
	public void initEmployees(long idEmployee, String nameEmp, String codeEmp, String roleEmp, String telEmp, 
			String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		this.idEmployee = idEmployee;
		this.nameEmp = nameEmp;
		this.codeEmp = codeEmp; // UNIQUE code comment à gérer !
		this.roleEmp = roleEmp;
		this.telEmp = telEmp;
		this.userCreate = userCreate;
		this.dateCreate = dateCreate;
		this.userModif = userModif;
		this.dateModif = dateModif;
	}
	
	// PEUT ETRE D'AUTRE A CREER SUIVANT LA DAO
	public Employees(String nameEmp, String codeEmp, String roleEmp, String telEmp, 
			String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		initEmployees(0, nameEmp, codeEmp, roleEmp, telEmp, userCreate, dateCreate, userModif, dateModif); // 0 ?
	}
	
	public Employees(long idEmployee, String nameEmp, String codeEmp, String roleEmp, String telEmp, 
			String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
		initEmployees(idEmployee, nameEmp, codeEmp, roleEmp, telEmp, userCreate, dateCreate, userModif, dateModif);
	}

	public long getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(long idEmployee) {
		this.idEmployee = idEmployee;
	}

	public String getNameEmp() {
		return nameEmp;
	}

	public void setNameEmp(String nameEmp) {
		this.nameEmp = nameEmp;
	}

	public String getCodeEmp() {
		return codeEmp;
	}

	public void setCodeEmp(String codeEmp) {
		this.codeEmp = codeEmp;
	}

	public String getRoleEmp() {
		return roleEmp;
	}

	public void setRoleEmp(String roleEmp) {
		this.roleEmp = roleEmp;
	}

	public String getTelEmp() {
		return telEmp;
	}

	public void setTelEmp(String telEmp) {
		this.telEmp = telEmp;
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
		return "Employees: [idEmployee = " + idEmployee + "]";
	}

	// Pour debugger
	@Override
	public String toString() {
		return "Employees: [idEmployee = " + idEmployee + ", nameEmp = " + nameEmp + ", codeEmp = " + codeEmp + ", roleEmp = " + roleEmp + ", telEmp = " + telEmp 
				+ ", userCreate = " + userCreate + ", dateCreate = " + dateCreate + ", userModif = " + userModif + ", dateModif = " + dateModif + "]";
	}
}
