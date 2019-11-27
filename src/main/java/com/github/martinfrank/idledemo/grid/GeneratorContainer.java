package com.github.martinfrank.idledemo.grid;

import com.github.martinfrank.geolib.GeoPoint;
import com.github.martinfrank.gridinventory.GridShape;
import com.github.martinfrank.idledemo.idle.TimberGenerator;
import javafx.scene.canvas.Canvas;

public class GeneratorContainer extends CanvasGridContainer<TimberGenerator> {

    public GeneratorContainer(GridSize gridSize, Canvas canvas) {
        super(gridSize, canvas);
    }

    @Override
    public void add(GridShape<TimberGenerator> shape, GeoPoint location) {
        super.add(shape, location);
        shape.getItem().draw(location.getX() * gridSize.getGridWidth(), location.getY() * gridSize.getGidHeight(), canvas);
    }

}
