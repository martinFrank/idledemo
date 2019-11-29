package com.github.martinfrank.idledemo.idle.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.martinfrank.idledemo.support.UrlSupporter;

import java.io.IOException;
import java.net.URL;

public class GeneratorFactoryManager {

    private GeneratorDefinitions generators;

    public GeneratorFactoryManager(UrlSupporter urlSupporter) throws IOException {
        loadGeneratorDefinitions(urlSupporter.getFactoryDescription());
    }

    private void loadGeneratorDefinitions(URL url) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        generators = objectMapper.readValue(url, GeneratorDefinitions.class);
    }

    public GeneratorDefinition getGeneratorDefinition(String name) {
        return generators.getGeneratorDefinition(name);
    }

}
