package com.go1dendragon.gdswim.client;

import com.go1dendragon.gdswim.client.gui.SettingsScreen;
import com.go1dendragon.gdswim.client.reminders.DailyQuestTracker;
import com.go1dendragon.gdswim.client.reminders.ReminderHud;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.resources.Identifier;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.minecraft.client.Minecraft;

public class GDSWIMClient implements ClientModInitializer {
	private boolean requestedOpenSettings = false;
	@Override
	public void onInitializeClient() {
		DailyQuestTracker tracker = new DailyQuestTracker();
		ReminderHud reminderHud = new ReminderHud(tracker);
		HudElementRegistry.attachElementAfter(VanillaHudElements.CHAT,
			Identifier.fromNamespaceAndPath("gd-swim", "reminder_hud"),
			reminderHud::render);
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			tracker.tick(client);

			if (requestedOpenSettings) {
				requestedOpenSettings = false;
				Minecraft.getInstance().gui.setScreen(new SettingsScreen());
			}
		});

		ClientCommandRegistrationCallback.EVENT.register(
			(dispatcher, buildContext) -> {
				dispatcher.register(
					ClientCommands.literal("gd").executes(context -> {
						requestedOpenSettings = true;
						return 1;
					})
				);
			}
		);
	}
}