package com.go1dendragon.gdswim.client.reminders;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;

public class ReminderHud {
    private final DailyQuestTracker tracker;

    public ReminderHud(DailyQuestTracker tracker) {
        this.tracker = tracker;
    }
    public void render(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker) {
        if (!this.tracker.isMatriarchAvailable()) {
            return;
        }
        int screenWidth = graphics.guiWidth();

        graphics.fill((screenWidth / 2) - 110,
                30,
                (screenWidth / 2) + 110,
                80,
                0xFFCC0000);

        graphics.pose().pushMatrix();
        graphics.pose().translate(screenWidth / 2f, 55f);
        graphics.pose().scale(2f, 2f);
        graphics.centeredText(Minecraft.getInstance().font,
                "!!! Matriarch Ready !!!",
                0,
                -4,
                0xFFFFFFFF);

        graphics.pose().popMatrix();
    }
}
