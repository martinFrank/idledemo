package com.github.martinfrank.idledemo.idle;

import com.github.martinfrank.idlelib.Output;
import com.github.martinfrank.idlelib.Progress;

import java.util.HashMap;
import java.util.Map;

public class BasicOutput implements Output<Map<ResourceType, Double>> {

    private final Map<ResourceType, Double> max;
    private final boolean isUnfinishedYieldAllowed;

    public BasicOutput(Map<ResourceType, Double> max) {
        this(max, false);
    }

    public BasicOutput(Map<ResourceType, Double> max, boolean isUnfinishedYieldAllowed) {
        this.max = max;
        this.isUnfinishedYieldAllowed = isUnfinishedYieldAllowed;
    }

    @Override
    public Map<ResourceType, Double> generateOutput(Progress progress) {
        Map<ResourceType, Double> output = new HashMap<>();
        for (Map.Entry<ResourceType, Double> entry : max.entrySet()) {
            output.put(entry.getKey(), Math.min(1, progress.getPercent()) * entry.getValue());
        }
        return output;
    }
}
