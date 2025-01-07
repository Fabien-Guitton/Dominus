package applications.checkout.checkout;

import java.net.URL;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import applications.checkout.order.CheckoutOrderController;
import applications.menu.MenuController;
import init.SceneManager;
import interfaces.ControllerMustHave;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import utilClass.Currency;
import utilClass.OrderConfirmation;
import utilClass.ScenesMap;

public class CheckoutCheckoutController implements Initializable, ControllerMustHave{
	
	@FXML
	private Label currentDate;

    @FXML
    private TextField priceContainer;
    
    @FXML
    private Label toReturn;

    @FXML
    private Label totalPriceLabel;
    
    @FXML
    private HBox addButtonContainer;
    
    @FXML
    private VBox errorContainer;
    
    @FXML
    private Text errorText;
    
    @FXML
    private Button okButton;
    
    private int positionTextField = 8;
    
    private Node orderPayed = null;
    
    private Boolean isOrderPayed = null;
    
    private Double totalPrice = 0.;
    
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
    
    public Boolean getIsOrderPayed() {
    	return isOrderPayed;
    }
	
	@Override
	public void refreshData() {
		priceContainer.requestFocus();
		Object controller = SceneManager.getController(ScenesMap.CHECKOUT_ORDER);
    	totalPrice = ((CheckoutOrderController) controller).getTotalCartPrice();
    	totalPriceLabel.setText(String.format("%05.2f", totalPrice).replace(',', '.') + Currency.MAIN_CURRENCY.getType());
    	
    	if (getInputPrice() - totalPrice < 0) {
			toReturn.setText("00.00" + Currency.MAIN_CURRENCY.getType());
		}else {
			toReturn.setText(String.format("%05.2f", getInputPrice() - totalPrice).replace(',', '.') + Currency.MAIN_CURRENCY.getType());
		}
	}
	
	@FXML
    void orderPayed(ActionEvent event) {
		Node clickedItem = (Node) event.getTarget();
		if (orderPayed != null) {
			orderPayed.getStyleClass().setAll("button", "clickableButton");
		}
		clickedItem.getStyleClass().setAll("button", "selectedButton");
		
		orderPayed = clickedItem;
		isOrderPayed = orderPayed.getId().equals("payNow");
    }
	
	@FXML
	void addAutoPrice(ActionEvent event) {
		Node clickedButton = (Node) event.getTarget();
		String fullId = clickedButton.getId();
		String[] separedId = fullId.split("-");
		String id = separedId[0];
		Double addPrice = Double.valueOf(id);
		addInputPrice(addPrice);
	}
	
	private void addInputPrice(Double price) {
		if (priceContainer.getLength() != 9) {
			positionTextField = 8;
			priceContainer.setText("00000.00" + Currency.MAIN_CURRENCY.getType());
			return;
		}
		Double actualPrice = getInputPrice();
		Double total = actualPrice + price;
		priceContainer.setText(String.format("%08.2f", total).replace(',', '.') + Currency.MAIN_CURRENCY.getType());
		positionTextField = String.format("%.2f", total).length() - 2;
		refreshData();
	}
	
	private Double getInputPrice() {
		String fullText = priceContainer.getText();
		String priceInCurrency = fullText.substring(0, 7).replace(",", "."); // 00000.00
		Double priceInInput = Double.valueOf(priceInCurrency);
		return priceInInput;
	}
	
	@FXML
    void resetPrice(ActionEvent event) {
		priceContainer.setText("00000.00" + Currency.MAIN_CURRENCY.getType());
		positionTextField = 8;
		priceContainer.requestFocus();
		refreshData();
    }
	
	@FXML
    private void digitInput(ActionEvent event) {
		// Gérer l'input pour pouvoir écrire de droite à gauche
    	int length = priceContainer.getLength();
    	priceContainer.requestFocus();
    	priceContainer.selectRange(length, length);
    	Node clickedButton = (Node) event.getTarget();
    	String[] digitId = clickedButton.getId().split("-");
    	String digit = digitId[1];
    	
    	if (!digit.equals("del") && positionTextField > 0 && positionTextField <= 8) {
    		positionTextField--;
	    	if (priceContainer.getText().charAt(positionTextField) == '.') {  // Passage de la virgule
	    		positionTextField--;
	    	}
    		String actualPrice = priceContainer.getText();
    		StringBuilder sb = new StringBuilder(actualPrice);
    		for(int i = 0; i < sb.length() - 2; i++) {
	    		if (sb.charAt(i+1) != '.') {
	    			sb.setCharAt(i, sb.charAt(i + 1));
	    		}else {
	    			sb.setCharAt(i, sb.charAt(i + 2));
	    			i++;
	    		}
	    	}
	    	sb.setCharAt(7, digit.charAt(0));
	    	priceContainer.setText(sb.toString());
    	}else if (digit.equals("del") && positionTextField >= 0 && positionTextField < 8){
    		String actualPrice = priceContainer.getText();
	    	StringBuilder sb = new StringBuilder(actualPrice);
	    	for(int i = sb.length() - 2; i > 0; i--) {
	    		if (sb.charAt(i-1) != '.') {
	    			sb.setCharAt(i, sb.charAt(i - 1));
	    		}else {
	    			sb.setCharAt(i, sb.charAt(i - 2));
	    			i--;
	    		}
	    	}
	    	sb.setCharAt(0, '0');
	    	priceContainer.setText(sb.toString());
    		positionTextField++;
	    	if (priceContainer.getText().charAt(positionTextField) == '.') {  // Passage de la virgule
	    		positionTextField++;
	    	}
    	}
    	
    	if (getInputPrice() - totalPrice < 0) {
			toReturn.setText("00.00" + Currency.MAIN_CURRENCY.getType());
		}else {
			toReturn.setText(String.format("%05.2f", getInputPrice() - totalPrice) + Currency.MAIN_CURRENCY.getType());
		}
    }
	
