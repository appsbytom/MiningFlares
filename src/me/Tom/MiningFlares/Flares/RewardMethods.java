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

//All methods linked with rewards

@SuppressWarnings("deprecation")
public class RewardMethods {

	private PluginCore core;
	private ConfigManager configs;
	private Utils<?> utils;
	public RewardMethods(PluginCore pl) {
		core = pl;
		configs = core.getConfigs();
		utils = core.getUtils();
		tier1rewards = new Utils<ItemStack>(core);
		tier2rewards = new Utils<ItemStack>(core);
		tier3rewards = new Utils<ItemStack>(core);
		tier4rewards = new Utils<ItemStack>(core);
		tier5rewards = new Utils<ItemStack>(core);
	}
	
	public void setup() {
		setupTier1Rewards();
		setupTier2Rewards();
		setupTier3Rewards();
		setupTier4Rewards();
		setupTier5Rewards();
	}
	
	private Utils<ItemStack> tier1rewards;
	public Utils<ItemStack> getTier1Rewards() {
		return tier1rewards;
	}
	public void setupTier1Rewards() {
		for (String numbers : configs.getTier1Cfg().getConfigurationSection("FlareRewards").getKeys(false)) {
			if(configs.getTier1Cfg().getDouble("FlareRewards." + numbers + ".Chance") > 0) {
				List<String> tier1lore = new ArrayList<String>();
				for(String lore : configs.getTier1Cfg().getStringList("FlareRewards." + numbers + ".Lore")) {
					if(configs.getTier1Cfg().getString("FlareRewards." + numbers + ".Lore") != null) {
						tier1lore.add(utils.format(lore));
					}
				}
				if(configs.getTier1Cfg().getString("FlareRewards." + numbers + ".Enchants") != null) {
					List<String> enchantments = configs.getTier1Cfg().getStringList("FlareRewards." + numbers + ".Enchants");
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
					if(configs.getTier1Cfg().getInt("FlareRewards." + numbers + ".Item") == 397) {
						tier1rewards.put(utils.makeHead(configs.getTier1Cfg().getString("FlareRewards." + numbers + ".Data"), configs.getTier1Cfg().getInt("FlareRewards." + numbers + ".Amount"), utils.format(configs.getTier1Cfg().getString("FlareRewards." + numbers + ".Name")), tier1lore, enchants), configs.getTier1Cfg().getDouble("FlareRewards." + numbers + ".Chance"));
					}
					else {
						tier1rewards.put(utils.makeItem(Material.getMaterial(configs.getTier1Cfg().getInt("FlareRewards." + numbers + ".Item")), configs.getTier1Cfg().getInt("FlareRewards." + numbers + ".Amount"), (short)configs.getTier1Cfg().getInt("FlareRewards." + numbers + ".Data"), utils.format(configs.getTier1Cfg().getString("FlareRewards." + numbers + ".Name")), tier1lore, enchants), configs.getTier1Cfg().getDouble("FlareRewards." + numbers + ".Chance"));
					}
				}
				else {
					if(configs.getTier1Cfg().getInt("FlareRewards." + numbers + ".Item") == 397) {
						tier1rewards.put(utils.makeHead(configs.getTier1Cfg().getString("FlareRewards." + numbers + ".Data"), configs.getTier1Cfg().getInt("FlareRewards." + numbers + ".Amount"), utils.format(configs.getTier1Cfg().getString("FlareRewards." + numbers + ".Name")), tier1lore), configs.getTier1Cfg().getDouble("FlareRewards." + numbers + ".Chance"));
					}
					else {
						tier1rewards.put(utils.makeItem(Material.getMaterial(configs.getTier1Cfg().getInt("FlareRewards." + numbers + ".Item")), configs.getTier1Cfg().getInt("FlareRewards." + numbers + ".Amount"), (short)configs.getTier1Cfg().getInt("FlareRewards." + numbers + ".Data"), utils.format(configs.getTier1Cfg().getString("FlareRewards." + numbers + ".Name")), tier1lore), configs.getTier1Cfg().getDouble("FlareRewards." + numbers + ".Chance"));
					}
				}
			}
		}
	}
	
