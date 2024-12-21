package applications.menu;
	
import java.io.IOException;

import init.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class Main extends Application {
	
	public void initializeScenes() throws IOException {
		// Scenes voisines pré-charger pour éviter le de temps de chargement du fxml
	    // SceneManager.addScene("historical", "/applications/historical/historical/historicalHistorical.fxml");
	    // SceneManager.addScene("payment", "/applications/historical/payment/historicalPayment.fxml");
	    SceneManager.addScene("menu", "/applications/menu/menu.fxml");
	}
	
	@Override
	public void start(Stage Stage) throws IOException {
		initializeScenes();
		String cssFile = "menu.css";
		try {
			Scene scene = SceneManager.getScene("menu");
			
			String css = this.getClass().getResource(cssFile).toExternalForm();
			scene.getStylesheets().add(css);
			
			Stage.setScene(scene);
			Stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
