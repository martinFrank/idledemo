package com.github.martinfrank.idledemo.idle;

import com.github.martinfrank.idlelib.Generator;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

import java.util.concurrent.TimeUnit;

public class TimberGenerator extends Generator<Double> {

    private final Image image;

    public TimberGenerator(Image image) {
        super(1000, TimeUnit.MILLISECONDS, new TimberOutput());
        this.image = image;
    }

    public void draw(int x, int y, Canvas canvas) {
        if (image != null) {
            canvas.getGraphicsContext2D().drawImage(image, x, y);
        }
    }
}