	private Utils<ItemStack> tier2rewards;
	public Utils<ItemStack> getTier2Rewards() {
		return tier2rewards;
	}
	public void setupTier2Rewards() {
		for (String numbers : configs.getTier2Cfg().getConfigurationSection("FlareRewards").getKeys(false)) {
			if(configs.getTier2Cfg().getDouble("FlareRewards." + numbers + ".Chance") > 0) {
				List<String> tier2lore = new ArrayList<String>();
				for(String lore : configs.getTier2Cfg().getStringList("FlareRewards." + numbers + ".Lore")) {
					if(configs.getTier2Cfg().getString("FlareRewards." + numbers + ".Lore") != null) {
						tier2lore.add(utils.format(lore));
					}
				}
				if(configs.getTier2Cfg().getString("FlareRewards." + numbers + ".Enchants") != null) {
					List<String> enchantments = configs.getTier2Cfg().getStringList("FlareRewards." + numbers + ".Enchants");
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
					if(configs.getTier2Cfg().getInt("FlareRewards." + numbers + ".Item") == 397) {
						tier2rewards.put(utils.makeHead(configs.getTier2Cfg().getString("FlareRewards." + numbers + ".Data"), configs.getTier2Cfg().getInt("FlareRewards." + numbers + ".Amount"), utils.format(configs.getTier2Cfg().getString("FlareRewards." + numbers + ".Name")), tier2lore, enchants), configs.getTier2Cfg().getDouble("FlareRewards." + numbers + ".Chance"));
					}
					else {
						tier2rewards.put(utils.makeItem(Material.getMaterial(configs.getTier2Cfg().getInt("FlareRewards." + numbers + ".Item")), configs.getTier2Cfg().getInt("FlareRewards." + numbers + ".Amount"), (short)configs.getTier2Cfg().getInt("FlareRewards." + numbers + ".Data"), utils.format(configs.getTier2Cfg().getString("FlareRewards." + numbers + ".Name")), tier2lore, enchants), configs.getTier2Cfg().getDouble("FlareRewards." + numbers + ".Chance"));
					}
				}
				else {
					if(configs.getTier2Cfg().getInt("FlareRewards." + numbers + ".Item") == 397) {
						tier2rewards.put(utils.makeHead(configs.getTier2Cfg().getString("FlareRewards." + numbers + ".Data"), configs.getTier2Cfg().getInt("FlareRewards." + numbers + ".Amount"), utils.format(configs.getTier2Cfg().getString("FlareRewards." + numbers + ".Name")), tier2lore), configs.getTier2Cfg().getDouble("FlareRewards." + numbers + ".Chance"));
					}
					else {
						tier2rewards.put(utils.makeItem(Material.getMaterial(configs.getTier2Cfg().getInt("FlareRewards." + numbers + ".Item")), configs.getTier2Cfg().getInt("FlareRewards." + numbers + ".Amount"), (short)configs.getTier2Cfg().getInt("FlareRewards." + numbers + ".Data"), utils.format(configs.getTier2Cfg().getString("FlareRewards." + numbers + ".Name")), tier2lore), configs.getTier2Cfg().getDouble("FlareRewards." + numbers + ".Chance"));
					}
				}
			}
		}
	}
	
