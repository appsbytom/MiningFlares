package me.Tom.MiningFlares.CommandManager.Commands;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.scheduler.BukkitRunnable;

import me.Tom.MiningFlares.ConfigManager;
import me.Tom.MiningFlares.PluginCore;
import me.Tom.MiningFlares.Utils;
import me.Tom.MiningFlares.CommandManager.SubCommands;
import me.Tom.MiningFlares.NPCs.NPCMethods;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.DespawnReason;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.npc.skin.SkinnableEntity;

public class NPCs extends SubCommands {
	
	private PluginCore core;
	private ConfigManager configs;
	private Utils<?> utils;
	private NPCMethods npcmethods;
	public NPCs(PluginCore pl) {
		core = pl;
		configs = core.getConfigs();
		utils = core.getUtils();
		npcmethods = core.getNPCMethods();
	}

	@Override
	public void onCommand(CommandSender sender, String[] args) {
		if(sender.hasPermission("mflare.npcs")) {
			if(args.length == 1) {
				sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.WrongFormat")));
			}
			else {
				if(args[1].equalsIgnoreCase("set")) {
					if(args.length >= 5) {
						if(utils.isInt(args[3])) {
							int NPCiD = Integer.parseInt(args[3]);
							if(args[2].equalsIgnoreCase("skin")) {
								if(configs.getNPCCfg().contains("NPCS." + NPCiD) && CitizensAPI.getNPCRegistry().getById(NPCiD) != null) {
									NPC npc = CitizensAPI.getNPCRegistry().getById(NPCiD);
									SkinnableEntity skinnable = (SkinnableEntity) npc.getEntity();
									Location npcloc = npc.getStoredLocation();
									skinnable.setSkinName(args[4]);
									npc.despawn(DespawnReason.PENDING_RESPAWN);
									npcmethods.removeHolograms();
									new BukkitRunnable() {
										
										@Override
										public void run() {
											npc.spawn(npcloc);
											configs.getNPCCfg().set("NPCS." + NPCiD + ".Skin", args[4]);
											npcmethods.loadNPCHolograms();
											configs.saveNPCCfg();
											configs.reloadNPCCfg();
											sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("NPC.Commands.SetSkin").replace("%Skin%", args[4])));
										}
									}.runTaskLater(core, 10L);
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("NPC.Commands.NotNPC")));
								}
							}
							else if(args[2].equalsIgnoreCase("name")) {
								if(configs.getNPCCfg().contains("NPCS." + NPCiD) && CitizensAPI.getNPCRegistry().getById(NPCiD) != null) {
									NPC npc = CitizensAPI.getNPCRegistry().getById(NPCiD);
									Location npcloc = npc.getStoredLocation();
									npc.setName(args[4]);
									npc.despawn(DespawnReason.PENDING_RESPAWN);
									npcmethods.removeHolograms();
									new BukkitRunnable() {
										
										@Override
										public void run() {
											npc.spawn(npcloc);
											configs.getNPCCfg().set("NPCS." + NPCiD + ".Name", args[4]);
											npcmethods.loadNPCHolograms();
											configs.saveNPCCfg();
											configs.reloadNPCCfg();
											sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("NPC.Commands.SetName").replace("%NPCName%", args[4])));
										}
									}.runTaskLater(core, 10L);
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("NPC.Commands.NotNPC")));
								}
							}
							else {
								sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.WrongFormat")));
							}
						}
						else {
							sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("NPC.Commands.NotNPC")));
						}
					}
					else {
						sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.WrongFormat")));	
					}
				}
				else if(args[1].equalsIgnoreCase("list")) {
					for(NPC npc : CitizensAPI.getNPCRegistry()) {
						int NPCiD = npc.getId();
						if(configs.getNPCCfg().contains("NPCS." + NPCiD)) {
							String name = npc.getName();
							SkinnableEntity skinnable = (SkinnableEntity) npc.getEntity();
							String skin = skinnable.getSkinName();
							sender.sendMessage(utils.format(configs.getMsgCfg().getStringList("NPC.Commands.List").toString().replace(", ", "\n").replace("[", "").replace("]", "").replace("%NPCID%", String.valueOf(NPCiD)).replace("%NPCName%", name).replace("%Skin%", skin)));
						}
						else {
							sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("NPC.Commands.NoNPCs")));
						}
					}
				}
				else if(args[1].equalsIgnoreCase("info")) {
					if(args.length >= 3) {
						if(utils.isInt(args[2])) {
							int NPCiD = Integer.parseInt(args[2]);
							if(configs.getNPCCfg().contains("NPCS." + NPCiD) && CitizensAPI.getNPCRegistry().getById(NPCiD) != null) {
								NPC npc = CitizensAPI.getNPCRegistry().getById(NPCiD);
								Location npcloc = npc.getStoredLocation();
								String name = npc.getName();
								SkinnableEntity skinnable = (SkinnableEntity) npc.getEntity();
								String skin = skinnable.getSkinName();
								sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("NPC.Commands.NPCInfo").replace("%NPCID%", String.valueOf(NPCiD)).replace("%NPCName%", name).replace("%Skin%", skin).replace("%NPCLocation%", npcloc.getWorld().getName() + ", " + npcloc.getX() + ", " + npcloc.getY() + ", " + npcloc.getZ())));
							}
							else {
								sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("NPC.Commands.NotNPC")));
							}
						}
						else {
							sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("NPC.Commands.NotInteger")));
						}
					}
					else {
						sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.WrongFormat")));	
					}
				}
				else if(args[1].equalsIgnoreCase("tp")) {
					if(sender instanceof Player) {
						Player p = (Player) sender;
						if(args.length >= 3) {
							if(utils.isInt(args[2])) {
								int NPCiD = Integer.parseInt(args[2]);
								if(configs.getNPCCfg().contains("NPCS." + NPCiD) && CitizensAPI.getNPCRegistry().getById(NPCiD) != null) {
									NPC npc = CitizensAPI.getNPCRegistry().getById(NPCiD);
									Location npcloc = npc.getStoredLocation();
									p.teleport(npcloc);
									p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("NPC.Commands.TpToNPC").replace("%NPCID%", String.valueOf(NPCiD))));
								}
								else {
									p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("NPC.Commands.NotNPC")));
								}
							}
							else {
								p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("NPC.Commands.NotInteger")));
							}
						}
						else {
							p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.WrongFormat")));	
						}
					}
					else {
						sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("NPC.Commands.WrongUser")));
					}
				}
				else if(args[1].equalsIgnoreCase("tphere")) {
					if(sender instanceof Player) {
						Player p = (Player) sender;
						if(args.length >= 3) {
							if(utils.isInt(args[2])) {
								int NPCiD = Integer.parseInt(args[2]);
								if(configs.getNPCCfg().contains("NPCS." + NPCiD) && CitizensAPI.getNPCRegistry().getById(NPCiD) != null) {
									if(configs.getNPCCfg().contains("NPCS." + NPCiD + ".Location")) {
										NPC npc = CitizensAPI.getNPCRegistry().getById(NPCiD);
										Location playerloc = p.getLocation();
										npcmethods.removeHolograms();
										new BukkitRunnable() {
											
											@Override
											public void run() {
												npc.teleport(playerloc, TeleportCause.COMMAND);
												configs.getNPCCfg().set("NPCS." + NPCiD + ".Location", String.valueOf(playerloc.getWorld().getName()) + ", " + playerloc.getX() + ", " + playerloc.getY() + ", " + playerloc.getZ() + ", " + playerloc.getYaw() + ", " + playerloc.getPitch());
												npcmethods.loadNPCHolograms();
												configs.saveNPCCfg();
												configs.reloadNPCCfg();
												p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("NPC.Commands.TpNPCHere").replace("%NPCID%", String.valueOf(NPCiD))));
											}
										}.runTaskLater(core, 10L);
									}
								}
								else {
									p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("NPC.Commands.NotNPC")));
								}
							}
							else {
								p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("NPC.Commands.NotInteger")));
							}
						}
						else {
							p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.WrongFormat")));
						}
					}
					else {
						sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("NPC.Commands.WrongUser")));
					}
				}
				else {
					sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.WrongFormat")));
				}
			}
		}
		else {
			sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("NPC.Commands.NoPerms")));
		}
	}

	@Override
	public String name() {
		return core.getCMDMgr().npc;
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
