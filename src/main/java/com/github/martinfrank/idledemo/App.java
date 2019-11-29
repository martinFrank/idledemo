package com.github.martinfrank.idledemo;

import com.github.martinfrank.idledemo.gui.ControllerFactory;
import com.github.martinfrank.idledemo.gui.RootController;
import com.github.martinfrank.idledemo.idle.IdleManager;
import com.github.martinfrank.idledemo.idle.generator.GeneratorFactoryManager;
import com.github.martinfrank.idledemo.idle.template.TemplateFactory;
import com.github.martinfrank.idledemo.image.ImageDefinitionManager;
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
    private static final int UPDATE_UI_INTERVAL = 200;

    @Override
    public void stop() throws Exception {
        LOGGER.debug("stop");
        stopAnimationThread();
        idleManager.stop();
        super.stop();
    }

    private void stopAnimationThread() {
        isRunning = false;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        LOGGER.debug("start");

        idleManager = new IdleManager();
        UrlSupporter urlSupporter = new UrlSupporter(getClass().getClassLoader());
        ImageManager imageManager = new ImageManager(urlSupporter);
        ImageDefinitionManager imageDefinitionManager = new ImageDefinitionManager(urlSupporter);
        GeneratorFactoryManager generatorFactoryManager = new GeneratorFactoryManager(urlSupporter);
        TemplateFactory templateFactory = new TemplateFactory(imageManager, imageDefinitionManager, generatorFactoryManager);

        ControllerFactory controllerFactory = new ControllerFactory();
        FXMLLoader fxmlLoader = new FXMLLoader(urlSupporter.getGuiRoot());
        fxmlLoader.setControllerFactory(controllerFactory);
        HBox root = fxmlLoader.load();
        rootController = fxmlLoader.getController();
        rootController.setTemplateFactory(templateFactory);
        rootController.setIdleManager(idleManager);
        rootController.init();

        Scene scene = new Scene(root);
        primaryStage.setTitle("Test");
        primaryStage.setScene(scene);
        primaryStage.show();

        startAnimationThread();
    }

    private void startAnimationThread() {
        Thread thread = new Thread(() -> {
            try {
                while (isRunning) {
                    TimeUnit.MILLISECONDS.sleep(UPDATE_UI_INTERVAL);
                    Platform.runLater(() -> rootController.updateUI());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
