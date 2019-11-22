package com.github.martinfrank.idledemo;

import com.github.martinfrank.idledemo.gui.ControllerFactory;
import com.github.martinfrank.idledemo.gui.RootController;
import com.github.martinfrank.idledemo.image.ImageManager;
import com.github.martinfrank.idledemo.resource.ResourceManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class App extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        LOGGER.debug("start");

        ResourceManager resourceManager = new ResourceManager(getClass().getClassLoader());
        ImageManager imageManager = new ImageManager(resourceManager);

        ControllerFactory controllerFactory = new ControllerFactory();
        FXMLLoader fxmlLoader = new FXMLLoader(resourceManager.getGuiRoot());
        fxmlLoader.setControllerFactory(controllerFactory);
        HBox root = fxmlLoader.load();
        RootController rootController = fxmlLoader.getController();
//        rootController.setTilesetManager(tilesetManager);
        rootController.setImageManager(imageManager);

        //all das muss in den View
        //view selbst muss ein Listener sein, der am Model listened (uiChangedEvent o.ä.)
        //view selbst supported den Control, weil hier die z.B. mouseListener (Controller) angehängt werden
        //update UI erfolgt via control oder model

        //model

        Scene scene = new Scene(root, 350, 250);
        primaryStage.setTitle("Test");
        primaryStage.setScene(scene);
        primaryStage.show();

        rootController.init();
    }
}
