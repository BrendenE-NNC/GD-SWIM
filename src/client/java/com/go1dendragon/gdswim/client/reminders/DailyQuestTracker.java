package com.go1dendragon.gdswim.client.reminders;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.network.chat.Component;
import java.util.Collection;

public class DailyQuestTracker {
    private int tickCounter = 0;
    private boolean matriarchAvailable = false;

    public boolean isMatriarchAvailable() {
        return matriarchAvailable;
    }

    public void tick(Minecraft client) {
        tickCounter++;
        if (tickCounter >= 20) {
            tickCounter = 0;
            matriarchAvailable = false;
            ClientPacketListener connection = client.getConnection();
            if (connection == null) {
                return;
            }

            Collection<PlayerInfo> tabListEntries = connection.getListedOnlinePlayers();
            for (PlayerInfo tabInfo : tabListEntries) {
                Component tabNames = tabInfo.getTabListDisplayName();
                if (tabNames != null) {
                    if (tabNames.getString().strip().equals("Matriarch: Available")) {
                        matriarchAvailable = true;
                        break;
                    }
                }
            }
        }
    }
}
