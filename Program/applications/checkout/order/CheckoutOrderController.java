package applications.checkout.order;


import java.net.URL;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import applications.checkout.discount.CheckoutDiscountController;
import applications.menu.MenuController;
import dao.IngredientsDAO;
import dao.ProductsDAO;
import init.SceneManager;
import interfaces.ControllerMustHave;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import tables.Ingredients;
import tables.Products;
import utilClass.Currency;
import utilClass.LBInfos;
import utilClass.OrderConfirmation;
import utilClass.ScenesMap;
import utilClass.Taxe;

public class CheckoutOrderController implements Initializable, ControllerMustHave{
	
	@FXML
	private Label currentDate, discountPrice, totalPrice, taxePriceLabel, taxePrice;
	
	@FXML
	private VBox ingredientContainer, LBContainer, pizzaOrderContainer;

	@FXML
	private FlowPane pizzaContainer, ingButtonContainer;
	
	@FXML
	private Button ingredientContainerButton, pizzaContainerButton, resetIngQuantity, addCartButton, deleteLBButton;

	@FXML
	private Spinner<Integer> quantityIng, quantityLB;
	
	@FXML
	private HBox sizeContainer;
	
	@FXML
	private Button pizzaMenuButton, extraMenuButton;
	
    @FXML
    private VBox errorContainer;
    
    @FXML
    private Text errorText;
    
    @FXML
    private Button okButton;
	
	private Node selectedLB = null, selectedProductSize = null, selectedProduct = null, selectedIng = null, selectedExtra = null;

	private Double currentLBPrice = 0.;
	
	private Double totalCartPrice = 0.;
	
	private String currentOrderType = "Pizzas";
	
	private int maxAffichageParPageProduct = 23, // Par défaut 23
				offsetAffichageProduct = 0,
				maxAffichageParPageIng = 17, // Par défaut 17
				offsetAffichageIng = 0;
	
	private int haveDiscount = -1;
	
	private ArrayList<LBInfos> cart = new ArrayList<LBInfos>();

	private HashMap<Long, Integer> allSelectedIngIds = new HashMap<Long, Integer>(); // idING, QUANTITY / Historique des changements d'ing, il faudra vérifier par rapport aux ing par défauts du produits
	
	private ArrayList<Button> pizzasButton = new ArrayList<Button>(), ingButton = new ArrayList<Button>();
	
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
	
	public ArrayList<LBInfos> getCart(){
		return cart;
	}
	
	public void setCart(ArrayList<LBInfos> cart){
		this.cart = cart;
	}
	
	public Double getTotalCartPrice() {
		return totalCartPrice;
	}
	
	private Button createProductButton(Products prod) {
		Button button = new Button(prod.getNameProduct().toUpperCase());
		button.setUserData(prod);
	    button.setAlignment(Pos.CENTER);
	    button.setMaxHeight(Double.MAX_VALUE);
	    button.setMaxWidth(Double.MAX_VALUE);
	    button.setOnAction(event -> selectProduct(event));
	    button.setPrefHeight(80.0);
	    button.setPrefWidth(120.0);
	    button.getStyleClass().add("clickableButton");
	    button.setWrapText(true);

	    // Police
	    button.setFont(Font.font("System", FontWeight.BOLD, 13.0));

	    // Effet
	    InnerShadow innerShadow = new InnerShadow();
	    innerShadow.setBlurType(BlurType.GAUSSIAN);
	    innerShadow.setHeight(22.47);
	    innerShadow.setRadius(10.37);
	    innerShadow.setWidth(21.01);
	    button.setEffect(innerShadow);
	    
	    if (currentOrderType == "Pizzas") {
	    	if (selectedProduct != null && selectedProduct.getUserData() == prod) {
		    	button.getStyleClass().add("selectedButton");
		    	selectedProduct = button;
		    }
	    }else {
	    	if (selectedExtra != null && selectedExtra.getUserData() == prod) {
		    	button.getStyleClass().add("selectedButton");
		    	selectedExtra = button;
		    }
	    }
	    
	    return button;
	}
	
	private Button createProductButton(String categoryProduct) {
		Button button = new Button(categoryProduct.toUpperCase());
		button.setUserData(categoryProduct);
	    button.setAlignment(Pos.CENTER);
	    button.setMaxHeight(Double.MAX_VALUE);
	    button.setMaxWidth(Double.MAX_VALUE);
	    button.setOnAction(event -> selectProduct(event));
	    button.setPrefHeight(80.0);
	    button.setPrefWidth(120.0);
	    button.getStyleClass().add("clickableButton");
	    button.setWrapText(true);

	    // Police
	    button.setFont(Font.font("System", FontWeight.BOLD, 13.0));

	    // Effet
	    InnerShadow innerShadow = new InnerShadow();
	    innerShadow.setBlurType(BlurType.GAUSSIAN);
	    innerShadow.setHeight(22.47);
	    innerShadow.setRadius(10.37);
	    innerShadow.setWidth(21.01);
	    button.setEffect(innerShadow);
	    
	    if (selectedProduct != null && selectedProduct.getUserData().equals(categoryProduct)) {
	    	button.getStyleClass().add("selectedButton");
	    	selectedProduct = button;
	    }
	    
	    return button;
	}
	
