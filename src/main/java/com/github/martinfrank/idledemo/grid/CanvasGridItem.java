package com.github.martinfrank.idledemo.grid;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class CanvasGridItem {

    private final Image image;

    public CanvasGridItem(Image image) {
        this.image = image;
    }

    public void draw(int x, int y, Canvas canvas) {
        if (image != null) {
            canvas.getGraphicsContext2D().drawImage(image, x, y);
        }
    }

    public Image getImage() {
        return image;
    }
}
