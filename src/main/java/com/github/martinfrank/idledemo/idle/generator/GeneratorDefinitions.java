package com.github.martinfrank.idledemo.idle.generator;

import java.util.Arrays;

public class GeneratorDefinitions {

    private GeneratorDefinition[] generatorDefinitions;


    public GeneratorDefinition getGeneratorDefinition(String name) {
        return Arrays.stream(generatorDefinitions).filter(d -> d.getGeneratorName().equals(name)).findAny().orElse(null);
    }

    public GeneratorDefinition[] getGeneratorDefinitions() {
        return generatorDefinitions;
    }

    public void setGeneratorDefinitions(GeneratorDefinition[] generatorDefinitions) {
        this.generatorDefinitions = generatorDefinitions;
    }
}
