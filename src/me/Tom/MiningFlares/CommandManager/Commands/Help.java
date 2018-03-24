package me.Tom.MiningFlares.CommandManager.Commands;

import org.bukkit.command.CommandSender;

import me.Tom.MiningFlares.ConfigManager;
import me.Tom.MiningFlares.PluginCore;
import me.Tom.MiningFlares.Utils;
import me.Tom.MiningFlares.CommandManager.SubCommands;

public class Help extends SubCommands {
	
	private PluginCore core;
	private ConfigManager configs;
	private Utils<?> utils;
	public Help(PluginCore pl) {
		core = pl;
		configs = core.getConfigs();
		utils = core.getUtils();
	}

	@Override
	public void onCommand(CommandSender sender, String[] args) {
		if(args.length == 1) {
			sender.sendMessage("");
			sender.sendMessage(utils.format("&7-=-=- &6MiningFlares Help &7-=-=-"));
			if(sender.hasPermission("mflare.give")) {
				sender.sendMessage(utils.format("&e/Mflare Give <Tier1-5> <Player> <Amount> &7- Give any flare to any online player"));
			}
			if(sender.hasPermission("mflare.rewards")) {
				sender.sendMessage(utils.format("&e/Mflare Rewards &7- Opens up the rewards menu"));
			}
			if(core.isHologramsEnabled()) {
				if(configs.getFlaresCfg().getBoolean("Redeeming.NPC") || configs.getFlaresCfg().getBoolean("Redeeming.Crates")) {
					if(sender.hasPermission("mflare.rod")) {
						sender.sendMessage(utils.format("&e/Mflare Rod &7- Enable rod mode to edit NPCs/Crates"));
					}
				}
				if(configs.getFlaresCfg().getBoolean("Redeeming.NPC")) {
					if(sender.hasPermission("mflare.npcs")) {
						sender.sendMessage(utils.format("&e/Mflare Help Npc &7- See all commands linked with /Mflare Npc"));
					}
				}
				if(configs.getFlaresCfg().getBoolean("Redeeming.Crates")) {
					if(sender.hasPermission("mflare.crates")) {
						sender.sendMessage(utils.format("&e/Mflare Help Crates &7- See all commands linked with /Mflare Crate"));
					}
				}
			}
			if(sender.hasPermission("mflare.editloot")) {
				sender.sendMessage(utils.format("&e/Mflare Help Loot &7- See all commands linked with /Mflare Loot"));
			}
			if(sender.hasPermission("mflare.reload")) {
				if(core.isHologramsEnabled()) {
					if(configs.getFlaresCfg().getBoolean("Redeeming.NPC")) {
						sender.sendMessage(utils.format("&e/Mflare Reload <All/Flares/Messages/NPCs/Tier1-5> &7- Reload MiningFlares configs"));
					}
					if(configs.getFlaresCfg().getBoolean("Redeeming.Crates")) {
						sender.sendMessage(utils.format("&e/Mflare Reload <All/Flares/Messages/Crates/Tier1-5> &7- Reload MiningFlares configs"));
					}
				}
				else {
					sender.sendMessage(utils.format("&e/Mflare Reload <All/Flares/Messages/Tier1-5> &7- Reload MiningFlares configs"));
				}
			}
			sender.sendMessage(utils.format("&e/Mflare Help &7- Shows all MiningFlares commands"));
			sender.sendMessage(utils.format("&7-=-=- &6MiningFlares Help &7-=-=-"));
			sender.sendMessage("");
		}
		else {
			if(args[1].equalsIgnoreCase("Npc")) {
				if(core.isHologramsEnabled()) {
					if(configs.getFlaresCfg().getBoolean("Redeeming.NPC")) {
						if(sender.hasPermission("mflare.npcs")) {
							sender.sendMessage("");
							sender.sendMessage(utils.format("&7-=-=- &6MiningFlares NPC Help &7-=-=-"));
							sender.sendMessage(utils.format("&e/Mflare Npc <Set> <Skin/Name> <NPCiD> <Name> &7- Change the skin or name of an NPC"));
							sender.sendMessage(utils.format("&e/Mflare Npc <List> &7- List all NPC's"));
							sender.sendMessage(utils.format("&e/Mflare Npc <Info> <NPCiD> &7- Retrieve info on a specific NPC"));
							sender.sendMessage(utils.format("&e/Mflare Npc <Tp> <NPCiD> &7- Tp to a specific NPC"));
							sender.sendMessage(utils.format("&e/Mflare Npc <Tphere> <NPCiD> &7- Tp a specific NPC to your position"));
							sender.sendMessage(utils.format("&7-=-=- &6MiningFlares NPC Help &7-=-=-"));
							sender.sendMessage("");
						}
						else {
							sender.sendMessage(utils.addPrefix() + utils.format("&4You do not have permissions to see NPC commands"));
						}
					}
					else {
						sender.sendMessage(utils.addPrefix() + utils.format("&4NPC commands are toggled off"));
					}
				}
			}
			else if(args[1].equalsIgnoreCase("Crates")) {
				if(core.isHologramsEnabled()) {
					if(configs.getFlaresCfg().getBoolean("Redeeming.Crates")) {
						if(sender.hasPermission("mflare.crates")) {
							sender.sendMessage("");
							sender.sendMessage(utils.format("&7-=-=- &6MiningFlares Crates Help &7-=-=-"));
							sender.sendMessage(utils.format("&e/Mflare Crate Tp <Tier1-5> &7- Tp to a specific crate location"));
							sender.sendMessage(utils.format("&7-=-=- &6MiningFlares Crates Help &7-=-=-"));
							sender.sendMessage("");
						}
						else {
							sender.sendMessage(utils.addPrefix() + utils.format("&4You do not have permissions to see Crate commands"));
						}
					}
					else {
						sender.sendMessage(utils.addPrefix() + utils.format("&4Crate commands are toggled off"));
					}
				}
			}
			else if(args[1].equalsIgnoreCase("Loot")) {
				if(sender.hasPermission("mflare.editloot")) {
					sender.sendMessage("");
					sender.sendMessage(utils.format("&7-=-=- &6MiningFlares Loot Help &7-=-=-"));
					sender.sendMessage(utils.format("&e/Mflare Loot <Add> <Tier1-5> <Chance> &7- Add your held item to the rewards"));
					sender.sendMessage(utils.format("&e/Mflare Loot <Remove> <Tier1-5> <lootID> &7- Remove loot from the rewards"));
					sender.sendMessage(utils.format("&e/Mflare Loot <Info> <Tier1-5> <lootID> &7- Retrieve info on specific loot"));
					sender.sendMessage(utils.format("&e/Mflare Loot <Set> <Item> <Tier1-5> <lootID> <itemID> &7- Set the itemID of any lootID"));
					sender.sendMessage(utils.format("&e/Mflare Loot <Set> <Data> <Tier1-5> <lootID> <dataID> &7- Set the dataID of any lootID"));
					sender.sendMessage(utils.format("&e/Mflare Loot <Set> <Name> <Tier1-5> <lootID> <Name> &7- Set the name of any lootID"));
					sender.sendMessage(utils.format("&e/Mflare Loot <Set> <Lore> <Tier1-5> <lootID> <Line1,Line2> &7- Set the lore of any lootID"));
					sender.sendMessage(utils.format("&e/Mflare Loot <Set> <Enchant> <Tier1-5> <lootID> <Name;Level,Name;Level> &7- Set the enchant name and level of any lootID"));
					sender.sendMessage(utils.format("&7-=-=- &6MiningFlares Loot Help &7-=-=-"));
					sender.sendMessage("");
				}
				else {
					sender.sendMessage(utils.addPrefix() + utils.format("&4You do not have permissions to see Loot commands"));
				}
			}
			else {
				sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.WrongFormat")));
			}
		}
	}

	@Override
	public String name() {
		return core.getCMDMgr().help;
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
