package me.Tom.MiningFlares.Flares;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import me.Tom.MiningFlares.PluginCore;

public class ClickFlare implements Listener {
	
	private PluginCore core;
	private FlareMethods flaremethods;
	private RewardMethods rewardmethods;
	public ClickFlare(PluginCore pl) {
		core = pl;
		flaremethods = core.getFlareMethods();
		rewardmethods = core.getRewardMethods();
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onRedeem(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		PlayerInventory inv = p.getInventory();
		ItemStack item = p.getItemInHand();
		
		if(item.isSimilar(flaremethods.Tier1())) {
			if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				event.setCancelled(true);
				inv.removeItem(flaremethods.Tier1());
				p.getInventory().addItem(rewardmethods.getTier1Rewards().get(new Random()));
				p.updateInventory();
			}
		}
		if(item.isSimilar(flaremethods.Tier2())) {
			if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				event.setCancelled(true);
				inv.removeItem(flaremethods.Tier2());
				p.getInventory().addItem(rewardmethods.getTier2Rewards().get(new Random()));
				p.updateInventory();
			}
		}
		if(item.isSimilar(flaremethods.Tier3())) {
			if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				event.setCancelled(true);
				inv.removeItem(flaremethods.Tier3());
				p.getInventory().addItem(rewardmethods.getTier3Rewards().get(new Random()));
				p.updateInventory();
			}
		}
		if(item.isSimilar(flaremethods.Tier4())) {
			if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				inv.removeItem(flaremethods.Tier4());
				p.getInventory().addItem(rewardmethods.getTier4Rewards().get(new Random()));
				p.updateInventory();
			}
		}
		if(item.isSimilar(flaremethods.Tier5())) {
			if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				event.setCancelled(true);
				inv.removeItem(flaremethods.Tier5());
				p.getInventory().addItem(rewardmethods.getTier5Rewards().get(new Random()));
				p.updateInventory();
			}
		}
	}
}

