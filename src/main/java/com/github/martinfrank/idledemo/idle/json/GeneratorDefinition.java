package com.github.martinfrank.idledemo.idle.json;

import com.github.martinfrank.geolib.GeoPoint;

public class GeneratorDefinition {

    private String generatorName;
    private GeoPoint[] shape;

    public GeoPoint[] getShape() {
        return shape;
    }

    public void setShape(GeoPoint[] shape) {
        this.shape = shape;
    }

    public String getGeneratorName() {
        return generatorName;
    }

    public void setGeneratorName(String generatorName) {
        this.generatorName = generatorName;
    }
}
