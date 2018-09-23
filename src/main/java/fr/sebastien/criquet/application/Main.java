package fr.sebastien.criquet.application;

import fr.sebastien.criquet.data.Stub;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main extends Application {

    private final String VERSION = "1.0";
    public final static Logger logger = LoggerFactory.getLogger("EdeniaCraft");

    @Override
    public void start(Stage stage) throws Exception {
        PropertyConfigurator.configure(getClass().getResource("/log4j/log4j.properties").getPath());
        Stub.initData();

        stage.setTitle("Edenia Launcher " + VERSION);
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/MainApplication.fxml"))));

        stage.setOnCloseRequest(__ -> {
            Platform.exit();
            System.exit(0);
        });

        stage.resizableProperty().setValue(false);
        stage.show();
    }

}
