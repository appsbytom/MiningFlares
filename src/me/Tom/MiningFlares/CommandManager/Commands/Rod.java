package me.Tom.MiningFlares.CommandManager.Commands;

import java.util.UUID;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.Tom.MiningFlares.ConfigManager;
import me.Tom.MiningFlares.PluginCore;
import me.Tom.MiningFlares.Utils;
import me.Tom.MiningFlares.CommandManager.SubCommands;
import me.Tom.MiningFlares.ControlRod.RodMethods;

public class Rod extends SubCommands {
	
	private PluginCore core;
	private ConfigManager configs;
	private Utils<?> utils;
	private RodMethods rodmethods;
	public Rod(PluginCore pl) {
		core = pl;
		configs = core.getConfigs();
		utils = core.getUtils();
		rodmethods = core.getRodMethods();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onCommand(CommandSender sender, String[] args) {
		if(sender.hasPermission("mflare.rod")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				UUID id = p.getUniqueId();
				if(p.getGameMode() == GameMode.SURVIVAL) {
					if(!rodmethods.getRod().contains(id)) {
						if(p.getItemInHand() == null || p.getItemInHand().getType() == Material.AIR) {
							p.setItemInHand(rodmethods.npcRod());
							rodmethods.getRod().add(id);
							rodmethods.getNPCList().add(id);
							rodmethods.getCreateList().add(id);
							p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Rod.Commands.Enable")));
						}
						else {
							p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Rod.Commands.EmptyHand")));
						}
					}
					else {
						for(ItemStack i : p.getInventory().getContents()) {
							if(i != null && i.getType() != Material.AIR && i.getType() == Material.STICK && i.hasItemMeta() && i.getItemMeta().hasDisplayName() && i.getItemMeta().hasLore()) {
								if(i.getItemMeta().getDisplayName().equalsIgnoreCase(utils.format("&6&lMiningFlares Rod &7[&aCreate&7]")) && i.getItemMeta().getLore().toString().contains(utils.format("&7* Shift to change between NPC and Crate mode!, &7* Right click to switch between create and delete mode!, &7* Left click on a block to create/delete a NPC/Crate!"))) {
									if(core.getVersion().equals("v1_8_R1") || core.getVersion().equals("v1_8_R2") || core.getVersion().equals("v1_8_R3")) {
										p.getInventory().remove(i);
									}
									else {
										i.setAmount(-1);
									}
								}
								if(i.getItemMeta().getDisplayName().equalsIgnoreCase(utils.format("&6&lMiningFlares Rod &7[&cDelete&7]")) && i.getItemMeta().getLore().toString().contains(utils.format("&7* Shift to change between NPC and Crate mode!, &7* Right click to switch between create and delete mode!, &7* Left click on a block to create/delete a NPC/Crate!"))) {
									if(core.getVersion().equals("v1_8_R1") || core.getVersion().equals("v1_8_R2") || core.getVersion().equals("v1_8_R3")) {
										p.getInventory().remove(i);
									}
									else {
										i.setAmount(-1);
									}
								}
							}
						}
						rodmethods.getRod().remove(id);
						rodmethods.getNPCList().remove(id);
						rodmethods.getCrateList().remove(id);
						rodmethods.getCreateList().remove(id);
						rodmethods.getDeleteList().remove(id);
						p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Rod.Commands.Disable")));
					}
				}
				else {
					p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Rod.Commands.CreativeMode")));
				}
			}
			else {
				sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Rod.Commands.WrongUser")));
			}
		}
		else {
			sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Rod.Commands.NoPerms")));
		}
	}

	@Override
	public String name() {
		return core.getCMDMgr().rod;
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
