package com.github.martinfrank.idledemo.idle.generator;

import com.github.martinfrank.idledemo.idle.ResourceType;
import com.github.martinfrank.idlelib.Generator;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BasicGenerator extends Generator<Map<ResourceType, Double>> {

    private final Image image;

    public BasicGenerator(Image image, Map<ResourceType, Double> output, boolean isUnfinishedYieldAllowed) {
        super(10000, TimeUnit.MILLISECONDS, new BasicOutput(output, isUnfinishedYieldAllowed));
        this.image = image;
    }

    public BasicGenerator(Image image, Map<ResourceType, Double> output) {
        this(image, output, false);
    }

    public void draw(int x, int y, Canvas canvas) {
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        if (image != null) {
            graphics.drawImage(image, x, y);
            graphics.setLineWidth(1d);
            double xProgress = x + image.getWidth() / 8;
            double yProgress = y + 7d * (image.getHeight() / 8);
            double widthProgress = 6 * (image.getWidth() / 8);
            double currentWidthProgress = Math.min(widthProgress, getProgress().getPercent() * widthProgress);
            double heightProgress = 6d;
            graphics.setFill(Color.WHITE);
            graphics.setStroke(Color.BLACK);
            graphics.fillRect(xProgress, yProgress, widthProgress, heightProgress);
            graphics.setFill(Color.GREEN);
            graphics.fillRect(xProgress, yProgress, currentWidthProgress, heightProgress);
            graphics.rect(xProgress, yProgress, widthProgress, heightProgress);
            graphics.stroke();
        }
    }
}
