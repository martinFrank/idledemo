package com.github.martinfrank.idledemo.idle;

import com.github.martinfrank.idlelib.GeneratorListener;
import com.github.martinfrank.idlelib.Timer;
import javafx.scene.image.Image;

public class GeneratorFactory {

    private final GeneratorListener listener;
    private final Timer timer;

    public GeneratorFactory(Timer timer, GeneratorListener generatorListener) {
        this.timer = timer;
        this.listener = generatorListener;
    }

    public TimberGenerator generate(GeneratorId id, Image image) {
        TimberGenerator timberGenerator = new TimberGenerator(image);
        timer.register(timberGenerator);
        timberGenerator.setGeneratorListener(listener);
        return timberGenerator;
    }

    public enum GeneratorId {SMALL, MEDIUM}


}
