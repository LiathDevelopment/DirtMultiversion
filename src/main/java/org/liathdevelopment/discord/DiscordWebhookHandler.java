package org.liathdevelopment.discord;

import org.pmw.tinylog.Logger;

import com.github.dirtpowered.dirtmv.api.Configuration;
import com.github.dirtpowered.dirtmv.config.YamlConfig;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;

public class DiscordWebhookHandler {
	private static Configuration configuration;
	
	public static void sendWebhookMessage(String username, String message) {
		configuration = new YamlConfig();
		
		WebhookClient client = WebhookClient.withUrl(configuration.getDiscordWebhookUrl());
		WebhookMessageBuilder builder = new WebhookMessageBuilder();
		builder.setUsername(username);
		builder.setAvatarUrl("https://minotar.net/avatar/" + UsernameConverter.usernameToUUID(username));
		builder.setContent(message);
		client.send(builder.build());
		client.close();
		
		Logger.info("[Webhook] Sent " + username + "'s message");
	}
}
