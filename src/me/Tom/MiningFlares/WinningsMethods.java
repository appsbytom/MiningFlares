package me.Tom.MiningFlares;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.Tom.MiningFlares.Flares.RewardMethods;

//All methods linked with NPC/Crate interaction

public class WinningsMethods {

	private PluginCore core;
	private ConfigManager configs;
	private Utils<?> utils;
	private RewardMethods rewardmethods;
	public WinningsMethods(PluginCore pl) {
		core = pl;
		configs = core.getConfigs();
		utils = core.getUtils();
		rewardmethods = core.getRewardMethods();
	}
	
	public void startGenericPicker(final String tier, final Inventory inv, final Player p) {
		startGenericAnimation(tier, 0L, inv, p);
		startGenericAnimation(tier, 5L, inv, p);
		startGenericAnimation(tier, 10L, inv, p);
		startGenericAnimation(tier, 15L, inv, p);
		startGenericAnimation(tier, 20L, inv, p);
		startGenericAnimation(tier, 25L, inv, p);
		startGenericAnimation(tier, 30L, inv, p);
		startGenericAnimation(tier, 35L, inv, p);
		startGenericAnimation(tier, 40L, inv, p);
		startGenericAnimation(tier, 45L, inv, p);
		startGenericAnimation(tier, 50L, inv, p);
		startGenericAnimation(tier, 55L, inv, p);
		startGenericAnimation(tier, 60L, inv, p);
		startGenericAnimation(tier, 65L, inv, p);
		startGenericAnimation(tier, 70L, inv, p);
		startGenericAnimation(tier, 75L, inv, p);
		selectGenericPrize(tier, p, inv);
		closeInv(p);
	}
	
