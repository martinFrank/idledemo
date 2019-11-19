package com.github.martinfrank.idledemo.resource;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class ResourceManager {

    private final ClassLoader classLoader;

    public ResourceManager(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public URL getGuiRoot() throws MalformedURLException {
        return resourceURL("gui/root.fxml");
    }
//
//    public URL getImagesRoot() throws MalformedURLException {
//        return resourceURL("images/images.xml");
//    }
//
//    public URL getResourceURL (String src) throws MalformedURLException {
//        return resourceURL(src);
//    }
//
//    public URL getGuiSetupDialog() throws MalformedURLException {
//        return resourceURL("gui/setupdialog.fxml");
//    }

    private URL resourceURL(String str) throws MalformedURLException {
        URL url = classLoader.getResource(str);
        if (url != null) {
            return new File(url.getPath()).toURI().toURL();
        }
        throw new MalformedURLException("url=null");
    }

    public URL getImage() throws MalformedURLException {
        return resourceURL("image/terrain_atlas.png");
    }
}