	private Button createIngButton(Ingredients ing) {
		Button button = new Button(ing.getNameIng());
		button.setUserData(ing);
	    
	    // Paramètres de base
	    button.setAlignment(Pos.CENTER);
	    button.setMaxHeight(Double.MAX_VALUE);
	    button.setMaxWidth(Double.MAX_VALUE);
	    button.setPrefHeight(80.0);
	    button.setPrefWidth(120.0);
	    button.getStyleClass().add("clickableButton");
	    button.setWrapText(true);

	    // Police
	    button.setFont(Font.font("System", FontWeight.BOLD, 13.0));

	    // Effet InnerShadow
	    InnerShadow innerShadow = new InnerShadow();
	    innerShadow.setBlurType(BlurType.GAUSSIAN);
	    innerShadow.setHeight(22.47);
	    innerShadow.setRadius(10.37);
	    innerShadow.setWidth(21.01);
	    button.setEffect(innerShadow);

	    // Action on click
	    button.setOnAction(event -> selectIng(event));

	    return button;
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
	    vbox.setDisable(true);
	    Label label1 = new Label(lb.getDiscount().getNameDist() + " " + lb.getDiscount().getValueDist() + "% (" + lb.getDiscount().getCodeDist() + ")");
	    label1.setStyle("-fx-text-fill: black;");
	    label1.setMaxWidth(Double.MAX_VALUE);
	    label1.setPrefWidth(170.0);
	    label1.setFont(Font.font("System", FontWeight.BOLD, 12.0));
	    HBox.setHgrow(label1, Priority.ALWAYS);
	    Label price1 = new Label();
	    price1.setStyle("-fx-text-fill: black;");
	    price1.setFont(Font.font("System", FontWeight.BOLD, 12.0));
	    hbox1.getChildren().addAll(label1, price1);
	    
	    // Add all HBoxes to VBox
	    vbox.getChildren().add(hbox1);
	    
	    currentLBPrice = 0.00; 

		price1.setText("");
	    
	    // Return the VBox
	    return vbox;
	}

	private VBox createLineBasket(LBInfos lb) {
	    VBox vbox = new VBox();
	    vbox.setUserData(lb);
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
	    vbox.setOnMouseClicked(event -> selectLB(event));
	    
	    // Return the VBox
	    return vbox;
	}
	
	@FXML
	private void nextPageIng(ActionEvent event) {
		refreshIng();
		offsetAffichageIng += maxAffichageParPageIng; // +1 margarita +1
		
		if (offsetAffichageIng >= ingButton.size()) {
			offsetAffichageIng = 0;
		}
		
		for(int i = 0; i < maxAffichageParPageIng; i++) {
    		if (ingButton.size() > i + offsetAffichageIng) {
    			ingButtonContainer.getChildren().add((ingButtonContainer.getChildren().size() - 1), ingButton.get(i + offsetAffichageIng));
    		}
		}
	}

	@FXML
	private void nextPageProduct(ActionEvent event) {
		pizzaContainer.getChildren().setAll(pizzaContainer.getChildren().get(pizzaContainer.getChildren().size() - 1));
		offsetAffichageProduct += maxAffichageParPageProduct; // +1 margarita +1
		
		if (offsetAffichageProduct >= pizzasButton.size()) {
			offsetAffichageProduct = 0;
		}

		for(int i = 0; i < maxAffichageParPageProduct; i++) {
    		if (pizzasButton.size() > i + offsetAffichageProduct) {
    			if (selectedProduct != null && ((Products) pizzasButton.get(i + offsetAffichageProduct).getUserData()).getNameProduct().equals(((Products) selectedProduct.getUserData()).getNameProduct()) && !pizzasButton.get(i).getStyleClass().contains("selectedButton")) {
    				pizzasButton.get(i + offsetAffichageProduct).getStyleClass().add("selectedButton");
    			}
    			pizzaContainer.getChildren().add((pizzaContainer.getChildren().size() - 1), pizzasButton.get(i + offsetAffichageProduct));
    		}
    	}
	}
	
	private HashMap<Long, Integer> getAllSelectedIngIds(){
		return allSelectedIngIds.size() >= 0 ? new HashMap<Long,Integer>(allSelectedIngIds) : new HashMap<Long,Integer>();
	}
	
	@FXML
	private void removeCart(ActionEvent event) {
		int index = cart.indexOf(selectedLB.getUserData());
		cart.remove(index);
		selectedLB = null;
		addCartButton.setText("AJOUTER AU PANIER");
		deleteLBButton.getStyleClass().remove("cancelClickableButton");
		deleteLBButton.setDisable(true);
		if (selectedProductSize != null) {
			selectedProductSize.getStyleClass().setAll("button", "clickableButton");
			selectedProductSize.setDisable(false);
		}
		selectedProductSize = null;
		selectedProduct = null;
		pizzaContainerButton.setDisable(false);
		pizzaContainerButton.fire();
		addCartButton.setDisable(true);
		addCartButton.getStyleClass().remove("clickableButtonAddLB");
		allSelectedIngIds.clear();

		refreshData();
		refreshCart();
	}
	
