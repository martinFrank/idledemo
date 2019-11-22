package com.github.martinfrank.idledemo.grid;

public class GridSize {

    private final int rows;
    private final int columns;
    private final int gridWidth;
    private final int gidHeight;

    public GridSize(int rows, int columns, int gridWidth, int gidHeight) {
        this.rows = rows;
        this.columns = columns;
        this.gridWidth = gridWidth;
        this.gidHeight = gidHeight;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public int getGidHeight() {
        return gidHeight;
    }

}
