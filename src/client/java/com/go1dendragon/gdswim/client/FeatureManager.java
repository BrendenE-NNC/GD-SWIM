package com.go1dendragon.gdswim.client;
import com.go1dendragon.gdswim.client.gui.Feature;

import java.util.ArrayList;

// This class allows us to share features between the menu and HUD easily, and
// allows us to close the settings menu without resetting the state of the settings
// to default.
public class FeatureManager {
    public static final String MATRIARCH_ID = "matriarch_reminder";
    private static final ArrayList<Feature> features = new ArrayList<>();
    static {
        features.add(new Feature(MATRIARCH_ID, "Matriarch Reminder", "Draws a reminder on screen when Matriarch is Ready", false));
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
