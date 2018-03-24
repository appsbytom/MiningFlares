package me.Tom.MiningFlares;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;


public class ConfigManager {
	
	private PluginCore core;
	private Utils<?> utils;
	public ConfigManager(PluginCore pl) {
		core = pl;
		utils = core.getUtils();
	}
	
	public void setup() {
		createMsgCfg(core);
		createFlaresCfg(core);
		createTempCfg(core);
		createTier1Cfg(core);
		createTier2Cfg(core);
		createTier3Cfg(core);
		createTier4Cfg(core);
		createTier5Cfg(core);
		createCratesCfg(core);
		createNPCCfg(core);
		createRodCfg(core);
	}
	
	public void reloadAll() {
		reloadMsgCfg();
		reloadFlaresCfg();
		reloadTier1Cfg();
		reloadTier2Cfg();
		reloadTier3Cfg();
		reloadTier4Cfg();
		reloadTier5Cfg();
		if(flarescfg.getBoolean("Redeeming.NPC")) {
			reloadNPCCfg();
		}
		if(flarescfg.getBoolean("Redeeming.Crates")) {
			reloadCratesCfg();
		}
	}
	
    private File temp;
	public void createTempCfg(Plugin p) {
		temp = new File(p.getDataFolder(), "Tiers/temp.yml");
		if(!temp.exists()) {
			temp.getParentFile().mkdirs();
			try {
				temp.createNewFile();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
			temp.delete();
		}
	}
	
	private File rodmodes;
	public File getRodFile() {
		return rodmodes;
	}
	
	private FileConfiguration rodmodescfg;
	public FileConfiguration getRodCfg() {
		return rodmodescfg;
	}
	
	public void createRodCfg(Plugin p) {
		rodmodes = new File(p.getDataFolder(), "rodmodes.yml");
		if(!rodmodes.exists()) {
			rodmodes.getParentFile().mkdirs();
			try {
				rodmodes.createNewFile();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		reloadRodCfg();
	}
	public void reloadRodCfg() {
		rodmodescfg = new YamlConfiguration();
		try {
			try {
				rodmodescfg.load(rodmodes);
				saveRodCfg();
			} catch (InvalidConfigurationException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void saveRodCfg() {
		try {
			rodmodescfg.save(rodmodes);
		}
		catch (IOException e) {
			Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save rodmodes.yml");
		}
	}
	
	private File messages;
	public File getMsgFile() {
		return messages;
	}
	
	private FileConfiguration messagescfg;
	public FileConfiguration getMsgCfg() {
		return messagescfg;
	}
	
	public void createMsgCfg(Plugin p) {
		messages = new File(p.getDataFolder(), "messages.yml");
		if(!messages.exists()) {
			messages.getParentFile().mkdirs();
			p.saveResource("messages.yml", false);
		}
		reloadMsgCfg();
	}
	public void reloadMsgCfg() {
		messagescfg = new YamlConfiguration();
		try {
			try {
				messagescfg.load(messages);
				saveMsgCfg();
			} catch (InvalidConfigurationException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void saveMsgCfg() {
		try {
			messagescfg.save(messages);
		}
		catch (IOException e) {
			Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save messages.yml");
		}
	}
	
	private File crates;
	public File getCratesFile() {
		return crates;
	}
	
	private FileConfiguration cratescfg;
	public FileConfiguration getCratesCfg() {
		return cratescfg;
	}
	
	public void createCratesCfg(Plugin p) {
		crates = new File(p.getDataFolder(), "crates.yml");
		if (!crates.exists()) {
            crates.getParentFile().mkdirs();
            p.saveResource("crates.yml", false);
        }
		reloadCratesCfg();
	}
	public void reloadCratesCfg() {
		cratescfg = new YamlConfiguration();
		try {
			try {
				cratescfg.load(crates);
				saveCratesCfg();
			} catch (InvalidConfigurationException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void saveCratesCfg() {
		try {
			cratescfg.save(crates);
		}
		catch (IOException e) {
			Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save crates.yml");
		}
	}
	
	private File npcs;
	public File getNPCFile() {
		return npcs;
	}
	
	private FileConfiguration npccfg;
	public FileConfiguration getNPCCfg() {
		return npccfg;
	}
	
	public void createNPCCfg(Plugin p) {
		npcs = new File(p.getDataFolder(), "npcs.yml");
		if (!npcs.exists()) {
            npcs.getParentFile().mkdirs();
            p.saveResource("npcs.yml", false);
        }
		reloadNPCCfg();
	}
	public void reloadNPCCfg() {
		npccfg = new YamlConfiguration();
		try {
			try {
				npccfg.load(npcs);
				saveNPCCfg();
			} catch (InvalidConfigurationException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void saveNPCCfg() {
		try {
			npccfg.save(npcs);
		}
		catch (IOException e) {
			Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save npcs.yml");
		}
	}
	
	private File flares;
	public File getFlaresFile() {
		return flares;
	}
	
	private FileConfiguration flarescfg;
	public FileConfiguration getFlaresCfg() {
		return flarescfg;
	}
	
	public void createFlaresCfg(Plugin p) {
		flares = new File(p.getDataFolder(), "flares.yml");
		if (!flares.exists()) {
            flares.getParentFile().mkdirs();
            p.saveResource("flares.yml", false);
        }
		reloadFlaresCfg();
	}
	public void reloadFlaresCfg() {
		flarescfg = new YamlConfiguration();
		try {
			try {
				flarescfg.load(flares);
				saveFlaresCfg();
			} catch (InvalidConfigurationException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void saveFlaresCfg() {
		try {
			flarescfg.save(flares);
		}
		catch (IOException e) {
			Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save flares.yml");
		}
	}
	
	private File tier1;
	public File getTier1File() {
		return tier1;
	}
	
	private FileConfiguration tier1cfg;
	public FileConfiguration getTier1Cfg() {
		return tier1cfg;
	}
	
	public void createTier1Cfg(Plugin p) {
		tier1 = new File(p.getDataFolder(), "Tiers/tier1.yml");
		if (!tier1.exists()) {
            tier1.getParentFile().mkdirs();
            p.saveResource("Tiers/tier1.yml", false);
        }
		reloadTier1Cfg();
	}
	public void reloadTier1Cfg() {
		tier1cfg = new YamlConfiguration();
		try {
			try {
				tier1cfg.load(tier1);
				saveTier1Cfg();
			} catch (InvalidConfigurationException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void saveTier1Cfg() {
		try {
			tier1cfg.save(tier1);
		}
		catch (IOException e) {
			Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save tier1.yml");
		}
	}
	
	private File tier2;
	public File getTier2File() {
		return tier2;
	}
	
	private FileConfiguration tier2cfg;
	public FileConfiguration getTier2Cfg() {
		return tier2cfg;
	}
	
	public void createTier2Cfg(Plugin p) {
		tier2 = new File(p.getDataFolder(), "Tiers/tier2.yml");
		if (!tier2.exists()) {
            tier2.getParentFile().mkdirs();
            p.saveResource("Tiers/tier2.yml", false);
        }
		reloadTier2Cfg();
	}
	public void reloadTier2Cfg() {
		tier2cfg = new YamlConfiguration();
		try {
			try {
				tier2cfg.load(tier2);
				saveTier2Cfg();
			} catch (InvalidConfigurationException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void saveTier2Cfg() {
		try {
			tier2cfg.save(tier2);
		}
		catch (IOException e) {
			Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save tier2.yml");
		}
	}
	
	private File tier3;
	public File getTier3File() {
		return tier3;
	}
	
	private FileConfiguration tier3cfg;
	public FileConfiguration getTier3Cfg() {
		return tier3cfg;
	}
	
	public void createTier3Cfg(Plugin p) {
		tier3 = new File(p.getDataFolder(), "Tiers/tier3.yml");
		if (!tier3.exists()) {
            tier3.getParentFile().mkdirs();
            p.saveResource("Tiers/tier3.yml", false);
        }
		reloadTier3Cfg();
	}
	public void reloadTier3Cfg() {
		tier3cfg = new YamlConfiguration();
		try {
			try {
				tier3cfg.load(tier3);
				saveTier3Cfg();
			} catch (InvalidConfigurationException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void saveTier3Cfg() {
		try {
			tier3cfg.save(tier3);
		}
		catch (IOException e) {
			Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save tier3.yml");
		}
	}
	
	private File tier4;
	public File getTier4File() {
		return tier4;
	}
	
	private FileConfiguration tier4cfg;
	public FileConfiguration getTier4Cfg() {
		return tier4cfg;
	}
	
	public void createTier4Cfg(Plugin p) {
		tier4 = new File(p.getDataFolder(), "Tiers/tier4.yml");
		if (!tier4.exists()) {
            tier4.getParentFile().mkdirs();
            p.saveResource("Tiers/tier4.yml", false);
        }
		reloadTier4Cfg();
	}
	public void reloadTier4Cfg() {
		tier4cfg = new YamlConfiguration();
		try {
			try {
				tier4cfg.load(tier4);
				saveTier4Cfg();
			} catch (InvalidConfigurationException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void saveTier4Cfg() {
		try {
			tier4cfg.save(tier4);
		}
		catch (IOException e) {
			Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save tier4.yml");
		}
	}
	
	private File tier5;
	public File getTier5File() {
		return tier5;
	}
	
	private FileConfiguration tier5cfg;
	public FileConfiguration getTier5Cfg() {
		return tier5cfg;
	}
	
	public void createTier5Cfg(Plugin p) {
		tier5 = new File(p.getDataFolder(), "Tiers/tier5.yml");
		if (!tier5.exists()) {
            tier5.getParentFile().mkdirs();
            p.saveResource("Tiers/tier5.yml", false);
        }
		reloadTier5Cfg();
	}
	public void reloadTier5Cfg() {
		tier5cfg = new YamlConfiguration();
		try {
			try {
				tier5cfg.load(tier5);
				saveTier5Cfg();
			} catch (InvalidConfigurationException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void saveTier5Cfg() {
		try {
			tier5cfg.save(tier5);
		}
		catch (IOException e) {
			Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save tier5.yml");
		}
	}
	
	public void reloadMethod(Plugin p, CommandSender sender, String type, Boolean message) {
		if(type.equalsIgnoreCase("tier1")) {
			reloadTier1Cfg();
			if(tier1cfg.getKeys(false).size() == 0) {
				tier1.delete();
				createTier1Cfg(p);
				if(message) {
					sender.sendMessage(utils.addPrefix() + utils.format("&cThere was an error in the &eTier1 &cconfig so it has been reset"));
				}
			}
			else if(tier1cfg.getKeys(false).size() > 0) {
				core.getLootMethods().setTier1Percent(0);
				for(String number : getTier1Cfg().getConfigurationSection("FlareRewards").getKeys(false)) {
					core.getLootMethods().addTier1Percents(getTier1Cfg().getDouble("FlareRewards." + number + ".Chance"));
				}
				if(core.getLootMethods().getTier1Percent() != 100) {
					sender.sendMessage(utils.addPrefix() + utils.format("&7The Tier1 total percentage does not equal &c100%, &7check the config file to fix this issue"));
					sender.sendMessage(utils.addPrefix() + utils.format("&7Plugin now &c&lDISABLING"));
					utils.unload(p);
					return;
				}
				core.getRewardMethods().getTier1Rewards().clear();
				core.getRewardMethods().setupTier1Rewards();
				core.getInvMethods().createTier1RewardsMenu();
				core.getInvMethods().setupTier1RewardsMenu();
				if(getTier1Cfg().getConfigurationSection("FlareRewards") != null) {
					for (final String st : getTier1Cfg().getConfigurationSection("FlareRewards").getKeys(false)) {
						final int id = Integer.parseInt(st);
						if(id > core.getLootMethods().getTier1LootID()) {
							core.getLootMethods().setTier1LootID(id);
						}
					}
				}
				if(message) {
					sender.sendMessage(utils.addPrefix() + utils.format("&7The &eTier1 &7config had no errors and successfully reloaded"));
				}
			}
		}
		if(type.equalsIgnoreCase("tier2")) {
			reloadTier2Cfg();
			if(tier2cfg.getKeys(false).size() == 0) {
				tier2.delete();
				createTier2Cfg(p);
				if(message) {
					sender.sendMessage(utils.addPrefix() + utils.format("&cThere was an error in the &eTier2 &cconfig so it has been reset"));
				}
			}
			else if(tier2cfg.getKeys(false).size() > 0) {
				core.getLootMethods().setTier2Percent(0);
				for(String number : getTier2Cfg().getConfigurationSection("FlareRewards").getKeys(false)) {
					core.getLootMethods().addTier2Percents(getTier2Cfg().getDouble("FlareRewards." + number + ".Chance"));
				}
				if(core.getLootMethods().getTier2Percent() != 100) {
					sender.sendMessage(utils.addPrefix() + utils.format("&7The Tier2 total percentage does not equal &c100%, &7check the config file to fix this issue"));
					sender.sendMessage(utils.addPrefix() + utils.format("&7Plugin now &c&lDISABLING"));
					utils.unload(p);
					return;
				}
				core.getRewardMethods().getTier2Rewards().clear();
				core.getRewardMethods().setupTier2Rewards();
				core.getInvMethods().createTier2RewardsMenu();
				core.getInvMethods().setupTier2RewardsMenu();
				if(getTier2Cfg().getConfigurationSection("FlareRewards") != null) {
					for (final String st : getTier2Cfg().getConfigurationSection("FlareRewards").getKeys(false)) {
						final int id = Integer.parseInt(st);
						if(id > core.getLootMethods().getTier2LootID()) {
							core.getLootMethods().setTier2LootID(id);
						}
					}
				}
				if(message) {
					sender.sendMessage(utils.addPrefix() + utils.format("&7The &eTier2 &7config had no errors and successfully reloaded"));
				}
			}
		}
		if(type.equalsIgnoreCase("tier3")) {
			reloadTier3Cfg();
			if(tier3cfg.getKeys(false).size() == 0) {
				tier3.delete();
				createTier3Cfg(p);
				if(message) {
					sender.sendMessage(utils.addPrefix() + utils.format("&cThere was an error in the &eTier3 &cconfig so it has been reset"));
				}
			}
			else if(tier3cfg.getKeys(false).size() > 0) {
				core.getLootMethods().setTier3Percent(0);
				for(String number : getTier3Cfg().getConfigurationSection("FlareRewards").getKeys(false)) {
					core.getLootMethods().addTier3Percents(getTier3Cfg().getDouble("FlareRewards." + number + ".Chance"));
				}
				if(core.getLootMethods().getTier3Percent() != 100) {
					sender.sendMessage(utils.addPrefix() + utils.format("&7The Tier3 total percentage does not equal &c100%, &7check the config file to fix this issue"));
					sender.sendMessage(utils.addPrefix() + utils.format("&7Plugin now &c&lDISABLING"));
					utils.unload(p);
					return;
				}
				core.getRewardMethods().getTier3Rewards().clear();
				core.getRewardMethods().setupTier3Rewards();
				core.getInvMethods().createTier3RewardsMenu();
				core.getInvMethods().setupTier3RewardsMenu();
				if(getTier3Cfg().getConfigurationSection("FlareRewards") != null) {
					for (final String st : getTier3Cfg().getConfigurationSection("FlareRewards").getKeys(false)) {
						final int id = Integer.parseInt(st);
						if(id > core.getLootMethods().getTier3LootID()) {
							core.getLootMethods().setTier3LootID(id);
						}
					}
				}
				if(message) {
					sender.sendMessage(utils.addPrefix() + utils.format("&7The &eTier3 &7config had no errors and successfully reloaded"));
				}
			}
		}
		if(type.equalsIgnoreCase("tier4")) {
			reloadTier4Cfg();
			if(tier4cfg.getKeys(false).size() == 0) {
				tier4.delete();
				createTier4Cfg(p);
				if(message) {
					sender.sendMessage(utils.addPrefix() + utils.format("&cThere was an error in the &eTier4 &cconfig so it has been reset"));
				}
			}
			else if(tier4cfg.getKeys(false).size() > 0) {
				core.getLootMethods().setTier4Percent(0);
				for(String number : getTier4Cfg().getConfigurationSection("FlareRewards").getKeys(false)) {
					core.getLootMethods().addTier4Percents(getTier4Cfg().getDouble("FlareRewards." + number + ".Chance"));
				}
				if(core.getLootMethods().getTier4Percent() != 100) {
					sender.sendMessage(utils.addPrefix() + utils.format("&7The Tier4 total percentage does not equal &c100%, &7check the config file to fix this issue"));
					sender.sendMessage(utils.addPrefix() + utils.format("&7Plugin now &c&lDISABLING"));
					utils.unload(p);
					return;
				}
				core.getRewardMethods().getTier4Rewards().clear();
				core.getRewardMethods().setupTier4Rewards();
				core.getInvMethods().createTier4RewardsMenu();
				core.getInvMethods().setupTier4RewardsMenu();
				if(getTier4Cfg().getConfigurationSection("FlareRewards") != null) {
					for (final String st : getTier4Cfg().getConfigurationSection("FlareRewards").getKeys(false)) {
						final int id = Integer.parseInt(st);
						if(id > core.getLootMethods().getTier4LootID()) {
							core.getLootMethods().setTier4LootID(id);
						}
					}
				}
				if(message) {
					sender.sendMessage(utils.addPrefix() + utils.format("&7The &eTier4 &7config had no errors and successfully reloaded"));
				}
			}
		}
		if(type.equalsIgnoreCase("tier5")) {
			reloadTier5Cfg();
			if(tier5cfg.getKeys(false).size() == 0) {
				tier5.delete();
				createTier1Cfg(p);
				if(message) {
					sender.sendMessage(utils.addPrefix() + utils.format("&cThere was an error in the &eTier5 &cconfig so it has been reset"));
				}
			}
			else if(tier5cfg.getKeys(false).size() > 0) {
				core.getLootMethods().setTier5Percent(0);
				for(String number : getTier5Cfg().getConfigurationSection("FlareRewards").getKeys(false)) {
					core.getLootMethods().addTier5Percents(getTier5Cfg().getDouble("FlareRewards." + number + ".Chance"));
				}
				if(core.getLootMethods().getTier5Percent() != 100) {
					sender.sendMessage(utils.addPrefix() + utils.format("&7The Tier5 total percentage does not equal &c100%, &7check the config file to fix this issue"));
					sender.sendMessage(utils.addPrefix() + utils.format("&7Plugin now &c&lDISABLING"));
					utils.unload(p);
					return;
				}
				core.getRewardMethods().getTier5Rewards().clear();
				core.getRewardMethods().setupTier5Rewards();
				core.getInvMethods().createTier5RewardsMenu();
				core.getInvMethods().setupTier5RewardsMenu();
				if(getTier5Cfg().getConfigurationSection("FlareRewards") != null) {
					for (final String st : getTier5Cfg().getConfigurationSection("FlareRewards").getKeys(false)) {
						final int id = Integer.parseInt(st);
						if(id > core.getLootMethods().getTier5LootID()) {
							core.getLootMethods().setTier5LootID(id);
						}
					}
				}
				if(message) {
					sender.sendMessage(utils.addPrefix() + utils.format("&7The &eTier5 &7config had no errors and successfully reloaded"));
				}
			}
		}
	}
}
