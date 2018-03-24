package me.Tom.MiningFlares;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.Tom.MiningFlares.CommandManager.CommandManager;
import me.Tom.MiningFlares.ControlRod.RodMethods;
import me.Tom.MiningFlares.Crates.CrateMethods;
import me.Tom.MiningFlares.EventManager.EventManager;
import me.Tom.MiningFlares.Flares.FlareMethods;
import me.Tom.MiningFlares.Flares.RewardMethods;
import me.Tom.MiningFlares.NPCs.NPCMethods;

public class PluginCore extends JavaPlugin {
	
	private PluginCore core;
	public PluginCore getCore() {
		return core;
	}
	
	private ConfigManager configs;
	public ConfigManager getConfigs() {
		return configs;
	}
	
	private CommandManager cmdMgr;
	public CommandManager getCMDMgr() {
		return cmdMgr;
	}
	
	private EventManager eventMgr;
	public EventManager getEventMgr() {
		return eventMgr;
	}
	
	private FlareMethods flaremethods;
	public FlareMethods getFlareMethods() {
		return flaremethods;
	}
	
	private InventoryMethods invmethods;
	public InventoryMethods getInvMethods() {
		return invmethods;
	}
	
	private RewardMethods rewardmethods;
	public RewardMethods getRewardMethods() {
		return rewardmethods;
	}
	
	private LootMethods lootmethods;
	public LootMethods getLootMethods() {
		return lootmethods;
	}
	
	private RodMethods rodmethods;
	public RodMethods getRodMethods() {
		return rodmethods;
	}
	
	private WinningsMethods winningsmethods;
	public WinningsMethods getWinningsMethods() {
		return winningsmethods;
	}
	
	private CrateMethods cratemethods;
	public CrateMethods getCrateMethods() {
		return cratemethods;
	}
	
	private NPCMethods npcmethods;
	public NPCMethods getNPCMethods() {
		return npcmethods;
	}
	
	private Utils<?> utils;
	public Utils<?> getUtils() {
		return utils;
	}
	
	private String version;
	public String getVersion() {
		return version;
	}
	
	private boolean citizens;
	public Boolean isCitizensEnabled() {
		return citizens;
	}
	
	private boolean holograms;
	public Boolean isHologramsEnabled() {
		return holograms;
	}
	
	private boolean worldguard;
	public Boolean isWGEnabled() {
		return worldguard;
	}
	
	
	public void onEnable() {
		core = this;
		version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
		utils = new Utils<Object>(this);
		getServer().getConsoleSender().sendMessage(utils.format("&6=-=-=-=-=-=-=-= " + getDescription().getName() + " =-=-=-=-=-=-=-="));
		getServer().getConsoleSender().sendMessage(utils.format("| &aVersion: " + getDescription().getVersion()));
		getServer().getConsoleSender().sendMessage(utils.format("| &aAuthor: " + getDescription().getAuthors()));
		configs = new ConfigManager(this);
		configs.setup();
		flaremethods = new FlareMethods(this);
		rewardmethods = new RewardMethods(this);
		rewardmethods.setup();
		invmethods = new InventoryMethods(this);
		invmethods.setup();
		lootmethods = new LootMethods(this);
		lootmethods.setup();
		if(configs.getFlaresCfg().getBoolean("Redeeming.NPC") || configs.getFlaresCfg().getBoolean("Redeeming.Crates")) {
			rodmethods = new RodMethods(this);
			winningsmethods = new WinningsMethods(this);
			if(configs.getFlaresCfg().getBoolean("Redeeming.Crates")) {
				cratemethods = new CrateMethods(this);
			}
			if(configs.getFlaresCfg().getBoolean("Redeeming.NPC")) {
				npcmethods = new NPCMethods(this);
			}
		}
		checkDepends();
		if(configs.getFlaresCfg().getBoolean("Plugin.BStats")) {
			createCharts();
		}
		cmdMgr = new CommandManager(this);
        cmdMgr.setup();
		eventMgr = new EventManager(this);
		eventMgr.setup();
		versionChecker();
		verifyMethods();
	}
	
