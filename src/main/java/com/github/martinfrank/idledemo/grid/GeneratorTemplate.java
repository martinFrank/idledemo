package com.github.martinfrank.idledemo.grid;

import com.github.martinfrank.idledemo.idle.GeneratorFactory;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class GeneratorTemplate {

    private final Image image;
    private final GeneratorFactory.GeneratorId generatorId;

    public GeneratorTemplate(Image image) {
        this.image = image;
        generatorId = GeneratorFactory.GeneratorId.SMALL;
    }

    public void draw(int x, int y, Canvas canvas) {
        if (image != null) {
            canvas.getGraphicsContext2D().drawImage(image, x, y);
        }
    }

    public Image getImage() {
        return image;
    }

    public GeneratorFactory.GeneratorId getTemplateId() {
        return generatorId;
    }
}
