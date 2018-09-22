package fr.sebastien.criquet.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private final String VERSION = "1.0";

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Edenia Launcher " + VERSION);
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/MainApplication.fxml"))));
        stage.show();
    }

}
