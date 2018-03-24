package me.Tom.MiningFlares.Flares;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import me.Tom.MiningFlares.ConfigManager;
import me.Tom.MiningFlares.PluginCore;
import me.Tom.MiningFlares.Utils;

//All methods linked with flares

@SuppressWarnings("deprecation")
public class FlareMethods {

	private PluginCore core;
	private ConfigManager configs;
	private Utils<?> utils;
	public FlareMethods(PluginCore pl) {
		core = pl;
		configs = core.getConfigs();
		utils = core.getUtils();
	}
	
	public ItemStack Tier1() {
		if(configs.getFlaresCfg().get("MiningFlares.tier1flare.Enchants") != null) {
			List<String> enchantments = configs.getFlaresCfg().getStringList("MiningFlares.tier1flare.Enchants");
			Map<Enchantment, Integer> enchants = new HashMap<Enchantment, Integer>();
			for(String e : enchantments) {
				if(e.contains(";")) {
					String[] parts = e.split(";");
					Enchantment enc = Enchantment.getByName(parts[0]);
					int level = 1;
					level = Integer.parseInt(parts[1]);
					enchants.put(enc, level);
				}
			}
			List<String> tier1lore = new ArrayList<>();
			for (String lore : configs.getFlaresCfg().getStringList("MiningFlares.tier1flare.Lore")) {
				tier1lore.add(utils.format(lore));
			}
			return utils.makeItem(Material.getMaterial(configs.getFlaresCfg().getInt("MiningFlares.tier1flare.Item")), 1, (short)configs.getFlaresCfg().getInt("MiningFlares.tier1flare.Data"), utils.format(configs.getFlaresCfg().getString("MiningFlares.tier1flare.Name")), tier1lore, enchants);
		}
		else {
			List<String> tier1lore = new ArrayList<>();
			for (String lore : configs.getFlaresCfg().getStringList("MiningFlares.tier1flare.Lore")) {
				tier1lore.add(utils.format(lore));
			}
			return utils.makeItem(Material.getMaterial(configs.getFlaresCfg().getInt("MiningFlares.tier1flare.Item")), 1, (short)configs.getFlaresCfg().getInt("MiningFlares.tier1flare.Data"), utils.format(configs.getFlaresCfg().getString("MiningFlares.tier1flare.Name")), tier1lore);
		}
	}
	
	public ItemStack Tier2() {
		if(configs.getFlaresCfg().get("MiningFlares.tier2flare.Enchants") != null) {
			List<String> enchantments = configs.getFlaresCfg().getStringList("MiningFlares.tier2flare.Enchants");
			Map<Enchantment, Integer> enchants = new HashMap<Enchantment, Integer>();
			for(String e : enchantments) {
				if(e.contains(";")) {
					String[] parts = e.split(";");
					Enchantment enc = Enchantment.getByName(parts[0]);
					int level = 1;
					level = Integer.parseInt(parts[1]);
					enchants.put(enc, level);
				}
			}
			List<String> tier2lore = new ArrayList<>();
			for (String lore : configs.getFlaresCfg().getStringList("MiningFlares.tier2flare.Lore")) {
				tier2lore.add(utils.format(lore));
			}
		return utils.makeItem(Material.getMaterial(configs.getFlaresCfg().getInt("MiningFlares.tier2flare.Item")), 1, (short)configs.getFlaresCfg().getInt("MiningFlares.tier2flare.Data"), utils.format(configs.getFlaresCfg().getString("MiningFlares.tier2flare.Name")), tier2lore, enchants);
		}
		else {
			List<String> tier2lore = new ArrayList<>();
			for (String lore : configs.getFlaresCfg().getStringList("MiningFlares.tier2flare.Lore")) {
				tier2lore.add(utils.format(lore));
			}
			return utils.makeItem(Material.getMaterial(configs.getFlaresCfg().getInt("MiningFlares.tier2flare.Item")), 1, (short)configs.getFlaresCfg().getInt("MiningFlares.tier2flare.Data"), utils.format(configs.getFlaresCfg().getString("MiningFlares.tier2flare.Name")), tier2lore);
		}
	}
	
