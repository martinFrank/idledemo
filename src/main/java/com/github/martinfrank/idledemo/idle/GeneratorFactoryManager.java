package com.github.martinfrank.idledemo.idle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.martinfrank.idledemo.idle.json.GeneratorDefinition;
import com.github.martinfrank.idledemo.idle.json.GeneratorDefinitions;
import com.github.martinfrank.idledemo.support.UrlProvider;

import java.io.IOException;
import java.net.URL;

public class GeneratorFactoryManager {

    private GeneratorDefinitions generators;

    public GeneratorFactoryManager(UrlProvider urlProvider) throws IOException {
        loadGeneratorDefinitions(urlProvider.getFactoryDescription());
    }

    private void loadGeneratorDefinitions(URL url) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        generators = objectMapper.readValue(url, GeneratorDefinitions.class);
    }

    public GeneratorDefinition getGeneratorDefinition(String name) {
        return generators.getGeneratorDefinition(name);
    }

}
