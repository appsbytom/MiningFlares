package me.Tom.MiningFlares.CommandManager.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Tom.MiningFlares.ConfigManager;
import me.Tom.MiningFlares.InventoryMethods;
import me.Tom.MiningFlares.PluginCore;
import me.Tom.MiningFlares.Utils;
import me.Tom.MiningFlares.CommandManager.SubCommands;

public class RewardsGUI extends SubCommands {
	
	private PluginCore core;
	private ConfigManager configs;
	private Utils<?> utils;
	private InventoryMethods invmethods;
	public RewardsGUI(PluginCore pl) {
		core = pl;
		configs = core.getConfigs();
		utils = core.getUtils();
		invmethods = core.getInvMethods();
	}
	
    @Override
    public void onCommand(CommandSender sender, String[] args) {
    	if(sender.hasPermission("mflare.rewards")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				utils.openInv(p, invmethods.getRewardsMenu());
				p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Rewards.MenuOpening")));
			}
			else {
				sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Rewards.WrongUser")));
			}
		}
		else {
			sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Rewards.NoPerms")));
		}
    }

    @Override
    public String name() {
        return core.getCMDMgr().rewards;
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
