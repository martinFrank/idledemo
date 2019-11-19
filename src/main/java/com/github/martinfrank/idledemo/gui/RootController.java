package com.github.martinfrank.idledemo.gui;

import com.github.martinfrank.idledemo.image.ImageManager;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class RootController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RootController.class);
    private static final String LEFT_ID = "LEFT_ID";
    public Canvas leftImage;
    public Canvas centerImage;
    private ImageManager imageManager;

    public RootController() {
        LOGGER.debug("<constructor>");
    }

    public void init() throws IOException {
        LOGGER.debug("init");
        final Image image1 = imageManager.getImage(ImageManager.ImageIdentifier.TERRAIN, 32);
        final Image image2 = imageManager.getImage(ImageManager.ImageIdentifier.TERRAIN, 34);
        //leftImage.setImage(image);
        leftImage.getGraphicsContext2D().drawImage(image1, 0, 0);
        leftImage.getGraphicsContext2D().drawImage(image2, 32, 0);

        leftImage.setOnDragDetected(mouseEvent -> {
            System.out.println("drag detect!!!");
//            activate();
            Dragboard db = leftImage.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            // Store the node ID in order to know what is dragged.
            content.putString(LEFT_ID);
            db.setContent(content);
            db.setDragView(image1, 380, 430);
            mouseEvent.consume();
        });


        centerImage.setOnDragOver((DragEvent event) -> {
            if (event.getGestureSource() != centerImage &&
                    event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
                System.out.println("ondrag over: move");
            }
            event.consume();
        });

        centerImage.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
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
            }
        });

    }

    public void setImageManager(ImageManager imageManager) {
        this.imageManager = imageManager;
    }
}
