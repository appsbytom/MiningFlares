package me.Tom.MiningFlares.CommandManager.Commands;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Tom.MiningFlares.ConfigManager;
import me.Tom.MiningFlares.PluginCore;
import me.Tom.MiningFlares.Utils;
import me.Tom.MiningFlares.CommandManager.SubCommands;
import me.Tom.MiningFlares.Crates.CrateMethods;

public class Crates extends SubCommands {
	
	private PluginCore core;
	private ConfigManager configs;
	private Utils<?> utils;
	private CrateMethods cratemethods;
	public Crates(PluginCore pl) {
		core = pl;
		configs = core.getConfigs();
		utils = core.getUtils();
		cratemethods = core.getCrateMethods();
	}

	@Override
	public void onCommand(CommandSender sender, String[] args) {
		if(sender.hasPermission("mflare.crates")) {
			if(args.length == 1) {
				sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.WrongFormat")));
			}
			else {
				if(args[1].equalsIgnoreCase("tp")) {
					if(sender instanceof Player) {
						Player p = (Player) sender;
						if(args.length >= 3) {
							if(args[2].equalsIgnoreCase("tier1")) {
								if(configs.getCratesCfg().contains("Crates.Tier1.Loc")) {
									Location loc = new Location(cratemethods.getTier1Loc().getWorld(), cratemethods.getTier1Loc().getX(), cratemethods.getTier1Loc().getY(), cratemethods.getTier1Loc().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch());
									p.teleport(loc.add(0.5, 1.0, 0.5));
								}
								else {
									p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Commands.NoCratePlaced").replace("%CrateName%", configs.getCratesCfg().getString("Crates.Tier1.Name"))));
								}
							}
							else if(args[2].equalsIgnoreCase("tier2")) {
								if(configs.getCratesCfg().contains("Crates.Tier2.Loc")) {
									Location loc = new Location(cratemethods.getTier2Loc().getWorld(), cratemethods.getTier2Loc().getX(), cratemethods.getTier2Loc().getY(), cratemethods.getTier2Loc().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch());
									p.teleport(loc.add(0.5, 1.0, 0.5));
								}
								else {
									p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Commands.NoCratePlaced").replace("%CrateName%", configs.getCratesCfg().getString("Crates.Tier2.Name"))));
								}
							}
							else if(args[2].equalsIgnoreCase("tier3")) {
								if(configs.getCratesCfg().contains("Crates.Tier3.Loc")) {
									Location loc = new Location(cratemethods.getTier3Loc().getWorld(), cratemethods.getTier3Loc().getX(), cratemethods.getTier3Loc().getY(), cratemethods.getTier3Loc().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch());
									p.teleport(loc.add(0.5, 1.0, 0.5));
								}
								else {
									p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Commands.NoCratePlaced").replace("%CrateName%", configs.getCratesCfg().getString("Crates.Tier3.Name"))));
								}
							}
							else if(args[2].equalsIgnoreCase("tier4")) {
								if(configs.getCratesCfg().contains("Crates.Tier4.Loc")) {
									Location loc = new Location(cratemethods.getTier4Loc().getWorld(), cratemethods.getTier4Loc().getX(), cratemethods.getTier4Loc().getY(), cratemethods.getTier4Loc().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch());
									p.teleport(loc.add(0.5, 1.0, 0.5));
								}
								else {
									p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Commands.NoCratePlaced").replace("%CrateName%", configs.getCratesCfg().getString("Crates.Tier4.Name"))));
								}
							}
							else if(args[2].equalsIgnoreCase("tier5")) {
								if(configs.getCratesCfg().contains("Crates.Tier5.Loc")) {
									Location loc = new Location(cratemethods.getTier5Loc().getWorld(), cratemethods.getTier5Loc().getX(), cratemethods.getTier5Loc().getY(), cratemethods.getTier5Loc().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch());
									p.teleport(loc.add(0.5, 1.0, 0.5));
								}
								else {
									p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Commands.NoCratePlaced").replace("%CrateName%", configs.getCratesCfg().getString("Crates.Tier5.Name"))));
								}
							}
							else {
								p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.WrongFormat")));
							}
						}
						else {
							p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.WrongFormat")));
						}
					}
					else {
						sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Commands.WrongUser")));
					}
					
				}
				else {
					sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.WrongFormat")));
				}
			}
		}
		else {
			sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Commands.NoPerms")));
		}
	}

	@Override
	public String name() {
		return core.getCMDMgr().crates;
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
