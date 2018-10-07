package fr.sebastien.criquet.thread;

import fr.theshark34.supdate.BarAPI;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class ProgressbarThread extends Thread {

    private int val;
    private int max;

    private ProgressBar progress;
    private Label indicator;

    public ProgressbarThread(ProgressBar progress, Label indicator) {
        this.progress = progress;
        this.indicator = indicator;
    }

    @Override
    public void run() {
        while(!isInterrupted()) {

            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            if(BarAPI.getNumberOfFileToDownload() == 0){
                Platform.runLater(() -> indicator.setText("Verifying files ..."));
            }

            val = (int) (BarAPI.getNumberOfTotalDownloadedBytes() / 1024);
            max = (int) (BarAPI.getNumberOfTotalBytesToDownload() / 1024);

            double percent = Math.floor((double) val / max * 1000) / 10;

            Platform.runLater(() -> {
                indicator.setText("Download " + BarAPI.getNumberOfDownloadedFiles() + " / " + BarAPI.getNumberOfFileToDownload());
                progress.setProgress(percent / 100);
            });
        }
    }

}
