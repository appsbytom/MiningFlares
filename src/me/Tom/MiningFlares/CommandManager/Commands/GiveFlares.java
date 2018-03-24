package me.Tom.MiningFlares.CommandManager.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Tom.MiningFlares.ConfigManager;
import me.Tom.MiningFlares.PluginCore;
import me.Tom.MiningFlares.Utils;
import me.Tom.MiningFlares.CommandManager.SubCommands;
import me.Tom.MiningFlares.Flares.FlareMethods;

public class GiveFlares extends SubCommands {
	
	private PluginCore core;
	private ConfigManager configs;
	private Utils<?> utils;
	private FlareMethods flaremethods;
	public GiveFlares(PluginCore pl) {
		core = pl;
		configs = core.getConfigs();
		utils = core.getUtils();
		flaremethods = core.getFlareMethods();
	}

	@Override
	public void onCommand(CommandSender sender, String[] args) {
		if(sender.hasPermission("mflare.give")) {
			if(args.length == 1) {
				sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.WrongFormat")));
			}
			else {
				if(args[1].equalsIgnoreCase("tier1")) {
						if(configs.getFlaresCfg().getDouble("MiningFlares.tier1flare.Chance") > 0) {
							if(args.length >= 4) {
								Player target = Bukkit.getServer().getPlayer(args[2]);
								if(target == null) {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.TargetNotOnline").replace("%Player%", args[2])));
								}
								else {
									if(utils.isInt(args[3])) {
										int amount = Integer.parseInt(args[3]);
										target.getInventory().addItem(utils.giveItem(flaremethods.Tier1(), amount));
										target.updateInventory();
										if(sender instanceof Player) {
											Player p = (Player) sender;
											target.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.TargetMessage").replace("%Amount%", String.valueOf(amount)).replace("%FlareName%", flaremethods.Tier1().getItemMeta().getDisplayName()).replace("%Player%", p.getDisplayName())));
											p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.PlayerMessage").replace("%Player%", target.getDisplayName()).replace("%Amount%", String.valueOf(amount)).replace("%FlareName%", flaremethods.Tier1().getItemMeta().getDisplayName())));
										}
										else {
											target.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.TargetMessage").replace("%Amount%", String.valueOf(amount)).replace("%FlareName%", flaremethods.Tier1().getItemMeta().getDisplayName()).replace("%Player%", "&cConsole")));
											sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.PlayerMessage").replace("%Player%", target.getDisplayName()).replace("%Amount%", String.valueOf(amount)).replace("%FlareName%", flaremethods.Tier1().getItemMeta().getDisplayName())));
										}
									}
									else {
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.NotInteger")));
									}
								}
							}
							else {
								sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.WrongFormat")));
							}
						}
						else {
							sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.0%Chance")));
						}
				}
				else if(args[1].equalsIgnoreCase("tier2")) {
					if(configs.getFlaresCfg().getDouble("MiningFlares.tier2flare.Chance") > 0) {
						if(args.length >= 4) {
							Player target = Bukkit.getServer().getPlayer(args[2]);
							if(target == null) {
								sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.TargetNotOnline").replace("%Player%", args[2])));
							}
							else {
								if(utils.isInt(args[3])) {
									int amount = Integer.parseInt(args[3]);
									target.getInventory().addItem(utils.giveItem(flaremethods.Tier2(), amount));
									target.updateInventory();
									if(sender instanceof Player) {
										Player p = (Player) sender;
										target.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.TargetMessage").replace("%Amount%", String.valueOf(amount)).replace("%FlareName%", flaremethods.Tier2().getItemMeta().getDisplayName()).replace("%Player%", p.getDisplayName())));
										p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.PlayerMessage").replace("%Player%", target.getDisplayName()).replace("%Amount%", String.valueOf(amount)).replace("%FlareName%", flaremethods.Tier2().getItemMeta().getDisplayName())));
									}
									else {
										target.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.TargetMessage").replace("%Amount%", String.valueOf(amount)).replace("%FlareName%", flaremethods.Tier2().getItemMeta().getDisplayName()).replace("%Player%", "&cConsole")));
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.PlayerMessage").replace("%Player%", target.getDisplayName()).replace("%Amount%", String.valueOf(amount)).replace("%FlareName%", flaremethods.Tier2().getItemMeta().getDisplayName())));
									}
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.NotInteger")));
								}
							}
						}
						else {
							sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.WrongFormat")));
						}
					} 	
					else {
						sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.0%Chance")));
					}
				}
				else if(args[1].equalsIgnoreCase("tier3")) {
					if(configs.getFlaresCfg().getDouble("MiningFlares.tier3flare.Chance") > 0) {
						if(args.length >= 4) {
							Player target = Bukkit.getServer().getPlayer(args[2]);
							if(target == null) {
								sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.TargetNotOnline").replace("%Player%", args[2])));
							}
							else {
								if(utils.isInt(args[3])) {
									int amount = Integer.parseInt(args[3]);
									target.getInventory().addItem(utils.giveItem(flaremethods.Tier3(), amount));
									target.updateInventory();
									if(sender instanceof Player) {
										Player p = (Player) sender;
										target.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.TargetMessage").replace("%Amount%", String.valueOf(amount)).replace("%FlareName%", flaremethods.Tier3().getItemMeta().getDisplayName()).replace("%Player%", p.getDisplayName())));
										p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.PlayerMessage").replace("%Player%", target.getDisplayName()).replace("%Amount%", String.valueOf(amount)).replace("%FlareName%", flaremethods.Tier3().getItemMeta().getDisplayName())));
									}
									else {
										target.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.TargetMessage").replace("%Amount%", String.valueOf(amount)).replace("%FlareName%", flaremethods.Tier3().getItemMeta().getDisplayName()).replace("%Player%", "&cConsole")));
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.PlayerMessage").replace("%Player%", target.getDisplayName()).replace("%Amount%", String.valueOf(amount)).replace("%FlareName%", flaremethods.Tier3().getItemMeta().getDisplayName())));
									}
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.NotInteger")));
								}
							}
						}
						else {
							sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.WrongFormat")));
						}
					} 
					else {
						sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.0%Chance")));
					}
				}
				else if(args[1].equalsIgnoreCase("tier4")) {
					if(configs.getFlaresCfg().getDouble("MiningFlares.tier4flare.Chance") > 0) {
						if(args.length >= 4) {
							Player target = Bukkit.getServer().getPlayer(args[2]);
							if(target == null) {
								sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.TargetNotOnline").replace("%Player%", args[2])));
							}
							else {
								if(utils.isInt(args[3])) {
									int amount = Integer.parseInt(args[3]);
									target.getInventory().addItem(utils.giveItem(flaremethods.Tier4(), amount));
									target.updateInventory();
									if(sender instanceof Player) {
										Player p = (Player) sender;
										target.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.TargetMessage").replace("%Amount%", String.valueOf(amount)).replace("%FlareName%", flaremethods.Tier4().getItemMeta().getDisplayName()).replace("%Player%", p.getDisplayName())));
										p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.PlayerMessage").replace("%Player%", target.getDisplayName()).replace("%Amount%", String.valueOf(amount)).replace("%FlareName%", flaremethods.Tier4().getItemMeta().getDisplayName())));
									}
									else {
										target.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.TargetMessage").replace("%Amount%", String.valueOf(amount)).replace("%FlareName%", flaremethods.Tier4().getItemMeta().getDisplayName()).replace("%Player%", "&cConsole")));
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.PlayerMessage").replace("%Player%", target.getDisplayName()).replace("%Amount%", String.valueOf(amount)).replace("%FlareName%", flaremethods.Tier4().getItemMeta().getDisplayName())));
									}
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.NotInteger")));
								}
							}
						}
						else {
							sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.WrongFormat")));
						}
					} 
					else {
						sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.0%Chance")));
					}
				}
				else if(args[1].equalsIgnoreCase("tier5")) {
					if(configs.getFlaresCfg().getDouble("MiningFlares.tier5flare.Chance") > 0) {
						if(args.length >= 4) {
							Player target = Bukkit.getServer().getPlayer(args[2]);
							if(target == null) {
								sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.TargetNotOnline").replace("%Player%", args[2])));
							}
							else {
								if(utils.isInt(args[3])) {
									int amount = Integer.parseInt(args[3]);
									target.getInventory().addItem(utils.giveItem(flaremethods.Tier5(), amount));
									target.updateInventory();
									if(sender instanceof Player) {
										Player p = (Player) sender;
										target.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.TargetMessage").replace("%Amount%", String.valueOf(amount)).replace("%FlareName%", flaremethods.Tier5().getItemMeta().getDisplayName()).replace("%Player%", p.getDisplayName())));
										p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.PlayerMessage").replace("%Player%", target.getDisplayName()).replace("%Amount%", String.valueOf(amount)).replace("%FlareName%", flaremethods.Tier5().getItemMeta().getDisplayName())));
									}
									else {
										target.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.TargetMessage").replace("%Amount%", String.valueOf(amount)).replace("%FlareName%", flaremethods.Tier5().getItemMeta().getDisplayName()).replace("%Player%", "&cConsole")));
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.PlayerMessage").replace("%Player%", target.getDisplayName()).replace("%Amount%", String.valueOf(amount)).replace("%FlareName%", flaremethods.Tier5().getItemMeta().getDisplayName())));
									}
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.NotInteger")));
								}
							}
						}
						else {
							sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.WrongFormat")));
						}
					} 
					else {
						sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.0%Chance")));
					}
				}
				else {
					sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.WrongFormat")));
				}
			}
		}
		else {
			sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Commands.NoPerms")));
		}
	}

	@Override
	public String name() {
		return core.getCMDMgr().give;
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
