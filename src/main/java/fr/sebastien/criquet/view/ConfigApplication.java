package fr.sebastien.criquet.view;

import fr.sebastien.criquet.model.launcher.Launcher;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

public class ConfigApplication {

    @FXML private Spinner<Integer> spinner;
    private static String[] args;

    public void initialize() {
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1024, 16381, 1024, 1024));
    }

    @FXML
    private void save() {
        Launcher.SPINNER_VALUE = spinner.getValue();

        args = new String[]{
            "-Xms512M", "-Xmx" + spinner.getValue() + "M"
        };

        Stage stage = (Stage) spinner.getScene().getWindow();
        stage.close();
    }

    public static String[] getArgs() {
        return args;
    }

}
