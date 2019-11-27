package com.github.martinfrank.idledemo.idle;

import com.github.martinfrank.idlelib.Output;
import com.github.martinfrank.idlelib.Progress;

public class TimberOutput implements Output<Double> {

    public static final double MAX = 1000;

    @Override
    public Double generateOutput(Progress progress) {
        return Math.min(1, progress.getPercent()) * MAX;
    }
}
