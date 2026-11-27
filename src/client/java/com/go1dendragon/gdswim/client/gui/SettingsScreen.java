package com.go1dendragon.gdswim.client.gui;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import org.jspecify.annotations.NonNull;

public class SettingsScreen extends Screen {
    public SettingsScreen() {
        super(Component.literal("GD SWIM"));
    }

    @Override
    public void extractRenderState(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractRenderState(graphics, mouseX, mouseY, delta);

        int screenWidth = graphics.guiWidth();
        int screenHeight = graphics.guiHeight();

        int panelWidth = (int) (0.8 * screenWidth / 2);
        int panelHeight = (int) (0.8 * screenHeight / 2);

        int horizBarHeight = (int) (0.55 * screenHeight / 2);
        int vertBarWidth = (int) (0.4 * screenWidth / 2);

        int left = (screenWidth / 2) - panelWidth;
        int right = (screenWidth / 2) + panelWidth;
        int top = (screenHeight / 2) - panelHeight;
        int bottom = (screenHeight / 2) + panelHeight;

        int dividerX = (screenWidth / 2) - vertBarWidth;
        int dividerY = (screenHeight / 2) - horizBarHeight;

        // Border
        graphics.fill(left - 2, top - 2, right + 2, bottom + 2, 0xFF102031);
        // Main Panel
        graphics.fill(left, top, right, bottom, 0xFF325b75);
        // Feature Section
        graphics.fill(dividerX, dividerY, right, bottom, 0xFF2b5071);
        // Horiz Divider
        graphics.fill(left, dividerY, right, dividerY + 2, 0xFFf3d066);
        // Vert Divider
        graphics.fill(dividerX, dividerY, dividerX+ 2, bottom, 0xFFf3d066);

    }
}
