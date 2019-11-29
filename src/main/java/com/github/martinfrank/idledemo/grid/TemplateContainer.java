package com.github.martinfrank.idledemo.grid;

import com.github.martinfrank.geolib.GeoPoint;
import com.github.martinfrank.gridinventory.Shape;
import com.github.martinfrank.idledemo.idle.template.GeneratorTemplate;
import javafx.scene.canvas.Canvas;

import java.util.Map;

public class TemplateContainer extends CanvasGridContainer<GeneratorTemplate> {

    public TemplateContainer(GridSize gridSize, Canvas canvas) {
        super(gridSize, canvas);
    }

    @Override
    public void drawContent() {
        for (Map.Entry<GeoPoint, Shape<GeneratorTemplate>> entry : getContent().entrySet()) {
            GeoPoint location = entry.getKey();
            entry.getValue().getItem().draw(location.getX() * gridSize.getGridWidth(),
                    location.getY() * gridSize.getGridHeight(), canvas);
        }
    }

}
