package init;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.util.HashMap;

public class SceneManager {
	private static final HashMap<String, Object> controllers = new HashMap<>();
    private static final HashMap<String, Scene> scenes = new HashMap<>();

    public static void addScene(String name, String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlPath));
        Pane pane = loader.load();
        Object controller = loader.getController();
        Scene scene = new Scene(pane);
        controllers.put(name, controller);
        scenes.put(name, scene);
    }
    
    public static void removeScene() {
    	scenes.clear();
    }
    
    public static void removeScene(String name) {
    	scenes.remove(name);
    }

    public static Scene getScene(String name) {
        return scenes.get(name);
    }
    
    public static Object getController(String name) {
        return controllers.get(name);
    }
}