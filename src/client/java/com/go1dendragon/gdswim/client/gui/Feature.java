package com.go1dendragon.gdswim.client.gui;

public class Feature {
    private final String name;
    private final String description;
    private boolean enabled;
    private boolean pendingEnabled;

    public Feature(String name, String description, boolean enabled) {
        this.name = name;
        this.description = description;
        this.enabled = enabled;
        this.pendingEnabled = enabled;
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
    public boolean isPendingEnabled() {
        return this.pendingEnabled;
    }
    public void toggle() {
        this.pendingEnabled = !this.pendingEnabled;
    }
    public void applyChanges() {
        enabled = pendingEnabled;
    }
    public void cancelChanges() {
        pendingEnabled = enabled;
    }
}
