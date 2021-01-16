package ro.mta.se.lab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ro.mta.se.lab.constants.ApplicationConstants;
import ro.mta.se.lab.controller.WeatherController;

import java.io.IOException;

/**
 * JavaFX Main
 */
public class Main extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML(ApplicationConstants.FXML_FILENAME), 640, 480);
        stage.setScene(scene);
        stage.show();

    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                Main.class.getResource(fxml + ApplicationConstants.FXML_EXTENSION));
        Parent loaded = fxmlLoader.load();

        WeatherController weatherController = fxmlLoader.getController();
        weatherController.loadInitialData();
        return loaded;

    }

    public static void main(String[] args) {
        launch();
    }

}