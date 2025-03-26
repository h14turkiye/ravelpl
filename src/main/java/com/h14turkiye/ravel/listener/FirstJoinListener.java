package com.h14turkiye.ravel.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;

public class FirstJoinListener implements Listener {

	@EventHandler
	public void onFirstJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		if (p.hasPlayedBefore())
			return;
		p.getInventory().setHeldItemSlot(4);
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta bookMeta = (BookMeta) book.getItemMeta();

		// Set book title, author, and pages
		bookMeta.setTitle("Bilgiler");
		bookMeta.setAuthor("AhmetDinc");
		bookMeta.setGeneration(BookMeta.Generation.ORIGINAL);
		bookMeta.addPages(
				Component.text("Hoşgeldin!\n" + "Sunucu hakkındaki bilgilere ulaşmak için discordumuza katıl!\n\n")
						.append(Component.text("TIKLA!").color(TextColor.fromCSSHexString("#7289da"))
								.clickEvent(ClickEvent.openUrl("https://discord.gg/DSUzfAK6tJ"))));
		book.setItemMeta(bookMeta);

		p.getInventory().setItemInMainHand(book);
	}
}
