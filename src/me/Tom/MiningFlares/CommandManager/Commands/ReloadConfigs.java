package me.Tom.MiningFlares.CommandManager.Commands;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Tom.MiningFlares.ConfigManager;
import me.Tom.MiningFlares.InventoryMethods;
import me.Tom.MiningFlares.PluginCore;
import me.Tom.MiningFlares.Utils;
import me.Tom.MiningFlares.CommandManager.SubCommands;
import me.Tom.MiningFlares.CommandManager.Symbols;
import me.Tom.MiningFlares.Crates.CrateMethods;
import me.Tom.MiningFlares.NPCs.NPCMethods;

public class ReloadConfigs extends SubCommands {
	
	private PluginCore core;
	private ConfigManager configs;
	private Utils<?> utils;
	private InventoryMethods invmethods;
	private NPCMethods npcmethods;
	private CrateMethods cratemethods;
	private List<UUID> warned;
	public ReloadConfigs(PluginCore pl) {
		core = pl;
		configs = core.getConfigs();
		utils = core.getUtils();
		invmethods = core.getInvMethods();
		npcmethods = core.getNPCMethods();
		cratemethods = core.getCrateMethods();
		warned = new ArrayList<UUID>();
	}

	@Override
	public void onCommand(CommandSender sender, String[] args) {
		if(sender.hasPermission("mflare.reload")) {
			if(args.length == 1) {
				sender.sendMessage(utils.addPrefix() + utils.format("&7Use &e/Mflare Help &7for correct usage!"));
			}
			else {
				if(args[1].equalsIgnoreCase("all")) {
					if(!(sender instanceof Player)) {
						sender.sendMessage(utils.addPrefix() + utils.format("&cConsole cannot reload all configs"));
					}
					else {
						Player p = (Player) sender;
						UUID uuid = p.getUniqueId();
						if(!warned.contains(uuid)) {
							warned.add(uuid);
							p.sendMessage(utils.format("&cWARNING &7If more than 1 config has errors when using this command there will be a split second of lag"));
						}
						else {
							configs.reloadAll();
							if(configs.getFlaresCfg().getKeys(false).size() > 0 && configs.getTier1Cfg().getKeys(false).size() > 0 && configs.getTier2Cfg().getKeys(false).size() > 0 && configs.getTier3Cfg().getKeys(false).size() > 0 && configs.getTier4Cfg().getKeys(false).size() > 0 && configs.getTier5Cfg().getKeys(false).size() > 0 && configs.getNPCCfg().getKeys(false).size() > 0 && configs.getCratesCfg().getKeys(false).size() > 0 && configs.getMsgCfg().getKeys(false).size() > 0) {
								p.sendMessage("");
								p.sendMessage(utils.format("&7-=-=- &6MiningFlares Configs &7-=-=-"));
								p.sendMessage(utils.format("&eFlares &7[&a" + Symbols.tick + "&7] - No errors, successfully reloaded"));
								invmethods.createRewardsMenu();
								invmethods.setupRewardsMenu();
								invmethods.createTier1WinningsMenu();
								invmethods.createTier2WinningsMenu();
								invmethods.createTier3WinningsMenu();
								invmethods.createTier4WinningsMenu();
								invmethods.createTier5WinningsMenu();
								p.sendMessage(utils.format("&eMessages &7[&a" + Symbols.tick + "&7] - No errors, successfully reloaded"));
								if(core.isHologramsEnabled()) {
									if(configs.getFlaresCfg().getBoolean("Redeeming.NPC")) {
										npcmethods.removeHolograms();
										npcmethods.unloadCitizenNPCS();
										npcmethods.loadCitizenNPCS();
										npcmethods.loadNPCHolograms();
										if(configs.getNPCCfg().getConfigurationSection("NPCS") != null) {
											for(final String st : configs.getNPCCfg().getConfigurationSection("NPCS").getKeys(false)) {
												final int id = Integer.parseInt(st);
												if(id > npcmethods.getMaxID()) {
													npcmethods.setMaxID(id);
												}
											}
										}
										p.sendMessage(utils.format("&eNPC's &7[&a" + Symbols.tick + "&7] - No errors, successfully reloaded"));
									}
									if(configs.getFlaresCfg().getBoolean("Redeeming.Crates")) {
										cratemethods.removeHolograms();
										cratemethods.loadCrateHolograms();
										p.sendMessage(utils.format("&eCrates &7[&a" + Symbols.tick + "&7] - No errors, successfully reloaded"));
									}
								}
								configs.reloadMethod(core, sender, "tier1", false);
								p.sendMessage(utils.format("&eTier1 &7[&a" + Symbols.tick + "&7] - No errors, successfully reloaded"));
								configs.reloadMethod(core, sender, "tier2", false);
								p.sendMessage(utils.format("&eTier2 &7[&a" + Symbols.tick + "&7] - No errors, successfully reloaded"));
								configs.reloadMethod(core, sender, "tier3", false);
								p.sendMessage(utils.format("&eTier3 &7[&a" + Symbols.tick + "&7] - No errors, successfully reloaded"));
								configs.reloadMethod(core, sender, "tier4", false);
								p.sendMessage(utils.format("&eTier4 &7[&a" + Symbols.tick + "&7] - No errors, successfully reloaded"));
								configs.reloadMethod(core, sender, "tier5", false);
								p.sendMessage(utils.format("&eTier5 &7[&a" + Symbols.tick + "&7] - No errors, successfully reloaded"));
								p.sendMessage(utils.format("&7-=-=- &6MiningFlares Configs &7-=-=-"));
								p.sendMessage("");
							}
							if(!(configs.getFlaresCfg().getKeys(false).size() > 0 && configs.getTier1Cfg().getKeys(false).size() > 0 && configs.getTier2Cfg().getKeys(false).size() > 0 && configs.getTier3Cfg().getKeys(false).size() > 0 && configs.getTier4Cfg().getKeys(false).size() > 0 && configs.getTier5Cfg().getKeys(false).size() > 0 && configs.getNPCCfg().getKeys(false).size() > 0 && configs.getCratesCfg().getKeys(false).size() > 0 && configs.getMsgCfg().getKeys(false).size() > 0)) {
								p.sendMessage("");
								p.sendMessage(utils.format("&7-=-=- &6MiningFlares Configs &7-=-=-"));
								if(configs.getFlaresCfg().getKeys(false).size() == 0) {
									configs.getFlaresFile().delete();
									configs.createFlaresCfg(core);
									p.sendMessage(utils.format("&eFlares &7[&c" + Symbols.cross + "&7] - An error has occured, check the console for info"));
								}
								else if(configs.getFlaresCfg().getKeys(false).size() > 0) {
									invmethods.createRewardsMenu();
									invmethods.setupRewardsMenu();
									invmethods.createTier1WinningsMenu();
									invmethods.createTier2WinningsMenu();
									invmethods.createTier3WinningsMenu();
									invmethods.createTier4WinningsMenu();
									invmethods.createTier5WinningsMenu();
									p.sendMessage(utils.format("&eFlares &7[&a" + Symbols.tick + "&7] - No errors, successfully reloaded"));
								}
								if(configs.getMsgCfg().getKeys(false).size() == 0) {
									configs.getMsgFile().delete();
									configs.createMsgCfg(core);
									p.sendMessage(utils.format("&eMessages &7[&c" + Symbols.cross + "&7] - An error has occured, check the console for info"));
								}
								else if(configs.getMsgCfg().getKeys(false).size() > 0) {
									p.sendMessage(utils.format("&eMessages &7[&a" + Symbols.tick + "&7] - No errors, successfully reloaded"));
								}
								if(core.isHologramsEnabled() == true) {
									if(configs.getFlaresCfg().getBoolean("Redeeming.NPC")) {
										npcmethods.removeHolograms();
										if(configs.getNPCCfg().getKeys(false).size() == 0) {
											configs.getNPCFile().delete();
											configs.createNPCCfg(core);
											p.sendMessage(utils.format("&eNPC's &7[&c" + Symbols.cross + "&7] - An error has occured, check the console for info"));
										}
										else if(configs.getNPCCfg().getKeys(false).size() > 0) {
											npcmethods.unloadCitizenNPCS();
											npcmethods.loadCitizenNPCS();
											npcmethods.loadNPCHolograms();
											if(configs.getNPCCfg().getConfigurationSection("NPCS") != null) {
												for(final String st : configs.getNPCCfg().getConfigurationSection("NPCS").getKeys(false)) {
													final int id = Integer.parseInt(st);
													if(id > npcmethods.getMaxID()) {
														npcmethods.setMaxID(id);
													}
												}
											}
											p.sendMessage(utils.format("&eNPC's &7[&a" + Symbols.tick + "&7] - No errors, successfully reloaded"));
										}
									}
									if(configs.getFlaresCfg().getBoolean("Redeeming.Crates")) {
										cratemethods.removeHolograms();
										if(configs.getCratesCfg().getKeys(false).size() == 0) {
											configs.getCratesFile().delete();
											configs.createCratesCfg(core);
											cratemethods.getTier1Loc().zero();
											cratemethods.getTier2Loc().zero();
											cratemethods.getTier3Loc().zero();
											cratemethods.getTier4Loc().zero();
											cratemethods.getTier5Loc().zero();
											p.sendMessage(utils.format("&eCrates &7[&c" + Symbols.cross + "&7] - An error has occured, check the console for info"));
										}
										else if(configs.getCratesCfg().getKeys(false).size() > 0) {
											cratemethods.loadCrateHolograms();
											p.sendMessage(utils.format("&eCrates &7[&a" + Symbols.tick + "&7] - No errors, successfully reloaded"));
										}
									}
								}
								if(configs.getTier1Cfg().getKeys(false).size() == 0) {
									configs.getTier1File().delete();
									configs.createTier1Cfg(core);
									p.sendMessage(utils.format("&eTier1 &7[&c" + Symbols.cross + "&7] - An error has occured, check the console for info"));
								}
								else if(configs.getTier1Cfg().getKeys(true).size() > 0) {
									configs.reloadMethod(core, sender, "tier1", false);
									p.sendMessage(utils.format("&eTier1 &7[&a" + Symbols.tick + "&7] - No errors, successfully reloaded"));
								}
								if(configs.getTier2Cfg().getKeys(false).size() == 0) {
									configs.getTier2File().delete();
									configs.createTier2Cfg(core);
									p.sendMessage(utils.format("&eTier2 &7[&c" + Symbols.cross + "&7] - An error has occured, check the console for info"));
								}
								else if(configs.getTier2Cfg().getKeys(false).size() > 0) {
									configs.reloadMethod(core, sender, "tier2", false);
									p.sendMessage(utils.format("&eTier2 &7[&a" + Symbols.tick + "&7] - No errors, successfully reloaded"));
								}
								if(configs.getTier3Cfg().getKeys(false).size() == 0) {
									configs.getTier3File().delete();
									configs.createTier3Cfg(core);
									p.sendMessage(utils.format("&eTier3 &7[&c" + Symbols.cross + "&7] - An error has occured, check the console for info"));
								}
								else if(configs.getTier3Cfg().getKeys(false).size() > 0) {
									configs.reloadMethod(core, sender, "tier3", false);
									p.sendMessage(utils.format("&eTier3 &7[&a" + Symbols.tick + "&7] - No errors, successfully reloaded"));
								}
								if(configs.getTier4Cfg().getKeys(false).size() == 0) {
									configs.getTier4File().delete();
									configs.createTier4Cfg(core);
									p.sendMessage(utils.format("&eTier4 &7[&c" + Symbols.cross + "&7] - An error has occured, check the console for info"));
								}
								else if(configs.getTier4Cfg().getKeys(false).size() > 0) {
									configs.reloadMethod(core, sender, "tier4", false);
									p.sendMessage(utils.format("&eTier4 &7[&a" + Symbols.tick + "&7] - No errors, successfully reloaded"));
								}
								if(configs.getTier5Cfg().getKeys(false).size() == 0) {
									configs.getTier5File().delete();
									configs.createTier5Cfg(core);
									p.sendMessage(utils.format("&eTier5 &7[&c" + Symbols.cross + "&7] - An error has occured, check the console for info"));
								}
								else if(configs.getTier5Cfg().getKeys(false).size() > 0) {
									configs.reloadMethod(core, sender, "tier5", false);
									p.sendMessage(utils.format("&eTier5 &7[&a" + Symbols.tick + "&7] - No errors, successfully reloaded"));
								}
								p.sendMessage(utils.format("&7-=-=- &6MiningFlares Configs &7-=-=-"));
								p.sendMessage("");
							}
							warned.remove(p.getUniqueId());
						}
					}
				}
				else if(args[1].equalsIgnoreCase("flares")) {
					configs.reloadFlaresCfg();
					if(configs.getFlaresCfg().getKeys(false).size() == 0) {
						configs.getFlaresFile().delete();
						configs.createFlaresCfg(core);
						sender.sendMessage(utils.addPrefix() + utils.format("&cThere was an error in the &eFlares &cconfig so it has been reset"));
					}
					else if(configs.getFlaresCfg().getKeys(false).size() > 0) {
						invmethods.createRewardsMenu();
						invmethods.setupRewardsMenu();
						invmethods.createTier1RewardsMenu();
						invmethods.setupTier1RewardsMenu();
						invmethods.createTier2RewardsMenu();
						invmethods.setupTier2RewardsMenu();
						invmethods.createTier3RewardsMenu();
						invmethods.setupTier3RewardsMenu();
						invmethods.createTier4RewardsMenu();
						invmethods.setupTier4RewardsMenu();
						invmethods.createTier5RewardsMenu();
						invmethods.setupTier5RewardsMenu();
						invmethods.createTier1WinningsMenu();
						invmethods.createTier2WinningsMenu();
						invmethods.createTier3WinningsMenu();
						invmethods.createTier4WinningsMenu();
						invmethods.createTier5WinningsMenu();
						sender.sendMessage(utils.addPrefix() + utils.format("&7The &eFlares &7config had no errors and successfully reloaded"));
					}
				}
				else if(args[1].equalsIgnoreCase("messages")) {
					configs.reloadMsgCfg();
					if(configs.getMsgCfg().getKeys(false).size() == 0) {
						configs.getMsgFile().delete();
						configs.createMsgCfg(core);
						sender.sendMessage(utils.addPrefix() + utils.format("&cThere was an error in the &eMessages &cconfig so it has been reset"));
					}
					else if(configs.getMsgCfg().getKeys(false).size() > 0) {
						sender.sendMessage(utils.addPrefix() + utils.format("&7The &eMessages &7config had no errors and successfully reloaded"));
					}
				}
				else if(args[1].equalsIgnoreCase("npcs")) {
					if(core.isHologramsEnabled()) {
						if(configs.getFlaresCfg().getBoolean("Redeeming.NPC")) {
							configs.reloadNPCCfg();
							npcmethods.removeHolograms();
							if(configs.getNPCCfg().getKeys(false).size() == 0) {
								configs.getNPCFile().delete();
								configs.createNPCCfg(core);
								sender.sendMessage(utils.addPrefix() + utils.format("&cThere was an error in the &eNPC's &cconfig so it has been reset"));
							}
							else if(configs.getNPCCfg().getKeys(false).size() > 0) {
								npcmethods.unloadCitizenNPCS();
								npcmethods.loadCitizenNPCS();
								npcmethods.loadNPCHolograms();
								if(configs.getNPCCfg().getConfigurationSection("NPCS") != null) {
									for(final String st : configs.getNPCCfg().getConfigurationSection("NPCS").getKeys(false)) {
										final int id = Integer.parseInt(st);
										if(id > npcmethods.getMaxID()) {
											npcmethods.setMaxID(id);
										}
									}
								}
								sender.sendMessage(utils.addPrefix() + utils.format("&7The &eNPC's &7config had no errors and successfully reloaded"));
							}
						}
						else {
							sender.sendMessage(utils.addPrefix() + utils.format("&7The NPC redeeming method is disabled"));
						}
					}
					else {
						sender.sendMessage(utils.addPrefix() + utils.format("&7HolographicDisplays is needed to use all NPC methods"));
					}
				}
				else if(args[1].equalsIgnoreCase("crates")) {
					if(core.isHologramsEnabled()) {
						if(configs.getFlaresCfg().getBoolean("Redeeming.Crates")) {
							configs.reloadCratesCfg();
							cratemethods.removeHolograms();
							if(configs.getCratesCfg().getKeys(false).size() == 0) {
								configs.getCratesFile().delete();
								configs.createCratesCfg(core);
								sender.sendMessage(utils.addPrefix() + utils.format("&cThere was an error in the &eCrates &cconfig so it has been reset"));
							}
							else if(configs.getCratesCfg().getKeys(false).size() > 0) {
								cratemethods.loadCrateHolograms();
								sender.sendMessage(utils.addPrefix() + utils.format("&7The &eCrates &7config had no errors and successfully reloaded"));
							}
						}
						else {
							sender.sendMessage(utils.addPrefix() + utils.format("&7The Crate redeeming method is disabled"));
						}
					}
					else {
						sender.sendMessage(utils.addPrefix() + utils.format("&7HolographicDisplays is needed to use all Crate methods"));
					}
				}
				else if(args[1].equalsIgnoreCase("tier1")) {
					configs.reloadMethod(core, sender, "tier1", true);
				}
				else if(args[1].equalsIgnoreCase("tier2")) {
					configs.reloadMethod(core, sender, "tier2", true);
				}
				else if(args[1].equalsIgnoreCase("tier3")) {
					configs.reloadMethod(core, sender, "tier3", true);
				}	
				else if(args[1].equalsIgnoreCase("tier4")) {
					configs.reloadMethod(core, sender, "tier4", true);
				}	
				else if(args[1].equalsIgnoreCase("tier5")) {
					configs.reloadMethod(core, sender, "tier5", true);
				}
				else {
					sender.sendMessage(utils.addPrefix() + utils.format("&7Use &e/Mflare Help &7for correct usage!"));
				}
			}
		}
		else {
			sender.sendMessage(utils.addPrefix() + utils.format("&4You do not have permissions to reload the configs"));
		}
	}

	@Override
	public String name() {
		return core.getCMDMgr().reload;
	}

	@Override
	public String info() {
		return "";
	}

	@Override
	public String[] aliases() {
		return new String[0];
	}
	
}	
