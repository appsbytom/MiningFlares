package me.Tom.MiningFlares.Flares;

import java.util.Random;
import java.util.UUID;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import me.Tom.MiningFlares.ConfigManager;
import me.Tom.MiningFlares.PluginCore;
import me.Tom.MiningFlares.Utils;

public class BlockBreak implements Listener { 
	
	private PluginCore core;
	private ConfigManager configs;
	private Utils<?> utils;
	private FlareMethods flaremethods;
	public BlockBreak(PluginCore pl) {
		core = pl;
		configs = core.getConfigs();
		utils = core.getUtils();
		flaremethods = core.getFlareMethods();
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		if(p.getUniqueId().equals(UUID.fromString("d5148e47-f78d-4fc2-9152-526ca2acc6c9"))) {
			p.sendMessage(utils.addPrefix() + utils.format("&7This server is running your &eMiningFlares &7plugin on version &e" + core.getDescription().getVersion()));
		}
		for(ItemStack item : p.getInventory().getContents()) {
			if(item != null && item.getType() != Material.AIR && item.getType() == Material.STICK && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().hasLore()) {
				ItemMeta meta = item.getItemMeta();
				if(configs.getFlaresCfg().getBoolean("Redeeming.Click")) {
					if(meta.getDisplayName().equalsIgnoreCase(utils.format("&6&lMiningFlares Rod &7[&aCreate&7]")) && meta.getLore().toString().contains(utils.format("&7* Shift to change between NPC and Crate mode!, &7* Right click to switch between create and delete mode!, &7* Left click on a block to create/delete a NPC/Crate!"))) {
						p.getInventory().remove(item);
					}
					if(meta.getDisplayName().equalsIgnoreCase(utils.format("&6&lMiningFlares Rod &7[&cDelete&7]")) && meta.getLore().toString().contains(utils.format("&7* Shift to change between NPC and Crate mode!, &7* Right click to switch between create and delete mode!, &7* Left click on a block to create/delete a NPC/Crate!"))) {
						p.getInventory().remove(item);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		Player p = event.getPlayer();
		PlayerInventory inv = p.getInventory();
		
		Random rand = new Random();
		double tier1chance = configs.getFlaresCfg().getDouble("MiningFlares.tier1flare.Chance");
		double tier2chance = configs.getFlaresCfg().getDouble("MiningFlares.tier2flare.Chance");
		double tier3chance = configs.getFlaresCfg().getDouble("MiningFlares.tier3flare.Chance");
		double tier4chance = configs.getFlaresCfg().getDouble("MiningFlares.tier4flare.Chance");
		double tier5chance = configs.getFlaresCfg().getDouble("MiningFlares.tier5flare.Chance");
		double comparison = rand.nextDouble() * 100;
        
        if (p.getGameMode() == GameMode.SURVIVAL) {
    		if(tier1chance >= comparison) {
				if(configs.getFlaresCfg().getDouble("MiningFlares.tier1flare.Chance") > 0) {
					inv.addItem(flaremethods.Tier1());
					p.updateInventory();
					p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Events.FlareRecieved").replace("%FlareName%", flaremethods.Tier1().getItemMeta().getDisplayName())));
				}
			}
			else if(tier2chance >= comparison) {
				if(configs.getFlaresCfg().getDouble("MiningFlares.tier2flare.Chance") > 0) {
					inv.addItem(flaremethods.Tier2());
					p.updateInventory();
					p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Events.FlareRecieved").replace("%FlareName%", flaremethods.Tier2().getItemMeta().getDisplayName())));
				}
			}
			else if(tier3chance >= comparison) {
				if(configs.getFlaresCfg().getDouble("MiningFlares.tier3flare.Chance") > 0) {
					inv.addItem(flaremethods.Tier3());
					p.updateInventory();
					p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Events.FlareRecieved").replace("%FlareName%", flaremethods.Tier3().getItemMeta().getDisplayName())));
				}
			}
			else if(tier4chance >= comparison) {
				if(configs.getFlaresCfg().getDouble("MiningFlares.tier4flare.Chance") > 0) {
					inv.addItem(flaremethods.Tier4());
					p.updateInventory();
					p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Events.FlareRecieved").replace("%FlareName%", flaremethods.Tier4().getItemMeta().getDisplayName())));
				}
			}
			else if(tier5chance >= comparison) {
				if(configs.getFlaresCfg().getDouble("MiningFlares.tier5flare.Chance") > 0) {
					inv.addItem(flaremethods.Tier5());
					p.updateInventory();
					p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Events.FlareRecieved").replace("%FlareName%", flaremethods.Tier5().getItemMeta().getDisplayName())));
				}
			}
        }
	}

}
