package com.github.martinfrank.idledemo.image;

public enum TilesetImageDescription {

    TERRAIN(32, 32, 32, 32);

    private final int rows;
    private final int columns;
    private final int tileWidth;
    private final int tileHeight;

    TilesetImageDescription(int columns, int rows, int tileWidth, int tileHeight) {
        this.columns = columns;
        this.rows = rows;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }
}