	public void onDisable() {
		if(holograms) {
			if(configs.getFlaresCfg().getBoolean("Redeeming.NPC") || configs.getFlaresCfg().getBoolean("Redeeming.Crates")) {
				if(configs.getFlaresCfg().getBoolean("Redeeming.NPC")) {
					npcmethods.removeHolograms();
				}
				if(configs.getFlaresCfg().getBoolean("Redeeming.Crates")) {
					cratemethods.removeHolograms();
				}
				if(!rodmethods.getRod().isEmpty() || !rodmethods.getNPCList().isEmpty() || !rodmethods.getCrateList().isEmpty() || !rodmethods.getCreateList().isEmpty() || !rodmethods.getDeleteList().isEmpty()) {
					configs.createRodCfg(this);
					if(configs.getRodFile().exists()) {
						if(!rodmethods.getRod().isEmpty()) {
							List<String> rodlist = new ArrayList<String>();
							for(UUID roduuids : rodmethods.getRod()) {
								rodlist.add(roduuids.toString());
							}
							configs.getRodCfg().set("RodModes.Rod", rodlist);
							rodmethods.getRod().clear();
						}
						if(!rodmethods.getNPCList().isEmpty()) {
							List<String> npclist = new ArrayList<String>();
							for(UUID npcuuids : rodmethods.getNPCList()) {
								npclist.add(npcuuids.toString());
							}
							configs.getRodCfg().set("RodModes.NPC", npclist);
							rodmethods.getNPCList().clear();
						}
						if(!rodmethods.getCrateList().isEmpty()) {
							List<String> cratelist = new ArrayList<String>();
							for(UUID crateuuids : rodmethods.getCrateList()) {
								cratelist.add(crateuuids.toString());
							}
							configs.getRodCfg().set("RodModes.Crate", cratelist);
							rodmethods.getCrateList().clear();
						}
						if(!rodmethods.getCreateList().isEmpty()) {
							List<String> createlist = new ArrayList<String>();
							for(UUID createuuids : rodmethods.getCreateList()) {
								createlist.add(createuuids.toString());
							}
							configs.getRodCfg().set("RodModes.Create", createlist);
							rodmethods.getCreateList().clear();
						}
						if(!rodmethods.getDeleteList().isEmpty()) {
							List<String> deletelist = new ArrayList<String>();
							for(UUID deleteuuids : rodmethods.getDeleteList()) {
								deletelist.add(deleteuuids.toString());
							}
							configs.getRodCfg().set("RodModes.Delete", deletelist);
							rodmethods.getDeleteList().clear();
						}
						configs.saveRodCfg();
						configs.reloadRodCfg();
					}
				}
			}
		}
		Bukkit.getScheduler().cancelAllTasks();
		core = null;
		Bukkit.getPluginManager().disablePlugin(this);
	}
	