	private Utils<ItemStack> tier3rewards;
	public Utils<ItemStack> getTier3Rewards() {
		return tier3rewards;
	}
	public void setupTier3Rewards() {
		for (String numbers : configs.getTier3Cfg().getConfigurationSection("FlareRewards").getKeys(false)) {
			if(configs.getTier3Cfg().getDouble("FlareRewards." + numbers + ".Chance") > 0) {
				List<String> tier3lore = new ArrayList<String>();
				for(String lore : configs.getTier3Cfg().getStringList("FlareRewards." + numbers + ".Lore")) {
					if(configs.getTier3Cfg().getString("FlareRewards." + numbers + ".Lore") != null) {
						tier3lore.add(utils.format(lore));
					}
				}
				if(configs.getTier3Cfg().getString("FlareRewards." + numbers + ".Enchants") != null) {
					List<String> enchantments = configs.getTier3Cfg().getStringList("FlareRewards." + numbers + ".Enchants");
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
					if(configs.getTier3Cfg().getInt("FlareRewards." + numbers + ".Item") == 397) {
						tier3rewards.put(utils.makeHead(configs.getTier3Cfg().getString("FlareRewards." + numbers + ".Data"), configs.getTier3Cfg().getInt("FlareRewards." + numbers + ".Amount"), utils.format(configs.getTier3Cfg().getString("FlareRewards." + numbers + ".Name")), tier3lore, enchants), configs.getTier3Cfg().getDouble("FlareRewards." + numbers + ".Chance"));
					}
					else {
						tier3rewards.put(utils.makeItem(Material.getMaterial(configs.getTier3Cfg().getInt("FlareRewards." + numbers + ".Item")), configs.getTier3Cfg().getInt("FlareRewards." + numbers + ".Amount"), (short)configs.getTier3Cfg().getInt("FlareRewards." + numbers + ".Data"), utils.format(configs.getTier3Cfg().getString("FlareRewards." + numbers + ".Name")), tier3lore, enchants), configs.getTier3Cfg().getDouble("FlareRewards." + numbers + ".Chance"));
					}
				}
				else {
					if(configs.getTier3Cfg().getInt("FlareRewards." + numbers + ".Item") == 397) {
						tier3rewards.put(utils.makeHead(configs.getTier3Cfg().getString("FlareRewards." + numbers + ".Data"), configs.getTier3Cfg().getInt("FlareRewards." + numbers + ".Amount"), utils.format(configs.getTier3Cfg().getString("FlareRewards." + numbers + ".Name")), tier3lore), configs.getTier3Cfg().getDouble("FlareRewards." + numbers + ".Chance"));
					}
					else {
						tier3rewards.put(utils.makeItem(Material.getMaterial(configs.getTier3Cfg().getInt("FlareRewards." + numbers + ".Item")), configs.getTier3Cfg().getInt("FlareRewards." + numbers + ".Amount"), (short)configs.getTier3Cfg().getInt("FlareRewards." + numbers + ".Data"), utils.format(configs.getTier3Cfg().getString("FlareRewards." + numbers + ".Name")), tier3lore), configs.getTier3Cfg().getDouble("FlareRewards." + numbers + ".Chance"));
					}
				}
			}
		}
	}
	
	private Utils<ItemStack> tier4rewards;
	public Utils<ItemStack> getTier4Rewards() {
		return tier4rewards;
	}
	public void setupTier4Rewards() {
		for (String numbers : configs.getTier4Cfg().getConfigurationSection("FlareRewards").getKeys(false)) {
			if(configs.getTier4Cfg().getDouble("FlareRewards." + numbers + ".Chance") > 0) {
				List<String> tier4lore = new ArrayList<String>();
				for(String lore : configs.getTier4Cfg().getStringList("FlareRewards." + numbers + ".Lore")) {
					if(configs.getTier4Cfg().getString("FlareRewards." + numbers + ".Lore") != null) {
						tier4lore.add(utils.format(lore));
					}
				}
				if(configs.getTier4Cfg().getString("FlareRewards." + numbers + ".Enchants") != null) {
					List<String> enchantments = configs.getTier4Cfg().getStringList("FlareRewards." + numbers + ".Enchants");
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
					if(configs.getTier4Cfg().getInt("FlareRewards." + numbers + ".Item") == 397) {
						tier4rewards.put(utils.makeHead(configs.getTier4Cfg().getString("FlareRewards." + numbers + ".Data"), configs.getTier4Cfg().getInt("FlareRewards." + numbers + ".Amount"), utils.format(configs.getTier4Cfg().getString("FlareRewards." + numbers + ".Name")), tier4lore, enchants), configs.getTier4Cfg().getDouble("FlareRewards." + numbers + ".Chance"));
					}
					else {
						tier4rewards.put(utils.makeItem(Material.getMaterial(configs.getTier4Cfg().getInt("FlareRewards." + numbers + ".Item")), configs.getTier4Cfg().getInt("FlareRewards." + numbers + ".Amount"), (short)configs.getTier4Cfg().getInt("FlareRewards." + numbers + ".Data"), utils.format(configs.getTier4Cfg().getString("FlareRewards." + numbers + ".Name")), tier4lore, enchants), configs.getTier4Cfg().getDouble("FlareRewards." + numbers + ".Chance"));
					}
				}
				else {
					if(configs.getTier4Cfg().getInt("FlareRewards." + numbers + ".Item") == 397) {
						tier4rewards.put(utils.makeHead(configs.getTier4Cfg().getString("FlareRewards." + numbers + ".Data"), configs.getTier4Cfg().getInt("FlareRewards." + numbers + ".Amount"), utils.format(configs.getTier4Cfg().getString("FlareRewards." + numbers + ".Name")), tier4lore), configs.getTier4Cfg().getDouble("FlareRewards." + numbers + ".Chance"));
					}
					else {
						tier4rewards.put(utils.makeItem(Material.getMaterial(configs.getTier4Cfg().getInt("FlareRewards." + numbers + ".Item")), configs.getTier4Cfg().getInt("FlareRewards." + numbers + ".Amount"), (short)configs.getTier4Cfg().getInt("FlareRewards." + numbers + ".Data"), utils.format(configs.getTier4Cfg().getString("FlareRewards." + numbers + ".Name")), tier4lore), configs.getTier4Cfg().getDouble("FlareRewards." + numbers + ".Chance"));
					}
				}
			}
		}
	}
	