	@FXML
	private void addCart(ActionEvent event) {
		if (selectedProduct != null && selectedProductSize != null) {
			deleteLBButton.getStyleClass().remove("cancelClickableButton");
			deleteLBButton.setDisable(true);
			if (selectedLB == null) { // Product being created
				int qteProduct = quantityLB.getValue();
				Products product = (Products) selectedProduct.getUserData();
				String productSize = selectedProductSize.getId();
				LBInfos lb = new LBInfos(qteProduct, product, productSize, getAllSelectedIngIds());
				LBInfos sameProductExist = cart.stream().filter(listlb -> listlb.getDiscount() == null && listlb.getProduct().getIdProduct() == lb.getProduct().getIdProduct() && listlb.getProductSize().equals(lb.getProductSize()) && listlb.getAllSelectedIng().equals(lb.getAllSelectedIng()))
						.findFirst()
			            .orElse(null);
				if (sameProductExist != null) {
					int index = cart.indexOf(sameProductExist);
					LBInfos lbSelected = cart.get(index);
					lbSelected.setQteProduct(sameProductExist.getQteProduct() + qteProduct);
					lbSelected.setProduct(product);
					lbSelected.setProductSize(productSize);
					lbSelected.setAllSelectedIng(getAllSelectedIngIds());
				}else {
					cart.add(lb);
				}
			}else { // Product being modified
				System.out.println("Modified");
				int qteProduct = quantityLB.getValue();
				Products product = (Products) selectedProduct.getUserData();
				String productSize = selectedProductSize.getId();
				//LBInfos lb = new LBInfos(qteProduct, product, productSize, allSelectedIng);

				int index = cart.indexOf(selectedLB.getUserData());
				LBInfos lbSelected = cart.get(index);
				LBInfos lbNew = new LBInfos(qteProduct, product, productSize, allSelectedIngIds);
				LBInfos sameProductExist = cart.stream().filter(listlb -> listlb.getDiscount() == null && listlb.getProduct().getIdProduct() == lbNew.getProduct().getIdProduct() && listlb.getProductSize().equals(lbNew.getProductSize()) && listlb.getAllSelectedIng().equals(lbNew.getAllSelectedIng()) && cart.indexOf(listlb) != index)
						.findFirst()
			            .orElse(null);
				if (sameProductExist != null) {
					int indexSame = cart.indexOf(sameProductExist);
					if (indexSame != index) {
						LBInfos lbSame = cart.get(indexSame);
						lbSame.setQteProduct(sameProductExist.getQteProduct() + qteProduct);
						lbSame.setProduct(product);
						lbSame.setProductSize(productSize);
						lbSame.setAllSelectedIng(getAllSelectedIngIds());
						cart.remove(index);
					}else {
						System.out.println(indexSame + "!=" + index);
						lbSelected.setQteProduct(qteProduct);
						lbSelected.setProduct(product);
						lbSelected.setProductSize(productSize);
						lbSelected.setAllSelectedIng(getAllSelectedIngIds());
					}
				}else {
					lbSelected.setQteProduct(qteProduct);
					lbSelected.setProduct(product);
					lbSelected.setProductSize(productSize);
					lbSelected.setAllSelectedIng(getAllSelectedIngIds());
				}
				
				selectedLB = null;
				addCartButton.setText("AJOUTER AU PANIER");
			}
			selectedProductSize.getStyleClass().setAll("button", "clickableButton");
			selectedProductSize.setDisable(false);
			selectedProductSize = null;
			selectedProduct = null;
			pizzaContainerButton.setDisable(false);
			pizzaContainerButton.fire();
			addCartButton.setDisable(true);
			addCartButton.getStyleClass().remove("clickableButtonAddLB");
			allSelectedIngIds.clear();
			
			refreshData();
			refreshCart();
			
		}else if (selectedExtra != null && selectedProductSize != null) {
			ProductsDAO prodDAO = new ProductsDAO();
		    Products prod = prodDAO.read((Products) selectedExtra.getUserData(), selectedProductSize.getId());
		    if (prod == null) {
		    	return;
		    }
		    
			deleteLBButton.getStyleClass().remove("cancelClickableButton");
			deleteLBButton.setDisable(true);
			if (selectedLB == null) { // Product being created
				int qteProduct = quantityLB.getValue();
				Products product = (Products) selectedExtra.getUserData();
				String productSize = selectedProductSize.getId();
				LBInfos lb = new LBInfos(qteProduct, product, productSize, getAllSelectedIngIds());
				LBInfos sameProductExist = cart.stream().filter(listlb -> listlb.getDiscount() == null && listlb.getProduct().getIdProduct() == lb.getProduct().getIdProduct() && listlb.getProductSize().equals(lb.getProductSize()) && listlb.getAllSelectedIng().equals(lb.getAllSelectedIng()))
						.findFirst()
			            .orElse(null);
				if (sameProductExist != null) {
					int index = cart.indexOf(sameProductExist);
					LBInfos lbSelected = cart.get(index);
					lbSelected.setQteProduct(sameProductExist.getQteProduct() + qteProduct);
					lbSelected.setProduct(product);
					lbSelected.setProductSize(productSize);
					lbSelected.setAllSelectedIng(getAllSelectedIngIds());
				}else {
					cart.add(lb);
				}
			}else { // Product being modified
				System.out.println("Modified");
				int qteProduct = quantityLB.getValue();
				Products product = (Products) selectedExtra.getUserData();
				String productSize = selectedProductSize.getId();
				//LBInfos lb = new LBInfos(qteProduct, product, productSize, allSelectedIng);

				int index = cart.indexOf(selectedLB.getUserData());
				LBInfos lbSelected = cart.get(index);
				LBInfos lbNew = new LBInfos(qteProduct, product, productSize, allSelectedIngIds);
				LBInfos sameProductExist = cart.stream().filter(listlb -> listlb.getDiscount() == null && listlb.getProduct().getIdProduct() == lbNew.getProduct().getIdProduct() && listlb.getProductSize().equals(lbNew.getProductSize()) && listlb.getAllSelectedIng().equals(lbNew.getAllSelectedIng()) && cart.indexOf(listlb) != index)
						.findFirst()
			            .orElse(null);
				if (sameProductExist != null) {
					int indexSame = cart.indexOf(sameProductExist);
					if (indexSame != index) {
						LBInfos lbSame = cart.get(indexSame);
						lbSame.setQteProduct(sameProductExist.getQteProduct() + qteProduct);
						lbSame.setProduct(product);
						lbSame.setProductSize(productSize);
						lbSame.setAllSelectedIng(getAllSelectedIngIds());
						cart.remove(index);
					}else {
						System.out.println(indexSame + "!=" + index);
						lbSelected.setQteProduct(qteProduct);
						lbSelected.setProduct(product);
						lbSelected.setProductSize(productSize);
						lbSelected.setAllSelectedIng(getAllSelectedIngIds());
					}
				}else {
					lbSelected.setQteProduct(qteProduct);
					lbSelected.setProduct(product);
					lbSelected.setProductSize(productSize);
					lbSelected.setAllSelectedIng(getAllSelectedIngIds());
				}
				
				selectedLB = null;
				addCartButton.setText("AJOUTER AU PANIER");
			}
			selectedProductSize.getStyleClass().setAll("button", "clickableButton");
			selectedProductSize.setDisable(false);
			selectedProductSize = null;
			selectedExtra = null;
			pizzaContainerButton.setDisable(false);
			pizzaContainerButton.fire();
			addCartButton.setDisable(true);
			addCartButton.getStyleClass().remove("clickableButtonAddLB");
			allSelectedIngIds.clear();
			
			refreshData();
			refreshCart();
			
		}
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
		totalCartPrice = total;
		
		Object controller = SceneManager.getController(ScenesMap.CHECKOUT_DISCOUNT);
		((CheckoutDiscountController) controller).setCart(cart);
    	((CheckoutDiscountController) controller).refreshCart();
	}
	
