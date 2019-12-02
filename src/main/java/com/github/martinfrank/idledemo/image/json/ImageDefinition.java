package com.github.martinfrank.idledemo.image.json;

import com.github.martinfrank.idledemo.image.TilesetImageDescription;

public class ImageDefinition {

    private String imageName;
    private String tileset;
    private SubImage[] subImage;
    private ImageMapping imageMapping;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public SubImage[] getSubImage() {
        return subImage;
    }

    public void setSubImage(SubImage[] subImage) {
        this.subImage = subImage;
    }

    public String getTileset() {
        return tileset;
    }

    public void setTileset(String tileset) {
        this.tileset = tileset;
    }

    public ImageMapping asMap() {
        if (imageMapping == null) {
            imageMapping = new ImageMapping();
            for (SubImage subImage : subImage) {
                imageMapping.add(subImage.getLocation(), subImage.getImageId());
            }
        }
        return imageMapping;
    }

    public TilesetImageDescription getImageDescription() {
        return TilesetImageDescription.valueOf(tileset);
    }
}