	private Utils<ItemStack> tier5rewards;
	public Utils<ItemStack> getTier5Rewards() {
		return tier5rewards;
	}
	public void setupTier5Rewards() {
		for (String numbers : configs.getTier5Cfg().getConfigurationSection("FlareRewards").getKeys(false)) {
			if(configs.getTier5Cfg().getDouble("FlareRewards." + numbers + ".Chance") > 0) {
				List<String> tier5lore = new ArrayList<String>();
				for(String lore : configs.getTier5Cfg().getStringList("FlareRewards." + numbers + ".Lore")) {
					if(configs.getTier5Cfg().getString("FlareRewards." + numbers + ".Lore") != null) {
						tier5lore.add(utils.format(lore));
					}
				}
				if(configs.getTier5Cfg().getString("FlareRewards." + numbers + ".Enchants") != null) {
					List<String> enchantments = configs.getTier5Cfg().getStringList("FlareRewards." + numbers + ".Enchants");
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
					if(configs.getTier5Cfg().getInt("FlareRewards." + numbers + ".Item") == 397) {
						tier5rewards.put(utils.makeHead(configs.getTier5Cfg().getString("FlareRewards." + numbers + ".Data"), configs.getTier5Cfg().getInt("FlareRewards." + numbers + ".Amount"), utils.format(configs.getTier5Cfg().getString("FlareRewards." + numbers + ".Name")), tier5lore, enchants), configs.getTier5Cfg().getDouble("FlareRewards." + numbers + ".Chance"));
					}
					else {
						tier5rewards.put(utils.makeItem(Material.getMaterial(configs.getTier5Cfg().getInt("FlareRewards." + numbers + ".Item")), configs.getTier5Cfg().getInt("FlareRewards." + numbers + ".Amount"), (short)configs.getTier5Cfg().getInt("FlareRewards." + numbers + ".Data"), utils.format(configs.getTier5Cfg().getString("FlareRewards." + numbers + ".Name")), tier5lore, enchants), configs.getTier5Cfg().getDouble("FlareRewards." + numbers + ".Chance"));
					}
				}
				else {
					if(configs.getTier5Cfg().getInt("FlareRewards." + numbers + ".Item") == 397) {
						tier5rewards.put(utils.makeHead(configs.getTier5Cfg().getString("FlareRewards." + numbers + ".Data"), configs.getTier5Cfg().getInt("FlareRewards." + numbers + ".Amount"), utils.format(configs.getTier5Cfg().getString("FlareRewards." + numbers + ".Name")), tier5lore), configs.getTier5Cfg().getDouble("FlareRewards." + numbers + ".Chance"));
					}
					else {
						tier5rewards.put(utils.makeItem(Material.getMaterial(configs.getTier5Cfg().getInt("FlareRewards." + numbers + ".Item")), configs.getTier5Cfg().getInt("FlareRewards." + numbers + ".Amount"), (short)configs.getTier5Cfg().getInt("FlareRewards." + numbers + ".Data"), utils.format(configs.getTier5Cfg().getString("FlareRewards." + numbers + ".Name")), tier5lore), configs.getTier5Cfg().getDouble("FlareRewards." + numbers + ".Chance"));
					}
				}
			}
		}
	}
}
