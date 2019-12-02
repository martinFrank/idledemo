package com.github.martinfrank.idledemo.image.json;

import com.github.martinfrank.geolib.GeoPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ImageMapping {

    private List<ImageMappingEntry> entries = new ArrayList<>();

    public void add(GeoPoint location, int imageId) {
        entries.add(new ImageMappingEntry(location, imageId));
    }

    public List<GeoPoint> keySet() {
        return entries.stream().map(e -> e.key).collect(Collectors.toList());
    }

    public List<ImageMappingEntry> entrySet() {
        return entries;
    }

    public class ImageMappingEntry {
        private final GeoPoint key;
        private final Integer value;

        public ImageMappingEntry(GeoPoint key, Integer value) {
            this.key = key;
            this.value = value;
        }

        public GeoPoint getKey() {
            return key;
        }

        public Integer getValue() {
            return value;
        }
    }

}
