package com.github.martinfrank.idledemo.grid;

import com.github.martinfrank.geolib.GeoPoint;
import com.github.martinfrank.gridinventory.BasicGridShape;
import com.github.martinfrank.idledemo.idle.TimberGenerator;

import java.util.List;

public class GeneratorShape extends BasicGridShape<TimberGenerator> {

    public GeneratorShape(List<GeoPoint> shape) {
        super(shape);
    }

}
