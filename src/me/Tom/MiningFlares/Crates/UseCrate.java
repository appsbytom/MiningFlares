package me.Tom.MiningFlares.Crates;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import me.Tom.MiningFlares.ConfigManager;
import me.Tom.MiningFlares.InventoryMethods;
import me.Tom.MiningFlares.PluginCore;
import me.Tom.MiningFlares.Utils;
import me.Tom.MiningFlares.WinningsMethods;
import me.Tom.MiningFlares.Flares.FlareMethods;
import me.Tom.MiningFlares.Flares.RewardMethods;

public class UseCrate implements Listener {

	private PluginCore core;
	private ConfigManager configs;
	private Utils<?> utils;
	private FlareMethods flaremethods;
	private RewardMethods rewardmethods;
	private InventoryMethods invmethods;
	private CrateMethods cratemethods;
	private WinningsMethods winningsmethods;
	public UseCrate(PluginCore pl) {
		core = pl;
		configs = core.getConfigs();
		utils = core.getUtils();
		flaremethods = core.getFlareMethods();
		rewardmethods = core.getRewardMethods();
		invmethods = core.getInvMethods();
		cratemethods = core.getCrateMethods();
		winningsmethods = core.getWinningsMethods();
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onRedeem(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		PlayerInventory inv = p.getInventory();
		Block block = e.getClickedBlock();
		ItemStack hand = p.getItemInHand();
		
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Location blockloc = block.getLocation();
			if(blockloc.equals(cratemethods.getTier1Loc())) {
				e.setCancelled(true);
				if(hand.isSimilar(flaremethods.Tier1())) {
					inv.removeItem(flaremethods.Tier1());
					if(configs.getCratesCfg().getBoolean("Crates.Opening.Generic")) {
						p.openInventory(invmethods.getTier1WinningsMenu());
						winningsmethods.startGenericPicker("tier1", invmethods.getTier1WinningsMenu(), p);
					}
					if(configs.getCratesCfg().getBoolean("Crates.Opening.Roulette")) {
						p.openInventory(invmethods.getTier1WinningsMenu());
						winningsmethods.startRoulettePicker("tier1", invmethods.getTier1WinningsMenu(), p);
					}
					if(configs.getCratesCfg().getBoolean("Crates.Opening.Quick")) {
						p.getInventory().addItem(rewardmethods.getTier1Rewards().get(new Random()));
						p.updateInventory();
					}
				}
				else {
					if(hand.getType().equals(Material.STICK) && hand.hasItemMeta() && hand.getItemMeta().hasDisplayName() && hand.getItemMeta().getDisplayName().contains(utils.format("&6&lMiningFlares Rod")) && hand.getItemMeta().hasLore() && hand.getItemMeta().getLore().toString().contains(utils.format("&7* Shift to change between NPC and Crate mode!, &7* Right click to switch between create and delete mode!, &7* Left click on a block to create/delete a NPC/Crate!"))) {
					}
					else {
						p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Events.WrongItem").replace("%FlareName%", flaremethods.Tier1().getItemMeta().getDisplayName())));
					}					
				}
			}
			else if(blockloc.equals(cratemethods.getTier2Loc())) {
				e.setCancelled(true);
				if(hand.isSimilar(flaremethods.Tier2())) {
					inv.removeItem(flaremethods.Tier2());
					if(configs.getCratesCfg().getBoolean("Crates.Opening.Generic")) {
						p.openInventory(invmethods.getTier2WinningsMenu());
						winningsmethods.startGenericPicker("tier2", invmethods.getTier2WinningsMenu(), p);
					}
					if(configs.getCratesCfg().getBoolean("Crates.Opening.Roulette")) {
						p.openInventory(invmethods.getTier2WinningsMenu());
						winningsmethods.startRoulettePicker("tier2", invmethods.getTier2WinningsMenu(), p);
					}
					if(configs.getCratesCfg().getBoolean("Crates.Opening.Quick")) {
						p.getInventory().addItem(rewardmethods.getTier2Rewards().get(new Random()));
						p.updateInventory();
					}
				}
				else {
					if(hand.getType().equals(Material.STICK) && hand.hasItemMeta() && hand.getItemMeta().hasDisplayName() && hand.getItemMeta().getDisplayName().contains(utils.format("&6&lMiningFlares Rod")) && hand.getItemMeta().hasLore() && hand.getItemMeta().getLore().toString().contains(utils.format("&7* Shift to change between NPC and Crate mode!, &7* Right click to switch between create and delete mode!, &7* Left click on a block to create/delete a NPC/Crate!"))) {
					}
					else {
						p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Events.WrongItem").replace("%FlareName%", flaremethods.Tier2().getItemMeta().getDisplayName())));
					}					
				}
			}
			else if(blockloc.equals(cratemethods.getTier3Loc())) {
				e.setCancelled(true);
				if(hand.isSimilar(flaremethods.Tier3())) {
					inv.removeItem(flaremethods.Tier3());
					if(configs.getCratesCfg().getBoolean("Crates.Opening.Generic")) {
						p.openInventory(invmethods.getTier3WinningsMenu());
						winningsmethods.startGenericPicker("tier3", invmethods.getTier3WinningsMenu(), p);
					}
					if(configs.getCratesCfg().getBoolean("Crates.Opening.Roulette")) {
						p.openInventory(invmethods.getTier3WinningsMenu());
						winningsmethods.startRoulettePicker("tier3", invmethods.getTier3WinningsMenu(), p);
					}
					if(configs.getCratesCfg().getBoolean("Crates.Opening.Quick")) {
						p.getInventory().addItem(rewardmethods.getTier3Rewards().get(new Random()));
						p.updateInventory();
					}
				}
				else {
					if(hand.getType().equals(Material.STICK) && hand.hasItemMeta() && hand.getItemMeta().hasDisplayName() && hand.getItemMeta().getDisplayName().contains(utils.format("&6&lMiningFlares Rod")) && hand.getItemMeta().hasLore() && hand.getItemMeta().getLore().toString().contains(utils.format("&7* Shift to change between NPC and Crate mode!, &7* Right click to switch between create and delete mode!, &7* Left click on a block to create/delete a NPC/Crate!"))) {
					}
					else {
						p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Events.WrongItem").replace("%FlareName%", flaremethods.Tier3().getItemMeta().getDisplayName())));
					}					
				}
			}
			else if(blockloc.equals(cratemethods.getTier4Loc())) {
				e.setCancelled(true);
				if(hand.isSimilar(flaremethods.Tier4())) {
					inv.removeItem(flaremethods.Tier4());
					if(configs.getCratesCfg().getBoolean("Crates.Opening.Generic")) {
						p.openInventory(invmethods.getTier4WinningsMenu());
						winningsmethods.startGenericPicker("tier4", invmethods.getTier4WinningsMenu(), p);
					}
					if(configs.getCratesCfg().getBoolean("Crates.Opening.Roulette")) {
						p.openInventory(invmethods.getTier4WinningsMenu());
						winningsmethods.startRoulettePicker("tier4", invmethods.getTier4WinningsMenu(), p);
					}
					if(configs.getCratesCfg().getBoolean("Crates.Opening.Quick")) {
						p.getInventory().addItem(rewardmethods.getTier4Rewards().get(new Random()));
						p.updateInventory();
					}
				}
				else {
					if(hand.getType().equals(Material.STICK) && hand.hasItemMeta() && hand.getItemMeta().hasDisplayName() && hand.getItemMeta().getDisplayName().contains(utils.format("&6&lMiningFlares Rod")) && hand.getItemMeta().hasLore() && hand.getItemMeta().getLore().toString().contains(utils.format("&7* Shift to change between NPC and Crate mode!, &7* Right click to switch between create and delete mode!, &7* Left click on a block to create/delete a NPC/Crate!"))) {
					}
					else {
						p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Events.WrongItem").replace("%FlareName%", flaremethods.Tier4().getItemMeta().getDisplayName())));
					}					
				}
			}
			else if(blockloc.equals(cratemethods.getTier5Loc())) {
				e.setCancelled(true);
				if(hand.isSimilar(flaremethods.Tier5())) {
					inv.removeItem(flaremethods.Tier5());
					if(configs.getCratesCfg().getBoolean("Crates.Opening.Generic")) {
						p.openInventory(invmethods.getTier5WinningsMenu());
						winningsmethods.startGenericPicker("tier5", invmethods.getTier5WinningsMenu(), p);
					}
					if(configs.getCratesCfg().getBoolean("Crates.Opening.Roulette")) {
						p.openInventory(invmethods.getTier5WinningsMenu());
						winningsmethods.startRoulettePicker("tier5", invmethods.getTier5WinningsMenu(), p);
					}
					if(configs.getCratesCfg().getBoolean("Crates.Opening.Quick")) {
						p.getInventory().addItem(rewardmethods.getTier5Rewards().get(new Random()));
						p.updateInventory();
					}
				}
				else {
					if(hand.getType().equals(Material.STICK) && hand.hasItemMeta() && hand.getItemMeta().hasDisplayName() && hand.getItemMeta().getDisplayName().contains(utils.format("&6&lMiningFlares Rod")) && hand.getItemMeta().hasLore() && hand.getItemMeta().getLore().toString().contains(utils.format("&7* Shift to change between NPC and Crate mode!, &7* Right click to switch between create and delete mode!, &7* Left click on a block to create/delete a NPC/Crate!"))) {
					}
					else {
						p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Events.WrongItem").replace("%FlareName%", flaremethods.Tier5().getItemMeta().getDisplayName())));
					}					
				}
			}
			else {
				if(hand.isSimilar(flaremethods.Tier1()) || hand.isSimilar(flaremethods.Tier2()) || hand.isSimilar(flaremethods.Tier3()) || hand.isSimilar(flaremethods.Tier4()) || hand.isSimilar(flaremethods.Tier5())) {
					e.setCancelled(true);
				}
			}
		}
		else if(e.getAction() == Action.LEFT_CLICK_BLOCK) {
			if(hand.getType().equals(Material.STICK) && hand.hasItemMeta() && hand.getItemMeta().hasDisplayName() && hand.getItemMeta().getDisplayName().contains(utils.format("&6&lMiningFlares Rod &7[&aCreate&7]")) && hand.getItemMeta().hasLore() && hand.getItemMeta().getLore().toString().contains(utils.format("&7* Shift to change between NPC and Crate mode!, &7* Right click to switch between create and delete mode!, &7* Left click on a block to create/delete a NPC/Crate!"))) {
				e.setCancelled(true);
			}
			else {
				if(block.getLocation().equals(cratemethods.getTier1Loc())) {
					e.setCancelled(true);
					utils.openInv(p, invmethods.getTier1RewardsMenu());
				}
				if(block.getLocation().equals(cratemethods.getTier2Loc())) {
					e.setCancelled(true);
					utils.openInv(p, invmethods.getTier2RewardsMenu());
				}
				if(block.getLocation().equals(cratemethods.getTier3Loc())) {
					e.setCancelled(true);
					utils.openInv(p, invmethods.getTier3RewardsMenu());
				}
				if(block.getLocation().equals(cratemethods.getTier4Loc())) {
					e.setCancelled(true);
					utils.openInv(p, invmethods.getTier4RewardsMenu());
				}
				if(block.getLocation().equals(cratemethods.getTier5Loc())) {
					e.setCancelled(true);
					utils.openInv(p, invmethods.getTier5RewardsMenu());
				}
			}
		}
	}
}
