package fr.sebastien.criquet.thread;

import fr.theshark34.supdate.BarAPI;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class SupdateThread extends Thread {

    private int val;
    private int max;

    private ProgressBar progress;
    private Label indicator;

    public SupdateThread(ProgressBar progress, Label indicator) {
        this.progress = progress;
        this.indicator = indicator;
    }

    @Override
    public void run() {
        while(isInterrupted()) {
            if(BarAPI.getNumberOfFileToDownload() == 0){
                indicator.setText("Verifying files ...");
                continue;
            }
            val = (int) (BarAPI.getNumberOfTotalDownloadedBytes() / 1000);
            max = (int) (BarAPI.getNumberOfTotalBytesToDownload() / 1000);

            progress.setProgress(val);
            progress.setMaxWidth(max);

            indicator.setText("Download " + BarAPI.getNumberOfDownloadedFiles() + " / " + BarAPI.getNumberOfFileToDownload());

        }
    }

}
