package com.github.martinfrank.idledemo.grid;

import com.github.martinfrank.gridinventory.RectangleContainer;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class CanvasGridContainer<R> extends RectangleContainer<R> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateContainer.class);
    public final Canvas canvas;

    public final GridSize gridSize;

    public CanvasGridContainer(GridSize gridSize, Canvas canvas) {

        super(gridSize.getRows(), gridSize.getColumns());
        this.canvas = canvas;
        this.gridSize = gridSize;

        Random random = new Random();

        canvas.getGraphicsContext2D().setFill(Color.YELLOW);
        canvas.getGraphicsContext2D().setStroke(Color.BLACK);
        canvas.getGraphicsContext2D().setLineWidth(2d);

        int gridWidth = gridSize.getGridWidth();
        int gridHeight = gridSize.getGridWidth();
        for (int dy = 0; dy < gridSize.getColumns(); dy++) {
            for (int dx = 0; dx < gridSize.getRows(); dx++) {
                canvas.getGraphicsContext2D().setFill(new Color(random.nextDouble(), random.nextDouble(), random.nextDouble(), 1));
                canvas.getGraphicsContext2D().fillRect(dx * gridWidth, dy * gridHeight, gridWidth, gridHeight);
                canvas.getGraphicsContext2D().rect(dx * gridWidth, dy * gridHeight, gridWidth, gridHeight);
            }
        }
        canvas.getGraphicsContext2D().stroke();
    }


}