	private void checkDepends() {
		if(Bukkit.getPluginManager().getPlugin("HolographicDisplays") != null) {
			if (Bukkit.getPluginManager().getPlugin("HolographicDisplays").isEnabled()) {
				if(configs.getFlaresCfg().getBoolean("Redeeming.NPC") || configs.getFlaresCfg().getBoolean("Redeeming.Crates")) {
					holograms = true;
					Bukkit.getServer().getConsoleSender().sendMessage(utils.format("| &aHolographicDisplays Features: Enabled"));
					if(configs.getRodFile().exists()) {
						if(configs.getRodCfg().contains("RodModes.Rod")) {
			        		for(String id : configs.getRodCfg().getStringList("RodModes.Rod")) {
			        			core.getRodMethods().getRod().add(UUID.fromString(id));
			        			configs.getRodCfg().set("RodModes.Rod", null);
			        		}
		        		}
		        		if(configs.getRodCfg().contains("RodModes.NPC")) {
			        		for(String id : configs.getRodCfg().getStringList("RodModes.NPC")) {
			        			core.getRodMethods().getNPCList().add(UUID.fromString(id));
			        			configs.getRodCfg().set("RodModes.NPC", null);
			        		}
		        		}
		        		if(configs.getRodCfg().contains("RodModes.Crate")) {
		        			for(String id : configs.getRodCfg().getStringList("RodModes.Crate")) {
		        				core.getRodMethods().getCrateList().add(UUID.fromString(id));
		        				configs.getRodCfg().set("RodModes.Crate", null);
		        			}
		        		}
		        		if(configs.getRodCfg().contains("RodModes.Create")) {
			        		for(String id : configs.getRodCfg().getStringList("RodModes.Create")) {
			        			core.getRodMethods().getCreateList().add(UUID.fromString(id));
			        			configs.getRodCfg().set("RodModes.Create", null);
			        		}
		        		}
		        		if(configs.getRodCfg().contains("RodModes.Delete")) {
		        			for(String id : configs.getRodCfg().getStringList("RodModes.Delete")) {
		        				core.getRodMethods().getDeleteList().add(UUID.fromString(id));
		        				configs.getRodCfg().set("RodModes.Delete", null);
		        			}
		        		}
		    			configs.getRodFile().delete();
					}
					invmethods.createTier1WinningsMenu();
					invmethods.createTier2WinningsMenu();
					invmethods.createTier3WinningsMenu();
					invmethods.createTier4WinningsMenu();
					invmethods.createTier5WinningsMenu();
					if(configs.getFlaresCfg().getBoolean("Redeeming.NPC")) {
						if(configs.getNPCCfg().getConfigurationSection("NPCS") != null) {
							for(final String st : configs.getNPCCfg().getConfigurationSection("NPCS").getKeys(false)) {
								final int id = Integer.parseInt(st);
								if(id > npcmethods.getMaxID()) {
									npcmethods.setMaxID(id);
								}
							}
						}
						npcmethods.loadNPCHolograms();
					}
					if(configs.getFlaresCfg().getBoolean("Redeeming.Crates")) {
						if(configs.getCratesCfg().contains("Crates.Tier1.Loc")) {
							cratemethods.setTier1Loc();
						}
						if(configs.getCratesCfg().contains("Crates.Tier2.Loc")) {
							cratemethods.setTier2Loc();
						}
						if(configs.getCratesCfg().contains("Crates.Tier3.Loc")) {
							cratemethods.setTier3Loc();
						}
						if(configs.getCratesCfg().contains("Crates.Tier4.Loc")) {
							cratemethods.setTier4Loc();
						}
						if(configs.getCratesCfg().contains("Crates.Tier5.Loc")) {
							cratemethods.setTier5Loc();
						}
						cratemethods.loadCrateHolograms();
					}
				}
				else {
					getServer().getConsoleSender().sendMessage(utils.format("| &cHolographicDisplays Features: Disabled"));
				}
			}
			else {
				getServer().getConsoleSender().sendMessage(utils.format("| &cHolographicDisplays Features: Disabled"));
			}
		}
		else {
			getServer().getConsoleSender().sendMessage(utils.format("| &cHolographicDisplays Features: Disabled"));
		}
        if(Bukkit.getPluginManager().getPlugin("WorldGuard") != null) {
        	if(Bukkit.getPluginManager().getPlugin("WorldGuard").isEnabled()) {
        		worldguard = true;
        		getServer().getConsoleSender().sendMessage(utils.format("| &aWorldGuard Features: Enabled"));
        	}
        	else {
            	getServer().getConsoleSender().sendMessage(utils.format("| &cWorldGuard Features: Disabled"));
            }
        }
        else {
        	getServer().getConsoleSender().sendMessage(utils.format("| &cWorldGuard Features: Disabled"));
        }
        if(holograms) {
	        if(Bukkit.getPluginManager().getPlugin("Citizens") != null) {
	        	if(Bukkit.getPluginManager().getPlugin("Citizens").isEnabled()) {
	        		if(configs.getFlaresCfg().getBoolean("Redeeming.NPC")) {
						if(version.equals("v1_8_R1") || version.equals("v1_8_R2") || version.equals("v1_8_R3")) {
							getServer().getConsoleSender().sendMessage(utils.format("| &cNPC features cannot function on &e1.8 - 1.8.8"));
							getServer().getConsoleSender().sendMessage(utils.format("| &cUpgrade your version to make use of NPC's"));
							return;
						}
						if(Bukkit.getPluginManager().getPlugin("Citizens").getDescription().getVersion().contains("2.0.17")) {
							getServer().getConsoleSender().sendMessage(utils.format("| &cNPC features cannot function on &e1.9"));
							getServer().getConsoleSender().sendMessage(utils.format("| &cUpgrade your version to make use of NPC's"));
							return;
						}
						else {
							citizens = true;
							npcmethods.unloadCitizenNPCS();
							npcmethods.loadCitizenNPCS();
							getServer().getConsoleSender().sendMessage(utils.format("| &aCitizens Features: Enabled"));
						}
	        		}
	        		else {
	                	getServer().getConsoleSender().sendMessage(utils.format("| &cCitizens Features: Disabled"));
	                }
	        	}
	        	else {
		        	getServer().getConsoleSender().sendMessage(utils.format("| &cCitizens Features: Disabled"));
		        }
	        }
	        else {
	        	getServer().getConsoleSender().sendMessage(utils.format("| &cCitizens Features: Disabled"));
	        }
        }
        else {
        	getServer().getConsoleSender().sendMessage(utils.format("| &cCitizens Features: Disabled"));
        }
    }
	
