package fr.sebastien.criquet.runnable;

import fr.sebastien.criquet.model.Images;

import java.util.Timer;
import java.util.TimerTask;

public class ImageRunnable implements Runnable {

    private Images images;

    public ImageRunnable(Images img) {
        images = img;
    }

    @Override
    public void run() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                images.newIndex(random(1, 24));
            }
        }, 0, (60*1000));

        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
    }

    private int random(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

}