	@FXML
	private void selectProductSize(ActionEvent event) { // Bug -> Active directement la possibilité d'ajouter au panier lorsqu'on selectionne une taille dans extra
		if (!currentOrderType.equals("Pizzas")) {		// 			Sans pour autant avoir selectionner un selctedExtra (un produit)
			if (selectedExtra != null) {
				addCartButton.getStyleClass().setAll("button", "clickableButtonAddLB");
				addCartButton.setDisable(false);
			}
		}else {
			if (selectedProduct != null) {
				addCartButton.getStyleClass().setAll("button", "clickableButtonAddLB");
				addCartButton.setDisable(false);
			}
		}
		Node clickedProductSize = (Node) event.getTarget();
		if (selectedProductSize != null) {
			selectedProductSize.getStyleClass().remove("selectedButton");
			selectedProductSize.setDisable(false);
		}
		clickedProductSize.getStyleClass().add("selectedButton");
		clickedProductSize.setDisable(true);
		selectedProductSize = clickedProductSize;
	}
	
//	private void waitForModify(Boolean wait) {
//		LBContainer.getChildren().stream().forEach(VBox -> VBox.setDisable(wait));
//	}
	
	@FXML
	private void selectLB(MouseEvent event) {
		Node clickedLB = (Node) event.getTarget();
		while (!(clickedLB instanceof VBox)) {
			clickedLB = clickedLB.getParent();
		}
		if (clickedLB.getUserData() != null && ((LBInfos) clickedLB.getUserData()).getDiscount() != null) {
			return;
		}
		deleteLBButton.getStyleClass().setAll("button","cancelClickableButton");
		deleteLBButton.setDisable(false);
		addCartButton.setText("MODIFIER");
		clickedLB.getStyleClass().setAll("lineBasket", "selectedLineBasket");
		clickedLB.setDisable(true);
		final Node clickedLBfinal = clickedLB;
		//refreshData();
		if (selectedLB != null) {
			selectedLB.getStyleClass().setAll("lineBasket");
		}
		
		if (selectedProduct != null) {
			selectedProduct.getStyleClass().setAll("button", "clickableButton");
			selectedProduct.setDisable(false);
		}
		
		if (selectedProductSize != null) {
			selectedProductSize.getStyleClass().setAll("button", "clickableButton");
			selectedProductSize.setDisable(false);
		}
		
		// SET PIZZA SCREEN
		// OR SET EXTRA SCREEN DIRECTLY TO PRODUCT SELECTED
		String category = ((LBInfos) clickedLB.getUserData()).getProduct().getCategoryProduct();
		selectedLB = clickedLB;
		
		if (category.equals("Pizzas")) { // pizzas tab
			pizzaMenuButton.fire();
		}else { // extras tab
			extraMenuButton.fire();
		}
		
		// selectedLB est passé à null
		if (((LBInfos) clickedLB.getUserData()).getProduct().getCategoryProduct().equals("Pizzas")) {
			addCartButton.setDisable(false);
			addCartButton.getStyleClass().setAll("button", "clickableButtonAddLB");
		}
		selectedLB = LBContainer.getChildren().get(LBContainer.getChildren().indexOf(clickedLB));
		LBContainer.getChildren().stream().forEach(VBox -> VBox.setDisable(true));
		selectedLB.getStyleClass().setAll("lineBasket", "selectedLineBasket");

		deleteLBButton.setDisable(false);
		deleteLBButton.getStyleClass().setAll("button", "cancelClickableButton");
		
		//int indexOfProduct = pizzaContainer.getChildren().indexOf(((LBInfos) clickedLB.getUserData()).getProduct());
		if  (((LBInfos) selectedLB.getUserData()).getProduct().getCategoryProduct().equals("Pizzas")) {
			selectedProduct = pizzaContainer.getChildren().stream().filter(button -> button.getUserData() != null && ((Products) button.getUserData()).getNameProduct().equals(((LBInfos) clickedLBfinal.getUserData()).getProduct().getNameProduct()))
					.findFirst()
					.orElse(null);
			
			//pizzaContainer.getChildren().stream().forEach(button -> System.out.println(button.getUserData() + "!= null && " + ((Products) button.getUserData()).getNameProduct() + " == " + ((LBInfos) clickedLBfinal.getUserData()).getProduct().getNameProduct()));
			selectedProductSize = sizeContainer.getChildren().stream().filter(element -> element instanceof Button && element.getId().equals(((LBInfos) clickedLBfinal.getUserData()).getProductSize()))
					.findFirst()
					.orElse(null);
			
			selectedProductSize.getStyleClass().add("selectedButton");
			selectedProduct.setDisable(true);
			selectedProduct.getStyleClass().add("selectedButton");
			selectedProduct.setDisable(true);
			ingredientContainerButton.setDisable(false);
			if (!ingredientContainerButton.getStyleClass().contains("clickableButton")) {
				ingredientContainerButton.getStyleClass().add("clickableButton");
			}
			
			pizzaContainerButton.fire();
			refreshIng();
		}

		quantityLB.getValueFactory().setValue(((LBInfos) clickedLB.getUserData()).getQteProduct());
	}
	
