package init;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import utilClass.ScenesMap;
import utilClass.UDPMultiCastApp;
import javafx.scene.Scene;


public class Main extends Application {
	
	@Override
	public void start(Stage Stage) throws IOException {
		SceneManager.addScene(ScenesMap.INIT);
		try {
			Scene scene = SceneManager.getScene(ScenesMap.INIT);
			
			Stage.setScene(scene);
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
