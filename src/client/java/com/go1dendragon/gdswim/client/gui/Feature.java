package com.go1dendragon.gdswim.client.gui;

public class Feature {
    private final String id;
    private final String name;
    private final String description;
    private boolean enabled;
    private boolean pendingEnabled;

    public Feature(String id, String name, String description, boolean enabled) {
        this. id = id;
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
    public String getId() {
        return this.id;
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