	@FXML
    private void switchScene(ActionEvent event) {
    	Node clickedMenu = (Node) event.getTarget();
    	String fullId = clickedMenu.getId();
    	String[] sepId = fullId.split("-");
    	String id = sepId[0];

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
    public void initialize(URL url, ResourceBundle rb) {
    	totalPrice = 0.;
    	System.out.println("Init checkout-customer");
    	priceContainer.setText("00000.00" + Currency.MAIN_CURRENCY.getType());
    	
    	addButtonContainer.getChildren().stream().forEach(button -> {
    		((Button) button).setText(((Button) button).getText() + Currency.MAIN_CURRENCY.getType());
    	});
    	
    	priceContainer.addEventFilter(KeyEvent.KEY_TYPED, e -> {
    		if (priceContainer.getLength() != 9) {
    			e.consume();
    			positionTextField = 5;
    			toReturn.setText("00.00" + Currency.MAIN_CURRENCY.getType());
    			priceContainer.setText("00000.00" + Currency.MAIN_CURRENCY.getType());
    			return;
    		}
    		
    		if (e.getCharacter().matches("[0-9]") && positionTextField > 0) {
    	        e.consume();  // Empêche l'entrée si c'est pas un chiffre
    	    	positionTextField--;
    	    	if (priceContainer.getText().charAt(positionTextField) == '.') {  // Passage de la virgule
    	    		positionTextField--;
    	    	}
    	    	String actualPrice = priceContainer.getText();
    	    	StringBuilder sb = new StringBuilder(actualPrice);
    	    	for(int i = 0; i < sb.length() - 2; i++) {
    	    		if (sb.charAt(i+1) != '.') {
    	    			sb.setCharAt(i, sb.charAt(i + 1));
    	    		}else {
    	    			sb.setCharAt(i, sb.charAt(i + 2));
    	    			i++;
    	    		}
    	    	}
    	    	sb.setCharAt(7, e.getCharacter().charAt(0));
    	    	priceContainer.setText(sb.toString());
    		}else if (e.getCharacter().equals("\b") && positionTextField >= 0 && positionTextField < 8) {
    			e.consume();
    			
    			String actualPrice = priceContainer.getText();
    	    	StringBuilder sb = new StringBuilder(actualPrice);
    	    	for(int i = sb.length() - 2; i > 0; i--) {
    	    		if (sb.charAt(i-1) != '.') {
    	    			sb.setCharAt(i, sb.charAt(i - 1));
    	    		}else {
    	    			sb.setCharAt(i, sb.charAt(i - 2));
    	    			i--;
    	    		}
    	    	}
    	    	sb.setCharAt(0, '0');
    	    	priceContainer.setText(sb.toString());
    	    	
    			positionTextField++;
    	    	if (priceContainer.getText().charAt(positionTextField) == '.') {  // Passage de la virgule
    	    		positionTextField++;
    	    	}
    	    }else{
    	    	e.consume(); 
    	    }
    		
    		if (getInputPrice() - totalPrice < 0) {
    			toReturn.setText("00.00" + Currency.MAIN_CURRENCY.getType());
    		}else {
    			toReturn.setText(String.format("%05.2f", getInputPrice() - totalPrice) + Currency.MAIN_CURRENCY.getType());
    		}
    		
    	});

    	refreshData();
    	currentDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy | HH:mm:ss")));
    	Timeline timeUpdate = new Timeline(new KeyFrame(Duration.seconds(1), e -> currentDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy | HH:mm:ss")))));
    	timeUpdate.setCycleCount(Timeline.INDEFINITE);
    	timeUpdate.play();
        
    }    

    
}
