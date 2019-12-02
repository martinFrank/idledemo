package com.github.martinfrank.idledemo.image.json;

import java.util.Arrays;

public class ImageDefinitions {

    private ImageDefinition[] imageDefinitions;

    public ImageDefinition[] getImageDefinitions() {
        return imageDefinitions;
    }

    public void setImageDefinitions(ImageDefinition[] imageDefinitions) {
        this.imageDefinitions = imageDefinitions;
    }

    public ImageDefinition getImageDefinition(String name) {
        return Arrays.stream(imageDefinitions).filter(i -> i.getImageName().equals(name)).findAny().orElse(null);
    }
}
