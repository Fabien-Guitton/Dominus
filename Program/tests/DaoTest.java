package tests;

import java.sql.Timestamp;
import java.util.ArrayList;
import dao.CustomersDAO;
import tables.Customers;

public class DaoTest {

	public static void main(String[] args) {
		Customers cst = new Customers(0, "Musa ADEMI", "0767948123", "14", "Rue Jules Favre", "37000", "SAUCE PIQUANTE", "Pas ouf", "", new Timestamp(System.currentTimeMillis()), "", new Timestamp(System.currentTimeMillis()));
		//System.out.println(cst);
		CustomersDAO cstDAO = new CustomersDAO(); // Implémentation DAO
		cst = cstDAO.create(cst); // Mise à jour ID pour l'objet java
		ArrayList<Customers> customers = cstDAO.readAll();
		customers.stream().forEach(System.out::println);
	}

}
