package com.h14turkiye.ravel.listener;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.h14turkiye.ravel.util.LegacyToMiniMessageConverter;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.platform.AudienceProvider;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

@SuppressWarnings("deprecation")
public class ChatFormatMiniMessageParser implements Listener{
	private final MiniMessage miniMessage;
	private final AudienceProvider audienceProvider;
	public ChatFormatMiniMessageParser(MiniMessage miniMessage, AudienceProvider audienceProvider) {
		this.miniMessage = miniMessage;
		this.audienceProvider = audienceProvider;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onAfterChat(AsyncPlayerChatEvent event) {
		
		event.setCancelled(true);
		String format = event.getFormat();
		String message = event.getMessage();
		Key font = new NamespacedKey("minecraft", "long");
		format = LegacyToMiniMessageConverter.convert(format);
		format = format.replace("%2$s", "");
		Component newFormat = miniMessage.deserialize(format).append(Component.text(message));
		newFormat = newFormat.font(font);
        for(Player recipient : event.getRecipients()) {	
        	Audience player = audienceProvider.player(recipient.getUniqueId());
        	player.sendMessage(newFormat);
        	}
        audienceProvider.console().sendMessage(newFormat);
    }
}
