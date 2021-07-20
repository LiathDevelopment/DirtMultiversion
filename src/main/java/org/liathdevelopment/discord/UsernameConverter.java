package org.liathdevelopment.discord;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class UsernameConverter {
	@SuppressWarnings("deprecation")
	public static String usernameToUUID(String username) {
		String uuid;
	    try {
	        BufferedReader in = new BufferedReader(new InputStreamReader(new URL("https://api.mojang.com/users/profiles/minecraft/" + username).openStream()));
	        uuid = (((JsonObject)new JsonParser().parse(in)).get("id")).toString().replaceAll("\"", "");
	        uuid = uuid.replaceAll("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5");
	        in.close();
	    } catch (Exception e) {
	        uuid = "8667ba71b85a4004af54457a9734eed7";
	    }
		return uuid;
	}
}
