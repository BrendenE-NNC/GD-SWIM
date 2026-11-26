package com.go1dendragon.gdswim.client.reminders;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.DeltaTracker;

public class ReminderHud {

    public void render(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker) {
        graphics.fill(50, 20, 250, 60, 0xFFCC0000);
    }
}
