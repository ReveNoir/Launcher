package fr.sebastien.criquet.application;

import com.electronwill.nightconfig.core.file.FileConfig;
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

import javax.inject.Inject;


public class Main extends Application {

    @Inject private static FileConfig conf;

    public final static Logger logger = LoggerFactory.getLogger("EdeniaCraft");

    public static void main(String[] args) {
        Main.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        conf = FileConfig.of("config.toml");
        conf.load();

        if(getConfig().get("data.ram") == null) {
            conf.set("data.ram", 1024);
            conf.save();
        }

        PropertyConfigurator.configure(getClass().getResourceAsStream("/log4j/log4j.properties"));
        Stub.initData();

        Platform.setImplicitExit(false);

        stage.setTitle("EdeniaCraft Launcher");
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/MainApplication.fxml"))));

        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/text/logo.png")));

        stage.setOnCloseRequest(__ -> {
            Platform.exit();
            System.exit(0);
            getConfig().close();
        });

        stage.resizableProperty().setValue(false);
        stage.show();
    }

    public static FileConfig getConfig() {
        return conf;
    }

}
