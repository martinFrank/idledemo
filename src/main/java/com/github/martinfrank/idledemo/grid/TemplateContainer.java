package com.github.martinfrank.idledemo.grid;

import com.github.martinfrank.geolib.GeoPoint;
import com.github.martinfrank.gridinventory.GridShape;
import javafx.scene.canvas.Canvas;

public class TemplateContainer extends CanvasGridContainer<GeneratorTemplate> {

    public TemplateContainer(GridSize gridSize, Canvas canvas) {
        super(gridSize, canvas);
    }

    @Override
    public void add(GridShape<GeneratorTemplate> shape, GeoPoint location) {
        super.add(shape, location);
        shape.getItem().draw(location.getX() * gridSize.getGridWidth(), location.getY() * gridSize.getGidHeight(), canvas);
    }

}
