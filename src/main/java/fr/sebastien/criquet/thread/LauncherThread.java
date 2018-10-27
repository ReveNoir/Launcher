package fr.sebastien.criquet.thread;

import fr.sebastien.criquet.application.Main;
import fr.sebastien.criquet.model.launcher.Launcher;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.application.Platform;
import javafx.scene.control.Alert;

public class LauncherThread extends Thread {

    private Launcher launcher;
    private String username;
    private Saver saver;

    public LauncherThread(Launcher launcher, String username, Saver saver) {
        this.launcher = launcher;
        this.username = username;
        this.saver = saver;
    }

    @Override
    public void run() {
        try {
            launcher.auth(username);
            launcher.update();
            launcher.launch();
            saver.set("username", username);
            saver.save();
        } catch (Exception e) {
            e.printStackTrace();
            Main.logger.error(e.getMessage());
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.setHeaderText(null);
                alert.show();
            });


        }
    }

}
