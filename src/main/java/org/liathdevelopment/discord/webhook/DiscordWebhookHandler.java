package org.liathdevelopment.discord.webhook;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.pmw.tinylog.Logger;

import com.github.dirtpowered.dirtmv.api.Configuration;
import com.github.dirtpowered.dirtmv.config.YamlConfig;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;

public class DiscordWebhookHandler {
	private static Configuration configuration;
	
	@SuppressWarnings("deprecation")
	private static String usernameToUUID(String username) {
		String uuid;
	    try {
	        BufferedReader in = new BufferedReader(new InputStreamReader(new URL("https://api.mojang.com/users/profiles/minecraft/" + username).openStream()));
	        uuid = (((JsonObject)new JsonParser().parse(in)).get("id")).toString().replaceAll("\"", "");
	        uuid = uuid.replaceAll("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5");
	        in.close();
	    } catch (Exception e) {
	        uuid = "069a79f444e94726a5befca90e38aaf5";
	    }
		return uuid;
	}
	
	public static void sendWebhookMessage(String username, String message) {
		configuration = new YamlConfig();
		
		WebhookClient client = WebhookClient.withUrl(configuration.getDiscordWebhookUrl());
		WebhookMessageBuilder builder = new WebhookMessageBuilder();
		builder.setUsername(username);
		builder.setAvatarUrl("https://minotar.net/avatar/" + usernameToUUID(username));
		builder.setContent(message);
		client.send(builder.build());
		client.close();
		
		Logger.info("[Webhook] Sent " + username + "'s message");
	}
}