	@FXML
	private void selectProduct(ActionEvent event) {
		if (selectedProductSize != null) {
			addCartButton.getStyleClass().setAll("button", "clickableButtonAddLB");
			addCartButton.setDisable(false);
		}
		allSelectedIngIds.clear();
		Node clickedButton = (Node) event.getTarget();
		clickedButton.setDisable(true);
		if (!clickedButton.getStyleClass().contains("selectedButton")) {
			clickedButton.getStyleClass().add("selectedButton");
		}

		if (currentOrderType.equals("Pizzas")) {
			System.out.println("Pizza");
			if (selectedProduct != null) {
				selectedProduct.getStyleClass().remove("selectedButton");
				selectedProduct.setDisable(false);
			}else {
				ingredientContainerButton.setDisable(false);
				ingredientContainerButton.getStyleClass().add("clickableButton");
			}
			selectedProduct = clickedButton;
			selectedExtra = null;
			refreshIng();
		}else {
			if (selectedExtra != null) {
				selectedExtra.getStyleClass().remove("selectedButton");
				selectedExtra.setDisable(false);
			}else {
				ingredientContainerButton.setDisable(false);
				ingredientContainerButton.getStyleClass().add("clickableButton");
				selectedExtra = clickedButton;
				ingredientContainerButton.fire();
			}
			selectedProduct = null;
			selectedExtra = clickedButton;
			refreshExtra();
		}
	}
	
	private void refreshExtra() {
		System.out.println("refresh extra");
	}

	@FXML
	private void quantityLBChange(ActionEvent event) {
		Node clickedButton = (Node) event.getTarget();
		String id = clickedButton.getId();
		
		if (id.equals("increment-LB")) {
			quantityLB.increment();
		}else if (id.equals("decrement-LB")){
			quantityLB.decrement();
		}
    }
	
	@FXML
	private void selectIng(ActionEvent event) {
		Node clickedButton = (Node) event.getTarget();
		Ingredients ing = (Ingredients) clickedButton.getUserData();
		int qte;
		if (allSelectedIngIds.get(ing.getidIngredient()) != null) {
			qte = allSelectedIngIds.get(ing.getidIngredient());
		}else {
			qte = 0;
		}
		
		quantityIng.getValueFactory().setValue(qte);
		clickedButton.getStyleClass().add("selectedButtonIng");
		if (selectedIng != null) {
			selectedIng.getStyleClass().remove("selectedButtonIng");
		}
		selectedIng = clickedButton;
		
		if (qte > 0) {
			if (!resetIngQuantity.getStyleClass().contains("cancelClickableButton")) {
				resetIngQuantity.getStyleClass().add("cancelClickableButton");
				resetIngQuantity.setDisable(false);
			}	
		}else{
			resetIngQuantity.getStyleClass().remove("cancelClickableButton");
			resetIngQuantity.setDisable(true);
		}
	}
	
