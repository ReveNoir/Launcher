package fr.sebastien.criquet.application;

import fr.sebastien.criquet.data.Stub;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main extends Application {

    public final static Logger logger = LoggerFactory.getLogger("EdeniaCraft");

    @Override
    public void start(Stage stage) throws Exception {
        PropertyConfigurator.configure(getClass().getResourceAsStream("/log4j/log4j.properties"));
        Stub.initData();

        Platform.setImplicitExit(false);

        stage.setTitle("EdeniaCraft Launcher");
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/MainApplication.fxml"))));

        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/text/logo.png")));

        stage.setOnCloseRequest(__ -> {
            Platform.exit();
            System.exit(0);
        });

        stage.resizableProperty().setValue(false);
        stage.show();
    }

}
