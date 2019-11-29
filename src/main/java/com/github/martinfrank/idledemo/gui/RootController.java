package com.github.martinfrank.idledemo.gui;

import com.github.martinfrank.geolib.GeoPoint;
import com.github.martinfrank.idledemo.grid.*;
import com.github.martinfrank.idledemo.idle.IdleManager;
import com.github.martinfrank.idledemo.idle.ResourceType;
import com.github.martinfrank.idledemo.idle.generator.BasicGenerator;
import com.github.martinfrank.idledemo.idle.generator.GeneratorFactory;
import com.github.martinfrank.idledemo.idle.template.GeneratorTemplate;
import com.github.martinfrank.idledemo.idle.template.TemplateFactory;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;


public class RootController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RootController.class);

    public Canvas leftCanvas;
    public Canvas centerCanvas;

    private TemplateFactory templateFactory;
    private TemplateContainer leftContainer;
    private GeneratorContainer centerContainer;
    private IdleManager idleManager;
    private GridSize leftGridSize;
    private GridSize centerGridSize;

    private Random random = new Random();

    private static GeoPoint getGeoPoint(MouseEvent event, GridSize gridSize) {
        return new GeoPoint((int) event.getX() / gridSize.getGridWidth(), (int) event.getY() / gridSize.getGridHeight());
    }

    private static GeoPoint getGeoPoint(DragEvent event, GridSize gridSize) {
        return new GeoPoint((int) event.getX() / gridSize.getGridWidth(), (int) event.getY() / gridSize.getGridHeight());
    }

    public void setTemplateFactory(TemplateFactory templateFactory) {
        this.templateFactory = templateFactory;
    }

    public void init() {
        LOGGER.debug("init");
        leftGridSize = new GridSize(4, 4, 32, 32);
        centerGridSize = new GridSize(4, 9, 32, 32);

        leftContainer = new TemplateContainer(leftGridSize, leftCanvas);
        centerContainer = new GeneratorContainer(centerGridSize, centerCanvas);

        createDragFromLeftToCenter();
        createGeneratorClickListener();
    }

    private void createGeneratorClickListener() {
        centerCanvas.setOnMouseClicked(mouseEvent -> {
            BasicGenerator pickedItem = centerContainer.getItemAt(getGeoPoint(mouseEvent, centerGridSize));
            if (pickedItem != null && pickedItem.getProgress().isComplete()) {
                Map<ResourceType, Double> amount = pickedItem.yield();
                for (Map.Entry<ResourceType, Double> entry : amount.entrySet()) {
                    LOGGER.debug("earned {} of {} from generator {}", entry.getValue(), entry.getKey(), pickedItem);
                }
            } else {
                LOGGER.debug("generator {} is not yet ready", pickedItem);
            }
        });
    }

    private void createDragFromLeftToCenter() {
        //fixme - kommt in SetupTemplates
        TemplateShape templateShape = templateFactory.createTemplate();
        Image compositeImage = templateShape.getItem().getImage();
        leftContainer.add(templateShape, new GeoPoint(0, 0));

        leftCanvas.setOnDragDetected(mouseEvent -> {
            GeneratorTemplate pickedItem = leftContainer.getItemAt(getGeoPoint(mouseEvent, leftGridSize));

            if (pickedItem != null) {
                Dragboard db = leftCanvas.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(pickedItem.getTemplateId().name());
                db.setContent(content);
                Image image = pickedItem.getImage();
                double x = image.getWidth() / 2d - leftGridSize.getGridWidth() / 2d;
                double y = -1 * image.getHeight() / 2d + leftGridSize.getGridHeight() / 2d;
                db.setDragView(pickedItem.getImage(), x, y);
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
            GeoPoint at = getGeoPoint(dragEvent, leftGridSize);
            LOGGER.debug("drop event!!!");
            Dragboard db = dragEvent.getDragboard();

            boolean success = true;
            // If this is a meaningful drop...
            if (db.hasString()) {
                GeneratorFactory.GeneratorId id = GeneratorFactory.GeneratorId.valueOf(db.getString());
                BasicGenerator generator = idleManager.getFactory().generate(id, compositeImage);
                GeneratorShape generatorShape = new GeneratorShape(new ArrayList<>(templateShape.getShape()));
                generatorShape.setItem(generator);

                if (centerContainer.fitsInside(generatorShape, at)) {
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

    public void updateUI() {
        if (leftCanvas.isVisible()) {
            leftCanvas.getGraphicsContext2D().setFill(new Color(random.nextDouble(), random.nextDouble(), random.nextDouble(), 1));
            leftCanvas.getGraphicsContext2D().fillRect(0, 0, 20, 20);
        }
        if (centerContainer.isVisible()) {
            centerContainer.updateUI();
        }
    }
}
