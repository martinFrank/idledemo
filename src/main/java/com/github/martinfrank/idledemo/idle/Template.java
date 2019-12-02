package com.github.martinfrank.idledemo.idle;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class Template {

    private final Image image;
    private final TemplateId templateId;

    public Template(Image image) {
        this.image = image;
        templateId = TemplateId.SMALL;
    }

    public void draw(int x, int y, Canvas canvas) {
        if (image != null) {
            canvas.getGraphicsContext2D().drawImage(image, x, y);
        }
    }

    public Image getImage() {
        return image;
    }

    public TemplateId getTemplateId() {
        return templateId;
    }

    public enum TemplateId {SMALL}
}
