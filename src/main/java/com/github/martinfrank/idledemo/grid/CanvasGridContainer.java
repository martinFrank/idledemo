package com.github.martinfrank.idledemo.grid;

import com.github.martinfrank.geolib.GeoPoint;
import com.github.martinfrank.gridinventory.RectangleContainer;
import com.github.martinfrank.gridinventory.Shape;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class CanvasGridContainer<R> extends RectangleContainer<R> {

    public final Canvas canvas;
    public final GridSize gridSize;

    public CanvasGridContainer(GridSize gridSize, Canvas canvas) {
        super(gridSize.getColumns(), gridSize.getRows());
        this.canvas = canvas;
        this.gridSize = gridSize;
        setCanvasSize();
        drawGrid();
    }

    private void drawGrid() {
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        graphics.setFill(Color.YELLOW);
        graphics.setStroke(Color.BLACK);
        graphics.setLineWidth(1d);

        int gridWidth = gridSize.getGridWidth();
        int gridHeight = gridSize.getGridWidth();
        for (int dy = 0; dy < gridSize.getRows(); dy++) {
            for (int dx = 0; dx < gridSize.getColumns(); dx++) {
                graphics.fillRect(dx * gridWidth, dy * gridHeight, gridWidth, gridHeight);
                graphics.rect(dx * gridWidth, dy * gridHeight, gridWidth, gridHeight);
            }
        }
        graphics.stroke();
    }

    private void setCanvasSize() {
        canvas.setWidth(gridSize.getColumns() * gridSize.getGridWidth());
        canvas.setHeight(gridSize.getRows() * gridSize.getGridHeight());
    }

    @Override
    public void add(Shape<R> shape, GeoPoint location) {
        super.add(shape, location);
        drawContent();
    }

    public abstract void drawContent();

    public boolean isVisible() {
        return canvas.isVisible();
    }

    public void updateUI() {
        canvas.getGraphicsContext2D().beginPath();
        drawGrid();
        canvas.getGraphicsContext2D().closePath();
        canvas.getGraphicsContext2D().beginPath();
        drawContent();
        canvas.getGraphicsContext2D().closePath();
    }
}
