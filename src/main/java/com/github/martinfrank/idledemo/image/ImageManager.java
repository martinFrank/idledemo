package com.github.martinfrank.idledemo.image;

import com.github.martinfrank.idledemo.resource.ResourceManager;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageManager {

    private final ResourceManager resourceManager;
    private Map<ImageIdentifier, Image[]> images = new HashMap<>();

    public ImageManager(ResourceManager resourceManager) throws IOException {
        this.resourceManager = resourceManager;
        splitImages();
    }

    public Image getImage(ImageIdentifier terrain, int index) {
        return images.get(terrain)[index];
    }

    public void splitImages() throws IOException {
        splitImages(ImageIdentifier.TERRAIN, 32, 32, 32, 32);
    }

    private void splitImages(ImageIdentifier identifier, int amountColumns, int amountRows, int tileWidth, int tileHeight) throws IOException {
        Image[] images = new Image[amountColumns * amountRows];
        Image srcImage = new Image(resourceManager.getImage().openStream());
        PixelReader pixelReader = srcImage.getPixelReader();
        PixelWriter pixelWriter = null;
        int index = 0;

        for (int dy = 0; dy < amountRows; dy++) {
            for (int dx = 0; dx < amountColumns; dx++) {
                WritableImage wImg = new WritableImage(tileWidth, tileHeight);
                pixelWriter = wImg.getPixelWriter();
                int yOffset = dy * tileHeight;
                for (int y = 0; y < tileHeight; y++) {
                    int xOffset = (dx * tileWidth);
                    for (int x = 0; x < tileWidth; x++) {
                        Color color = pixelReader.getColor(x + xOffset, y + yOffset);
                        pixelWriter.setColor(x, y, color);
                    }
                }
                images[index] = wImg;
                index = index + 1;
            }
        }
        this.images.put(identifier, images);
    }


    public enum ImageIdentifier {
        TERRAIN
    }
}
