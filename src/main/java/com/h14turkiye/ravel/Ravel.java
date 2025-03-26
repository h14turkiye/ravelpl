package com.h14turkiye.ravel;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.SmithingTransformRecipe;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.h14turkiye.ravel.listener.ChatFormatMiniMessageParser;
import com.h14turkiye.ravel.listener.CustomTrimRecipeListener;
import com.h14turkiye.ravel.listener.FirstJoinListener;
import com.h14turkiye.ravel.placeholder.ChevyPlaceholder;

import net.kyori.adventure.platform.AudienceProvider;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public final class Ravel extends JavaPlugin implements Listener {
	private AudienceProvider audienceProvider;
	private MiniMessage miniMessage;

	@Override
	public void onEnable() {
		audienceProvider = BukkitAudiences.create(this);
		miniMessage = MiniMessage.miniMessage();
		registerPlaceholders();
		registerListeners();
		registerRecipes();
	}
	
	public void registerPlaceholders() {
		new ChevyPlaceholder().register();
	}

	private void registerListeners() {
		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(new ChatFormatMiniMessageParser(miniMessage,audienceProvider), this);
		pluginManager.registerEvents(new CustomTrimRecipeListener(), this);
		pluginManager.registerEvents(new FirstJoinListener(), this);
	}

	

	public void registerRecipes() {
		// CUSTOM_TRIM
		RecipeChoice template = new RecipeChoice.MaterialChoice(Material.CARROT_ON_A_STICK);
		RecipeChoice base = new RecipeChoice.MaterialChoice(Material.BOW, Material.NETHERITE_AXE, Material.NETHERITE_PICKAXE, Material.NETHERITE_HOE, Material.NETHERITE_SHOVEL, Material.NETHERITE_SWORD, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_BOOTS, Material.CHAINMAIL_HELMET, Material.CHAINMAIL_LEGGINGS, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_HELMET, Material.GOLDEN_BOOTS, Material.GOLDEN_LEGGINGS, Material.IRON_CHESTPLATE, Material.IRON_BOOTS, Material.IRON_HELMET, Material.IRON_LEGGINGS, Material.LEATHER_CHESTPLATE, Material.LEATHER_BOOTS, Material.LEATHER_HELMET, Material.LEATHER_LEGGINGS, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_HELMET, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS, Material.NETHERITE_BOOTS, Material.NETHERITE_CHESTPLATE, Material.NETHERITE_HELMET, Material.NETHERITE_LEGGINGS);
		RecipeChoice addition = new RecipeChoice.MaterialChoice(Material.EMERALD, Material.REDSTONE, Material.LAPIS_LAZULI, Material.AMETHYST_SHARD, Material.QUARTZ, Material.NETHERITE_INGOT, Material.DIAMOND, Material.GOLD_INGOT, Material.IRON_INGOT, Material.COPPER_INGOT);

		NamespacedKey key = new NamespacedKey(this, "custom_trim");
		NamespacedKey key2 = new NamespacedKey(this, "custom_trim_2");
		SmithingTransformRecipe recipe = new SmithingTransformRecipe(key, new ItemStack(Material.ACACIA_BUTTON), template, base, addition, true);
		Bukkit.removeRecipe(key);
		Bukkit.addRecipe(recipe);
		
		ItemStack result = new ItemStack(Material.CARROT_ON_A_STICK);
		ItemMeta meta = result.getItemMeta();
		meta.setCustomModelData(2);
		meta.displayName(Component.text("Şablon"));
		result.setItemMeta(meta);
		Damageable meta2 = (Damageable) result.getItemMeta();
		meta2.setDamage(Math.max(1, meta2.getDamage()-1));
		ItemStack realResult = result.clone();
		realResult.setItemMeta(meta2);
		ShapedRecipe recipe2 = new ShapedRecipe(key2, realResult.add());
		recipe2.shape("DRD", "DAD", "DDD");
		recipe2.setIngredient('D', new ItemStack(Material.AIR));
		recipe2.setIngredient('A', new ItemStack(Material.GOLD_INGOT));
		recipe2.setIngredient('R', result);
		Bukkit.removeRecipe(key2);
		Bukkit.addRecipe(recipe2);
		// CUSTOM_TRIM END
	}
}
