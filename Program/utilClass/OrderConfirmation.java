package utilClass;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import applications.checkout.checkout.CheckoutCheckoutController;
import applications.checkout.customer.CheckoutCustomerController;
import applications.checkout.menu.CheckoutMenuController;
import applications.checkout.order.CheckoutOrderController;
import applications.menu.MenuController;
import dao.CustomersDAO;
import dao.DiscountsDAO;
import dao.IngredientsDAO;
import dao.LineBasketDAO;
import dao.OrdersDAO;
import dao.SupplementsDAO;
import dao.TakeResponsabilityForDAO;
import init.SceneManager;
import tables.Customers;
import tables.Discounts;
import tables.Employees;
import tables.Ingredients;
import tables.LineBasket;
import tables.Orders;
import tables.Products;
import tables.Supplements;
import tables.TakeResponsabilityFor;

public class OrderConfirmation {
	
	private static String missingInfoScene = "";
	private static final int PIZZA_ESTIMATED_TIME = 5; // 5 minutes de préparation par pizza
	private static Double lbPrice = 0.;
	
	public static String getMissingInfoScene() {
		return missingInfoScene;
	}

	private static ArrayList<LBInfos> getCart(){
		CheckoutOrderController controller = (CheckoutOrderController) SceneManager.getController(ScenesMap.CHECKOUT_ORDER);
    	return controller.getCart();
	}
	
	private static Boolean isOrderPayed() {
		CheckoutCheckoutController controller = (CheckoutCheckoutController) SceneManager.getController(ScenesMap.CHECKOUT_PAYMENT);
    	return controller.getIsOrderPayed();
	}
	
	private static String getOrderName() {
		CheckoutCustomerController controller = (CheckoutCustomerController) SceneManager.getController(ScenesMap.CHECKOUT_CUSTOMER);
    	return controller.getNameCst();
	}
	
	private static String getTelCst() {
		CheckoutCustomerController controller = (CheckoutCustomerController) SceneManager.getController(ScenesMap.CHECKOUT_CUSTOMER);
    	return controller.getTelCst();
	}

	private static String getNumAdressCst() {
		CheckoutCustomerController controller = (CheckoutCustomerController) SceneManager.getController(ScenesMap.CHECKOUT_CUSTOMER);
		return controller.getNumAdressCst();
	}
	
	private static String getAdressCst() {
		CheckoutCustomerController controller = (CheckoutCustomerController) SceneManager.getController(ScenesMap.CHECKOUT_CUSTOMER);
		return controller.getAdressCst();
	}

	private static String getPostalCodeCst() {
		CheckoutCustomerController controller = (CheckoutCustomerController) SceneManager.getController(ScenesMap.CHECKOUT_CUSTOMER);
		return controller.getPostalCodeCst();
	}
	
	private static String getInstructionsCst() {
		CheckoutCustomerController controller = (CheckoutCustomerController) SceneManager.getController(ScenesMap.CHECKOUT_CUSTOMER);
		return controller.getInstructionsCst();
	}
	
	private static String getInternalComCst() {
		CheckoutCustomerController controller = (CheckoutCustomerController) SceneManager.getController(ScenesMap.CHECKOUT_CUSTOMER);
		return controller.getInternalComCst();
	}
	
	private static Customers getCst() {
		CheckoutCustomerController controller = (CheckoutCustomerController) SceneManager.getController(ScenesMap.CHECKOUT_CUSTOMER);
    	return controller.getCst();
	}
	
	private static Double getITPrice() {
		CheckoutOrderController controller = (CheckoutOrderController) SceneManager.getController(ScenesMap.CHECKOUT_ORDER);
    	return controller.getTotalCartPrice();
	}
	
	private static Double getETPrice() {
		CheckoutOrderController controller = (CheckoutOrderController) SceneManager.getController(ScenesMap.CHECKOUT_ORDER);
    	return controller.getTotalCartPrice() * (1 - Taxe.CURRENT_TAXES.getTaxe() / 100.);
	}
	
	private static String getOrderType() {
		CheckoutMenuController controller = (CheckoutMenuController) SceneManager.getController(ScenesMap.CHECKOUT_MENU);
    	return controller.getOrderType();
	}
	
	private static Employees getConnectedEmp() {
		MenuController controller = (MenuController) SceneManager.getController(ScenesMap.MENU);
    	return controller.getConnectedEmp();
	}
	
	public static Boolean isCustomerValid() {
		Customers cst = getCst();
		String ordName = getOrderName();
		return (cst != null || (cst == null && ordName != null));
	}
	
	public static Boolean isCartValid() {
		return getCart().size() > 0;
	}
	
	public static Boolean isOrderTypeValidClassic() {		
		return getOrderType() != null;
	}
	
