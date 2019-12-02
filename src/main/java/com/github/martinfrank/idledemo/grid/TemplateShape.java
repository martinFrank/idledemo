package com.github.martinfrank.idledemo.grid;

import com.github.martinfrank.geolib.GeoPoint;
import com.github.martinfrank.gridinventory.BasicShape;
import com.github.martinfrank.idledemo.idle.Template;

import java.util.List;

public class TemplateShape extends BasicShape<Template> {

    public TemplateShape(List<GeoPoint> shape) {
        super(shape);
    }
}
