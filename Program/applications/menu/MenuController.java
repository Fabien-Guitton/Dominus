package applications.menu;

import java.io.IOException;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.EmployeesDAO;
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
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import tables.Employees;
import utilClass.ScenesMap;
import utilClass.UDPMultiCastApp;

public class MenuController implements Initializable, ControllerMustHave{
	
	@FXML
	private Label currentDate;
	@FXML
	private Button connectionButton;
	@FXML
	private Button deconnectButton;
	@FXML
	private Button menuHistorical;
	@FXML
	private Button menuPayment;
	@FXML
	private Button menuCaisse;
	@FXML
	private VBox mainContainer;
	@FXML
	private VBox loginContainer;
	@FXML
	private PasswordField passContainer;
	@FXML
	private Pane errorContainer;
	@FXML
	private Text errorText;
	@FXML
	private Text employeText;
	@FXML
	private HBox employeeContainer;
	
	private Timeline timeUpdate;
	
	private ArrayList<Long> connectedEmployees = new ArrayList<Long>();
	private Employees connectedEmp = null;
    
    @FXML
    private void switchScene(ActionEvent event) throws IOException{
    	if (timeUpdate != null) {
    		timeUpdate.pause();
    	}
    	Node clickedButton = (Node) event.getTarget();
    	String sceneName = clickedButton.getId();
    	
    	//FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
    	//root = loader.load();
    	//HistoricalHistoricalController historicalController = loader.getController();
    	//historicalController.refreshData(); PAS BESOIN CAR void initialize EST LA POUR CA
    	
    	Object controller = SceneManager.getController(Enum.valueOf(ScenesMap.class, sceneName.toUpperCase()));
    	((ControllerMustHave) controller).refreshData();
    	Scene scene = SceneManager.getScene(Enum.valueOf(ScenesMap.class, sceneName.toUpperCase()));
    	if (scene != null) {
    		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    	}
    }
    
    @FXML
    private void loginPane(ActionEvent event){
    	if (timeUpdate != null) {
    		timeUpdate.pause();
    	}
    	mainContainer.setVisible(false);
    	loginContainer.setVisible(true);
    	passContainer.requestFocus();
    }
    
    @FXML
    private void mainPane(ActionEvent event){
    	refreshData();
    	passContainer.clear();
    	mainContainer.setVisible(true);
    	loginContainer.setVisible(false);
    }
    
    @FXML
    private void digitInput(ActionEvent event) {
    	int length = passContainer.getLength();
    	passContainer.requestFocus();
    	passContainer.selectRange(length, length);
    	Node clickedButton = (Node) event.getTarget();
    	String[] digitId = clickedButton.getId().split("-");
    	String digit = digitId[1];
    	if (!digit.equals("del") && length < 4) {
    		passContainer.appendText(digit);
    	}else if (digit.equals("del") && length > 0){
    		passContainer.deleteText(length - 1, length);
    	}
    }
    
    @FXML
    private void login(ActionEvent event) throws IOException{
    	if (passContainer.getLength() == 4) {
    		String code = passContainer.getText();
    		EmployeesDAO empDAO = new EmployeesDAO();
    		Employees emp = empDAO.read(code);
    		if (emp != null) { // Connexion reussi
    			if (!connectedEmployees.contains(emp.getIdEmployee())) {
    				// Préchargement des scenes voisines
    				SceneManager.addScene(ScenesMap.HISTORICAL_HISTORICAL);
    			    SceneManager.addScene(ScenesMap.HISTORICAL_PAYMENT);
//    			    SceneManager.addScene("checkout", "/applications/checkout/menu/menu.fxml"); LA CAISSE A RELIER
        			
        			connectedEmp = emp;
        			UDPMultiCastApp.sendCommand(utilClass.Command.ADD_CONNECTED_EMPLOYEE.getCommand() + Long.toString(emp.getIdEmployee()));
        			employeText.setText("[" + connectedEmp.getRoleEmp() + "] " + connectedEmp.getNameEmp());
        			mainPane(event);
    			}else {
    				error("L'employé est déjà connecté à une caisse.");
    			}
    		}else {
    			error("Le code est erroné OU l'employé n'est pas pointé.");
    		}
    	}else {
    		error("Veuillez saisir un code correcte");
    	}
    }
    
