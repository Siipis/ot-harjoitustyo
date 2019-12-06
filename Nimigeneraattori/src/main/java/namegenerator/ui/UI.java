package namegenerator.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * UI initializer.
 */
public class UI extends Application {
    @Override
    public void init() throws Exception {
        super.init();
    }

    /**
     * Renders the UI view.
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("Name Generator");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Launches the application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
