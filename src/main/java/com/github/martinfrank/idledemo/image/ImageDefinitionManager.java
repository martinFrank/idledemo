package com.github.martinfrank.idledemo.image;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.martinfrank.idledemo.image.json.ImageDefinition;
import com.github.martinfrank.idledemo.image.json.ImageDefinitions;
import com.github.martinfrank.idledemo.support.UrlProvider;

import java.io.IOException;
import java.net.URL;

public class ImageDefinitionManager {

    private ImageDefinitions images;

    public ImageDefinitionManager(UrlProvider urlProvider) throws IOException {
        loadImageDefinitions(urlProvider.getImageDescription());
    }

    private void loadImageDefinitions(URL url) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        images = objectMapper.readValue(url, ImageDefinitions.class);
    }

    public ImageDefinition getImageDefinition(String name) {
        return images.getImageDefinition(name);
    }
}
