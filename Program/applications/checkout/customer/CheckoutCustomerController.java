package applications.checkout.customer;


import java.net.URL;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import applications.menu.MenuController;
import dao.CustomersDAO;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import tables.Customers;
import utilClass.OrderConfirmation;
import utilClass.ScenesMap;

public class CheckoutCustomerController implements Initializable, ControllerMustHave{
	
	@FXML
	private Label currentDate;
	
	@FXML
	private TextField telCst, nameCst, numAdressCst, adressCst, postalCodeCst;
	
	@FXML
	private TextArea instructionsCst, internalComCst;
	
	@FXML
	private VBox inputContainer;
	
    @FXML
    private VBox errorContainer;
    
    @FXML
    private Text errorText;
    
    @FXML
    private Button okButton;
	
	private Customers cst = null;
	
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
	
	@FXML
	private void searchCustomer(ActionEvent event) {
		if (telCst.getText().matches("^(?:06|07)\\d{8}$")) {
			// Chercher client
			CustomersDAO cstDAO = new CustomersDAO();
			cst = cstDAO.read(telCst.getText());
			if (cst != null) {
				telCst.setText(cst.getTelCst());
				nameCst.setText(cst.getNameCst());
				numAdressCst.setText(cst.getStreetNumberCst());
				adressCst.setText(cst.getStreetNameCst());
				postalCodeCst.setText(cst.getPostcodeCst());
				instructionsCst.setText(cst.getInstructionsCst());
				internalComCst.setText(cst.getInternalComCst());
			}
		}
	}
	
	public String getInternalComCst() {
		return internalComCst.getText().isEmpty()? null : internalComCst.getText();
	}
	
	public String getInstructionsCst() {
		return instructionsCst.getText().isEmpty()? null : instructionsCst.getText();
	}
	
	public String getPostalCodeCst() {
		return postalCodeCst.getText().isEmpty()? null : postalCodeCst.getText();
	}
	
	public String getAdressCst() {
		return adressCst.getText().isEmpty()? null : adressCst.getText();
	}
	
	public String getNumAdressCst() {
		return numAdressCst.getText().isEmpty()? null : numAdressCst.getText();
	}
	
	public String getNameCst() {
		return nameCst.getText().isEmpty()? null : nameCst.getText();
	}
	
	public String getTelCst() {
		return telCst.getText().isEmpty()? null : telCst.getText();
	}
	
	public Customers getCst() {
		return cst;
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
    	System.out.println("Init checkout-customer");
    	refreshData();
    	currentDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy | HH:mm:ss")));
    	Timeline timeUpdate = new Timeline(new KeyFrame(Duration.seconds(1), e -> currentDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy | HH:mm:ss")))));
    	timeUpdate.setCycleCount(Timeline.INDEFINITE);
    	timeUpdate.play();
        
    }    

    
}
