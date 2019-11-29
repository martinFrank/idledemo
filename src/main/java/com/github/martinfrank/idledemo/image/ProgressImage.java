package com.github.martinfrank.idledemo.image;

import com.github.martinfrank.idlelib.Progress;
import javafx.scene.image.Image;

public class ProgressImage {

    private final Image[] images;

    public ProgressImage(Image[] images) {
        this.images = images;
    }

    public Image getImage(Progress progress) {
        int index = (int) (images.length * progress.getPercent(true));
        return images[index];
    }
}
