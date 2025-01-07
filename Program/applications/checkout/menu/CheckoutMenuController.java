package applications.checkout.menu;


import java.net.URL;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import utilClass.OrderConfirmation;
import utilClass.ScenesMap;

public class CheckoutMenuController implements Initializable, ControllerMustHave{
	
	@FXML
	private Label currentDate;
	
    @FXML
    private VBox errorContainer;
    
    @FXML
    private Text errorText;
    
    @FXML
    private Button okButton;
	
	private Node selectedItem = null;

	private String orderType = null;
	
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
	
	@Override
	public void refreshData() {
		
	}
	
	public String getOrderType() {
		return orderType;
	}
	
	@FXML
	private void selectOrderType(ActionEvent event) {
		Node clickedType = (Node) event.getTarget();
		String fullId = clickedType.getId();
    	String[] sepId = fullId.split("-");
    	String id = sepId[0];
    	clickedType.getStyleClass().setAll("button", "clickableButton", "selectedButton");
    	if (selectedItem != null) {
    		selectedItem.getStyleClass().setAll("button", "clickableButton");
    	}
    	selectedItem = clickedType;
    	orderType = id;
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
    public void initialize(URL url, ResourceBundle rb) {
    	System.out.println("Init checkout-menu");

    	refreshData();
    	currentDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy | HH:mm:ss")));
    	Timeline timeUpdate = new Timeline(new KeyFrame(Duration.seconds(1), e -> currentDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy | HH:mm:ss")))));
    	timeUpdate.setCycleCount(Timeline.INDEFINITE);
    	timeUpdate.play();
        
    }    

    
}