    @FXML
    private void logout(ActionEvent event) {
    	UDPMultiCastApp.sendCommand(utilClass.Command.RM_CONNECTED_EMPLOYEE.getCommand() + Long.toString(connectedEmp.getIdEmployee()));
    	connectedEmp = null;
    	mainPane(event);
    }
    
    private void error(String message) {
    	errorText.setText(message);
    	mainContainer.setVisible(false);
    	loginContainer.setVisible(false);
    	errorContainer.setVisible(true);
    }
    
    @FXML
    private void okError(ActionEvent event) {
    	errorContainer.setVisible(false);
    	mainPane(event);
    }
    
    public void setConnectedEmployees(Employees emp) {
    	connectedEmp = emp;
    }
    
    public void addConnectedEmp(Long idEmployee) {
    	connectedEmployees.add(idEmployee);
    }
    
    public void removeConnectedEmp(Long idEmployee) {
    	connectedEmployees.remove(idEmployee);
    }
    
    private void isConnected(Boolean isConnected){
    	connectionButton.getStyleClass().remove("connectClickableButton");
    	deconnectButton.getStyleClass().remove("disconnectClickableButton");
		menuHistorical.getStyleClass().remove("menuClickableButton");
		menuPayment.getStyleClass().remove("menuClickableButton");
		menuCaisse.getStyleClass().remove("checkoutClickableButton");
		
    	if (isConnected) {
    		connectionButton.getStyleClass().remove("connectClickableButton");
			deconnectButton.getStyleClass().add("disconnectClickableButton");
			menuHistorical.getStyleClass().add("menuClickableButton");
			menuPayment.getStyleClass().add("menuClickableButton");
			menuCaisse.getStyleClass().add("checkoutClickableButton");
			connectionButton.setDisable(true);
			deconnectButton.setDisable(false);
			menuHistorical.setDisable(false);
			menuPayment.setDisable(false);
			menuCaisse.setDisable(false);
			employeeContainer.setVisible(true);		
		    
    	}else {
    		
    		// Dé-chargement des scenes car elles ne sont plus voisines vu que non connecté
        	SceneManager.removeScene(ScenesMap.HISTORICAL_HISTORICAL);
    	    SceneManager.removeScene(ScenesMap.HISTORICAL_PAYMENT);
    	    
    		connectionButton.getStyleClass().add("connectClickableButton");
			deconnectButton.getStyleClass().remove("disconnectClickableButton");
			menuHistorical.getStyleClass().remove("menuClickableButton");
			menuPayment.getStyleClass().remove("menuClickableButton");
			menuCaisse.getStyleClass().remove("checkoutClickableButton");
			connectionButton.setDisable(false);
			deconnectButton.setDisable(true);
			menuHistorical.setDisable(true);
			menuPayment.setDisable(true);
			menuCaisse.setDisable(true);
			employeeContainer.setVisible(false);
    	}
    }
    
    @Override
    public void refreshData() {
    	System.out.println("Refresh Menu");
    	int afk_timeout = 60; // In secondes
    	
    	if (connectedEmp != null) { // An employee is already connected
			isConnected(true);
    		timeUpdate = new Timeline(new KeyFrame(Duration.seconds(afk_timeout), e -> { // Timeout if AFK
    			logout(null);
    			isConnected(false);
    		}));
        	timeUpdate.play();
    	}else {
    		isConnected(false);
    	}
	}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	System.out.println("Init Menu");
    	try {
			SceneManager.addScene(ScenesMap.LOGIN);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	passContainer.addEventFilter(KeyEvent.KEY_TYPED, e -> {
    	    if (!e.getCharacter().matches("[0-9]") || passContainer.getLength() >= 4) {
    	        e.consume();  // Empêche l'entrée si c'est pas un chiffre
    	    }

            if (e.getCharacter().equals("\r")) {
                // Si la touche Entrée est pressée, appeler la méthode
                try{
                	login(null);
                } catch (IOException er) {
                	er.printStackTrace();
                }
            }
    	});
    	mainContainer.setVisible(true);
    	loginContainer.setVisible(false);
    	errorContainer.setVisible(false);
    	employeeContainer.setVisible(false);
    	refreshData();
    	currentDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy | HH:mm:ss")));
    	Timeline timeUpdate = new Timeline(new KeyFrame(Duration.seconds(1), e -> currentDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy | HH:mm:ss")))));
    	timeUpdate.setCycleCount(Timeline.INDEFINITE);
    	timeUpdate.play();
    }   

    
}
