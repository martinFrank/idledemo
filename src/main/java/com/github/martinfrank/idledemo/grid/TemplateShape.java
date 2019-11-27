package com.github.martinfrank.idledemo.grid;

import com.github.martinfrank.geolib.GeoPoint;
import com.github.martinfrank.gridinventory.BasicGridShape;

import java.util.List;

public class TemplateShape extends BasicGridShape<GeneratorTemplate> {

    public TemplateShape(List<GeoPoint> shape) {
        super(shape);
    }
}