	public void startGenericAnimation(final String tier, final long delay, final Inventory inv, final Player p) {
		new BukkitRunnable() {
			public void run() {
				
				for(int x = 0; x < inv.getSize(); x++) {
					inv.setItem(x, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) ((short) new Random().nextInt(15 - 0) + 0)));
				}
				if(tier.equalsIgnoreCase("tier1")) {
					inv.setItem(13, rewardmethods.getTier1Rewards().get(new Random()));
				}
				else if(tier.equalsIgnoreCase("tier2")) {
					inv.setItem(13, rewardmethods.getTier2Rewards().get(new Random()));
				}
				else if(tier.equalsIgnoreCase("tier3")) {
					inv.setItem(13, rewardmethods.getTier3Rewards().get(new Random()));
				}
				else if(tier.equalsIgnoreCase("tier4")) {
					inv.setItem(13, rewardmethods.getTier4Rewards().get(new Random()));
				}
				else if(tier.equalsIgnoreCase("tier5")) {
					inv.setItem(13, rewardmethods.getTier5Rewards().get(new Random()));
				}
				else {
					inv.setItem(13, new ItemStack(Material.BARRIER));
					p.sendMessage(utils.addPrefix() + utils.format("&cAn error has occured trying to gather rewards"));
				}
			}
		}.runTaskLater(core, delay);
	}
	
	public void selectGenericPrize(final String tier, final Player p, final Inventory inv) {
		new BukkitRunnable() {
			
			public void run() {
				ItemStack win = new ItemStack(Material.BARRIER);
				if(tier.equalsIgnoreCase("tier1")) {
					win = rewardmethods.getTier1Rewards().get(new Random());
				}
				else if(tier.equalsIgnoreCase("tier2")) {
					win = rewardmethods.getTier2Rewards().get(new Random());
				}
				else if(tier.equalsIgnoreCase("tier3")) {
					win = rewardmethods.getTier3Rewards().get(new Random());
				}
				else if(tier.equalsIgnoreCase("tier4")) {
					win = rewardmethods.getTier4Rewards().get(new Random());
				}
				else if(tier.equalsIgnoreCase("tier5")) {
					win = rewardmethods.getTier5Rewards().get(new Random());
				}
				inv.setItem(13, win);
				if(win.getType() != Material.BARRIER) {
					p.getInventory().addItem(win);
					p.updateInventory();
					if(win.hasItemMeta() && win.getItemMeta().hasDisplayName()) {
						p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Animations.PrizeGiven").replace("%PrizeName%", win.getItemMeta().getDisplayName()).replace("%Amount%", String.valueOf(win.getAmount()))));
						if(!configs.getMsgCfg().getString("Crates.Animations.BroadcastPrize").isEmpty()) {
							Bukkit.broadcastMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Animations.BroadcastPrize").replace("%PlayerName%", p.getDisplayName()).replace("%PrizeName%", win.getItemMeta().getDisplayName()).replace("%Amount%", String.valueOf(win.getAmount()))));
						}
					}
					else {
						p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Animations.PrizeGiven").replace("%PrizeName%", win.getType().toString()).replace("%Amount%", String.valueOf(win.getAmount()))));
						if(!configs.getMsgCfg().getString("Crates.Animations.BroadcastPrize").isEmpty()) {
							Bukkit.broadcastMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Animations.BroadcastPrize").replace("%PlayerName%", p.getDisplayName()).replace("%PrizeName%", win.getType().toString()).replace("%Amount%", String.valueOf(win.getAmount()))));
						}
					}
				}
				else {
					p.sendMessage(utils.addPrefix() + utils.format("&cAn error has occured when selecting a prize"));
				}
			}
		}.runTaskLater(core, 80L);
	}
	
	public void closeInv(final Player p) {
		new BukkitRunnable() {

			@Override
			public void run() {
				p.closeInventory();
			}
		}.runTaskLater(core, 90L);
	}
	
	public void startRoulettePicker(final String tier, final Inventory inv, final Player p) {
		startRouletteAnimation(tier, 0L, inv, p);
		startRouletteAnimation(tier, 5L, inv, p);
		startRouletteAnimation(tier, 10L, inv, p);
		startRouletteAnimation(tier, 15L, inv, p);
		startRouletteAnimation(tier, 20L, inv, p);
		startRouletteAnimation(tier, 25L, inv, p);
		startRouletteAnimation(tier, 30L, inv, p);
		startRouletteAnimation(tier, 35L, inv, p);
		startRouletteAnimation(tier, 40L, inv, p);
		startRouletteAnimation(tier, 45L, inv, p);
		startRouletteAnimation(tier, 50L, inv, p);
		startRouletteAnimation(tier, 55L, inv, p);
		startRouletteAnimation(tier, 60L, inv, p);
		startRouletteAnimation(tier, 65L, inv, p);
		startRouletteAnimation(tier, 70L, inv, p);
		startRouletteAnimation(tier, 75L, inv, p);
		selectRoulettePrize(tier, p, inv);
		closeInv(p);
	}
	
	public void startRouletteAnimation(final String tier, final long delay, final Inventory inv, final Player p) {
		new BukkitRunnable() {
			public void run() {
				
				for(int x = 0; x < inv.getSize(); x++) {
					inv.setItem(x, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) ((short) new Random().nextInt(15 - 0) + 0)));
				}
				
				inv.setItem(4, new ItemStack(Material.REDSTONE_TORCH_ON));
				if(tier.equalsIgnoreCase("tier1")) {
					inv.setItem(11, rewardmethods.getTier1Rewards().get(new Random()));
					inv.setItem(12, rewardmethods.getTier1Rewards().get(new Random()));
					inv.setItem(13, rewardmethods.getTier1Rewards().get(new Random()));
					inv.setItem(14, rewardmethods.getTier1Rewards().get(new Random()));
					inv.setItem(15, rewardmethods.getTier1Rewards().get(new Random()));
				}
				else if(tier.equalsIgnoreCase("tier2")) {
					inv.setItem(11, rewardmethods.getTier2Rewards().get(new Random()));
					inv.setItem(12, rewardmethods.getTier2Rewards().get(new Random()));
					inv.setItem(13, rewardmethods.getTier2Rewards().get(new Random()));
					inv.setItem(14, rewardmethods.getTier2Rewards().get(new Random()));
					inv.setItem(15, rewardmethods.getTier2Rewards().get(new Random()));
				}
				else if(tier.equalsIgnoreCase("tier3")) {
					inv.setItem(11, rewardmethods.getTier3Rewards().get(new Random()));
					inv.setItem(12, rewardmethods.getTier3Rewards().get(new Random()));
					inv.setItem(13, rewardmethods.getTier3Rewards().get(new Random()));
					inv.setItem(14, rewardmethods.getTier3Rewards().get(new Random()));
					inv.setItem(15, rewardmethods.getTier3Rewards().get(new Random()));
				}
				else if(tier.equalsIgnoreCase("tier4")) {
					inv.setItem(11, rewardmethods.getTier4Rewards().get(new Random()));
					inv.setItem(12, rewardmethods.getTier4Rewards().get(new Random()));
					inv.setItem(13, rewardmethods.getTier4Rewards().get(new Random()));
					inv.setItem(14, rewardmethods.getTier4Rewards().get(new Random()));
					inv.setItem(15, rewardmethods.getTier4Rewards().get(new Random()));
				}
				else if(tier.equalsIgnoreCase("tier5")) {
					inv.setItem(11, rewardmethods.getTier5Rewards().get(new Random()));
					inv.setItem(12, rewardmethods.getTier5Rewards().get(new Random()));
					inv.setItem(13, rewardmethods.getTier5Rewards().get(new Random()));
					inv.setItem(14, rewardmethods.getTier5Rewards().get(new Random()));
					inv.setItem(15, rewardmethods.getTier5Rewards().get(new Random()));
				}
				else {
					inv.setItem(11, new ItemStack(Material.BARRIER));
					inv.setItem(12, new ItemStack(Material.BARRIER));
					inv.setItem(13, new ItemStack(Material.BARRIER));
					inv.setItem(14, new ItemStack(Material.BARRIER));
					inv.setItem(15, new ItemStack(Material.BARRIER));
					p.sendMessage(utils.addPrefix() + utils.format("&cAn error has occured trying to gather rewards"));
				}
			}
		}.runTaskLater(core, delay);
	}
	
	public void selectRoulettePrize(final String tier, final Player p, final Inventory inv) {
		new BukkitRunnable() {
			
			public void run() {
				ItemStack win = new ItemStack(Material.BARRIER);
				if(tier.equalsIgnoreCase("tier1")) {
					win = rewardmethods.getTier1Rewards().get(new Random());
				}
				else if(tier.equalsIgnoreCase("tier2")) {
					win = rewardmethods.getTier2Rewards().get(new Random());
				}
				else if(tier.equalsIgnoreCase("tier3")) {
					win = rewardmethods.getTier3Rewards().get(new Random());
				}
				else if(tier.equalsIgnoreCase("tier4")) {
					win = rewardmethods.getTier4Rewards().get(new Random());
				}
				else if(tier.equalsIgnoreCase("tier5")) {
					win = rewardmethods.getTier5Rewards().get(new Random());
				}
				inv.setItem(11, win);
				inv.setItem(12, win);
				inv.setItem(13, win);
				inv.setItem(14, win);
				inv.setItem(15, win);
				if(win.getType() != Material.BARRIER) {
					p.getInventory().addItem(win);
					p.updateInventory();
					if(win.hasItemMeta() && win.getItemMeta().hasDisplayName()) {
						p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Animations.PrizeGiven").replace("%PrizeName%", win.getItemMeta().getDisplayName()).replace("%Amount%", String.valueOf(win.getAmount()))));
						if(!configs.getMsgCfg().getString("Crates.Animations.BroadcastPrize").isEmpty()) {
							Bukkit.broadcastMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Animations.BroadcastPrize").replace("%PlayerName%", p.getDisplayName()).replace("%PrizeName%", win.getItemMeta().getDisplayName()).replace("%Amount%", String.valueOf(win.getAmount()))));
						}
					}
					else {
						p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Animations.PrizeGiven").replace("%PrizeName%", win.getType().toString()).replace("%Amount%", String.valueOf(win.getAmount()))));
						if(!configs.getMsgCfg().getString("Crates.Animations.BroadcastPrize").isEmpty()) {
							Bukkit.broadcastMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Animations.BroadcastPrize").replace("%PlayerName%", p.getDisplayName()).replace("%PrizeName%", win.getType().toString()).replace("%Amount%", String.valueOf(win.getAmount()))));
						}
					}
				}
				else {
					p.sendMessage(utils.addPrefix() + utils.format("&cAn error has occured when selecting a prize"));
				}
			}
		}.runTaskLater(core, 80L);
	}
}
