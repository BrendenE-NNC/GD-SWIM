package com.go1dendragon.gdswim.client.gui;

public class Feature {
    private final String name;
    private final String description;
    private boolean enabled;

    public Feature(String name, String description, boolean enabled) {
        this.name = name;
        this.description = description;
        this.enabled = enabled;
    }

    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
    public boolean isEnabled() {
        return this.enabled;
    }
    public void toggle() {
        this.enabled = !this.enabled;
    }
}
