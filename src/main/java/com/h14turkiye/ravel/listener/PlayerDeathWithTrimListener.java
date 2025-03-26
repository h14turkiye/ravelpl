package com.h14turkiye.ravel.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class PlayerDeathWithTrimListener implements Listener {
	
	private Map<UUID, List<String>> droppedTrims;
	private Plugin plugin;

	public PlayerDeathWithTrimListener(Plugin plugin) {
		this.plugin = plugin;
		droppedTrims = new HashMap<>();
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player p = event.getPlayer();
		List<String> trims = new ArrayList<>();
		ItemStack[] armorMirrors = p.getEquipment().getArmorContents();
		for(ItemStack armorM : armorMirrors) {
			ItemMeta itemMeta = armorM.getItemMeta();
			if (!(itemMeta instanceof ArmorMeta)) return;
			ArmorMeta armorMeta = (ArmorMeta) itemMeta;
			
			NamespacedKey key = armorMeta.getTrim().getPattern().getKey();
			if(key.getNamespace().equalsIgnoreCase("cutrim")) {
				// remove trim from drops, when player respawns give trim item with 20 damage.
				trims.add(key.getKey());
				armorMeta.setTrim(null);
				armorM.setItemMeta(armorMeta);
			}
		}
		if(trims.isEmpty()) return;
		droppedTrims.put(p.getUniqueId(), trims);
		Bukkit.getScheduler().runTaskLater(plugin, () -> { p.spigot().respawn(); }, 1L);
	}
	
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
		PlayerInventory inventory = player.getInventory();
		UUID uniqueId = player.getUniqueId();
		List<String> trimlist = droppedTrims.get(uniqueId);
		if(trimlist.isEmpty()) return;
		for(String trim: trimlist) {
			ItemStack item = new ItemStack(Material.CARROT_ON_A_STICK);
			Damageable itemMeta = (Damageable) item.getItemMeta();
			itemMeta.getPersistentDataContainer().set(NamespacedKey.minecraft("Stored"), PersistentDataType.STRING, "trim."+trim);
			itemMeta.setDamage(20);
			itemMeta.setCustomModelData(2);
			item.setItemMeta(itemMeta);
			
			inventory.addItem(item);
		} 
		droppedTrims.remove(uniqueId);
	}
	
}
