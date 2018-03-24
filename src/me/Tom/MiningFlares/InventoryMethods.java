package me.Tom.MiningFlares;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;

//All methods linked with inventories

@SuppressWarnings("deprecation")
public class InventoryMethods {

	private PluginCore core;
	private ConfigManager configs;
	private Utils<?> utils;
	public InventoryMethods(PluginCore pl) {
		core = pl;
		configs = core.getConfigs();
		utils = core.getUtils();
	}
	
	public void setup() {
		createRewardsMenu();
		setupRewardsMenu();
		createTier1RewardsMenu();
		setupTier1RewardsMenu();
		createTier2RewardsMenu();
		setupTier2RewardsMenu();
		createTier3RewardsMenu();
		setupTier3RewardsMenu();
		createTier4RewardsMenu();
		setupTier4RewardsMenu();
		createTier5RewardsMenu();
		setupTier5RewardsMenu();
	}
	
	private Inventory rewardsmenu;
	public Inventory getRewardsMenu() {
		return rewardsmenu;
	}
	public Inventory createRewardsMenu() {
		return rewardsmenu = Bukkit.createInventory(null, 9, utils.format(configs.getFlaresCfg().getString("GUIFiller.GUIName")));
	}
	public void setupRewardsMenu() {
		List<String> tier1 = new ArrayList<String>();
		for(String string : configs.getFlaresCfg().getStringList("MiningFlares.tier1flare.RewardsGUI.Lore")) {
			tier1.add(utils.format(string));
		}
		if(configs.getFlaresCfg().getBoolean("MiningFlares.tier1flare.RewardsGUI.Glow")) {
			rewardsmenu.setItem(configs.getFlaresCfg().getInt("MiningFlares.tier1flare.RewardsGUI.Slot"), utils.addHiddenGlow(utils.makeItem(Material.getMaterial(configs.getFlaresCfg().getInt("MiningFlares.tier1flare.RewardsGUI.Item")), 1, (short)configs.getFlaresCfg().getInt("MiningFlares.tier1flare.RewardsGUI.Data"), utils.format(configs.getFlaresCfg().getString("MiningFlares.tier1flare.RewardsGUI.Name")), tier1)));
		}
		else {
			rewardsmenu.setItem(configs.getFlaresCfg().getInt("MiningFlares.tier1flare.RewardsGUI.Slot"), utils.makeItem(Material.getMaterial(configs.getFlaresCfg().getInt("MiningFlares.tier1flare.RewardsGUI.Item")), 1, (short)configs.getFlaresCfg().getInt("MiningFlares.tier1flare.RewardsGUI.Data"), utils.format(configs.getFlaresCfg().getString("MiningFlares.tier1flare.RewardsGUI.Name")), tier1));
		}
		List<String> tier2 = new ArrayList<String>();
		for(String string : configs.getFlaresCfg().getStringList("MiningFlares.tier2flare.RewardsGUI.Lore")) {
			tier2.add(utils.format(string));
		}
		if(configs.getFlaresCfg().getBoolean("MiningFlares.tier2flare.RewardsGUI.Glow")) {
			rewardsmenu.setItem(configs.getFlaresCfg().getInt("MiningFlares.tier2flare.RewardsGUI.Slot"), utils.addHiddenGlow(utils.makeItem(Material.getMaterial(configs.getFlaresCfg().getInt("MiningFlares.tier2flare.RewardsGUI.Item")), 1, (short)configs.getFlaresCfg().getInt("MiningFlares.tier2flare.RewardsGUI.Data"), utils.format(configs.getFlaresCfg().getString("MiningFlares.tier2flare.RewardsGUI.Name")), tier2)));
		}
		else {
			rewardsmenu.setItem(configs.getFlaresCfg().getInt("MiningFlares.tier2flare.RewardsGUI.Slot"), utils.makeItem(Material.getMaterial(configs.getFlaresCfg().getInt("MiningFlares.tier2flare.RewardsGUI.Item")), 1, (short)configs.getFlaresCfg().getInt("MiningFlares.tier2flare.RewardsGUI.Data"), utils.format(configs.getFlaresCfg().getString("MiningFlares.tier2flare.RewardsGUI.Name")), tier2));
		}
		List<String> tier3 = new ArrayList<String>();
		for(String string : configs.getFlaresCfg().getStringList("MiningFlares.tier3flare.RewardsGUI.Lore")) {
			tier3.add(utils.format(string));
		}
		if(configs.getFlaresCfg().getBoolean("MiningFlares.tier3flare.RewardsGUI.Glow")) {
			rewardsmenu.setItem(configs.getFlaresCfg().getInt("MiningFlares.tier3flare.RewardsGUI.Slot"), utils.addHiddenGlow(utils.makeItem(Material.getMaterial(configs.getFlaresCfg().getInt("MiningFlares.tier3flare.RewardsGUI.Item")), 1, (short)configs.getFlaresCfg().getInt("MiningFlares.tier3flare.RewardsGUI.Data"), utils.format(configs.getFlaresCfg().getString("MiningFlares.tier3flare.RewardsGUI.Name")), tier3)));
		}
		else {
			rewardsmenu.setItem(configs.getFlaresCfg().getInt("MiningFlares.tier3flare.RewardsGUI.Slot"), utils.makeItem(Material.getMaterial(configs.getFlaresCfg().getInt("MiningFlares.tier3flare.RewardsGUI.Item")), 1, (short)configs.getFlaresCfg().getInt("MiningFlares.tier3flare.RewardsGUI.Data"), utils.format(configs.getFlaresCfg().getString("MiningFlares.tier3flare.RewardsGUI.Name")), tier3));
		}
		List<String> tier4 = new ArrayList<String>();
		for(String string : configs.getFlaresCfg().getStringList("MiningFlares.tier4flare.RewardsGUI.Lore")) {
			tier4.add(utils.format(string));
		}
		if(configs.getFlaresCfg().getBoolean("MiningFlares.tier4flare.RewardsGUI.Glow")) {
			rewardsmenu.setItem(configs.getFlaresCfg().getInt("MiningFlares.tier4flare.RewardsGUI.Slot"), utils.addHiddenGlow(utils.makeItem(Material.getMaterial(configs.getFlaresCfg().getInt("MiningFlares.tier4flare.RewardsGUI.Item")), 1, (short)configs.getFlaresCfg().getInt("MiningFlares.tier4flare.RewardsGUI.Data"), utils.format(configs.getFlaresCfg().getString("MiningFlares.tier4flare.RewardsGUI.Name")), tier4)));
		}
		else {
			rewardsmenu.setItem(configs.getFlaresCfg().getInt("MiningFlares.tier4flare.RewardsGUI.Slot"), utils.makeItem(Material.getMaterial(configs.getFlaresCfg().getInt("MiningFlares.tier4flare.RewardsGUI.Item")), 1, (short)configs.getFlaresCfg().getInt("MiningFlares.tier4flare.RewardsGUI.Data"), utils.format(configs.getFlaresCfg().getString("MiningFlares.tier4flare.RewardsGUI.Name")), tier4));
		}
		List<String> tier5 = new ArrayList<String>();
		for(String string : configs.getFlaresCfg().getStringList("MiningFlares.tier5flare.RewardsGUI.Lore")) {
			tier5.add(utils.format(string));
		}
		if(configs.getFlaresCfg().getBoolean("MiningFlares.tier5flare.RewardsGUI.Glow")) {
			rewardsmenu.setItem(configs.getFlaresCfg().getInt("MiningFlares.tier5flare.RewardsGUI.Slot"), utils.addHiddenGlow(utils.makeItem(Material.getMaterial(configs.getFlaresCfg().getInt("MiningFlares.tier5flare.RewardsGUI.Item")), 1, (short)configs.getFlaresCfg().getInt("MiningFlares.tier5flare.RewardsGUI.Data"), utils.format(configs.getFlaresCfg().getString("MiningFlares.tier5flare.RewardsGUI.Name")), tier5)));
		}
		else {
			rewardsmenu.setItem(configs.getFlaresCfg().getInt("MiningFlares.tier5flare.RewardsGUI.Slot"), utils.makeItem(Material.getMaterial(configs.getFlaresCfg().getInt("MiningFlares.tier5flare.RewardsGUI.Item")), 1, (short)configs.getFlaresCfg().getInt("MiningFlares.tier5flare.RewardsGUI.Data"), utils.format(configs.getFlaresCfg().getString("MiningFlares.tier5flare.RewardsGUI.Name")), tier5));
		}
		for (int i = 0; i < 9; i++) {
		    if (rewardsmenu.getItem(i) == null) {
		    	if(configs.getFlaresCfg().getBoolean("GUIFiller.Glow")) { 
		    		rewardsmenu.setItem(i, utils.addHiddenGlow(utils.makeItem(Material.getMaterial(configs.getFlaresCfg().getInt("GUIFiller.Item")), 1, (short)configs.getFlaresCfg().getInt("GUIFiller.Data"), utils.format(configs.getFlaresCfg().getString("GUIFiller.Name")))));
		    	}
		    	else {
		    		rewardsmenu.setItem(i, utils.makeItem(Material.getMaterial(configs.getFlaresCfg().getInt("GUIFiller.Item")), 1, (short)configs.getFlaresCfg().getInt("GUIFiller.Data"), utils.format(configs.getFlaresCfg().getString("GUIFiller.Name"))));
		    	}
		    }
		}
	}
	
