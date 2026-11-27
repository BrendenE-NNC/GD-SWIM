package com.go1dendragon.gdswim.client;
import com.go1dendragon.gdswim.client.gui.Feature;

import java.util.ArrayList;

public class FeatureManager {
    private static final ArrayList<Feature> features = new ArrayList<>();
    static {
        features.add(new Feature("Matriarch Reminder", "Draws a reminder on screen when Matriarch is Ready", false));
    }

    public static ArrayList<Feature> getFeatures() {
        return features;
    }
}
