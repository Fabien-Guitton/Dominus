package tests;

//import java.sql.Timestamp;
import java.util.ArrayList;
import dao.*;
import tables.*;

public class DaoTest {
	
	public static <T> void showData(ArrayList<T> data) {
		if(data.size() == 0) {
			System.out.println("NO DATA");
		}else {
			data.stream().forEach(System.out::println);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		/*
		Customers cst = new Customers(0, "Musa ADEMI", "0767948123", "14", "Rue Jules Favre", "37000", "SAUCE PIQUANTE", "Pas ouf", "", new Timestamp(System.currentTimeMillis()), "", new Timestamp(System.currentTimeMillis()));
		//System.out.println(cst);
		CustomersDAO cstDAO = new CustomersDAO(); // Implémentation DAO
		cst = cstDAO.create(cst); // Mise à jour ID pour l'objet java
		ArrayList<Customers> customers = cstDAO.readAll();
		customers.stream().forEach(System.out::println);
		*/
		
		ClockingInDAO cloDAO = new ClockingInDAO();
		ArrayList<ClockingIn> clo = new ArrayList<>();
		clo = cloDAO.readAll();
		showData(clo);
		
		CustomersDAO custDAO = new CustomersDAO();
		ArrayList<Customers> cust = new ArrayList<>();
		cust = custDAO.readAll();
		showData(cust);
		
		DefaultIngredientsDAO defaultingDAO = new DefaultIngredientsDAO();
		ArrayList<DefaultIngredients> defaulting = new ArrayList<>();
		defaulting = defaultingDAO.readAll();
		showData(defaulting);
		
		DiscountsDAO distDAO = new DiscountsDAO();
		ArrayList<Discounts> dist = new ArrayList<>();
		dist = distDAO.readAll();
		showData(dist);
		
		EmployeesDAO empDAO = new EmployeesDAO();
		ArrayList<Employees> emp = new ArrayList<>();
		emp = empDAO.readAll();
		showData(emp);
		
		IngredientsDAO ingDAO = new IngredientsDAO();
		ArrayList<Ingredients> ing = new ArrayList<>();
		ing = ingDAO.readAll();
		showData(ing);
		
		LineBasketDAO lbDAO = new LineBasketDAO();
		ArrayList<LineBasket> lb = new ArrayList<>();
		lb = lbDAO.readAll();
		showData(lb);
		
		OrdersDAO ordDAO = new OrdersDAO();
		ArrayList<Orders> ord = new ArrayList<>();
		ord = ordDAO.readAll();
		showData(ord);
		
		ProductsDAO prodDAO = new ProductsDAO();
		ArrayList<Products> prod = new ArrayList<>();
		prod = prodDAO.readAll();
		showData(prod);
		
		SupplementsDAO supDAO = new SupplementsDAO();
		ArrayList<Supplements> sup = new ArrayList<>();
		sup = supDAO.readAll();
		showData(sup);
		
		TakeResponsabilityForDAO trfDAO = new TakeResponsabilityForDAO();
		ArrayList<TakeResponsabilityFor> trf = new ArrayList<>();
		trf = trfDAO.readAll();
		showData(trf);
		
	}

}
