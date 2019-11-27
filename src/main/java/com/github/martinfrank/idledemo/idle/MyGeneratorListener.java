package com.github.martinfrank.idledemo.idle;

import com.github.martinfrank.idlelib.Generator;
import com.github.martinfrank.idlelib.GeneratorListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MyGeneratorListener implements GeneratorListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyGeneratorListener.class);

    @Override
    public void notifyYield(Generator generator) {
        LOGGER.debug("i'm ready to yield... ");
        generator.yield();
    }
}