	@FXML
	private void quantityIngChange(ActionEvent event) {
		Node clickedButton = (Node) event.getTarget();
		String id = clickedButton.getId();
		
		if (selectedIng != null) {
			if (id.equals("increment-Ing")) {
				quantityIng.increment();
			}else if (id.equals("decrement-Ing")){
				quantityIng.decrement();
			}else if (id.equals("reset-Ing")) {
				quantityIng.getValueFactory().setValue(0);
			}
			
			int qte = quantityIng.getValue();
			
			if (qte > 0) {
				if (!resetIngQuantity.getStyleClass().contains("cancelClickableButton")) {
					resetIngQuantity.getStyleClass().add("cancelClickableButton");
					resetIngQuantity.setDisable(false);
				}
			}else {
				resetIngQuantity.getStyleClass().remove("cancelClickableButton");
				resetIngQuantity.setDisable(true);
			}
			
			Ingredients ing = (Ingredients) selectedIng.getUserData();
			if (!selectedIng.getStyleClass().contains("selectedButtonIngSupp")) { // n'est pas selectionner et pas par défaut grâce à la class de style donc vérif si qte > 0
				if (qte != 0) {
					selectedIng.getStyleClass().add("selectedButtonIngSupp");
					allSelectedIngIds.put(ing.getidIngredient(), qte);
				}
			}else { // Est selectionner
				if (qte == 0 && !selectedIng.getStyleClass().contains("selectedButtonIngAll")) {
					selectedIng.getStyleClass().remove("selectedButtonIngSupp");
					if (allSelectedIngIds.containsKey(ing.getidIngredient())) {
						allSelectedIngIds.remove(ing.getidIngredient());
					}
				}else {
					allSelectedIngIds.put(ing.getidIngredient(), qte);
				}
			}
			
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
	
	@FXML
	private void switchOrderContainer(ActionEvent event) {
		pizzaContainerButton.fire();
		LBContainer.getChildren().stream().forEach(VBox -> {
			if ((LBInfos) VBox.getUserData() != null &&  ((LBInfos) VBox.getUserData()).getDiscount() == null)
			VBox.setDisable(false);
		});
		
		if (selectedLB != null) selectedLB.getStyleClass().setAll("lineBasket");
		selectedLB = null;
		
		addCartButton.setDisable(true);
		addCartButton.getStyleClass().setAll("button");
		deleteLBButton.setDisable(true);
		deleteLBButton.getStyleClass().setAll("button");
		
		if (selectedIng != null) selectedIng.getStyleClass().remove("selectedButton");
		selectedIng = null;
		
		if (selectedProduct != null) selectedProduct.getStyleClass().remove("selectedButton");
		selectedProduct = null;
		
		if (selectedExtra != null) selectedExtra.getStyleClass().remove("selectedButton");
		selectedExtra = null;
		
		if (selectedProductSize != null) selectedProductSize.getStyleClass().remove("");
		selectedProductSize = null;
		
		allSelectedIngIds.clear();
		
		Node clickedItem = (Node) event.getTarget();
		String id = clickedItem.getId();

		if (id.equals("extra-order") && !extraMenuButton.getStyleClass().contains("selectedButton")) {
			currentOrderType = "Extras";
			sizeContainer.getChildren().setAll(sizeContainer.getChildren().get(0));
			pizzaMenuButton.setDisable(false);
			pizzaMenuButton.getStyleClass().remove("selectedButton");
			extraMenuButton.setDisable(true);
			extraMenuButton.getStyleClass().add("selectedButton");
			
			pizzaContainerButton.setDisable(true);
			pizzaContainerButton.setText("EXTRA");
			pizzaContainerButton.getStyleClass().setAll("button", "clickableButton", "selectedButton");
			pizzaContainerButton.setOnAction(pizzaevent -> switchExtraContainer(pizzaevent));
			
			ingredientContainerButton.setDisable(true);
			ingredientContainerButton.setText("DETAILS");
			ingredientContainerButton.getStyleClass().setAll("button");
			ingredientContainerButton.setOnAction(ingevent -> switchExtraContainer(ingevent));
			
		}else if (id.equals("pizza-order") &&!pizzaMenuButton.getStyleClass().contains("selectedButton")){ // pizza-order
			currentOrderType = "Pizzas";
			refreshSize();
			pizzaMenuButton.setDisable(true);
			pizzaMenuButton.getStyleClass().add("selectedButton");
			extraMenuButton.setDisable(false);
			extraMenuButton.getStyleClass().remove("selectedButton");
			
			pizzaContainerButton.setDisable(true);
			pizzaContainerButton.setText("PIZZAS");
			pizzaContainerButton.getStyleClass().setAll("button", "clickableButton", "selectedButton");
			pizzaContainerButton.setOnAction(pizzaevent -> switchContainer(pizzaevent));
			
			ingredientContainerButton.setDisable(true);
			ingredientContainerButton.setText("INGREDIENTS");
			ingredientContainerButton.getStyleClass().setAll("button");
			ingredientContainerButton.setOnAction(ingevent -> switchContainer(ingevent));
		}
		
		refreshData();
		quantityIng.getValueFactory().setValue(0);
		quantityLB.getValueFactory().setValue(1);
	}
	
	private void switchExtraContainer(ActionEvent event) {
		Node clickedButton = (Node) event.getTarget();
		if (clickedButton == ingredientContainerButton) { // Côté details extra
			//resetIngQuantity.setDisable(true);
			//resetIngQuantity.getStyleClass().remove("cancelClickableButton");
			//selectedIng = null;
			//quantityIng.getValueFactory().setValue(0);
			
			//ingredientContainer.setVisible(true);
			//pizzaContainer.setVisible(false);
			pizzaContainerButton.getStyleClass().remove("selectedButton");
			ingredientContainerButton.getStyleClass().add("selectedButton");
			pizzaContainerButton.setDisable(false);
			ingredientContainerButton.setDisable(true);
			
			pizzaContainer.getChildren().setAll(pizzaContainer.getChildren().get(pizzaContainer.getChildren().size() - 1));
	    	pizzasButton.clear();
	    	
			ProductsDAO prodDAO = new ProductsDAO();
			ArrayList<Products> prodList = prodDAO.readAllProducts(selectedExtra.getUserData().toString());
			prodList.stream().map(prod -> createProductButton(prod)).forEach(button -> pizzasButton.add(button));
			refreshSize();
			selectedExtra = null;
			offsetAffichageProduct = 0;
			
			for(int i = 0; i < maxAffichageParPageProduct; i++) {
	    		if (pizzasButton.size() > i + offsetAffichageProduct) {
	    			pizzaContainer.getChildren().add((pizzaContainer.getChildren().size() - 1), pizzasButton.get(i + offsetAffichageProduct));
	    		}
	    	}
			
			// Refresh ingredient par rapport à selected item et verifier changement si yen a eu pour pas supprimer
		}else if (clickedButton == pizzaContainerButton) { // Côté pizzas
			sizeContainer.getChildren().setAll(sizeContainer.getChildren().get(0));
			selectedExtra = null;
			ingredientContainer.setVisible(false);
			pizzaContainer.setVisible(true);
			if (!pizzaContainerButton.getStyleClass().contains("selectedButton")) {
				pizzaContainerButton.getStyleClass().add("selectedButton");
			}
			pizzaContainerButton.setDisable(true);
			ingredientContainerButton.getStyleClass().remove("selectedButton");
			addCartButton.getStyleClass().setAll("button");
			addCartButton.setDisable(true);
			if (selectedProduct != null) {
				ingredientContainerButton.setDisable(false);
			}else {
				ingredientContainerButton.getStyleClass().remove("clickableButton");
				ingredientContainerButton.setDisable(true);
			}
			
			refreshData();
			
			// Refresh pizza et verifier pizza selected
		}
	}

	@FXML
	private void switchContainer(ActionEvent event) {
		Node clickedButton = (Node) event.getTarget();
		if (clickedButton == ingredientContainerButton) { // Côté ingrédients
			resetIngQuantity.setDisable(true);
			resetIngQuantity.getStyleClass().remove("cancelClickableButton");
			selectedIng = null;
			quantityIng.getValueFactory().setValue(0);
			
			ingredientContainer.setVisible(true);
			pizzaContainer.setVisible(false);
			pizzaContainerButton.getStyleClass().remove("selectedButton");
			ingredientContainerButton.getStyleClass().add("selectedButton");
			pizzaContainerButton.setDisable(false);
			ingredientContainerButton.setDisable(true);
			
			refreshIng();
			
			// (RESOLU POUR L'INSTANT) Bug bizzare quand je change plusieurs fois de container (dans cette méthode est le PB et c'est pas la DB pck j'ai bien les résultats)

			offsetAffichageIng = 0;
			
	    	for(int i = 0; i < maxAffichageParPageIng; i++) {
	    		if (ingButton.size() > i + offsetAffichageIng) {
	    			ingButtonContainer.getChildren().add((ingButtonContainer.getChildren().size() - 1), ingButton.get(i + offsetAffichageIng));
	    		}
	    	}
			
			// Refresh ingredient par rapport à selected item et verifier changement si yen a eu pour pas supprimer
			
		}else if (clickedButton == pizzaContainerButton) { // Côté pizzas
			ingredientContainer.setVisible(false);
			pizzaContainer.setVisible(true);
			if (!pizzaContainerButton.getStyleClass().contains("selectedButton")) {
				pizzaContainerButton.getStyleClass().add("selectedButton");
			}
			pizzaContainerButton.setDisable(true);
			ingredientContainerButton.getStyleClass().remove("selectedButton");
			
			if (selectedProduct != null) {
				ingredientContainerButton.setDisable(false);
			}else {
				ingredientContainerButton.getStyleClass().remove("clickableButton");
				ingredientContainerButton.setDisable(true);
			}
			
			// Refresh pizza et verifier pizza selected
		}
	}
	
	private Button createSizeButton(String sizeProduct) {
		Button button = new Button(sizeProduct.toUpperCase());
	    button.setId(sizeProduct);
	    button.setMaxHeight(Double.MAX_VALUE);
	    button.setPrefHeight(70.0);
	    button.setPrefWidth(170.0);
	    button.getStyleClass().add("clickableButton");
	    button.setTextFill(Color.WHITE);
	    button.setFont(Font.font("System", FontWeight.BOLD, 20.0));

	    InnerShadow innerShadow = new InnerShadow();
	    innerShadow.setBlurType(BlurType.GAUSSIAN);
	    innerShadow.setHeight(22.47);
	    innerShadow.setRadius(10.37);
	    innerShadow.setWidth(21.01);

	    button.setEffect(innerShadow);

	    button.setOnAction(event -> selectProductSize(event)); // Remplacez par votre méthode de gestion d'action

	    return button;
	}
	
	private void refreshSize() {
		sizeContainer.getChildren().setAll(sizeContainer.getChildren().get(0));
		ProductsDAO prodDAO = new ProductsDAO();
		ArrayList<String> categoryProduct = new ArrayList<String>();
		if (!currentOrderType.equals("Pizzas")) {
			categoryProduct = prodDAO.readCategorySize(((Products) pizzasButton.getFirst().getUserData()).getCategoryProduct());
		}else {
			categoryProduct = prodDAO.readCategorySize(currentOrderType);
		}
		categoryProduct.stream().map(sizeProduct -> createSizeButton(sizeProduct)).forEach(button -> sizeContainer.getChildren().add(button));
	}
    
    @Override
    public void refreshData() {
    	System.out.println("Refresh Checkout Order");
    	setupDefault();
    	
    	ProductsDAO prodDAO = new ProductsDAO();
    	pizzaContainer.getChildren().setAll(pizzaContainer.getChildren().get(pizzaContainer.getChildren().size() - 1));
    	pizzasButton.clear();
    	offsetAffichageProduct = 0;
    	if (currentOrderType == "Extras") {
    		ArrayList<String> prodList = prodDAO.readAllCategory();
    		prodList.stream().map(cat -> createProductButton(cat)).forEach(button -> pizzasButton.add(button));
    	}else {
    		ArrayList<Products> prodList = prodDAO.readAllProducts(currentOrderType);
    		prodList.stream().map(prod -> createProductButton(prod)).forEach(button -> pizzasButton.add(button));
    	}
    	
    	for(int i = 0; i < maxAffichageParPageProduct; i++) {
    		if (pizzasButton.size() > i + offsetAffichageProduct) {
    			if (selectedProduct != null && ((Products) pizzasButton.get(i + offsetAffichageProduct).getUserData()).getNameProduct().equals(((Products) selectedProduct.getUserData()).getNameProduct()) && !pizzasButton.get(i + offsetAffichageProduct).getStyleClass().contains("selectedButton")) {
    				pizzasButton.get(i + offsetAffichageProduct).getStyleClass().add("selectedButton");
    			}
    			pizzaContainer.getChildren().add((pizzaContainer.getChildren().size() - 1), pizzasButton.get(i + offsetAffichageProduct));
    		}
    	}
    	
    	// Send refresh to some scene to know what order is created or modified
	}
    
    private void refreshIng() {
    	ingButton.clear();
    	Products product = (Products) selectedProduct.getUserData();
		IngredientsDAO ingDAO = new IngredientsDAO();
		ArrayList<Ingredients> allIngList = ingDAO.readAllPizzaIng();
		ArrayList<Ingredients> pizzaIngList = ingDAO.readAllPizzaIng(product.getIdProduct());
		if (selectedLB != null) {
			allSelectedIngIds = ((LBInfos) selectedLB.getUserData()).getAllSelectedIng();
		}
		ingButtonContainer.getChildren().setAll(ingButtonContainer.getChildren().get(ingButtonContainer.getChildren().size() - 1));
		
		allIngList.stream().map(ing -> createIngButton(ing)).forEach(button -> {
			// Verif s'il existe déja dans ing defaut puis si a été retiré ou ajouter
			pizzaIngList.stream().forEach(pizzaIng -> {
				if (pizzaIng.getidIngredient() == ((Ingredients) button.getUserData()).getidIngredient() && allSelectedIngIds != null) { // isDefaultIng of selectedPizza
					if (allSelectedIngIds.containsKey(pizzaIng.getidIngredient())) { // Have it in historic of (not)changed ing
						button.getStyleClass().add("selectedButtonIngAll");
					}else { // Haven't it in historic of (not)changed ing as it should be
						allSelectedIngIds.put(pizzaIng.getidIngredient(), 1);
						button.getStyleClass().add("selectedButtonIngAll");
					}
					
				}else {
					if (allSelectedIngIds != null && allSelectedIngIds.get(((Ingredients) button.getUserData()).getidIngredient()) != null && allSelectedIngIds.get(((Ingredients) button.getUserData()).getidIngredient()) != 0 && !button.getStyleClass().contains("selectedButtonIngSupp")) {
						button.getStyleClass().add("selectedButtonIngSupp");
					}
					
				}
			});
			ingButton.add(button);
		});
    }
    
    private void setupDefault() {
    	// Create an IntegerSpinnerValueFactory with min, max, and default values
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 1); // Min: 0, Max: 100, Default: 0
        quantityIng.setValueFactory(valueFactory);
        SpinnerValueFactory<Integer> valueFactoryProducts = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1); // Min: 0, Max: 100, Default: 0
        quantityLB.setValueFactory(valueFactoryProducts);
    }
    
