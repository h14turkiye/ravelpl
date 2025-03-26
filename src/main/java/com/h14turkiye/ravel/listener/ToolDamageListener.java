package com.h14turkiye.ravel.listener;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
public class ToolDamageListener implements Listener {
	
	List<Material> materialList = new ArrayList<>();
	public ToolDamageListener() {
		materialList.add(Material.DIAMOND_SWORD); materialList.add(Material.GOLDEN_SWORD); materialList.add(Material.IRON_SWORD); materialList.add(Material.NETHERITE_SWORD); materialList.add(Material.STONE_SWORD); materialList.add(Material.WOODEN_SWORD);
		materialList.add(Material.DIAMOND_AXE); materialList.add(Material.GOLDEN_AXE); materialList.add(Material.IRON_AXE); materialList.add(Material.NETHERITE_AXE); materialList.add(Material.STONE_AXE); materialList.add(Material.WOODEN_AXE);
		materialList.add(Material.DIAMOND_PICKAXE); materialList.add(Material.GOLDEN_PICKAXE); materialList.add(Material.IRON_PICKAXE); materialList.add(Material.NETHERITE_PICKAXE); materialList.add(Material.STONE_PICKAXE); materialList.add(Material.WOODEN_PICKAXE);
		materialList.add(Material.DIAMOND_SHOVEL); materialList.add(Material.GOLDEN_SHOVEL); materialList.add(Material.IRON_SHOVEL); materialList.add(Material.NETHERITE_SHOVEL); materialList.add(Material.STONE_SHOVEL); materialList.add(Material.WOODEN_SHOVEL);
		materialList.add(Material.DIAMOND_HOE); materialList.add(Material.GOLDEN_HOE); materialList.add(Material.IRON_HOE); materialList.add(Material.NETHERITE_HOE); materialList.add(Material.STONE_HOE); materialList.add(Material.WOODEN_HOE);
	}
	
	@EventHandler
	public void onToolDamage(PlayerItemDamageEvent event) {
		Player player = event.getPlayer();
		ItemStack item = event.getItem();
		if(player.hasPermission("ravel.unbreakabletool")) {
			if(materialList.contains(item.getType())) event.setCancelled(true);
		}
	}
}
