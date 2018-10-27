package fr.sebastien.criquet.view;

import fr.sebastien.criquet.application.Main;
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
        spinner.getValueFactory().setValue(Main.getConfig().getInt("data.ram"));
    }

    @FXML
    private void save() {
        Launcher.SPINNER_VALUE = spinner.getValue();

        args = new String[]{
            "-Xms512M", "-Xmx" + spinner.getValue() + "M"
        };

        Main.getConfig().set("data.ram", spinner.getValue());
        Main.getConfig().save();

        Stage stage = (Stage) spinner.getScene().getWindow();
        stage.close();
    }

    public static String[] getArgs() {
        if(args == null){
            args = new String[]{
                "-Xms512M", "-Xmx" + Main.getConfig().getInt("data.ram") + "M"
            };
        }
        return args;
    }

}
