package applications.login;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import applications.menu.MenuController;
import dao.ClockingInDAO;
import dao.EmployeesDAO;
import init.SceneManager;
import interfaces.ControllerMustHave;
import javafx.fxml.Initializable;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import tables.ClockingIn;
import tables.Employees;
import utilClass.ScenesMap;

public class LoginController implements Initializable, ControllerMustHave {
	
	@FXML
    private HBox messageContainer;

    @FXML
    private Text msg;

    @FXML
    private VBox pointageContainer;
	
    @FXML
    private Button btnAnnuler;

    @FXML
    private Button btnPointage;
    
    @FXML
    private PasswordField codePointage;

    @FXML
    private Label currentDate;
    
    @FXML
    private void cancelClockingIn(ActionEvent event) {
    	codePointage.clear();
    	Scene menu = SceneManager.getScene(ScenesMap.MENU);
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(menu);
        stage.show();
    }
    
    @FXML
    private void confirmClockingIn(ActionEvent event) {
    	if (codePointage.getLength() == 4) {
            String code = codePointage.getText();
            ClockingInDAO cloDAO = new ClockingInDAO();
            ClockingIn clo = cloDAO.read(code);
            if (clo != null) { // L'employé est déjà pointé
               clo.setEndClockingIn(new Timestamp(System.currentTimeMillis()));
               cloDAO.update(clo);
               showMessage("L'employé " + clo.getIdEmployee().getNameEmp() +" est dépointé.");
            }else { // L'employé n'est pas pointé
            	EmployeesDAO empDAO = new EmployeesDAO();
                Employees emp = empDAO.readEmp(code);
                if (emp!=null) {
                	clo = new ClockingIn(new Timestamp(System.currentTimeMillis()), null, emp);
                	cloDAO.create(clo);
                	showMessage("L'employé "+emp.getNameEmp()+" est pointé.");
                }else {
                	showMessage("Veuillez saisir un code correcte (l'employé n'existe pas).");
                }
            }
        }else {
            showMessage("Veuillez saisir un code correcte.");
        }
    }
    
    private void showMessage(String message) {
    	pointageContainer.setVisible(false);
    	messageContainer.setVisible(true);
    	msg.setText(message);
    }
    
    @FXML
    private void okMessage(ActionEvent event) {
    	codePointage.clear();
    	pointageContainer.setVisible(true);
    	messageContainer.setVisible(false);
    	Object controller = SceneManager.getController(ScenesMap.MENU);
    	((MenuController) controller).logout(event);
    	Scene scene = SceneManager.getScene(ScenesMap.MENU);
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();	
    }
    
    
    @FXML
    private void digitInput(ActionEvent event) {
        int length = codePointage.getLength();
        codePointage.requestFocus();
        codePointage.selectRange(length, length);
        Node clickedButton = (Node) event.getTarget();
        String[] digitId = clickedButton.getId().split("-");
        String digit = digitId[1];
        if (!digit.equals("del") && length < 4) {
        	codePointage.appendText(digit);
        }else if (digit.equals("del") && length > 0){
        	codePointage.deleteText(length - 1, length);
        }
    }
    
	@Override
	public void refreshData() {
		System.out.println("Refresh login");
		codePointage.requestFocus(); // Pour ecrire direct avec le clavier sans cliquer dans le passwordfield
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		codePointage.addEventFilter(KeyEvent.KEY_TYPED, e -> {
            if (!e.getCharacter().matches("[0-9]") || codePointage.getLength() >= 4) {
                e.consume();  // Empêche l'entrée si c'est pas un chiffre
            }

            if (e.getCharacter().equals("\r")) {
                // Si la touche Entrée est pressée, appeler la méthode
            	confirmClockingIn(null);
            }
        });
		
		currentDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy | HH:mm:ss")));
    	Timeline timeUpdate = new Timeline(new KeyFrame(Duration.seconds(1), e -> currentDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy | HH:mm:ss")))));
    	timeUpdate.setCycleCount(Timeline.INDEFINITE);
    	timeUpdate.play();
		
	}
	

}
