package com.github.martinfrank.idledemo.gui;

import com.github.martinfrank.geolib.GeoPoint;
import com.github.martinfrank.idledemo.grid.*;
import com.github.martinfrank.idledemo.idle.GeneratorFactory;
import com.github.martinfrank.idledemo.idle.IdleManager;
import com.github.martinfrank.idledemo.idle.TimberGenerator;
import com.github.martinfrank.idledemo.image.ImageDescription;
import com.github.martinfrank.idledemo.image.ImageManager;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;


public class RootController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RootController.class);
    public Canvas leftCanvas;
    public Canvas centerCanvas;

    private ImageManager imageManager;
    private TemplateContainer leftContainer;
    private GeneratorContainer centerContainer;
    private IdleManager idleManager;

    public RootController() {
        LOGGER.debug("<constructor>");
    }

    private Random random = new Random();

    public void setImageManager(ImageManager imageManager) {
        this.imageManager = imageManager;
    }

    private static GeoPoint getGeoPoint(MouseEvent event, GridSize gridSize) {
        return new GeoPoint((int) event.getX() / gridSize.getGridWidth(), (int) event.getY() / gridSize.getGidHeight());
    }

    private static GeoPoint getGeoPoint(DragEvent event, GridSize gridSize) {
        return new GeoPoint((int) event.getX() / gridSize.getGridWidth(), (int) event.getY() / gridSize.getGidHeight());
    }

    public void init() throws IOException {
        LOGGER.debug("init");

        GridSize gridSize = new GridSize(4, 4, 32, 32);

        leftContainer = new TemplateContainer(gridSize, leftCanvas);

        centerContainer = new GeneratorContainer(gridSize, centerCanvas);

        Map<GeoPoint, Image> composite = new HashMap<>();
        composite.put(new GeoPoint(0, 0), imageManager.getImage(ImageDescription.TERRAIN, 32));
        composite.put(new GeoPoint(1, 0), imageManager.getImage(ImageDescription.TERRAIN, 34));
        List<GeoPoint> shape = Arrays.asList(new GeoPoint(0, 0), new GeoPoint(1, 0));
        TemplateShape templateShape = new TemplateShape(new ArrayList<>(composite.keySet()));
        final Image compositeImage = imageManager.createComposite(composite);
        templateShape.setItem(new GeneratorTemplate(compositeImage));

        leftContainer.add(templateShape, new GeoPoint(1, 2));

        leftCanvas.setOnDragDetected(mouseEvent -> {
            GeneratorTemplate pickedItem = leftContainer.getItemAt(getGeoPoint(mouseEvent, gridSize));

            if (pickedItem != null) {
                Dragboard db = leftCanvas.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(pickedItem.getTemplateId().name());
                db.setContent(content);
                db.setDragView(pickedItem.getImage());
            }
            mouseEvent.consume();
        });

        centerCanvas.setOnDragOver(e -> e.acceptTransferModes(TransferMode.MOVE));
//        centerCanvas.setOnDragOver((DragEvent dragEvent) -> {
//            if (dragEvent.getGestureSource() != centerCanvas &&
//                    dragEvent.getDragboard().hasString()) {
//                dragEvent.acceptTransferModes(TransferMode.MOVE);
//                System.out.println("ondrag over: move");
//            }
//            dragEvent.consume();
//        });

        centerCanvas.setOnDragDropped(dragEvent -> {
            GeoPoint at = getGeoPoint(dragEvent, gridSize);
            LOGGER.debug("drop event!!!");
            Dragboard db = dragEvent.getDragboard();
            //Get an item ID here, which was stored when the drag started.
            boolean success = true;
            // If this is a meaningful drop...
            if (db.hasString()) {
                String nodeId = db.getString();
                GeneratorFactory.GeneratorId id = GeneratorFactory.GeneratorId.valueOf(db.getString());
                TimberGenerator generator = idleManager.getFactory().generate(id, compositeImage);
                GeneratorShape generatorShape = new GeneratorShape(new ArrayList<>(composite.keySet()));
                generatorShape.setItem(generator);

                if (centerContainer.fitsInside(generatorShape, at)) {
                    //FIXME should be a command!
                    centerContainer.add(generatorShape, at);
                    LOGGER.debug("successfully add (new) generator {} at {}", id, at);
                } else {
                    success = false;
                    LOGGER.debug("could not add generator {} at {}", id, at);
                }

            }
            dragEvent.setDropCompleted(success);
            dragEvent.consume();
        });

    }

    public void setIdleManager(IdleManager idleManager) {
        this.idleManager = idleManager;
    }

    public void doIt() {
        if (leftCanvas.isVisible()) {
            leftCanvas.getGraphicsContext2D().setFill(new Color(random.nextDouble(), random.nextDouble(), random.nextDouble(), 1));
            leftCanvas.getGraphicsContext2D().fillRect(0, 0, 20, 20);
        }
    }
}
