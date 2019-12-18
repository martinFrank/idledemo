package com.github.martinfrank.idledemo.idle;

import com.github.martinfrank.idledemo.support.UrlProvider;
import com.github.martinfrank.idlelib.Generator;
import com.github.martinfrank.idlelib.GeneratorListener;
import com.github.martinfrank.idlelib.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class IdleManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(IdleManager.class);

    private final GeneratorFactory factory;
    private final GeneratorListener listener;
    private final Timer timer;


    public IdleManager(UrlProvider urlProvider) throws IOException {
        timer = new Timer();
        listener = new IdleManagerGeneratorListener();
        factory = new GeneratorFactory(timer, listener, urlProvider);
    }

    public GeneratorFactory getFactory() {
        return factory;
    }

    public GeneratorListener getListener() {
        return listener;
    }

    public Timer getTimer() {
        return timer;
    }

    public void stop() {
        timer.stop();
    }

    public static class IdleManagerGeneratorListener implements GeneratorListener {

        @Override
        public void notifyYield(Generator generator) {
            LOGGER.debug("i'm ready to yield... ");
        }
    }
}
