package applications.menu;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import applications.ControllerMustHave;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import tables.Employees;

public class MenuController implements Initializable, ControllerMustHave{
	
	@FXML
	private Label currentDate;
	@FXML
	private Button connectionButton;
	@FXML
	private Button deconnectButton;
	
	private Employees connectedEmployees = null;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
    
    @FXML
    private void switchScene(ActionEvent event) throws IOException{
    	String path = "";
    	Node clickedButton = (Node) event.getTarget();
    	switch (clickedButton.getId()) {
    		case "historical":
    			path = "/applications/historical/historical/historicalHistorical.fxml";
    			break;
    		case "payment":
    			path = "/applications/historical/payment/historicalPayment.fxml";
    			break;
    		case "menu":
    			path = "/applications/menu/menu.fxml";
    			break;
    		default:
    			path = "/applications/menu/menu.fxml";
    	}
    	FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
    	root = loader.load();
    	
    	//HistoricalHistoricalController historicalController = loader.getController();
    	//historicalController.refreshData(); PAS BESOIN CAR void initialize EST LA POUR CA
    	
    	stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
    
    public void setConnectedEmployees(Employees emp) {
    	connectedEmployees = emp;
    }
    
    @Override
    public void refreshData() {
    	int afk_timeout = 60; // In secondes
    	if (connectedEmployees != null) { // An employee is already connected
    		connectionButton.getStyleClass().add("connectedButton");
			connectionButton.getStyleClass().remove("NOTconnectedButton");
			deconnectButton.getStyleClass().add("deconnectButton");
			deconnectButton.getStyleClass().remove("NOTdeconnectButton");
    		Timeline timeUpdate = new Timeline(new KeyFrame(Duration.seconds(afk_timeout), e -> { // Timeout if AFK
    			connectedEmployees = null;
    			connectionButton.getStyleClass().add("NOTconnectedButton");
    			connectionButton.getStyleClass().remove("connectedButton");
    			deconnectButton.getStyleClass().add("NOTdeconnectButton");
    			deconnectButton.getStyleClass().remove("deconnectButton");
    			System.out.println("Vous avez été deconnecté");
    		}));
        	timeUpdate.play();
    	}else {
    		connectionButton.getStyleClass().add("NOTconnectedButton");
			connectionButton.getStyleClass().remove("connectedButton");
			deconnectButton.getStyleClass().add("NOTdeconnectButton");
			deconnectButton.getStyleClass().remove("deconnectButton");
    	}
	}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	refreshData();
    	currentDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
    	Timeline timeUpdate = new Timeline(new KeyFrame(Duration.seconds(1), e -> currentDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")))));
    	timeUpdate.setCycleCount(Timeline.INDEFINITE);
    	timeUpdate.play();
    }   

    
}
