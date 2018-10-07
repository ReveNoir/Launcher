package fr.sebastien.criquet.view;

import fr.sebastien.criquet.application.Main;
import fr.sebastien.criquet.data.Stub;
import fr.sebastien.criquet.exception.ImageNotFoundException;
import fr.sebastien.criquet.model.images.Images;
import fr.sebastien.criquet.model.launcher.Launcher;
import fr.sebastien.criquet.runnable.ImageRunnable;
import fr.sebastien.criquet.thread.LauncherThread;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MainApplication {

    private Images img;
    private Launcher launcher;
    private Saver saver;

    @FXML private BorderPane background;

    @FXML private ProgressBar progress;
    @FXML private TextField pseudo;
    @FXML private Label indicator;

    public static int SPINNER_VALUE = 1024;

    @FXML
    private void initialize() {
        img = Stub.IMAGES;

        launcher = new Launcher(this);
        saver = new Saver(new File(launcher.getDir(), "config.json"));

        new Thread(new ImageRunnable(img)).start();

        img.indexProperty().addListener((obs, oldV, newV) -> {
            try {
                BackgroundImage bg = new BackgroundImage(new Image(img.get(newV.intValue()).getRessource(), background.getWidth(), background.getHeight(),false,true),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);

                background.setBackground(new Background(bg));
            } catch (ImageNotFoundException e) {
                Main.logger.error(e.getMessage());
            }
        });

        pseudo.setText(saver.get("username"));
    }

    @FXML
    private void play() {
        if(pseudo.getText().trim().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Pseudo error");
            alert.setHeaderText(null);
            alert.show();
        }

        Thread launch = new LauncherThread(launcher, pseudo.getText(), saver);
        launch.start();

    }

    @FXML
    private void config() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/ConfigApplication.fxml")));
        stage.setScene(scene);
        stage.setTitle("Configuration");
        stage.showAndWait();
    }

    public ProgressBar getProgress() {
        return progress;
    }

    public Label getIndicator() {
        return indicator;
    }

}
