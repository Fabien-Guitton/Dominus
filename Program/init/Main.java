package init;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import utilClass.ScenesMap;
import utilClass.UDPMultiCastApp;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {
	
	@Override
	public void start(Stage Stage) throws IOException {
		SceneManager.addScene(ScenesMap.INIT);
		try {
			Scene scene = SceneManager.getScene(ScenesMap.INIT);
			
			Stage.setScene(scene);
			
			   // Ajouter un logo (icône) à la fenêtre
	        Image logo = new Image("file:src/images/logo.png"); // Chemin vers l'image
	        Stage.getIcons().add(logo);
			Stage.setTitle("Dominus");
			//Stage.setFullScreen(true);
			
			Stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		UDPMultiCastApp.joinCanal();
		launch(args);
	}
}
