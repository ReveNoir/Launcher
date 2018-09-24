package fr.sebastien.criquet.view;

import fr.sebastien.criquet.application.Main;
import fr.sebastien.criquet.data.Stub;
import fr.sebastien.criquet.exception.ImageNotFoundException;
import fr.sebastien.criquet.model.Images;
import fr.sebastien.criquet.runnable.ImageRunnable;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class MainApplication {

    private Images img;

    @FXML private BorderPane background;

    @FXML private ProgressBar progress;
    @FXML private TextField pseudo;
    @FXML private ImageView images;

    @FXML
    private void initialize() {
        img = Stub.IMAGES;

        new Thread(new ImageRunnable(img)).start();

        img.indexProperty().addListener((obs, oldV, newV) -> {
            try {
                BackgroundImage bg = new BackgroundImage(new Image(img.get(newV.intValue()).getRessource(), background.getWidth(),background.getHeight(),true,true),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);

                background.setBackground(new Background(bg));
            } catch (ImageNotFoundException e) {
                Main.logger.error(e.getMessage());
            }
        });
    }

    @FXML
    private void play() {

    }

}
