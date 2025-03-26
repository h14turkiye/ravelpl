package com.h14turkiye.ravel.listener;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.SmithingInventory;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;

public class CustomTrimRecipeListener implements Listener {
	private Map<String, String> material = new HashMap<>();
	{
		material.put("amethyst", "1");
		material.put("copper", "2");
		material.put("diamond", "3");
		material.put("emerald", "4");
		material.put("gold", "5");
		material.put("iron", "6");
		material.put("lapis", "7");
		material.put("netherite", "8");
		material.put("quartz", "9");
		material.put("redstone", "0");
	}

	@EventHandler
	public void onSmithingTableEvent(PrepareSmithingEvent event) {
		SmithingInventory inventory = event.getInventory();

		ItemStack template = inventory.getItem(0);
		ItemStack base = inventory.getItem(1);
		ItemStack addition = inventory.getItem(2);

		if (template == null || base == null || addition == null || template.getType() == Material.AIR
				|| base.getType() == Material.AIR || addition.getType() == Material.AIR) {
			return;
		}
		ItemStack result = base.clone();

		ItemMeta tempMeta = template.getItemMeta();
		String str = tempMeta.getAsString();
		String stored = str.substring(str.indexOf("Stored:\"") + 8);
		stored = stored.substring(0, stored.indexOf("\""));

		String mat = addition.getType().name().toLowerCase().replace("_ingot", "").replace("_shard", "");
		if (stored.contains("trim.")) {
			stored = stored.replace("trim.", "");
			ArmorMeta meta = (ArmorMeta) result.getItemMeta();
			TrimMaterial trimMaterial = Registry.TRIM_MATERIAL.get(NamespacedKey.minecraft(mat));
			TrimPattern trimPattern = Registry.TRIM_PATTERN.get(NamespacedKey.fromString("cutrim:" + stored));
			meta.setTrim(new ArmorTrim(trimMaterial, trimPattern));
			result.setItemMeta(meta);
			event.setResult(result);
		}
		if (stored.contains("modeldata.")) {
			stored = stored.replace("modeldata.", "");
			String start = stored.substring(0, stored.length() - 1);
			if (start.equalsIgnoreCase("3110")) {
				String mate = material.get(mat);
				String type = stored.substring(stored.length() - 1);
				if (mate.equalsIgnoreCase("0"))
					type = ((Integer) (Integer.parseInt(type) + 1)).toString();
				stored = start + type + mate;
			}
			ItemMeta meta = result.getItemMeta();
			meta.setCustomModelData(Integer.parseInt(stored));
			result.setItemMeta(meta);
			event.setResult(result);
		}
	}


	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		NamespacedKey key = new NamespacedKey("ravelcore", "custom_trim_2");
		if (!player.hasDiscoveredRecipe(key))
			player.discoverRecipe(key);
	}

	@EventHandler
	public void onWorkbenchEvent(PrepareItemCraftEvent event) {
		CraftingInventory inventory = event.getInventory();
		ItemStack[] matrix = inventory.getMatrix();
		if (matrix[0].getType() == Material.AIR
				&& matrix[2].getType() == Material.AIR && matrix[3].getType() == Material.AIR
				&& matrix[5].getType() == Material.AIR && matrix[6].getType() == Material.AIR
				&& matrix[7].getType() == Material.AIR && matrix[8].getType() == Material.AIR) {
			ItemStack template = matrix[1];
			ItemMeta tempMeta = template.getItemMeta();
			if (!tempMeta.hasCustomModelData() && !template.getType().equals(Material.CARROT_ON_A_STICK))
				return;
			if (matrix[4].getType() != Material.GOLD_INGOT)
				return;
			if (((Damageable) template.getItemMeta()).getDamage() >= 15)
				return;
			ItemStack result = template.clone();
			result.setAmount(2);
			Damageable meta2 = (Damageable) result.getItemMeta();
			meta2.setDamage(meta2.getDamage() + 10);
			result.setItemMeta(meta2);
			inventory.setResult(result);
		}
	}
}
