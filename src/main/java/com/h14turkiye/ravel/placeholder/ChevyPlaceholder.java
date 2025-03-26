package com.h14turkiye.ravel.placeholder;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.OfflinePlayer;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class ChevyPlaceholder extends PlaceholderExpansion{
	private static final Map<Character, Character> CHAR= new HashMap<>();
	static {
		CHAR.put('A', 'ᴀ');
        CHAR.put('B', 'ʙ');
        CHAR.put('C', 'ᴄ');
        CHAR.put('Ç', 'ç');
        CHAR.put('D', 'ᴅ');
        CHAR.put('E', 'ᴇ');
        CHAR.put('F', 'ꜰ');
        CHAR.put('G', 'ɢ');
        CHAR.put('Ğ', 'ɢ');
        CHAR.put('H', 'ʜ');
        CHAR.put('I', 'ɪ');
        CHAR.put('İ', 'ɪ');
        CHAR.put('J', 'ᴊ');
        CHAR.put('K', 'ᴋ');
        CHAR.put('L', 'ʟ');
        CHAR.put('M', 'ᴍ');
        CHAR.put('N', 'ɴ');
        CHAR.put('O', 'ᴏ');
        CHAR.put('Ö', 'ö');
        CHAR.put('P', 'ᴘ');
        CHAR.put('Q', 'ǫ');
        CHAR.put('R', 'ʀ');
        CHAR.put('S', 'ꜱ');
        CHAR.put('T', 'ᴛ');
        CHAR.put('U', 'ᴜ');
        CHAR.put('Ü', 'ü');
        CHAR.put('V', 'ᴠ');
        CHAR.put('W', 'ᴡ');
        CHAR.put('X', 'x'); 
        CHAR.put('Y', 'ʏ');
        CHAR.put('Z', 'z'); 
        CHAR.put('a', 'ᴀ');
        CHAR.put('b', 'ʙ');
        CHAR.put('c', 'ᴄ');
        CHAR.put('ç', 'ç');
        CHAR.put('d', 'ᴅ');
        CHAR.put('e', 'ᴇ');
        CHAR.put('f', 'ꜰ');
        CHAR.put('g', 'ɢ');
        CHAR.put('ğ', 'ɢ');
        CHAR.put('h', 'ʜ');
        CHAR.put('i', 'ɪ');
        CHAR.put('ı', 'ɪ');
        CHAR.put('j', 'ᴊ');
        CHAR.put('k', 'ᴋ');
        CHAR.put('l', 'ʟ');
        CHAR.put('m', 'ᴍ');
        CHAR.put('n', 'ɴ');
        CHAR.put('o', 'ᴏ');
        CHAR.put('ö', 'ö');
        CHAR.put('p', 'ᴘ');
        CHAR.put('q', 'ǫ');
        CHAR.put('r', 'ʀ');
        CHAR.put('s', 'ꜱ');
        CHAR.put('ş', 'ş');
        CHAR.put('t', 'ᴛ');
        CHAR.put('u', 'ᴜ');
        CHAR.put('ü', 'ü');
        CHAR.put('v', 'ᴠ');
        CHAR.put('w', 'ᴡ');
        CHAR.put('x', 'x'); 
        CHAR.put('y', 'ʏ');
        CHAR.put('z', 'z'); 
	}
	@Override
    public String onRequest(OfflinePlayer player, String params) {
		if(params.contains("{")){
			params = params.replace("{", "%");
			params = params.replace("}", "%");
		}
		params = PlaceholderAPI.setPlaceholders(player, params);
        // Convert the text into the "ChevyRay Pinch" font style
		return convertToChevyRayPinch(params);
    }
	
	String convertToChevyRayPinch(String text) {
        // Convert the text using the character mapping
        StringBuilder convertedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            char convertedChar = CHAR.getOrDefault(c, c);
            convertedText.append(convertedChar);
        }
        return convertedText.toString();
    }
	@Override
	public String getIdentifier() {
		return "cr";
	}
	@Override
	public String getAuthor() {
		return "oz";
	}
	@Override
	public String getVersion() {
		return "1";
	}
}
