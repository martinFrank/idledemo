package com.github.martinfrank.idledemo.idle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.martinfrank.idledemo.idle.json.GeneratorDefinition;
import com.github.martinfrank.idledemo.idle.json.GeneratorDefinitions;
import com.github.martinfrank.idledemo.support.UrlProvider;
import com.github.martinfrank.idlelib.GeneratorListener;
import com.github.martinfrank.idlelib.Timer;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GeneratorFactory {

    private final GeneratorListener listener;
    private final Timer timer;
    private GeneratorDefinitions generators;

    public GeneratorFactory(Timer timer, GeneratorListener generatorListener, UrlProvider urlProvider) throws IOException {
        this.timer = timer;
        this.listener = generatorListener;
        loadGeneratorDefinitions(urlProvider.getFactoryDescription());
    }

    public BasicGenerator generate(Template.TemplateId id, Image image) {
        Map<ResourceType, Double> output = createOutput(id);
        BasicGenerator basicGenerator = new BasicGenerator(image, output);
        timer.register(basicGenerator);
        basicGenerator.setGeneratorListener(listener);
        return basicGenerator;
    }

    private Map<ResourceType, Double> createOutput(Template.TemplateId id) {
        if (id == Template.TemplateId.SMALL) {
            HashMap<ResourceType, Double> hashMap = new HashMap<>();
            hashMap.put(ResourceType.TIMBER, 10d);
            return hashMap;
        }
        //FIXME
        return null;
    }

//    public enum GeneratorId {SMALL}

    private void loadGeneratorDefinitions(URL url) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        generators = objectMapper.readValue(url, GeneratorDefinitions.class);
    }

    public GeneratorDefinition getGeneratorDefinition(String name) {
        return generators.getGeneratorDefinition(name);
    }


}
