package com.go1dendragon.gdswim.client.gui;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import org.jspecify.annotations.NonNull;

public class SettingsScreen extends Screen {
    public static Component TITLE = Component.literal("GD").withColor(0xf3d066)
            .append(Component.literal(" SWIM").withColor(0x1cb2b5));
    private int left;
    private int right;
    private int top;
    private int bottom;
    private int dividerX;
    private int dividerY;
    private int buttonX;

    public SettingsScreen() {
        super(Component.literal("GD SWIM"));
    }

    @Override
    public void extractRenderState(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractRenderState(graphics, mouseX, mouseY, delta);
        getLayout(graphics);
        drawBorder(graphics);
        drawPanel(graphics);
        drawFeatureSection(graphics);
        drawSideBar(graphics);
        drawDividers(graphics);
    }

    private void getLayout(GuiGraphicsExtractor graphics) {
        int screenWidth = graphics.guiWidth();
        int screenHeight = graphics.guiHeight();

        int panelWidth = (int) (0.8 * screenWidth / 2);
        int panelHeight = (int) (0.8 * screenHeight / 2);

        int horizBarHeight = (int) (0.55 * screenHeight / 2);
        int vertBarWidth = (int) (0.4 * screenWidth / 2);

        left = (screenWidth / 2) - panelWidth;
        right = (screenWidth / 2) + panelWidth;
        top = (screenHeight / 2) - panelHeight;
        bottom = (screenHeight / 2) + panelHeight;

        dividerX = (screenWidth / 2) - vertBarWidth;
        dividerY = (screenHeight / 2) - horizBarHeight;
        buttonX = (left + dividerX) / 2;
    }

    private void drawBorder(GuiGraphicsExtractor graphics) {
        graphics.fill(left - 2, top - 2, right + 2, bottom + 2, 0xFF102031);
    }
    private void drawPanel(GuiGraphicsExtractor graphics) {
        graphics.fill(left, top, right, bottom, 0xFF325b7f);
        graphics.pose().pushMatrix();
        graphics.pose().translate((left + right) / 2f, (top + dividerY) / 2f);
        graphics.pose().scale(2.5f, 2.5f);
        graphics.centeredText(font, TITLE, 0, -4, 0xFFFFFFFF);
        graphics.pose().popMatrix();
    }
    private void drawFeatureSection(GuiGraphicsExtractor graphics) {
        graphics.fill(dividerX, dividerY, right, bottom, 0xFF2b5071);
    }
    private void drawSideBar(GuiGraphicsExtractor graphics) {
        graphics.fill(left + 4, dividerY + 6, dividerX - 4, dividerY + 26, 0xFF5aa6c3);
        graphics.fill(left + 5, dividerY + 7, dividerX - 5, dividerY + 25, 0xFF3e6e98);

        graphics.pose().pushMatrix();
        graphics.pose().translate(buttonX, dividerY + 12);
        graphics.pose().scale(1f, 1f);
        graphics.centeredText(font, "Reminders", 0, 0, 0xFFd0e2ef);
        graphics.pose().popMatrix();
    }
    private void drawDividers(GuiGraphicsExtractor graphics) {
        graphics.fill(left, dividerY, right, dividerY + 2, 0xFFf3d066);
        graphics.fill(dividerX, dividerY, dividerX+ 2, bottom, 0xFFf3d066);
    }
}