	private void verifyMethods() {
		for(String number : configs.getTier1Cfg().getConfigurationSection("FlareRewards").getKeys(false)) {
			lootmethods.addTier1Percents(configs.getTier1Cfg().getDouble("FlareRewards." + number + ".Chance"));
		}
		for(String number : configs.getTier2Cfg().getConfigurationSection("FlareRewards").getKeys(false)) {
			lootmethods.addTier2Percents(configs.getTier2Cfg().getDouble("FlareRewards." + number + ".Chance"));
		}
		for(String number : configs.getTier3Cfg().getConfigurationSection("FlareRewards").getKeys(false)) {
			lootmethods.addTier3Percents(configs.getTier3Cfg().getDouble("FlareRewards." + number + ".Chance"));
		}
		for(String number : configs.getTier4Cfg().getConfigurationSection("FlareRewards").getKeys(false)) {
			lootmethods.addTier4Percents(configs.getTier4Cfg().getDouble("FlareRewards." + number + ".Chance"));
		}
		for(String number : configs.getTier5Cfg().getConfigurationSection("FlareRewards").getKeys(false)) {
			lootmethods.addTier5Percents(configs.getTier5Cfg().getDouble("FlareRewards." + number + ".Chance"));
		}
		if(lootmethods.getTier1Percent() != 100 || lootmethods.getTier2Percent() != 100 || lootmethods.getTier3Percent() != 100 || lootmethods.getTier4Percent() != 100 || lootmethods.getTier5Percent() != 100) {
			getServer().getConsoleSender().sendMessage(utils.format("&7The total percentage for each Tier rewards must equal &c100%&7!"));
			getServer().getConsoleSender().sendMessage(utils.format("&7Currently it is Tier1&7: &c" + lootmethods.getTier1Percent() + "%&7, Tier2&7: &c" + lootmethods.getTier2Percent() + "%&7, Tier3&7: &c" + lootmethods.getTier3Percent() + "%&7, Tier4&7: &c" + lootmethods.getTier4Percent() + "%&7, Tier5&7: &c" + lootmethods.getTier5Percent() + "%"));
			getServer().getConsoleSender().sendMessage(utils.format("&6=-=-=-=-= Ultimate Customisation =-=-=-=-="));
			utils.unload(this);
			return;
		}
		if(configs.getFlaresCfg().getBoolean("Redeeming.Click") && !configs.getFlaresCfg().getBoolean("Redeeming.NPC") && !configs.getFlaresCfg().getBoolean("Redeeming.Crates")) {
			getServer().getConsoleSender().sendMessage(utils.format("&7You have the &cClick &7redeeming method enabled!"));
		}
		else if(!configs.getFlaresCfg().getBoolean("Redeeming.Click") && configs.getFlaresCfg().getBoolean("Redeeming.NPC") && !configs.getFlaresCfg().getBoolean("Redeeming.Crates")) {
			if(!holograms) {
				getServer().getConsoleSender().sendMessage(utils.format("&7You have the &cNPC &7redeeming method enabled but &cHolographicDisplays &7is not operating!"));
				getServer().getConsoleSender().sendMessage(utils.format("&6=-=-=-=-= Ultimate Customisation =-=-=-=-="));
				utils.unload(this);
				return;
			}
			if(!citizens) {
				getServer().getConsoleSender().sendMessage(utils.format("&7You have the &cNPC &7redeeming method enabled but &cCitizens &7is not operating!"));
				getServer().getConsoleSender().sendMessage(utils.format("&6=-=-=-=-= Ultimate Customisation =-=-=-=-="));
				utils.unload(this);
				return;
			}
			else {
				getServer().getConsoleSender().sendMessage(utils.format("&7You have the &cNPC &7redeeming method enabled!"));
			}
		}
		else if(!configs.getFlaresCfg().getBoolean("Redeeming.Click") && !configs.getFlaresCfg().getBoolean("Redeeming.NPC") && configs.getFlaresCfg().getBoolean("Redeeming.Crates")) {
			if(!holograms) {
				getServer().getConsoleSender().sendMessage(utils.format("&7You have the &cCrate &7redeeming method enabled but &cHolographicDisplays &7is not installed!"));
				getServer().getConsoleSender().sendMessage(utils.format("&6=-=-=-=-= Ultimate Customisation =-=-=-=-="));
				utils.unload(this);
				return;
			}
			else {
				getServer().getConsoleSender().sendMessage(utils.format("&7You have the &cCrate &7redeeming method enabled!"));
				if(configs.getCratesCfg().getBoolean("Crates.Opening.Generic") && !configs.getCratesCfg().getBoolean("Crates.Opening.Roulette") && !configs.getCratesCfg().getBoolean("Crates.Opening.Quick")) {
					getServer().getConsoleSender().sendMessage(utils.format("&7You have the &cGeneric &7opening method enabled!"));
				}
				else if(!configs.getCratesCfg().getBoolean("Crates.Opening.Generic") && configs.getCratesCfg().getBoolean("Crates.Opening.Roulette")&& !configs.getCratesCfg().getBoolean("Crates.Opening.Quick")) {
					getServer().getConsoleSender().sendMessage(utils.format("&7You have the &cRoulette &7opening method enabled!"));
				}
				else if(!configs.getCratesCfg().getBoolean("Crates.Opening.Generic") && !configs.getCratesCfg().getBoolean("Crates.Opening.Roulette") && configs.getCratesCfg().getBoolean("Crates.Opening.Quick")) {
					getServer().getConsoleSender().sendMessage(utils.format("&7You have the &cQuick &7opening method enabled!"));
				}
				else {
					int num = 0;
					if(configs.getCratesCfg().getBoolean("Crates.Opening.Generic")) {
						num += 1;
					}
					if(configs.getCratesCfg().getBoolean("Crates.Opening.Roulette")) {
						num += 1;
					}
					if(configs.getCratesCfg().getBoolean("Crates.Opening.Quick")) {
						num += 1;
					}
					getServer().getConsoleSender().sendMessage(utils.format("&7Only &c1 &7opening method must be enabled at once, you currently have &c" + num + "&7!"));
					getServer().getConsoleSender().sendMessage(utils.format("&6=-=-=-=-= Ultimate Customisation =-=-=-=-="));
					utils.unload(this);
					return;
				}
			}
		}
		else {
			int num = 0;
			if(configs.getFlaresCfg().getBoolean("Redeeming.Click")) {
				num += 1;
			}
			if(configs.getFlaresCfg().getBoolean("Redeeming.NPC")) {
				num += 1;
			}
			if(configs.getFlaresCfg().getBoolean("Redeeming.Crates")) {
				num += 1;
			}
			getServer().getConsoleSender().sendMessage(utils.format("&7Only &c1 &7redeeming method must be enabled at once, you currently have &c" + num + "&7!"));
			getServer().getConsoleSender().sendMessage(utils.format("&6=-=-=-=-= Ultimate Customisation =-=-=-=-="));
			utils.unload(this);
			return;
		}
		getServer().getConsoleSender().sendMessage(utils.format("&6=-=-=-=-= Ultimate Customisation =-=-=-=-="));
	}
	
