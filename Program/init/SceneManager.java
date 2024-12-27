package init;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import utilClass.ScenesMap;

import java.io.IOException;
import java.util.HashMap;

public class SceneManager {
	private static final HashMap<ScenesMap, Object> controllers = new HashMap<>();
    private static final HashMap<ScenesMap, Scene> scenes = new HashMap<>();

    public static void addScene(ScenesMap sceneName) throws IOException {
        FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(sceneName.getFXML()));
        Pane pane = loader.load();
        Object controller = loader.getController();
        Scene scene = new Scene(pane);
        controllers.put(sceneName, controller);
        scenes.put(sceneName, scene);
    }
    
    public static void removeScene() {
    	scenes.clear();
    }
    
    public static void removeScene(ScenesMap sceneName) {
    	scenes.remove(sceneName);
    }

    public static Scene getScene(ScenesMap sceneName) {
        return scenes.get(sceneName);
    }
    
    public static void getScenes() {
    	System.out.println(scenes);
    }
    
    public static Object getController(ScenesMap sceneName) {
        return controllers.get(sceneName);
    }
}