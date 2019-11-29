package com.github.martinfrank.idledemo.idle.generator;

import com.github.martinfrank.idledemo.idle.ResourceType;
import com.github.martinfrank.idlelib.GeneratorListener;
import com.github.martinfrank.idlelib.Timer;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class GeneratorFactory {

    private final GeneratorListener listener;
    private final Timer timer;

    public GeneratorFactory(Timer timer, GeneratorListener generatorListener) {
        this.timer = timer;
        this.listener = generatorListener;
    }

    public BasicGenerator generate(GeneratorId id, Image image) {
        Map<ResourceType, Double> output = createOutput(id);
        BasicGenerator basicGenerator = new BasicGenerator(image, output);
        timer.register(basicGenerator);
        basicGenerator.setGeneratorListener(listener);
        return basicGenerator;
    }

    private Map<ResourceType, Double> createOutput(GeneratorId id) {
        if (id == GeneratorId.SMALL) {
            HashMap<ResourceType, Double> hashMap = new HashMap<>();
            hashMap.put(ResourceType.TIMBER, 10d);
            return hashMap;
        }
        //FIXME
        return null;
    }

    public enum GeneratorId {SMALL}


}
