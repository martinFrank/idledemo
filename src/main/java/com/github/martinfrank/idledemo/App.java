package com.github.martinfrank.idledemo;

import com.github.martinfrank.idledemo.gui.ControllerFactory;
import com.github.martinfrank.idledemo.gui.RootController;
import com.github.martinfrank.idledemo.idle.IdleManager;
import com.github.martinfrank.idledemo.image.ImageManager;
import com.github.martinfrank.idledemo.support.UrlSupporter;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class App extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        launch(args);
    }

    private IdleManager idleManager;
    private RootController rootController;
    private boolean isRunning = true;

    @Override
    public void stop() throws Exception {
        LOGGER.debug("stop");
        isRunning = false;
        if (idleManager != null) {
            idleManager.stop();
        }
        super.stop();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        LOGGER.debug("start");

        idleManager = new IdleManager();

        UrlSupporter urlSupporter = new UrlSupporter(getClass().getClassLoader());
        ImageManager imageManager = new ImageManager(urlSupporter);

        ControllerFactory controllerFactory = new ControllerFactory();
        FXMLLoader fxmlLoader = new FXMLLoader(urlSupporter.getGuiRoot());
        fxmlLoader.setControllerFactory(controllerFactory);
        HBox root = fxmlLoader.load();
        rootController = fxmlLoader.getController();
//        rootController.setTilesetManager(tilesetManager);
        rootController.setImageManager(imageManager);
        rootController.setIdleManager(idleManager);


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

        startAnimationThread();


    }

    private void startAnimationThread() {

        new Thread() {

            {
                setDaemon(true);
            }

            @Override
            public void run() {
                try {
                    while (isRunning) {
                        TimeUnit.SECONDS.sleep(1);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                rootController.doIt();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }
}
