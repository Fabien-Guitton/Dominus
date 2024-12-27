package init;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import utilClass.ScenesMap;

public class InitController implements Initializable{
	@FXML
	private Label currentDate;
	@FXML
	private HBox loadBar;
	
	@FXML
	private void selectApp(ActionEvent event) {
		loadBar.setVisible(true);
		Node clickedApp = (Node) event.getTarget();
		String app = clickedApp.getId();
		try {
			SceneManager.addScene(Enum.valueOf(ScenesMap.class, app.toUpperCase()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		SceneManager.removeScene(ScenesMap.INIT);
		
		Scene selectedApp = SceneManager.getScene(Enum.valueOf(ScenesMap.class, app.toUpperCase()));
		if (selectedApp != null) {
    		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(selectedApp);
            stage.show();
    	}
	}
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
    	System.out.println("Init Application");
    	currentDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy | HH:mm:ss")));
    	Timeline timeUpdate = new Timeline(new KeyFrame(Duration.seconds(1), e -> currentDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy | HH:mm:ss")))));
    	timeUpdate.setCycleCount(Timeline.INDEFINITE);
    	timeUpdate.play();
    }   
}
