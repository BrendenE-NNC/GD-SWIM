package com.go1dendragon.gdswim.client.reminders;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.network.chat.Component;
import java.util.Collection;

public class DailyQuestTracker {
    private int tickCounter = 0;
    private boolean matriarchAvailable = false;
    private static final int SCAN_INTERVAL = 20;

    public boolean isMatriarchAvailable() {
        return matriarchAvailable;
    }

    public void tick(Minecraft client) {
        tickCounter++;
        // Scanning the tablist ~once per second instead of tick saves on processing (20 ticks = ~1 second)
        if (tickCounter >= SCAN_INTERVAL) {
            tickCounter = 0;
            // Resets the variable in case player disconnects or tab entry cannot be found
            matriarchAvailable = false;
            ClientPacketListener connection = client.getConnection();
            if (connection == null) {
                return;
            }

            Collection<PlayerInfo> tabListEntries = connection.getListedOnlinePlayers();
            for (PlayerInfo tabEntry : tabListEntries) {
                Component displayName = tabEntry.getTabListDisplayName();
                if (displayName == null) {
                    continue;
                }

                // strip() is necessary because the Hypixel Tablist has whitespaces before the actual text
                if (displayName.getString().strip().equals("Matriarch: Available")) {
                    matriarchAvailable = true;
                    break;
                }
            }
        }
    }
}