    @FXML
    private void switchScene(ActionEvent event) {
    	Node clickedMenu = (Node) event.getTarget();
    	String fullId = clickedMenu.getId();
    	String[] sepId = fullId.split("-");
    	String id = sepId[0];

    	// Pas besoin de refresh car rien de special
    	Scene scene = SceneManager.getScene(Enum.valueOf(ScenesMap.class, id.toUpperCase()));
    	
    	if (Enum.valueOf(ScenesMap.class, id.toUpperCase()) == ScenesMap.CHECKOUT_PAYMENT) {
    		Object controller = SceneManager.getController(ScenesMap.CHECKOUT_PAYMENT);
    		((ControllerMustHave) controller).refreshData();
    	}
    	
    	if (scene != null) {
    		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    	}
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	System.out.println("Init checkout-order");
    	
    	taxePriceLabel.setText("Taxes (" + Taxe.CURRENT_TAXES.getTaxe() + "%)");
    	
    	quantityIng.getEditor().setFont(Font.font("System", FontWeight.BOLD, 26));
    	quantityIng.getEditor().setAlignment(javafx.geometry.Pos.CENTER); // Center the text
    	quantityLB.getEditor().setFont(Font.font("System", FontWeight.BOLD, 26));
    	quantityLB.getEditor().setAlignment(javafx.geometry.Pos.CENTER); // Center the text
    	
    	refreshSize();
    	refreshData();
    	currentDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy | HH:mm:ss")));
    	Timeline timeUpdate = new Timeline(new KeyFrame(Duration.seconds(1), e -> currentDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy | HH:mm:ss")))));
    	timeUpdate.setCycleCount(Timeline.INDEFINITE);
    	timeUpdate.play();
        
    }    

    
}