	public static Boolean isOrderTypeValid() {
//		System.out.println(getOrderType() + " -> 'Delivery'");
//		System.out.println((getOrderType() != null && !getOrderType().equals("Delivery")) + " -> FALSE");
//		System.out.println((getOrderType() != null && (getOrderType().equals("Delivery") && getCst() != null)) + " -> FALSE");
//		System.out.println((getOrderType().equals("Delivery") && getOrderName() != null && getTelCst() != null && getNumAdressCst() != null && getAdressCst() != null && getPostalCodeCst() != null) + " -> FALSE");
		
		return (getOrderType() != null && !getOrderType().equals("Delivery")) || (getOrderType() != null && (getOrderType().equals("Delivery") && getCst() != null)) || (getOrderType().equals("Delivery") && getOrderName() != null && getTelCst() != null && getNumAdressCst() != null && getAdressCst() != null && getPostalCodeCst() != null);
	}
	
	public static Boolean isOrderPayedValid() {
		return isOrderPayed() != null;
	}
	
	public static Boolean isOrderValid () {
		return isCustomerValid() && isCartValid() && isOrderTypeValid() && isOrderPayedValid();
	}
	
	/* Confirm order to save it */
	public static Boolean SendOrder() {
		if (!isCustomerValid()) {
			// Send error
			missingInfoScene = "Client";
			System.out.println("error SendOrder() - customer Invalid");
			return false;
		}else if (!isOrderTypeValidClassic()) {
			// Send error
			missingInfoScene = "Accueil";
			System.out.println("error SendOrder() - orderType Invalid");
			return false;
		}else if (!isCartValid()) {
			// Send error
			missingInfoScene = "Panier";
			System.out.println("error SendOrder() - cart Invalid");
			return false;
			
		}else if (!isOrderPayedValid()) {
			// Send error
			missingInfoScene = "Commande payé ?";
			System.out.println("error SendOrder() - orderPayed Invalid");
			return false;
		}
		
		// La commande est valide
		Customers cst = null;
		String nameOrd = getOrderName();
		CustomersDAO cstDAO = new CustomersDAO();
		if (getCst() != null) {
			// Customer existe
			Customers actualCst = getCst();
			Customers searchCst = cstDAO.read(actualCst.getIdCustomer());
			if (!actualCst.getTelCst().equals(getTelCst()) 
					|| !actualCst.getNameCst().equals(getOrderName()) 
					|| !actualCst.getStreetNumberCst().equals(getNumAdressCst())
					|| !actualCst.getStreetNameCst().equals(getAdressCst())
					|| !actualCst.getPostcodeCst().equals(getPostalCodeCst())
					|| !actualCst.getInstructionsCst().equals(getInstructionsCst())
					|| !actualCst.getInternalComCst().equals(getInternalComCst())
					) {
				// Actualisation du client car mis à jour (on pouvait supprimer le numéro de l'ancien et créer un nouveau si on voulait garder l'ancien dans la db)
				actualCst.setTelCst(getTelCst());
				actualCst.setNameCst(getOrderName());
				actualCst.setStreetNumberCst(getNumAdressCst());
				actualCst.setStreetNameCst(getAdressCst());
				actualCst.setPostcodeCst(getPostalCodeCst());
				actualCst.setInstructionsCst(getInstructionsCst());
				actualCst.setInternalComCst(getInternalComCst());
				cst = cstDAO.update(actualCst);
			}else {
				cst = searchCst;
			}
		}else if (getOrderName() != null && getTelCst() != null && getNumAdressCst() != null && getAdressCst() != null && getPostalCodeCst() != null){
			// Customer n'existe pas mais a été créer
			cst = new Customers(nameOrd, getTelCst(), getNumAdressCst(), getAdressCst(), getPostalCodeCst(), getInstructionsCst(), getInternalComCst());
			cst = cstDAO.create(cst);
		}
		
		DiscountsDAO discDAO = new DiscountsDAO();
		ArrayList<LBInfos> cart = new ArrayList<LBInfos>(getCart());
		LBInfos lb = cart.stream().filter(lbSearch -> lbSearch.getDiscount() != null).findFirst().orElse(null);
		Double reduction = 0.;
		Discounts disc = null;
		if (lb != null) {
			disc = lb.getDiscount();
			if (disc != null) {
				disc = discDAO.read(disc.getIdDiscount()); // Verifier si le code existe réellement
				reduction = disc.getValueDist();
			}
		}
		
		OrdersDAO ordDAO = new OrdersDAO();
		LineBasketDAO lbDAO = new LineBasketDAO();
		
		int minutesAttente = 0;
		minutesAttente = cart.stream().filter(lbSearch -> lbSearch.getDiscount() == null && lbSearch.getProduct().getCategoryProduct().equals("Pizzas")).mapToInt(lbSearch -> lbSearch.getQteProduct()).sum();
		minutesAttente *= PIZZA_ESTIMATED_TIME;
		Timestamp estimatedActualTime = lbDAO.readEstimatedWaitingTime();
		Timestamp estimatedTotalTime = null;
		if (estimatedActualTime != null) {
			LocalDateTime localDateTime = estimatedActualTime.toLocalDateTime();
			estimatedTotalTime = Timestamp.valueOf(localDateTime.plusMinutes(minutesAttente));
		}else {
			LocalDateTime localDateTime = LocalDateTime.now();
			estimatedTotalTime = Timestamp.valueOf(localDateTime.plusMinutes(minutesAttente));
		}
		System.out.println(estimatedActualTime);
		System.out.println(estimatedTotalTime);
		
		Orders ord = new Orders(nameOrd, getOrderType(), isOrderPayed(), reduction, new Timestamp(System.currentTimeMillis()), estimatedTotalTime, getETPrice(), getITPrice(), disc, cst);
		ord = ordDAO.create(ord);
		
		SupplementsDAO supDAO = new SupplementsDAO();
		IngredientsDAO ingDAO = new IngredientsDAO();
		ArrayList<LineBasket> lbList = new ArrayList<LineBasket>();
		ArrayList<Supplements> supList = new ArrayList<Supplements>();
		
		cart = new ArrayList<LBInfos>(getCart());
		
		cart.stream().filter(lbSearch -> lbSearch.getDiscount() == null).forEach(lbSearch -> {
			
			lbPrice = 0.;
			Products prod = lbSearch.getProduct();
			HashMap<Long, Integer> allIng = lbSearch.getAllSelectedIng();
		    ArrayList<Ingredients> defaultIng = ingDAO.readAllPizzaIng(prod.getIdProduct());
		    HashMap<Long, Integer> traitementIng = new HashMap<Long, Integer>();
		    LineBasket lbNow = new LineBasket(lbSearch.getQteProduct(), 0., 0., prod, null);
		    
		    allIng.forEach((ingId, qte) -> {
				defaultIng.stream().forEach(defIng -> {
					if (defIng.getidIngredient() == ingId && !traitementIng.containsKey(ingId)) {
						// Il faut vérifier si l'ingrédient par défaut a été modifié
						// Afficher l'ingrédient (suppression ou supplement) car changement de defaultIng
						traitementIng.put(ingId, qte);
						if (qte == 0) { // -1 Default ingredient
							Ingredients currentIng = ingDAO.read(ingId);
							lbPrice -= currentIng.getPriceETIng();
							Supplements sup = new Supplements(currentIng, null, qte, false);
							supList.add(sup);
						}else if (qte > 1){  // +1 Default ingredient
							Ingredients currentIng = ingDAO.read(ingId);
							lbPrice += currentIng.getPriceETIng() * qte;
							Supplements sup = new Supplements(currentIng, null, qte, true);
							supList.add(sup);
						}
					}
				});
				
				if (qte != 0 && !traitementIng.containsKey(ingId)) {  // +1 Supplement ingredient
					traitementIng.put(ingId, qte);
					Ingredients currentIng = ingDAO.read(ingId);
					lbPrice += currentIng.getPriceETIng() * qte;
					Supplements sup = new Supplements(currentIng, null, qte, true);
					supList.add(sup);
				}
				
			});
		    
		    lbNow.setPriceETLB(lbPrice);
		    lbNow.setPriceITLB(lbPrice * (1 + Taxe.CURRENT_TAXES.getTaxe() / 100.));
		    lbList.add(lbNow);
		    
		    
		});
		
		final Orders ordFinal = ord;
		lbList.stream().forEach(lbSearch -> {
			lbSearch.setIdOrder(ordFinal);
			LineBasket lbNow = lbDAO.create(lbSearch);
			supList.stream().forEach(sup -> {
				sup.setIdLineBasket(lbNow);
				supDAO.create(sup);
			});
		});
		
		
		TakeResponsabilityForDAO trfDAO = new TakeResponsabilityForDAO();
		
		Boolean isDelivery = false;
		if (getOrderType().equals("Takeaway")) {
			isDelivery = true;
		}
		
		TakeResponsabilityFor trf = new TakeResponsabilityFor(ord, getConnectedEmp(), isDelivery, isOrderPayed(), new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
		trfDAO.create(trf);
		
		// Actualiser delivery, historical, historical_payment DANS TOUTES LES APPLICATIONS
		UDPMultiCastApp.sendCommand(Command.REFRESH.getCommand() + ScenesMap.HISTORICAL_HISTORICAL.name() + "," + ScenesMap.HISTORICAL_PAYMENT.name() + "," + ScenesMap.DELIVERY.name());
		
		return true;
//		Object controller = SceneManager.getController(ScenesMap.MENU);
//    	((MenuController) controller).logout(null);
//    	Scene scene = SceneManager.getScene(ScenesMap.MENU);
//    	if (scene != null) {
//    		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            stage.setScene(scene);
//            stage.show();
//    	}
	}
	
}
