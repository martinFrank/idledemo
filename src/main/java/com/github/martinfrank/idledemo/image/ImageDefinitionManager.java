package com.github.martinfrank.idledemo.image;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.martinfrank.idledemo.support.UrlSupporter;

import java.io.IOException;
import java.net.URL;

public class ImageDefinitionManager {

    private ImageDefinitions images;

    public ImageDefinitionManager(UrlSupporter urlSupporter) throws IOException {
        loadImageDefinitions(urlSupporter.getImageDescription());
    }

    private void loadImageDefinitions(URL url) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        images = objectMapper.readValue(url, ImageDefinitions.class);
    }

    public ImageDefinition getImageDefinition(String name) {
        return images.getImageDefinition(name);
    }
}
