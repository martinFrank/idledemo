package com.github.martinfrank.idledemo.grid;

import com.github.martinfrank.geolib.GeoPoint;
import com.github.martinfrank.gridinventory.BasicShape;
import com.github.martinfrank.idledemo.idle.generator.BasicGenerator;

import java.util.List;

public class GeneratorShape extends BasicShape<BasicGenerator> {

    public GeneratorShape(List<GeoPoint> shape) {
        super(shape);
    }

}
