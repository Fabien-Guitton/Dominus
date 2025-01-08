package applications.checkout.discount;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import applications.checkout.order.CheckoutOrderController;
import applications.menu.MenuController;
import dao.DiscountsDAO;
import dao.IngredientsDAO;
import dao.ProductsDAO;
import init.SceneManager;
import interfaces.ControllerMustHave;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.util.Duration;
import tables.Discounts;
import tables.Ingredients;
import tables.Products;
import utilClass.Currency;
import utilClass.LBInfos;
import utilClass.OrderConfirmation;
import utilClass.ScenesMap;
import utilClass.Taxe;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CheckoutDiscountController implements Initializable, ControllerMustHave {
	
    @FXML
    private Label currentDate, discountPrice, taxePriceLabel, taxePrice, totalPrice;
    
    @FXML
    private FlowPane discountContainer;
    
    @FXML
    private VBox LBContainer;
    
    @FXML
    private Button addCartButton, deleteButton;
    
    @FXML
    private VBox errorContainer;
    
    @FXML
    private Text errorText;
    
    @FXML
    private Button okButton;
    
    private int maxAffichageParPage = 15, // Par défaut 15
			offsetAffichage = 0;
    
    private Node disc = null, selectedDiscountLB = null;
    
    private ArrayList<LBInfos> cart = new ArrayList<LBInfos>();
    
    private ArrayList<Button> discountButton = new ArrayList<Button>();

	private double totalCartPrice;

	private int haveDiscount = -1;

	private double currentLBPrice;
	
	@FXML
	private void validCommand(ActionEvent event) {
		String message = "";
		Boolean isValid = OrderConfirmation.SendOrder();
		if (!isValid) { // Cas ou c'est pas bon
			message = "Veuillez remplir tout les informations nécessaires à la commande. (" + OrderConfirmation.getMissingInfoScene() + ")";
		}else { // Cas ou c'est bon
			message = "Commande prise !";
		}
		
		showMessage(message, isValid);
	}
	
	private void showMessage(String message, Boolean isValid) {
		errorText.setText(message);
		okButton.setUserData(isValid);
		errorContainer.setVisible(true);
	}
	
	@FXML
	private void okMessage(ActionEvent event) {
		Boolean isValid = (Boolean) okButton.getUserData();
		errorContainer.setVisible(false);
		// Si erreur -> rien, Si succès -> passage menu et deconnexion (backPage())
		if (isValid) {
			backMenu(event);
		}
	}
    
    private Button createDiscountButton(LBInfos lb) {
        // Création du bouton
        Button button = new Button();
        
        // Propriétés du bouton
        button.setText(lb.getDiscount().getNameDist()+ " " + String.format("%.0f", lb.getDiscount().getValueDist()) + "% (" + lb.getDiscount().getCodeDist() + ")");
        button.setUserData(lb);
        button.setWrapText(true);
        button.setPrefHeight(100.0);
        button.setPrefWidth(184.0);
        button.setMaxHeight(Double.MAX_VALUE);
        button.setMaxWidth(Double.MAX_VALUE);
       
        button.getStyleClass().add("clickableButton");
        
        button.setOnAction(event -> selectDiscount(event));
        
        // Effet InnerShadow
        InnerShadow innerShadow = new InnerShadow();
	    innerShadow.setBlurType(BlurType.GAUSSIAN);
	    innerShadow.setHeight(22.47);
	    innerShadow.setRadius(10.37);
	    innerShadow.setWidth(21.01);
	    button.setEffect(innerShadow);
        
        // Police du texte
        button.setFont(Font.font("System", FontWeight.BOLD, 18.0));
        
        return button;
    }
    
    private void selectDiscount(ActionEvent event) {
    	Node clickedCoupon = (Node) event.getTarget();
    	clickedCoupon.getStyleClass().setAll("button","clickableButton", "selectedButton");
    	if (disc != null) { // Un coupon estdéjà selectionner
			disc.getStyleClass().setAll("button","clickableButton");
		}
    	disc = clickedCoupon;
    	
    	addCartButton.getStyleClass().setAll("button", "clickableButtonAddLB");
    	addCartButton.setDisable(false);
    	if (selectedDiscountLB == null) {
    		deleteButton.getStyleClass().setAll("button");
        	deleteButton.setDisable(true);
    	}
	}
    
    @FXML
    private void selectDiscountLB(MouseEvent event) {
    	Node clickedItem = (Node) event.getTarget();
    	while (!(clickedItem instanceof VBox)) {
    		clickedItem = clickedItem.getParent();
		}
    	
    	clickedItem.getStyleClass().setAll("lineBasket", "selectedLineBasket");
    	clickedItem.setDisable(true);
    	
    	final Node usedClickedItem = clickedItem;
    	// Traitement de la selection
    	discountContainer.getChildren().stream().forEach(button -> {
    		LBInfos lb = (LBInfos) button.getUserData();
    		if (lb != null && lb.getDiscount().getIdDiscount() == ((LBInfos) usedClickedItem.getUserData()).getDiscount().getIdDiscount()) {
    			((Button) button).fire();
    		}
    	});
    	
    	selectedDiscountLB = clickedItem;
    	addCartButton.getStyleClass().setAll("button", "clickableButtonAddLB");
    	addCartButton.setDisable(false);
    	addCartButton.setText("MODIFIER");
    	deleteButton.getStyleClass().setAll("button", "cancelClickableButton");
    	deleteButton.setDisable(false);
    	
    }

	public ArrayList<LBInfos> getCart(){
		return cart;
	}
	
	public void setCart(ArrayList<LBInfos> cart){
		this.cart = cart;
	}

	@Override
	public void refreshData() {
		System.out.println("Refresh checkout discount");
		if (disc != null) {
			addCartButton.getStyleClass().setAll("button", "clickableButtonAddLB");
	    	deleteButton.getStyleClass().setAll("button", "cancelClickableButton");
		}else {
			addCartButton.getStyleClass().setAll("button");
	    	deleteButton.getStyleClass().setAll("button");
		}
		DiscountsDAO discDAO = new DiscountsDAO();
		ArrayList<Discounts> discList = discDAO.readAll();
		discountContainer.getChildren().setAll(discountContainer.getChildren().get(discountContainer.getChildren().size() - 1));
		discountButton.clear();
		offsetAffichage = 0;
		discList.stream().forEach(disc -> {
			LBInfos lb = new LBInfos(0, null, "", null);
			lb.setDiscount(disc);
			discountButton.add(createDiscountButton(lb));
		});
		// discountContainer.getChildren().add(discountContainer.getChildren().size() - 1,button)
		
		for(int i = 0; i < maxAffichageParPage; i++) {
    		if (discountButton.size() > i + offsetAffichage) {
    			if (disc != null && ((LBInfos) discountButton.get(i + offsetAffichage).getUserData()).getDiscount().getIdDiscount() == ((LBInfos) disc.getUserData()).getDiscount().getIdDiscount() && !discountButton.get(i + offsetAffichage).getStyleClass().contains("selectedButton")) {
    				discountButton.get(i + offsetAffichage).getStyleClass().add("selectedButton");
    			}
    			discountContainer.getChildren().add((discountContainer.getChildren().size() - 1), discountButton.get(i + offsetAffichage));
    		}
    	}
		
		refreshCart();
	}
	
	@FXML
	private void nextPageDiscount(ActionEvent event) {
		discountContainer.getChildren().setAll(discountContainer.getChildren().get(discountContainer.getChildren().size() - 1));
		offsetAffichage += maxAffichageParPage; // +1 margarita +1
		
		if (offsetAffichage >= discountButton.size()) {
			offsetAffichage = 0;
		}

		for(int i = 0; i < maxAffichageParPage; i++) {
    		if (discountButton.size() > i + offsetAffichage) {
    			if (disc != null && ((Discounts) discountButton.get(i + offsetAffichage).getUserData()).getIdDiscount() == ((Discounts) disc.getUserData()).getIdDiscount() && !discountButton.get(i + offsetAffichage).getStyleClass().contains("selectedButton")) {
    				discountButton.get(i + offsetAffichage).getStyleClass().add("selectedButton");
    			}
    			discountContainer.getChildren().add((discountContainer.getChildren().size() - 1), discountButton.get(i + offsetAffichage));
    		}
    	}
	}
	
	private VBox createDiscountLineBasket(LBInfos lb) {
	    VBox vbox = new VBox();
	    vbox.setUserData(lb);
	    vbox.setAlignment(Pos.CENTER_LEFT);
	    vbox.setMaxWidth(Double.MAX_VALUE);
	    vbox.getStyleClass().add("lineBasket");
	    vbox.setPadding(new Insets(10, 5, 10, 5));
	    
	    // First HBox
	    HBox hbox1 = new HBox();
	    hbox1.setPrefWidth(200.0);
	    
	    vbox.getStyleClass().add("discountLB");
	    vbox.setDisable(false);
	    Label label1 = new Label(lb.getDiscount().getNameDist() + " " + lb.getDiscount().getValueDist() + "% (" + lb.getDiscount().getCodeDist() + ")");
	    label1.setStyle("-fx-text-fill: black;");
	    label1.setMaxWidth(Double.MAX_VALUE);
	    label1.setPrefWidth(170.0);
	    label1.setFont(Font.font("System", FontWeight.BOLD, 12.0));
	    HBox.setHgrow(label1, Priority.ALWAYS);
	    Label price1 = new Label();
	    price1.setFont(Font.font("System", FontWeight.BOLD, 12.0));
	    price1.setStyle("-fx-text-fill: black;");
	    hbox1.getChildren().addAll(label1, price1);
	    
	    // Add all HBoxes to VBox
	    vbox.getChildren().add(hbox1);
	    
	    currentLBPrice = 0.00; 

		price1.setText("");
		
		vbox.setOnMouseClicked(event -> selectDiscountLB(event));
	    
	    // Return the VBox
	    return vbox;
	}
	
	private VBox createLineBasket(LBInfos lb) {
	    VBox vbox = new VBox();
	    vbox.setUserData(lb);
	    vbox.setDisable(true);
	    vbox.setAlignment(Pos.CENTER_LEFT);
	    vbox.setMaxWidth(Double.MAX_VALUE);
	    vbox.getStyleClass().add("lineBasket");
	    vbox.setPadding(new Insets(10, 5, 10, 5));

	    ProductsDAO prodDAO = new ProductsDAO();
	    Products prod = prodDAO.read(lb.getProduct(), lb.getProductSize());
	    
	    // First HBox
	    HBox hbox1 = new HBox();
	    hbox1.setPrefWidth(200.0);
	    
	    Label label1;
	    label1 = new Label(lb.getQteProduct() + "x " + prod.getNameProduct() + " " + prod.getSizeProduct());
	    label1.setStyle("-fx-text-fill: black;");
	    label1.setMaxWidth(Double.MAX_VALUE);
	    label1.setPrefWidth(170.0);
	    label1.setFont(Font.font("System", FontWeight.BOLD, 12.0));
	    HBox.setHgrow(label1, Priority.ALWAYS);
	    Label price1 = new Label();
	    price1.setFont(Font.font("System", FontWeight.BOLD, 12.0));
	    price1.setStyle("-fx-text-fill: black;");
	    hbox1.getChildren().addAll(label1, price1);
	    
	    // Add all HBoxes to VBox
	    vbox.getChildren().add(hbox1);
	    
	    IngredientsDAO ingDAO = new IngredientsDAO();
	    
	    currentLBPrice = 0.00;
	    currentLBPrice = prod.getPriceETProduct(); // Prix du produit par défaut hors taxe
	    
	    HashMap<Long, Integer> allIng = lb.getAllSelectedIng();
	    ArrayList<Ingredients> defaultIng = ingDAO.readAllPizzaIng(prod.getIdProduct());
	    HashMap<Long, Integer> traitementIng = new HashMap<Long, Integer>();
	    
		allIng.forEach((ingId, qte) -> {
			// Vérifier les ingrédients par défaut, regarder les différences
			// (retirer/ajouter) et faire l'affichage avec le calcul de prix
			defaultIng.stream().forEach(defIng -> {
				//System.out.println(defIng.getidIngredient() + "==" + ingId + " && " + qte + "!= 1" + " && " + !afficherIngModif.containsKey(defIng));
				if (defIng.getidIngredient() == ingId && !traitementIng.containsKey(ingId)) {
					// Il faut vérifier si l'ingrédient par défaut a été modifié
					// Afficher l'ingrédient (suppression ou supplement) car changement de defaultIng
					traitementIng.put(ingId, qte);
					if (qte == 0) { // -1 Default ingredient
						Ingredients currentIng = ingDAO.read(ingId);
						currentLBPrice -= currentIng.getPriceETIng();
						HBox hbox = new HBox();
						hbox.setPrefWidth(200.0);
						Label label = new Label("- " + (qte + 1) + " " + currentIng.getNameIng());
						label.setStyle("-fx-text-fill: black;");
						label.setMaxWidth(Double.MAX_VALUE);
						label.setPrefWidth(170.0);
						HBox.setHgrow(label, Priority.ALWAYS);
						Label priceLabel = new Label("-" + (currentIng.getPriceETIng() * lb.getQteProduct()) + Currency.MAIN_CURRENCY.getType() + " (" + currentIng.getPriceETIng() + Currency.MAIN_CURRENCY.getType() + "/u)");
						priceLabel.setStyle("-fx-text-fill: black;");
						hbox.getChildren().addAll(label, priceLabel);
						hbox.setPadding(new Insets(0, 0, 0, 15));
						vbox.getChildren().add(hbox);
					}else if (qte > 1){  // +1 Default ingredient
						Ingredients currentIng = ingDAO.read(ingId);
						currentLBPrice += currentIng.getPriceETIng() * (qte - 1);
						HBox hbox = new HBox();
						hbox.setPrefWidth(200.0);
						Label label = new Label("+ " + (qte - 1) + " " + currentIng.getNameIng());
						label.setStyle("-fx-text-fill: black;");
						label.setMaxWidth(Double.MAX_VALUE);
						label.setPrefWidth(170.0);
						HBox.setHgrow(label, Priority.ALWAYS);
						Label priceLabel = new Label((currentIng.getPriceETIng() * lb.getQteProduct() * (qte - 1)) + Currency.MAIN_CURRENCY.getType() + " (" + currentIng.getPriceETIng() + Currency.MAIN_CURRENCY.getType() + "/u)");
						priceLabel.setStyle("-fx-text-fill: black;");
						hbox.getChildren().addAll(label, priceLabel);
						hbox.setPadding(new Insets(0, 0, 0, 15));
						vbox.getChildren().add(hbox);
					}
				}
			});
			
			if (qte != 0 && !traitementIng.containsKey(ingId)) {  // +1 Supplement ingredient
				traitementIng.put(ingId, qte);
				Ingredients currentIng = ingDAO.read(ingId);
				currentLBPrice += currentIng.getPriceETIng() * qte;
				HBox hbox = new HBox();
				hbox.setPrefWidth(200.0);
				Label label = new Label("+ " + qte + " " + currentIng.getNameIng());
				label.setStyle("-fx-text-fill: black;");
				label.setMaxWidth(Double.MAX_VALUE);
				label.setPrefWidth(170.0);
				HBox.setHgrow(label, Priority.ALWAYS);
				Label priceLabel = new Label((currentIng.getPriceETIng() * lb.getQteProduct() * qte) + Currency.MAIN_CURRENCY.getType() + " (" + currentIng.getPriceETIng() + Currency.MAIN_CURRENCY.getType() + "/u)");
				priceLabel.setStyle("-fx-text-fill: black;");
				hbox.getChildren().addAll(label, priceLabel);
				hbox.setPadding(new Insets(0, 0, 0, 15));
				vbox.getChildren().add(hbox);
			}
			
		});

		price1.setText((currentLBPrice * lb.getQteProduct()) + Currency.MAIN_CURRENCY.getType());
		totalCartPrice += (currentLBPrice * lb.getQteProduct());
	    
	    // Return the VBox
	    return vbox;
	}
	
	public void refreshCart() {
		totalCartPrice = 0.;
		Double disc = 0.0;
		haveDiscount = -1;
		LBContainer.getChildren().clear();
		cart.stream().forEach(lb -> {
			if (lb.getDiscount() != null) {
				// Un coupon est utilisé
				haveDiscount = cart.indexOf(lb);
			}else {
				LBContainer.getChildren().add(createLineBasket(lb));
			}
		});
		
		if (haveDiscount != -1) { // Pour qu'il soit à la fin de la liste de course
			LBContainer.getChildren().add(createDiscountLineBasket(cart.get(haveDiscount)));
			disc = cart.get(haveDiscount).getDiscount().getValueDist();
		}
		
		
//		if (OrderInfos.getDiscount() != null) {
//			discount = OrderInfos.getETPrice() * (OrderInfos.getDiscount().getValueDist() / 100.);
//			discountPrice.setText(String.format("%.2f", discount) + Currency.MAIN_CURRENCY.getType());
//		}else {
//			discount = 0.;
//			discountPrice.setText("0.00" + Currency.MAIN_CURRENCY.getType());
//		}
		Double taxes = totalCartPrice * (Taxe.CURRENT_TAXES.getTaxe() / 100.);
		Double total = (totalCartPrice + taxes) - ((totalCartPrice + taxes) * (disc / 100.));
		taxePrice.setText(String.format("%.2f", taxes) + Currency.MAIN_CURRENCY.getType());
		totalPrice.setText((String.format("%.2f", total)) + Currency.MAIN_CURRENCY.getType());
	}
	
	@FXML
	private void removeDiscount(ActionEvent event) {
		int index = cart.indexOf(selectedDiscountLB.getUserData());
		cart.remove(index);
		selectedDiscountLB = null;
		disc = null;
		addCartButton.setText("AJOUTER AU PANIER");
		deleteButton.getStyleClass().remove("cancelClickableButton");
		deleteButton.setDisable(true);
		addCartButton.setDisable(true);
		addCartButton.getStyleClass().remove("clickableButtonAddLB");
		Object controller = SceneManager.getController(ScenesMap.CHECKOUT_ORDER);
    	((CheckoutOrderController) controller).setCart(cart);
    	((CheckoutOrderController) controller).refreshCart();
		refreshCart();
		refreshData();
	}
	
	@FXML
	private void addDsicount(ActionEvent event) {
		if (disc != null) { // Un coupon estdéjà selectionner
			addCartButton.setText("AJOUTER AU PANIER");
			LBInfos lbExisting = cart.stream().filter(lb -> lb.getDiscount() != null).findFirst().orElse(null);
			
			if (lbExisting != null) {
				int index = cart.indexOf(lbExisting);
				cart.remove(index);
			}
			
			// Créer LBInfos
			LBInfos lb = new LBInfos(0, null, "", null);
			lb.setDiscount(((LBInfos) disc.getUserData()).getDiscount());
			cart.add(lb);
			disc.getStyleClass().setAll("button","clickableButton");
			Object controller = SceneManager.getController(ScenesMap.CHECKOUT_ORDER);
	    	((CheckoutOrderController) controller).setCart(cart);
	    	((CheckoutOrderController) controller).refreshCart();
	    	disc = null;
	    	addCartButton.setDisable(true);
	    	addCartButton.getStyleClass().setAll("button");
	    	deleteButton.setDisable(true);
	    	deleteButton.getStyleClass().setAll("button");
	    	
	    	refreshCart();
	    	refreshData();
		}
	}
	
	@FXML
    private void switchScene(ActionEvent event) {
    	Node clickedMenu = (Node) event.getTarget();
    	String fullId = clickedMenu.getId();
    	String[] sepId = fullId.split("-");
    	String id = sepId[0];
    	
    	if (Enum.valueOf(ScenesMap.class, id.toUpperCase()) == ScenesMap.CHECKOUT_PAYMENT) {
    		Object controller = SceneManager.getController(ScenesMap.CHECKOUT_PAYMENT);
    		((ControllerMustHave) controller).refreshData();
    	}

    	// Pas besoin de refresh car rien de special
    	Scene scene = SceneManager.getScene(Enum.valueOf(ScenesMap.class, id.toUpperCase()));
    	if (scene != null) {
    		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    	}
    }
	
	@FXML
	private void backMenu(ActionEvent event) {
		Object controller = SceneManager.getController(ScenesMap.MENU);
    	((MenuController) controller).logout(event);
    	Scene scene = SceneManager.getScene(ScenesMap.MENU);
    	if (scene != null) {
    		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    	}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("Init checkout discount");
		refreshData();
		
		currentDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy | HH:mm:ss")));
    	Timeline timeUpdate = new Timeline(new KeyFrame(Duration.seconds(1), e -> currentDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy | HH:mm:ss")))));
    	timeUpdate.setCycleCount(Timeline.INDEFINITE);
    	timeUpdate.play();
    	
	}

}
