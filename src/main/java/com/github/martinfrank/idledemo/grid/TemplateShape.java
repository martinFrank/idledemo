package com.github.martinfrank.idledemo.grid;

import com.github.martinfrank.geolib.GeoPoint;
import com.github.martinfrank.gridinventory.BasicShape;
import com.github.martinfrank.idledemo.idle.template.GeneratorTemplate;

import java.util.List;

public class TemplateShape extends BasicShape<GeneratorTemplate> {

    public TemplateShape(List<GeoPoint> shape) {
        super(shape);
    }
}
