package fr.sebastien.criquet.model.images;

import fr.sebastien.criquet.application.Main;
import fr.sebastien.criquet.exception.ImageNotFoundException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;

public class Images {

    private List<Image> images = new ArrayList<>();

    private IntegerProperty index = new SimpleIntegerProperty(0);
    public int getIndex() { return index.get(); }
    public IntegerProperty indexProperty() { return index; }
    private void setIndex(int index) { this.index.set(index); }

    public void addImage(String name) {
        Main.logger.debug("Load image with name: {}", name);
        images.add(new Image(name));
    }

    public void previous() {
        if(getIndex() - 1 > 0){
            setIndex(getIndex() - 1);
        }
    }

    public void next() {
        if(getIndex() + 1 < images.size()){
            setIndex(getIndex() + 1);
        } else {
            reset();
        }
    }

    public void newIndex(int index) {
        setIndex(index);
    }

    private void reset() {
        setIndex(0);
    }

    public Image get(int index) throws ImageNotFoundException {
        if(images.get(index) != null) {
            return images.get(index);
        }
        throw new ImageNotFoundException("This image (" + index + ") was not found.");
    }

}
