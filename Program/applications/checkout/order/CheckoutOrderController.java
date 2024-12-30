package applications.checkout.order;


import java.net.URL;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import dao.IngredientsDAO;
import dao.ProductsDAO;
import interfaces.ControllerMustHave;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import tables.Ingredients;
import tables.Products;

public class CheckoutOrderController implements Initializable, ControllerMustHave{
	
	@FXML
	private Label currentDate;
	
	@FXML
	private VBox ingredientContainer;

	@FXML
	private FlowPane pizzaContainer;
	
	@FXML
	private FlowPane ingButtonContainer;
	
	@FXML
	private Button ingredientContainerButton;

	@FXML
	private Button pizzaContainerButton;

	@FXML
	private Spinner<Integer> quantityIng;

	@FXML
	private Spinner<Integer> quantityLB;
	
	@FXML
	private Button resetIngQuantity;
	
	private int maxAffichageParPagePizzas = 23;
	private int offsetAffichagePizzas = 0;
	
	private int maxAffichageParPageIng = 17;
	private int offsetAffichageIng = 0;
	
	private Node selectedPizza = null;
	//private ArrayList<>
	private Node selectedIng = null;
	private HashMap<Long, Integer> allSelectedIng = new HashMap<Long, Integer>(); // ID ING, QUANTITY / Historique des changements d'ing, il faudra vérifier par rapport aux ing par défauts du produits

	private ArrayList<Button> pizzasButton = new ArrayList<Button>();
	private ArrayList<Button> ingButton = new ArrayList<Button>();
	
	
	
	private Button createPizzaButton(Products prod) {
		Button button = new Button(prod.getNameProduct().toUpperCase());
		button.setUserData(prod.getIdProduct());
	    button.setAlignment(Pos.CENTER);
	    button.setMaxHeight(Double.MAX_VALUE);
	    button.setMaxWidth(Double.MAX_VALUE);
	    button.setOnAction(event -> selectPizza(event));
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
	    
	    if (selectedPizza != null && (Long) selectedPizza.getUserData() == prod.getIdProduct()) {
	    	button.getStyleClass().add("selectedButton");
	    	selectedPizza = button;
	    }
	    
	    return button;
	}
	