	private Inventory tier1rewardsmenu;
	public Inventory getTier1RewardsMenu() {
		return tier1rewardsmenu;
	}
	public Inventory createTier1RewardsMenu() {
		return tier1rewardsmenu = Bukkit.createInventory(null, configs.getFlaresCfg().getInt("MiningFlares.tier1flare.RewardsGUI.GUISize"), utils.format(configs.getFlaresCfg().getString("MiningFlares.tier1flare.RewardsGUI.GUIName")));
	}
	public void setupTier1RewardsMenu() {
		for (String numbers : configs.getTier1Cfg().getConfigurationSection("FlareRewards").getKeys(false)) {
			if(configs.getTier1Cfg().getDouble("FlareRewards." + numbers + ".Chance") > 0) {
				List<String> tier1lore = new ArrayList<String>();
				for(String lore : configs.getTier1Cfg().getStringList("FlareRewards." + numbers + ".Lore")) {
					if(configs.getTier1Cfg().getString("FlareRewards." + numbers + ".Lore") != null) {
						tier1lore.add(utils.format(lore));
					}
				}
				tier1lore.add("");
				tier1lore.add(ChatColor.GOLD + "Chance: " + configs.getTier1Cfg().getDouble("FlareRewards." + numbers + ".Chance") + "%");
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
						tier1rewardsmenu.addItem(utils.makeHead(configs.getTier1Cfg().getString("FlareRewards." + numbers + ".Data"), configs.getTier1Cfg().getInt("FlareRewards." + numbers + ".Amount"), utils.format(configs.getTier1Cfg().getString("FlareRewards." + numbers + ".Name")), tier1lore, enchants));
					}
					else {
						tier1rewardsmenu.addItem(utils.makeItem(Material.getMaterial(configs.getTier1Cfg().getInt("FlareRewards." + numbers + ".Item")), configs.getTier1Cfg().getInt("FlareRewards." + numbers + ".Amount"), (short)configs.getTier1Cfg().getInt("FlareRewards." + numbers + ".Data"), utils.format(configs.getTier1Cfg().getString("FlareRewards." + numbers + ".Name")), tier1lore, enchants, ItemFlag.HIDE_ATTRIBUTES));
					}
				}
				else {
					if(configs.getTier1Cfg().getInt("FlareRewards." + numbers + ".Item") == 397) {
						tier1rewardsmenu.addItem(utils.makeHead(configs.getTier1Cfg().getString("FlareRewards." + numbers + ".Data"), configs.getTier1Cfg().getInt("FlareRewards." + numbers + ".Amount"), utils.format(configs.getTier1Cfg().getString("FlareRewards." + numbers + ".Name")), tier1lore));
					}
					else {
						tier1rewardsmenu.addItem(utils.makeItem(Material.getMaterial(configs.getTier1Cfg().getInt("FlareRewards." + numbers + ".Item")), configs.getTier1Cfg().getInt("FlareRewards." + numbers + ".Amount"), (short)configs.getTier1Cfg().getInt("FlareRewards." + numbers + ".Data"), utils.format(configs.getTier1Cfg().getString("FlareRewards." + numbers + ".Name")), tier1lore, ItemFlag.HIDE_ATTRIBUTES));
					}
				}
			}
		}
	}
	
	private Inventory tier2rewardsmenu;
	public Inventory getTier2RewardsMenu() {
		return tier2rewardsmenu;
	}
	public Inventory createTier2RewardsMenu() {
		return tier2rewardsmenu = Bukkit.createInventory(null, configs.getFlaresCfg().getInt("MiningFlares.tier2flare.RewardsGUI.GUISize"), utils.format(configs.getFlaresCfg().getString("MiningFlares.tier2flare.RewardsGUI.GUIName")));
	}
	public void setupTier2RewardsMenu() {
		for (String numbers : configs.getTier2Cfg().getConfigurationSection("FlareRewards").getKeys(false)) {
			if(configs.getTier2Cfg().getDouble("FlareRewards." + numbers + ".Chance") > 0) {
				List<String> tier2lore = new ArrayList<String>();
				for(String lore : configs.getTier2Cfg().getStringList("FlareRewards." + numbers + ".Lore")) {
					if(configs.getTier2Cfg().getString("FlareRewards." + numbers + ".Lore") != null) {
						tier2lore.add(utils.format(lore));
					}
				}
				tier2lore.add("");
				tier2lore.add(ChatColor.GOLD + "Chance: " + configs.getTier2Cfg().getDouble("FlareRewards." + numbers + ".Chance") + "%");
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
						tier2rewardsmenu.addItem(utils.makeHead(configs.getTier2Cfg().getString("FlareRewards." + numbers + ".Data"), configs.getTier2Cfg().getInt("FlareRewards." + numbers + ".Amount"), utils.format(configs.getTier2Cfg().getString("FlareRewards." + numbers + ".Name")), tier2lore, enchants));
					}
					else {
						tier2rewardsmenu.addItem(utils.makeItem(Material.getMaterial(configs.getTier2Cfg().getInt("FlareRewards." + numbers + ".Item")), configs.getTier2Cfg().getInt("FlareRewards." + numbers + ".Amount"), (short)configs.getTier2Cfg().getInt("FlareRewards." + numbers + ".Data"), utils.format(configs.getTier2Cfg().getString("FlareRewards." + numbers + ".Name")), tier2lore, enchants, ItemFlag.HIDE_ATTRIBUTES));
					}
				}
				else {
					if(configs.getTier2Cfg().getInt("FlareRewards." + numbers + ".Item") == 397) {
						tier2rewardsmenu.addItem(utils.makeHead(configs.getTier2Cfg().getString("FlareRewards." + numbers + ".Data"), configs.getTier2Cfg().getInt("FlareRewards." + numbers + ".Amount"), utils.format(configs.getTier2Cfg().getString("FlareRewards." + numbers + ".Name")), tier2lore));
					}
					else {
						tier2rewardsmenu.addItem(utils.makeItem(Material.getMaterial(configs.getTier2Cfg().getInt("FlareRewards." + numbers + ".Item")), configs.getTier2Cfg().getInt("FlareRewards." + numbers + ".Amount"), (short)configs.getTier2Cfg().getInt("FlareRewards." + numbers + ".Data"), utils.format(configs.getTier2Cfg().getString("FlareRewards." + numbers + ".Name")), tier2lore, ItemFlag.HIDE_ATTRIBUTES));
					}
				}
			}
		}
	}
	
	private Inventory tier3rewardsmenu;
	public Inventory getTier3RewardsMenu() {
		return tier3rewardsmenu;
	}
	public Inventory createTier3RewardsMenu() {
		return tier3rewardsmenu = Bukkit.createInventory(null, configs.getFlaresCfg().getInt("MiningFlares.tier3flare.RewardsGUI.GUISize"), utils.format(configs.getFlaresCfg().getString("MiningFlares.tier3flare.RewardsGUI.GUIName")));
	}
	public void setupTier3RewardsMenu() {
		for (String numbers : configs.getTier3Cfg().getConfigurationSection("FlareRewards").getKeys(false)) {
			if(configs.getTier3Cfg().getDouble("FlareRewards." + numbers + ".Chance") > 0) {
				List<String> tier3lore = new ArrayList<String>();
				for(String lore : configs.getTier3Cfg().getStringList("FlareRewards." + numbers + ".Lore")) {
					if(configs.getTier3Cfg().getString("FlareRewards." + numbers + ".Lore") != null) {
						tier3lore.add(utils.format(lore));
					}
				}
				tier3lore.add("");
				tier3lore.add(ChatColor.GOLD + "Chance: " + configs.getTier3Cfg().getDouble("FlareRewards." + numbers + ".Chance") + "%");
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
						tier3rewardsmenu.addItem(utils.makeHead(configs.getTier3Cfg().getString("FlareRewards." + numbers + ".Data"), configs.getTier3Cfg().getInt("FlareRewards." + numbers + ".Amount"), utils.format(configs.getTier3Cfg().getString("FlareRewards." + numbers + ".Name")), tier3lore, enchants));
					}
					else {
						tier3rewardsmenu.addItem(utils.makeItem(Material.getMaterial(configs.getTier3Cfg().getInt("FlareRewards." + numbers + ".Item")), configs.getTier3Cfg().getInt("FlareRewards." + numbers + ".Amount"), (short)configs.getTier3Cfg().getInt("FlareRewards." + numbers + ".Data"), utils.format(configs.getTier3Cfg().getString("FlareRewards." + numbers + ".Name")), tier3lore, enchants, ItemFlag.HIDE_ATTRIBUTES));
					}
				}
				else {
					if(configs.getTier3Cfg().getInt("FlareRewards." + numbers + ".Item") == 397) {
						tier3rewardsmenu.addItem(utils.makeHead(configs.getTier3Cfg().getString("FlareRewards." + numbers + ".Data"), configs.getTier3Cfg().getInt("FlareRewards." + numbers + ".Amount"), utils.format(configs.getTier3Cfg().getString("FlareRewards." + numbers + ".Name")), tier3lore));
					}
					else {
						tier3rewardsmenu.addItem(utils.makeItem(Material.getMaterial(configs.getTier3Cfg().getInt("FlareRewards." + numbers + ".Item")), configs.getTier3Cfg().getInt("FlareRewards." + numbers + ".Amount"), (short)configs.getTier3Cfg().getInt("FlareRewards." + numbers + ".Data"), utils.format(configs.getTier3Cfg().getString("FlareRewards." + numbers + ".Name")), tier3lore, ItemFlag.HIDE_ATTRIBUTES));
					}
				}
			}
		}
	}
	
	private Inventory tier4rewardsmenu;
	public Inventory getTier4RewardsMenu() {
		return tier4rewardsmenu;
	}
	public Inventory createTier4RewardsMenu() {
		return tier4rewardsmenu = Bukkit.createInventory(null, configs.getFlaresCfg().getInt("MiningFlares.tier4flare.RewardsGUI.GUISize"), utils.format(configs.getFlaresCfg().getString("MiningFlares.tier4flare.RewardsGUI.GUIName")));
	}
	public void setupTier4RewardsMenu() {
		for (String numbers : configs.getTier4Cfg().getConfigurationSection("FlareRewards").getKeys(false)) {
			if(configs.getTier4Cfg().getDouble("FlareRewards." + numbers + ".Chance") > 0) {
				List<String> tier4lore = new ArrayList<String>();
				for(String lore : configs.getTier4Cfg().getStringList("FlareRewards." + numbers + ".Lore")) {
					if(configs.getTier4Cfg().getString("FlareRewards." + numbers + ".Lore") != null) {
						tier4lore.add(utils.format(lore));
					}
				}
				tier4lore.add("");
				tier4lore.add(ChatColor.GOLD + "Chance: " + configs.getTier4Cfg().getDouble("FlareRewards." + numbers + ".Chance") + "%");
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
						tier4rewardsmenu.addItem(utils.makeHead(configs.getTier4Cfg().getString("FlareRewards." + numbers + ".Data"), configs.getTier4Cfg().getInt("FlareRewards." + numbers + ".Amount"), utils.format(configs.getTier4Cfg().getString("FlareRewards." + numbers + ".Name")), tier4lore, enchants));
					}
					else {
						tier4rewardsmenu.addItem(utils.makeItem(Material.getMaterial(configs.getTier4Cfg().getInt("FlareRewards." + numbers + ".Item")), configs.getTier4Cfg().getInt("FlareRewards." + numbers + ".Amount"), (short)configs.getTier4Cfg().getInt("FlareRewards." + numbers + ".Data"), utils.format(configs.getTier4Cfg().getString("FlareRewards." + numbers + ".Name")), tier4lore, enchants, ItemFlag.HIDE_ATTRIBUTES));
					}
				}
				else {
					if(configs.getTier4Cfg().getInt("FlareRewards." + numbers + ".Item") == 397) {
						tier4rewardsmenu.addItem(utils.makeHead(configs.getTier4Cfg().getString("FlareRewards." + numbers + ".Data"), configs.getTier4Cfg().getInt("FlareRewards." + numbers + ".Amount"), utils.format(configs.getTier4Cfg().getString("FlareRewards." + numbers + ".Name")), tier4lore));
					}
					else {
						tier4rewardsmenu.addItem(utils.makeItem(Material.getMaterial(configs.getTier4Cfg().getInt("FlareRewards." + numbers + ".Item")), configs.getTier4Cfg().getInt("FlareRewards." + numbers + ".Amount"), (short)configs.getTier4Cfg().getInt("FlareRewards." + numbers + ".Data"), utils.format(configs.getTier4Cfg().getString("FlareRewards." + numbers + ".Name")), tier4lore, ItemFlag.HIDE_ATTRIBUTES));
					}
				}
			}
		}
	}
	
	private Inventory tier5rewardsmenu;
	public Inventory getTier5RewardsMenu() {
		return tier5rewardsmenu;
	}
	public Inventory createTier5RewardsMenu() {
		return tier5rewardsmenu = Bukkit.createInventory(null, configs.getFlaresCfg().getInt("MiningFlares.tier5flare.RewardsGUI.GUISize"), utils.format(configs.getFlaresCfg().getString("MiningFlares.tier5flare.RewardsGUI.GUIName")));
	}
	public void setupTier5RewardsMenu() {
		for (String numbers : configs.getTier5Cfg().getConfigurationSection("FlareRewards").getKeys(false)) {
			if(configs.getTier5Cfg().getDouble("FlareRewards." + numbers + ".Chance") > 0) {
				List<String> tier5lore = new ArrayList<String>();
				for(String lore : configs.getTier5Cfg().getStringList("FlareRewards." + numbers + ".Lore")) {
					if(configs.getTier5Cfg().getString("FlareRewards." + numbers + ".Lore") != null) {
						tier5lore.add(utils.format(lore));
					}
				}
				tier5lore.add("");
				tier5lore.add(ChatColor.GOLD + "Chance: " + configs.getTier5Cfg().getDouble("FlareRewards." + numbers + ".Chance") + "%");
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
						tier5rewardsmenu.addItem(utils.makeHead(configs.getTier5Cfg().getString("FlareRewards." + numbers + ".Data"), configs.getTier5Cfg().getInt("FlareRewards." + numbers + ".Amount"), utils.format(configs.getTier5Cfg().getString("FlareRewards." + numbers + ".Name")), tier5lore, enchants));
					}
					else {
						tier5rewardsmenu.addItem(utils.makeItem(Material.getMaterial(configs.getTier5Cfg().getInt("FlareRewards." + numbers + ".Item")), configs.getTier5Cfg().getInt("FlareRewards." + numbers + ".Amount"), (short)configs.getTier5Cfg().getInt("FlareRewards." + numbers + ".Data"), utils.format(configs.getTier5Cfg().getString("FlareRewards." + numbers + ".Name")), tier5lore, enchants, ItemFlag.HIDE_ATTRIBUTES));
					}
				}
				else {
					if(configs.getTier5Cfg().getInt("FlareRewards." + numbers + ".Item") == 397) {
						tier5rewardsmenu.addItem(utils.makeHead(configs.getTier5Cfg().getString("FlareRewards." + numbers + ".Data"), configs.getTier5Cfg().getInt("FlareRewards." + numbers + ".Amount"), utils.format(configs.getTier5Cfg().getString("FlareRewards." + numbers + ".Name")), tier5lore));
					}
					else {
						tier5rewardsmenu.addItem(utils.makeItem(Material.getMaterial(configs.getTier5Cfg().getInt("FlareRewards." + numbers + ".Item")), configs.getTier5Cfg().getInt("FlareRewards." + numbers + ".Amount"), (short)configs.getTier5Cfg().getInt("FlareRewards." + numbers + ".Data"), utils.format(configs.getTier5Cfg().getString("FlareRewards." + numbers + ".Name")), tier5lore, ItemFlag.HIDE_ATTRIBUTES));
					}
				}
			}
		}
	}
	
	private Inventory tier1winningsmenu;
	public Inventory getTier1WinningsMenu() {
		return tier1winningsmenu;
	}
	public Inventory createTier1WinningsMenu() {
		return tier1winningsmenu = Bukkit.createInventory(null, InventoryType.CHEST, utils.format(configs.getFlaresCfg().getString("MiningFlares.tier1flare.WinningsGUI.Name")));
	}
	
	private Inventory tier2winningsmenu;
	public Inventory getTier2WinningsMenu() {
		return tier2winningsmenu;
	}
	public Inventory createTier2WinningsMenu() {
		return tier2winningsmenu = Bukkit.createInventory(null, InventoryType.CHEST, utils.format(configs.getFlaresCfg().getString("MiningFlares.tier2flare.WinningsGUI.Name")));
	}
	
	private Inventory tier3winningsmenu;
	public Inventory getTier3WinningsMenu() {
		return tier3winningsmenu;
	}
	public Inventory createTier3WinningsMenu() {
		return tier3winningsmenu = Bukkit.createInventory(null, InventoryType.CHEST, utils.format(configs.getFlaresCfg().getString("MiningFlares.tier3flare.WinningsGUI.Name")));
	}
	
	private Inventory tier4winningsmenu;
	public Inventory getTier4WinningsMenu() {
		return tier4winningsmenu;
	}
	public Inventory createTier4WinningsMenu() {
		return tier4winningsmenu = Bukkit.createInventory(null, InventoryType.CHEST, utils.format(configs.getFlaresCfg().getString("MiningFlares.tier4flare.WinningsGUI.Name")));
	}
	
	private  Inventory tier5winningsmenu;
	public Inventory getTier5WinningsMenu() {
		return tier5winningsmenu;
	}
	public Inventory createTier5WinningsMenu() {
		return tier5winningsmenu = Bukkit.createInventory(null, InventoryType.CHEST, utils.format(configs.getFlaresCfg().getString("MiningFlares.tier5flare.WinningsGUI.Name")));
	}
}
