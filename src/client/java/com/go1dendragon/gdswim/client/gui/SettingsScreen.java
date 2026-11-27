package com.go1dendragon.gdswim.client.gui;

import com.go1dendragon.gdswim.client.FeatureManager;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import org.jspecify.annotations.NonNull;
import net.minecraft.client.input.MouseButtonEvent;

import java.util.ArrayList;

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
    private final ArrayList<Feature> features = FeatureManager.getFeatures();
    private static final int FEATURE_PADDING = 6;
    private static final int FEATURE_HEIGHT = 30;
    private static final int FEATURE_SPACING = 36;

    public SettingsScreen() {
        super(Component.literal("GD SWIM"));
        for (Feature feature : features) {
            feature.cancelChanges();
        }
    }

    @Override
    public void extractRenderState(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractRenderState(graphics, mouseX, mouseY, delta);
        getLayout(graphics);
        drawBorder(graphics);
        drawPanel(graphics);
        drawFeatureSection(graphics, mouseX, mouseY);
        drawSideBar(graphics);
        drawDividers(graphics);
    }

    private void getLayout(GuiGraphicsExtractor graphics) {
        int screenWidth = graphics.guiWidth();
        int screenHeight = graphics.guiHeight();

        int panelWidth = (int) (0.9 * screenWidth / 2);
        int panelHeight = (int) (0.8 * screenHeight / 2);

        int horizBarHeight = (int) (0.55 * screenHeight / 2);
        int vertBarWidth = (int) (0.5 * screenWidth / 2);

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
    private void drawFeatureSection(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        graphics.fill(dividerX, dividerY, right, bottom, 0xFF2b5071);
        int drawY = FEATURE_PADDING;
        for (Feature feature : features) {
            drawFeature(graphics, feature.getName(), feature.getDescription(), feature.isPendingEnabled(), drawY, mouseX, mouseY);
            drawY += FEATURE_SPACING;
        }
    }
    private void drawFeature(GuiGraphicsExtractor graphics, String name, String description, boolean enabled, int y, int mouseX, int mouseY) {
        int featureLeft = dividerX + FEATURE_PADDING;
        int featureRight = right - FEATURE_PADDING;
        int featureTop = dividerY + y;
        int featureBottom = featureTop + FEATURE_HEIGHT;
        boolean hovered = isWithinFeature(mouseX, mouseY, y);

        if (hovered) {
            graphics.fill(featureLeft, featureTop, featureRight,featureBottom, 0xFF5aa6c3);
        } else {
            graphics.fill(featureLeft, featureTop, featureRight,featureBottom, 0xFF1c3a54);
        }
        if (enabled) {
            graphics.fill(featureLeft + 1, featureTop + 1, featureRight - 1,featureBottom - 1, 0xFFa47b43);
        } else {
            graphics.fill(featureLeft + 1, featureTop + 1, featureRight - 1,featureBottom - 1, 0xFF3e6e98);
        }
        drawScaledText(graphics, name,featureLeft + 5, featureTop + 7, 1.1f, 0xFFd0e2ef);
        drawScaledText(graphics, description,featureLeft + 5, featureTop + 18, 0.7f, 0xFFa9c1d3);
        drawToggle(graphics, featureRight - 35, featureTop + 10, enabled);
    }
    private void drawScaledText(GuiGraphicsExtractor graphics, String text, int x,
                                int y, float scale, int color) {
        graphics.pose().pushMatrix();
        graphics.pose().translate(x, y);
        graphics.pose().scale(scale, scale);
        graphics.text(font, text, 0, 0, color);
        graphics.pose().popMatrix();

    }
    private void drawToggle(GuiGraphicsExtractor graphics, int x, int y, boolean enabled) {
        graphics.fill(x - 1, y - 1, x + 23, y + 11, 0xFF263d62);
        if (enabled) {
            graphics.fill(x, y, x + 22, y + 10, 0xFFffb650);
            graphics.fill(x + 13, y + 1, x + 21, y + 9, 0xFFcfe2f3);
        } else {
            graphics.fill(x, y, x + 22, y + 10, 0xFF65747a);
            graphics.fill(x + 1, y + 1, x + 9, y + 9, 0xFFcfe2f3);
        }
    }
    private boolean isWithinBounds(double mouseX, double mouseY, int left, int right, int top, int bottom) {
        return (mouseX >= left && mouseX <= right && mouseY >= top && mouseY <= bottom);
    }
    private boolean isWithinFeature(double mouseX, double mouseY, int y) {
        int featureTop = dividerY + y;

        return isWithinBounds(mouseX, mouseY, dividerX + FEATURE_PADDING,
                right - FEATURE_PADDING, featureTop, featureTop + FEATURE_HEIGHT);
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

    @Override
    public boolean mouseClicked(@NonNull MouseButtonEvent event, boolean doubleClick) {
        int y = FEATURE_PADDING;
        if (event.button() != 0) {
            return super.mouseClicked(event, doubleClick);
        }
        for (Feature feature : features) {
            if(isWithinFeature(event.x(), event.y(), y)) {
                feature.toggle();
                return true;
            }
            y += FEATURE_SPACING;
        }
        return super.mouseClicked(event, doubleClick);
    }

    @Override
    public void removed() {
        for (Feature feature : features) {
            feature.applyChanges();
        }
        super.removed();
    }
}
