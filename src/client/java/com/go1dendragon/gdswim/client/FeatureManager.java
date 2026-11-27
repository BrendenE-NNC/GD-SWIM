package com.go1dendragon.gdswim.client;
import com.go1dendragon.gdswim.client.gui.Feature;

import java.util.ArrayList;

public class FeatureManager {
    private static final ArrayList<Feature> features = new ArrayList<>();
    static {
        features.add(new Feature("matriarch_reminder", "Matriarch Reminder", "Draws a reminder on screen when Matriarch is Ready", false));
    }

    public static ArrayList<Feature> getFeatures() {
        return features;
    }

    public static Feature getFeature(String id) {
        for (Feature feature : features) {
            if (feature.getId().equals(id)) {
                return feature;
            }
        }
        return null;
    }
}
