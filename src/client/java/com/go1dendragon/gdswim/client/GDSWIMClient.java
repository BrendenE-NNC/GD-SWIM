package com.go1dendragon.gdswim.client;

import com.go1dendragon.gdswim.client.reminders.DailyQuestTracker;
import com.go1dendragon.gdswim.client.reminders.ReminderHud;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.resources.Identifier;

public class GDSWIMClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		DailyQuestTracker tracker = new DailyQuestTracker();
		ReminderHud reminderHud = new ReminderHud();
		HudElementRegistry.attachElementAfter(VanillaHudElements.CHAT,
				Identifier.fromNamespaceAndPath("gd-swim", "reminder_hud"),
				reminderHud::render);
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			tracker.tick(client);
		});
	}
}