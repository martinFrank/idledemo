package com.github.martinfrank.idledemo.gui;

import com.github.martinfrank.geolib.GeoPoint;
import com.github.martinfrank.idledemo.grid.CanvasGridContainer;
import com.github.martinfrank.idledemo.grid.CanvasGridItem;
import com.github.martinfrank.idledemo.grid.CanvasGridShape;
import com.github.martinfrank.idledemo.grid.GridSize;
import com.github.martinfrank.idledemo.image.ImageDescription;
import com.github.martinfrank.idledemo.image.ImageManager;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;


public class RootController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RootController.class);
    private static final String LEFT_ID = "LEFT_ID";
    public Canvas leftCanvas;
    public Canvas centerCanvas;

    private ImageManager imageManager;
    private CanvasGridContainer leftContainer;

    public RootController() {
        LOGGER.debug("<constructor>");
    }

    public void init() throws IOException {
        LOGGER.debug("init");

        GridSize gridSize = new GridSize(4, 4, 32, 32);

        leftContainer = new CanvasGridContainer(gridSize, leftCanvas);

        Map<GeoPoint, Image> composite = new HashMap<>();
        composite.put(new GeoPoint(0, 0), imageManager.getImage(ImageDescription.TERRAIN, 32));
        composite.put(new GeoPoint(1, 0), imageManager.getImage(ImageDescription.TERRAIN, 34));
        List<GeoPoint> shape = Arrays.asList(new GeoPoint(0, 0), new GeoPoint(1, 0));
        CanvasGridShape canvasGridShape = new CanvasGridShape(new ArrayList<>(composite.keySet()));
        final Image compositeImage = imageManager.createComposite(composite);
        canvasGridShape.setItem(new CanvasGridItem(compositeImage));

        leftContainer.add(canvasGridShape, new GeoPoint(1, 2));

        leftCanvas.setOnDragDetected(mouseEvent -> {
            LOGGER.debug("drag detect!!!");
            GeoPoint at = new GeoPoint((int) mouseEvent.getX() / gridSize.getGridWidth(), (int) mouseEvent.getY() / gridSize.getGidHeight());
            CanvasGridItem pickedItem = leftContainer.getItemAt(at);
            LOGGER.debug("picked item {} at : {}", pickedItem, at);
            if (pickedItem != null) {
                Dragboard db = leftCanvas.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(LEFT_ID);
                db.setContent(content);
                db.setDragView(pickedItem.getImage());
            }
            mouseEvent.consume();
        });


        centerCanvas.setOnDragOver((DragEvent dragEvent) -> {
            if (dragEvent.getGestureSource() != centerCanvas &&
                    dragEvent.getDragboard().hasString()) {
                dragEvent.acceptTransferModes(TransferMode.MOVE);
                System.out.println("ondrag over: move");
            }
            dragEvent.consume();
        });

        centerCanvas.setOnDragDropped(dragEvent -> {
            System.out.println("drop event!!!");
            Dragboard db = dragEvent.getDragboard();
            //Get an item ID here, which was stored when the drag started.
            boolean success = true;
            // If this is a meaningful drop...
            if (db.hasString()) {
                String nodeId = db.getString();
                // ...search for the item on body. If it is there...
                System.out.println("nodeId:" + nodeId);
            }
            dragEvent.setDropCompleted(success);
            dragEvent.consume();
        });

    }

    public void setImageManager(ImageManager imageManager) {
        this.imageManager = imageManager;
    }

//    public void setTilesetManager(TileManager tilesetManager) {
//        this.tilesetManager = tilesetManager;
//    }
}
