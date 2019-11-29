package com.github.martinfrank.idledemo.image;

import com.github.martinfrank.geolib.GeoPoint;
import com.github.martinfrank.idledemo.support.UrlSupporter;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageManager {

    private final UrlSupporter urlSupporter;
    private final Map<ImageDescription, Image[]> images = new HashMap<>();
    private final Map<String, Image> imageDefinitions = new HashMap<>();

    public ImageManager(UrlSupporter urlSupporter) throws IOException {
        this.urlSupporter = urlSupporter;
        loadTileset(ImageDescription.TERRAIN);
    }

    public Image getImage(ImageDescription tileset, int index) {
        return images.get(tileset)[index];
    }

    public Image getImage(ImageDefinition definition) {
        return imageDefinitions.computeIfAbsent(definition.getImageName(), k -> createComposite(definition));
    }

    private void loadTileset(ImageDescription tileset) throws IOException {
        Image[] images = new Image[tileset.getColumns() * tileset.getRows()];
        Image srcImage = new Image(urlSupporter.getImage(tileset).openStream());
        PixelReader pixelReader = srcImage.getPixelReader();
        PixelWriter pixelWriter;
        int index = 0;

        for (int dy = 0; dy < tileset.getRows(); dy++) {
            for (int dx = 0; dx < tileset.getColumns(); dx++) {
                WritableImage subImage = new WritableImage(tileset.getTileWidth(), tileset.getTileHeight());
                pixelWriter = subImage.getPixelWriter();
                int yOffset = dy * tileset.getTileHeight();
                for (int y = 0; y < tileset.getTileHeight(); y++) {
                    int xOffset = (dx * tileset.getTileWidth());
                    for (int x = 0; x < tileset.getTileWidth(); x++) {
                        Color color = pixelReader.getColor(x + xOffset, y + yOffset);
                        pixelWriter.setColor(x, y, color);
                    }
                }
                images[index] = subImage;
                index = index + 1;
            }
        }
        this.images.put(tileset, images);
    }

//    public Image createComposite(Map<GeoPoint, Image> composite) {//FIXME create datatype for that
//        Image partImage = composite.values().iterator().next();
//        int partImageWidth = (int) partImage.getWidth();
//        int partImageHeight = (int) partImage.getHeight();
//
//        int compositeWidth = composite.keySet().stream().mapToInt(GeoPoint::getX).max().orElse(0);
//        compositeWidth = (compositeWidth + 1) * partImageWidth;
//        int compositeHeight = composite.keySet().stream().mapToInt(GeoPoint::getY).max().orElse(0);
//        compositeHeight = (compositeHeight + 1) * partImageHeight;
//
//        WritableImage compositeImage = new WritableImage(compositeWidth, compositeHeight);
//        PixelWriter pixelWriter = compositeImage.getPixelWriter();
//        for (Map.Entry<GeoPoint, Image> entry : composite.entrySet()) {
//            GeoPoint point = entry.getKey();
//            Image entryImage = entry.getValue();
//            PixelReader pixelReader = entryImage.getPixelReader();
//            for (int dy = 0; dy < partImageHeight; dy++) {
//                for (int dx = 0; dx < partImageWidth; dx++) {
//                    int xOnComposite = point.getX() * partImageWidth + dx;
//                    int yOnComposite = point.getY() * partImageHeight + dy;
//                    Color color = pixelReader.getColor(dx, dy);
//                    pixelWriter.setColor(xOnComposite, yOnComposite, color);
//                }
//            }
//        }
//        return compositeImage;
//    }

    public Image createComposite(ImageDefinition imageDefinition) {
        ImageDescription tileset = imageDefinition.getImageDescription();

        int partImageWidth = tileset.getTileWidth();
        int partImageHeight = tileset.getTileHeight();
        ImageMapping imageMapping = imageDefinition.asMap();

        int compositeWidth = imageMapping.keySet().stream().mapToInt(GeoPoint::getX).max().orElse(0);
        compositeWidth = (compositeWidth + 1) * partImageWidth;
        int compositeHeight = imageMapping.keySet().stream().mapToInt(GeoPoint::getY).max().orElse(0);
        compositeHeight = (compositeHeight + 1) * partImageHeight;

        WritableImage compositeImage = new WritableImage(compositeWidth, compositeHeight);
        PixelWriter pixelWriter = compositeImage.getPixelWriter();
        for (ImageMapping.ImageMappingEntry entry : imageMapping.entrySet()) {
            GeoPoint point = entry.getKey();
            Image entryImage = getImage(tileset, entry.getValue());
            PixelReader subImagePixelReader = entryImage.getPixelReader();
            for (int dy = 0; dy < partImageHeight; dy++) {
                for (int dx = 0; dx < partImageWidth; dx++) {
                    int xOnComposite = point.getX() * partImageWidth + dx;
                    int yOnComposite = point.getY() * partImageHeight + dy;
                    Color colorNew = subImagePixelReader.getColor(dx, dy);
                    //adding to existing image
                    if (colorNew.isOpaque()) {
                        pixelWriter.setColor(xOnComposite, yOnComposite, colorNew);
                    }
                }
            }
        }
        return compositeImage;
//        return null;
    }
}
