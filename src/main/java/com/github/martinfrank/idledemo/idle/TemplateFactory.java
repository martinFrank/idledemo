package com.github.martinfrank.idledemo.idle;

import com.github.martinfrank.geolib.GeoPoint;
import com.github.martinfrank.idledemo.grid.TemplateShape;
import com.github.martinfrank.idledemo.idle.json.GeneratorDefinition;
import com.github.martinfrank.idledemo.image.ImageDefinitionManager;
import com.github.martinfrank.idledemo.image.ImageManager;
import javafx.scene.image.Image;

import java.util.Arrays;
import java.util.List;

public class TemplateFactory {

    private final ImageManager imageManager;
    private final ImageDefinitionManager imageDefinitionManager;
    private final GeneratorFactory generatorFactory;

    public TemplateFactory(ImageManager imageManager, ImageDefinitionManager imageDefinitionManager, GeneratorFactory generatorFactory) {
        this.imageManager = imageManager;
        this.imageDefinitionManager = imageDefinitionManager;
        this.generatorFactory = generatorFactory;
    }

    public TemplateShape createTemplate() {
        GeneratorDefinition generatorDefinition = generatorFactory.getGeneratorDefinition("gen_test");
        List<GeoPoint> shape = Arrays.asList(generatorDefinition.getShape());
        TemplateShape templateShape = new TemplateShape(shape);
        final Image compositeImage = imageManager.createComposite(
                imageDefinitionManager.getImageDefinition("tomato_4x2_1_of_4"));
        System.out.println("compositeImage:" + compositeImage.getHeight() + "/" + compositeImage.getHeight());
        templateShape.setItem(new Template(compositeImage));
        return templateShape;
    }
}
