package com.github.martinfrank.idledemo.grid;

import com.github.martinfrank.geolib.GeoPoint;
import com.github.martinfrank.gridinventory.BasicGridShape;

import java.util.List;

public class CanvasGridShape extends BasicGridShape<CanvasGridItem> {

    public CanvasGridShape(List<GeoPoint> shape) {
        super(shape);
    }
}