	private Button createIngButton(Ingredients ing) {
		Button button = new Button(ing.getNameIng());
		button.setUserData(ing.getidIngredient());
	    
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
	
	@FXML
	private void nextPageIng(ActionEvent event) {
		
	}

	@FXML
	private void nextPagePizza(ActionEvent event) {

	}
	
	@FXML
	private void selectPizza(ActionEvent event) {
		offsetAffichageIng = 0;
		
		allSelectedIng.clear();
		Node clickedButton = (Node) event.getTarget();
		clickedButton.setDisable(true);
		clickedButton.getStyleClass().add("selectedButton");
		if (selectedPizza != null) {
			selectedPizza.getStyleClass().remove("selectedButton");
			selectedPizza.setDisable(false);
		}else {
			ingredientContainerButton.setDisable(false);
			ingredientContainerButton.getStyleClass().add("clickableButton");
		}
		selectedPizza = clickedButton;
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
		Long id = (Long) clickedButton.getUserData();
		int qte;
		if (allSelectedIng.get(id) != null) {
			qte = allSelectedIng.get(id);
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
			
			if (!selectedIng.getStyleClass().contains("selectedButtonIngSupp")) { // n'est pas selectionner et pas par défaut grâce à la class de style donc vérif si qte > 0
				if (qte != 0) {
					selectedIng.getStyleClass().add("selectedButtonIngSupp");
				}
			}else {
				if (qte == 0) {
					selectedIng.getStyleClass().remove("selectedButtonIngSupp");
				}
			}
		
			Long idIng = (Long) selectedIng.getUserData();
			allSelectedIng.put(idIng, qte);
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
			ingButton.clear();
			
			Long idProduct = (Long) selectedPizza.getUserData();
			IngredientsDAO ingDAO = new IngredientsDAO();
			ArrayList<Ingredients> allIngList = ingDAO.readAllPizzaIng();
			ArrayList<Ingredients> pizzaIngList = ingDAO.readAllPizzaIng(idProduct);
			ingButtonContainer.getChildren().setAll(ingButtonContainer.getChildren().get(ingButtonContainer.getChildren().size() - 1));
			
			allIngList.stream().map(ing -> createIngButton(ing)).forEach(button -> {
				// Verif s'il existe déja dans ing defaut puis si a été retiré ou ajouter
				pizzaIngList.stream().forEach(pizzaIng -> {
					if (pizzaIng.getidIngredient() == (Long) button.getUserData()) { // isDefaultIng of selectedPizza
						if (allSelectedIng.containsKey(pizzaIng.getidIngredient())) { // Have it in historic of (not)changed ing
							button.getStyleClass().add("selectedButtonIngAll");
						}else { // Haven't it in historic of (not)changed ing as it should be
							allSelectedIng.put(pizzaIng.getidIngredient(), 1);
							button.getStyleClass().add("selectedButtonIngAll");
						}
					}else {
						if (allSelectedIng.containsKey(button.getUserData()) && allSelectedIng.get(button.getUserData()) != 0 && !button.getStyleClass().contains("selectedButtonIngSupp")) {
							button.getStyleClass().add("selectedButtonIngSupp");
						}
					}
				});
				ingButton.add(button);
			});
			
			// (RESOLU POUR L'INSTANT) Bug bizzare quand je change plusieurs fois de container (dans cette méthode est le PB et c'est pas la DB pck j'ai bien les résultats)

	    	for(int i = 0; i < maxAffichageParPageIng; i++) {
	    		if (i < ingButton.size()) {
	    			ingButtonContainer.getChildren().add((ingButtonContainer.getChildren().size() - 1), ingButton.get(i + offsetAffichageIng));
	    		}
	    		/*
	    		if (offsetAffichageIng >= ingButton.size() - 1) {
	    			offsetAffichageIng = 0;
	    			break;
	    		}else {
	    			offsetAffichageIng++;
	    		}
	    		*/
	    	}
			
			// Refresh ingredient par rapport à selected item et verifier changement si yen a eu pour pas supprimer
			
		}else if (clickedButton == pizzaContainerButton) { // Côté pizzas
			ingredientContainer.setVisible(false);
			pizzaContainer.setVisible(true);
			pizzaContainerButton.getStyleClass().add("selectedButton");
			ingredientContainerButton.getStyleClass().remove("selectedButton");
			pizzaContainerButton.setDisable(true);
			ingredientContainerButton.setDisable(false);
			
			// Refresh pizza et verifier pizza selected
		}
	}
    
    @Override
    public void refreshData() {
    	System.out.println("Refresh Checkout Order");
    	setupDefault();
    	
    	ProductsDAO prodDAO = new ProductsDAO();
    	ArrayList<Products> prodList = prodDAO.readAllPizzas();
    	pizzaContainer.getChildren().setAll(pizzaContainer.getChildren().get(pizzaContainer.getChildren().size() - 1));
    	prodList.stream().map(prod -> createPizzaButton(prod)).forEach(button -> pizzasButton.add(button));
    	for(int i = offsetAffichagePizzas; i < maxAffichageParPagePizzas; i++) {
    		if (pizzasButton.size() > i) {
    			pizzaContainer.getChildren().add((pizzaContainer.getChildren().size() - 1), pizzasButton.get(i));
    			offsetAffichagePizzas++;
    		}
    	}
    	
    	// Send refresh to some scene to know what order is created or modified
	}
    
    private void refreshIng() {
    	
    	
    }
    
    private void setupDefault() {
    	// Create an IntegerSpinnerValueFactory with min, max, and default values
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 1); // Min: 0, Max: 100, Default: 0
        quantityIng.setValueFactory(valueFactory);
        SpinnerValueFactory<Integer> valueFactoryProducts = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1); // Min: 0, Max: 100, Default: 0
        quantityLB.setValueFactory(valueFactoryProducts);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	System.out.println("Init checkout-order");
    	
    	quantityIng.getEditor().setFont(Font.font("System", FontWeight.BOLD, 26));
    	quantityIng.getEditor().setAlignment(javafx.geometry.Pos.CENTER); // Center the text
    	quantityLB.getEditor().setFont(Font.font("System", FontWeight.BOLD, 26));
    	quantityLB.getEditor().setAlignment(javafx.geometry.Pos.CENTER); // Center the text
    	
    	refreshData();
    	currentDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy | HH:mm:ss")));
    	Timeline timeUpdate = new Timeline(new KeyFrame(Duration.seconds(1), e -> currentDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy | HH:mm:ss")))));
    	timeUpdate.setCycleCount(Timeline.INDEFINITE);
    	timeUpdate.play();
        
    }    

    
}