	@SuppressWarnings("deprecation")
	private void versionChecker() {
		try {
			String spigotversion = IOUtils.toString(new URL("https://api.spigotmc.org/legacy/update.php?resource=44120"));
			String pluginversion = getDescription().getVersion();
			Double spigotver = Double.parseDouble(spigotversion);
			Double pluginver = Double.parseDouble(pluginversion);
			if(spigotver > pluginver) {
				getServer().getConsoleSender().sendMessage("");
                getServer().getConsoleSender().sendMessage(utils.format("&cYou are not using the most up to date version of MiningFlares!"));
                getServer().getConsoleSender().sendMessage(utils.format("&cConsider updating too 1.2 for new features at:"));
                getServer().getConsoleSender().sendMessage(utils.format("&chttps://www.spigotmc.org/resources/miningflares.44120/"));
                getServer().getConsoleSender().sendMessage("");
			}
			else {
            	getServer().getConsoleSender().sendMessage("");
                getServer().getConsoleSender().sendMessage(utils.format("&aYou are using the most up to date version of MiningFlares!"));
                getServer().getConsoleSender().sendMessage("");
            }
		}
		catch (IOException e) {
			getServer().getConsoleSender().sendMessage("");
            getServer().getConsoleSender().sendMessage(utils.format("&cCould not make connection to SpigotMC.org to check for updates!"));
            getServer().getConsoleSender().sendMessage("");
		}
    }
    
	private void createCharts() {
		final Metrics metrics = new Metrics(this);
		// metrics.addCustomChart(new Metrics.SimplePie("chart_id", () -> "My value"));
		metrics.addCustomChart(new Metrics.SimplePie("redeem_Click", () -> {
			if(configs.getFlaresCfg().getBoolean("Redeeming.Click")) {
				return "enabled";
			}
			return "disabled";
		}
        ));
        metrics.addCustomChart(new Metrics.SimplePie("redeem_NPC", () -> {
        	if(configs.getFlaresCfg().getBoolean("Redeeming.NPC")) {
        		return "enabled";
        	}
        	return "disabled";
        }
        ));
        metrics.addCustomChart(new Metrics.SimplePie("redeem_Crates", () -> {
        	if(configs.getFlaresCfg().getBoolean("Redeeming.Crates")) {
        		return "enabled";
        	}
        	return "disabled";
        }
        ));
	}
}