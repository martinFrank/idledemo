package com.github.martinfrank.idledemo.gui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RootController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RootController.class);


    public RootController() {
        LOGGER.debug("<constructor>");
    }

    public void init() {
        LOGGER.debug("init");
    }

}