	public ItemStack Tier3() {
		if(configs.getFlaresCfg().get("MiningFlares.tier3flare.Enchants") != null) {
			List<String> enchantments = configs.getFlaresCfg().getStringList("MiningFlares.tier3flare.Enchants");
			Map<Enchantment, Integer> enchants = new HashMap<Enchantment, Integer>();
			for(String e : enchantments) {
				if(e.contains(";")) {
					String[] parts = e.split(";");
					Enchantment enc = Enchantment.getByName(parts[0]);
					int level = 1;
					level = Integer.parseInt(parts[1]);
					enchants.put(enc, level);
				}
			}
			List<String> tier3lore = new ArrayList<>();
			for (String lore : configs.getFlaresCfg().getStringList("MiningFlares.tier3flare.Lore")) {
				tier3lore.add(utils.format(lore));
			}
		return utils.makeItem(Material.getMaterial(configs.getFlaresCfg().getInt("MiningFlares.tier3flare.Item")), 1, (short)configs.getFlaresCfg().getInt("MiningFlares.tier3flare.Data"), utils.format(configs.getFlaresCfg().getString("MiningFlares.tier3flare.Name")), tier3lore, enchants);
		}
		else {
			List<String> tier3lore = new ArrayList<>();
			for (String lore : configs.getFlaresCfg().getStringList("MiningFlares.tier3flare.Lore")) {
				tier3lore.add(utils.format(lore));
			}
			return utils.makeItem(Material.getMaterial(configs.getFlaresCfg().getInt("MiningFlares.tier3flare.Item")), 1, (short)configs.getFlaresCfg().getInt("MiningFlares.tier3flare.Data"), utils.format(configs.getFlaresCfg().getString("MiningFlares.tier3flare.Name")), tier3lore);
		}
	}
	
	public ItemStack Tier4() {
		if(configs.getFlaresCfg().get("MiningFlares.tier4flare.Enchants") != null) {
			List<String> enchantments = configs.getFlaresCfg().getStringList("MiningFlares.tier4flare.Enchants");
			Map<Enchantment, Integer> enchants = new HashMap<Enchantment, Integer>();
			for(String e : enchantments) {
				if(e.contains(";")) {
					String[] parts = e.split(";");
					Enchantment enc = Enchantment.getByName(parts[0]);
					int level = 1;
					level = Integer.parseInt(parts[1]);
					enchants.put(enc, level);
				}
			}
			List<String> tier4lore = new ArrayList<>();
			for (String lore : configs.getFlaresCfg().getStringList("MiningFlares.tier4flare.Lore")) {
				tier4lore.add(utils.format(lore));
			}
		return utils.makeItem(Material.getMaterial(configs.getFlaresCfg().getInt("MiningFlares.tier4flare.Item")), 1, (short)configs.getFlaresCfg().getInt("MiningFlares.tier4flare.Data"), utils.format(configs.getFlaresCfg().getString("MiningFlares.tier4flare.Name")), tier4lore, enchants);
		}
		else {
			List<String> tier4lore = new ArrayList<>();
			for (String lore : configs.getFlaresCfg().getStringList("MiningFlares.tier4flare.Lore")) {
				tier4lore.add(utils.format(lore));
			}
			return utils.makeItem(Material.getMaterial(configs.getFlaresCfg().getInt("MiningFlares.tier4flare.Item")), 1, (short)configs.getFlaresCfg().getInt("MiningFlares.tier4flare.Data"), utils.format(configs.getFlaresCfg().getString("MiningFlares.tier4flare.Name")), tier4lore);
		}
	}
	
	public ItemStack Tier5() {
		if(configs.getFlaresCfg().get("MiningFlares.tier5flare.Enchants") != null) {
			List<String> enchantments = configs.getFlaresCfg().getStringList("MiningFlares.tier5flare.Enchants");
			Map<Enchantment, Integer> enchants = new HashMap<Enchantment, Integer>();
			for(String e : enchantments) {
				if(e.contains(";")) {
					String[] parts = e.split(";");
					Enchantment enc = Enchantment.getByName(parts[0]);
					int level = 1;
					level = Integer.parseInt(parts[1]);
					enchants.put(enc, level);
				}
			}
			List<String> tier5lore = new ArrayList<>();
			for (String lore : configs.getFlaresCfg().getStringList("MiningFlares.tier5flare.Lore")) {
				tier5lore.add(utils.format(lore));
			}
		return utils.makeItem(Material.getMaterial(configs.getFlaresCfg().getInt("MiningFlares.tier5flare.Item")), 1, (short)configs.getFlaresCfg().getInt("MiningFlares.tier5flare.Data"), utils.format(configs.getFlaresCfg().getString("MiningFlares.tier5flare.Name")), tier5lore, enchants);
		}
		else {
			List<String> tier5lore = new ArrayList<>();
			for (String lore : configs.getFlaresCfg().getStringList("MiningFlares.tier5flare.Lore")) {
				tier5lore.add(utils.format(lore));
			}
			return utils.makeItem(Material.getMaterial(configs.getFlaresCfg().getInt("MiningFlares.tier5flare.Item")), 1, (short)configs.getFlaresCfg().getInt("MiningFlares.tier5flare.Data"), utils.format(configs.getFlaresCfg().getString("MiningFlares.tier5flare.Name")), tier5lore);
		}
	}
}
