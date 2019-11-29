package com.github.martinfrank.idledemo.idle;

import com.github.martinfrank.idledemo.idle.generator.GeneratorFactory;
import com.github.martinfrank.idlelib.GeneratorListener;
import com.github.martinfrank.idlelib.Timer;

public class IdleManager {

    private final GeneratorFactory factory;
    private final GeneratorListener listener;
    private final Timer timer;

    public IdleManager() {
        timer = new Timer();
        listener = new MyGeneratorListener();
        factory = new GeneratorFactory(timer, listener);
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
        if (timer != null) {
            timer.stop();
        }
    }
}
