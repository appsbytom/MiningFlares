package me.Tom.MiningFlares.Flares;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.Tom.MiningFlares.ConfigManager;
import me.Tom.MiningFlares.InventoryMethods;
import me.Tom.MiningFlares.PluginCore;
import me.Tom.MiningFlares.Utils;

public class RewardsMenuClick implements Listener {

	private PluginCore core;
	private ConfigManager configs;
	private Utils<?> utils;
	private InventoryMethods invmethods;
	public RewardsMenuClick(PluginCore pl) {
		core = pl;
		configs = core.getConfigs();
		utils = core.getUtils();
		invmethods = core.getInvMethods();
	}
	
	@EventHandler
	public void invClick(InventoryClickEvent event) {
		Player p = (Player) event.getWhoClicked();
		ItemStack clicked = event.getCurrentItem();
		Inventory inventory = event.getInventory();
		Integer slot = event.getSlot();
		
		if(inventory.equals(invmethods.getRewardsMenu())) {
			if(clicked != null && clicked.getType() != null && clicked.hasItemMeta() != false) {
				if(slot == configs.getFlaresCfg().getInt("MiningFlares.tier1flare.RewardsGUI.Slot")) {
					event.setCancelled(true);
					if(configs.getFlaresCfg().getDouble("MiningFlares.tier1flare.Chance") > 0) {
						utils.openInv(p, invmethods.getTier1RewardsMenu());
					}
					else {
						p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Events.FlareDisabled").replace("%FlareName%", "Tier1")));
					}
				}
				if(slot == configs.getFlaresCfg().getInt("MiningFlares.tier2flare.RewardsGUI.Slot")) {
					event.setCancelled(true);
					if(configs.getFlaresCfg().getDouble("MiningFlares.tier2flare.Chance") > 0) {
						utils.openInv(p, invmethods.getTier2RewardsMenu());
					}
					else {
						p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Events.FlareDisabled").replace("%FlareName%", "Tier2")));
					}
				}
				if(slot == configs.getFlaresCfg().getInt("MiningFlares.tier3flare.RewardsGUI.Slot")) {
					event.setCancelled(true);
					if(configs.getFlaresCfg().getDouble("MiningFlares.tier3flare.Chance") > 0) {
						utils.openInv(p, invmethods.getTier3RewardsMenu());
					}
					else {
						p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Events.FlareDisabled").replace("%FlareName%", "Tier3")));
					}
				}
				if(slot == configs.getFlaresCfg().getInt("MiningFlares.tier4flare.RewardsGUI.Slot")) {
					event.setCancelled(true);
					if(configs.getFlaresCfg().getDouble("MiningFlares.tier4flare.Chance") > 0) {
						utils.openInv(p, invmethods.getTier4RewardsMenu());
					}
					else {
						p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Events.FlareDisabled").replace("%FlareName%", "Tier4")));
					}
				}
				if(slot == configs.getFlaresCfg().getInt("MiningFlares.tier5flare.RewardsGUI.Slot")) {
					event.setCancelled(true);
					if(configs.getFlaresCfg().getDouble("MiningFlares.tier5flare.Chance") > 0) {
						utils.openInv(p, invmethods.getTier5RewardsMenu());
					}
					else {
						p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Flares.Events.FlareDisabled").replace("%FlareName%", "Tier5")));
					}
				}
				if(slot != configs.getFlaresCfg().getInt("MiningFlares.tier1flare.RewardsGUI.Slot")) {
					event.setCancelled(true);
				}
				if(slot != configs.getFlaresCfg().getInt("MiningFlares.tier2flare.RewardsGUI.Slot")) {
					event.setCancelled(true);
				}
				if(slot != configs.getFlaresCfg().getInt("MiningFlares.tier3flare.RewardsGUI.Slot")) {
					event.setCancelled(true);
				}
				if(slot != configs.getFlaresCfg().getInt("MiningFlares.tier4flare.RewardsGUI.Slot")) {
					event.setCancelled(true);
				}
				if(slot != configs.getFlaresCfg().getInt("MiningFlares.tier5flare.RewardsGUI.Slot")) {
					event.setCancelled(true);
				}
			}
		}
		if(inventory.equals(invmethods.getTier1RewardsMenu()) || inventory.equals(invmethods.getTier2RewardsMenu()) || inventory.equals(invmethods.getTier3RewardsMenu()) || inventory.equals(invmethods.getTier4RewardsMenu()) || inventory.equals(invmethods.getTier5RewardsMenu())) {
			if(clicked != null) {
				event.setCancelled(true);
			}
		}
	}
}
