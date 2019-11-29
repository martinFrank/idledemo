package com.github.martinfrank.idledemo.support;

import com.github.martinfrank.idledemo.image.ImageDescription;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlSupporter {

    private final ClassLoader classLoader;

    public UrlSupporter(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public URL getGuiRoot() throws MalformedURLException {
        return resourceURL("gui/root.fxml");
    }

    public URL getImageDescription() throws MalformedURLException {
        return resourceURL("json/images.json");
    }

    public URL getFactoryDescription() throws MalformedURLException {
        return resourceURL("json/generators.json");
    }
//    public URL getImagesRoot() throws MalformedURLException {
//        return resourceURL("images/images.xml");
//    }

//    public URL getResourceURL (String src) throws MalformedURLException {
//        return resourceURL(src);
//    }

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

    public URL getImage(ImageDescription name) throws MalformedURLException {
        if (name == ImageDescription.TERRAIN) {
            return resourceURL("image/terrain_atlas.png");
        }
        return null;
    }


}
