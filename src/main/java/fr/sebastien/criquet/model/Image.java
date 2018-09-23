package fr.sebastien.criquet.model;

import fr.sebastien.criquet.application.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Image {

    private String folder = getClass().getResource("/images/background/").toString();
    private StringProperty ressource = new SimpleStringProperty();
    public String getRessource() { return ressource.get(); }
    public StringProperty ressourceProperty() { return ressource; }
    private void setRessource(String ressource) { this.ressource.set(ressource); }

    public Image(String name) {
        setRessource(getFolder() + name);
    }
    public Image() {}

    public Image setImage(String path) {
        setRessource(path);
        Main.logger.debug("Load image with path: {}", path);
        return this;
    }

    public String getFolder() { return folder; }

}
