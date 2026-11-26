package com.go1dendragon.gdswim.client;

import com.go1dendragon.gdswim.client.reminders.DailyQuestTracker;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class GDSWIMClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		DailyQuestTracker tracker = new DailyQuestTracker();
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			tracker.tick(client);
		});
	}
}