package fr.sebastien.criquet.data;

import fr.sebastien.criquet.model.images.Image;
import fr.sebastien.criquet.model.images.Images;

public class Stub {

    public static final Images IMAGES = new Images();
    public static Image header = new Image().setImage("/images/text/header.png");

    public static void initData() {
        for(int i = 1; i <= 24; i++){
            IMAGES.addImage(i + ".png");
        }
    }

}
