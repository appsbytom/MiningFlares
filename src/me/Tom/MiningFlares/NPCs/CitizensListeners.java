package me.Tom.MiningFlares.NPCs;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.Tom.MiningFlares.ConfigManager;
import me.Tom.MiningFlares.InventoryMethods;
import me.Tom.MiningFlares.PluginCore;
import me.Tom.MiningFlares.Utils;
import me.Tom.MiningFlares.WinningsMethods;
import me.Tom.MiningFlares.Flares.FlareMethods;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.NPCLeftClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;

public class CitizensListeners implements Listener {
	
	private PluginCore core;
    private ConfigManager configs;
    private Utils<?> utils;
    private FlareMethods flaremethods;
    private InventoryMethods invmethods;
    private NPCMethods npcmethods;
    private WinningsMethods winningsmethods;
    private Map<String, Map<Integer, Integer>> cooldownTimer;
	private Map<String, Map<Integer, BukkitRunnable>> cooldownTask;
	public CitizensListeners(PluginCore pl) {
		core = pl;
		configs = core.getConfigs();
		utils = core.getUtils();
		flaremethods = core.getFlareMethods();
		invmethods = core.getInvMethods();
		npcmethods = core.getNPCMethods();
		winningsmethods = core.getWinningsMethods();
		cooldownTimer = new HashMap<String, Map<Integer, Integer>>();
		cooldownTask = new HashMap<String, Map<Integer, BukkitRunnable>>();
	}
    
	@SuppressWarnings("deprecation")
	@EventHandler
	public void deleteNPC(NPCLeftClickEvent e) {
		Player p = e.getClicker().getPlayer();
		NPC npc = e.getNPC();
		int id = npc.getId();
		NPCRegistry registry = CitizensAPI.getNPCRegistry();
		ItemStack item = p.getItemInHand();
		
		if(registry.getById(id) != null && configs.getNPCCfg().contains("NPCS." + id)) {
			if(item.getType() == Material.STICK && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equalsIgnoreCase(utils.format("&6&lMiningFlares Rod &7[&cDelete&7]")) && item.getItemMeta().hasLore() && item.getItemMeta().getLore().toString().contains(utils.format("&7* Shift to change between NPC and Crate mode!, &7* Right click to switch between create and delete mode!, &7* Left click on a block to create/delete a NPC/Crate!"))) {
				registry.deregister(npc);
				npc.destroy();
				npcmethods.getHolos().get(id).delete();
				npcmethods.getHolos().remove(id);
				configs.getNPCCfg().set("NPCS." + id, null);
		        if(configs.getNPCCfg().getConfigurationSection("NPCS").getKeys(false).isEmpty()) {
		        	configs.getNPCCfg().set("NPCS", null);
		        }
		        configs.saveNPCCfg();
		        configs.reloadNPCCfg();
		        p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Rod.NPC.Delete").replace("%NPCID%", String.valueOf(id))));
			}
		}
	}
	
    @SuppressWarnings("deprecation")
	@EventHandler
    public void npcClick(NPCLeftClickEvent e) {
    	final Player p = e.getClicker().getPlayer();
		final NPC npc = e.getNPC();
		final int id = npc.getId();
		final ItemStack item = p.getItemInHand();
		final Inventory inv = p.getInventory();
		
		if(CitizensAPI.getNPCRegistry().getById(id) != null) {
			if(configs.getNPCCfg().contains("NPCS." + id)) {
				if(!cooldownTask.containsKey(p.getName()) || (cooldownTask.containsKey(p.getName()) && !cooldownTask.get(p.getName()).containsKey(id))) {
					if(npc.getName().equals("Merchant")) {
						if(item.isSimilar(flaremethods.Tier1())) {
							p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("NPC.Events.OpeningFlare").replace("%FlareName%", flaremethods.Tier1().getItemMeta().getDisplayName())));
				  			inv.removeItem(flaremethods.Tier1());
				  			p.openInventory(invmethods.getTier1WinningsMenu());
				  			winningsmethods.startGenericPicker("tier1", invmethods.getTier1WinningsMenu(), p);
						}
						else if(item.isSimilar(flaremethods.Tier2())) {
							p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("NPC.Events.OpeningFlare").replace("%FlareName%", flaremethods.Tier2().getItemMeta().getDisplayName())));
			  				inv.removeItem(flaremethods.Tier2());
			  				p.openInventory(invmethods.getTier2WinningsMenu());
			  				winningsmethods.startGenericPicker("tier2", invmethods.getTier2WinningsMenu(), p);
						}
						else if(item.isSimilar(flaremethods.Tier3())) {
							p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("NPC.Events.OpeningFlare").replace("%FlareName%", flaremethods.Tier3().getItemMeta().getDisplayName())));
			  				inv.removeItem(flaremethods.Tier3());
			  				p.openInventory(invmethods.getTier3WinningsMenu());
			  				winningsmethods.startGenericPicker("tier3", invmethods.getTier3WinningsMenu(), p);
						}
						else if(item.isSimilar(flaremethods.Tier4())) {
							p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("NPC.Events.OpeningFlare").replace("%FlareName%", flaremethods.Tier4().getItemMeta().getDisplayName())));
			  				inv.removeItem(flaremethods.Tier4());
			  				p.openInventory(invmethods.getTier4WinningsMenu());
			  				winningsmethods.startGenericPicker("tier4", invmethods.getTier4WinningsMenu(), p);
						}
						else if(item.isSimilar(flaremethods.Tier5())) {
							p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("NPC.Events.OpeningFlare").replace("%FlareName%", flaremethods.Tier5().getItemMeta().getDisplayName())));
			  				inv.removeItem(flaremethods.Tier5());
			  				p.openInventory(invmethods.getTier5WinningsMenu());
			  				winningsmethods.startGenericPicker("tier5", invmethods.getTier5WinningsMenu(), p);
						}
						else if(item.getType() == Material.STICK && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().contains(utils.format("&6&lMiningFlares Rod")) && item.getItemMeta().hasLore() && item.getItemMeta().getLore().toString().contains(utils.format("&7* Shift to change between NPC and Crate mode!, &7* Right click to switch between create and delete mode!, &7* Left click on a block to create/delete a NPC/Crate!"))) {
							
						}
						else {
							p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("NPC.Events.EmptyHand")));
						}
					}
					if(npc.getName().equals("Rewards")) {
						if(item.getType() == Material.STICK && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().contains(utils.format("&6&lMiningFlares Rod")) && item.getItemMeta().hasLore() && item.getItemMeta().getLore().toString().contains(utils.format("&7* Shift to change between NPC and Crate mode!, &7* Right click to switch between create and delete mode!, &7* Left click on a block to create/delete a NPC/Crate!"))) {
							
						}
						else {
							p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Rewards.MenuOpening")));
							utils.openInv(p, invmethods.getRewardsMenu());
						}
					}
					if(!npc.getName().equals("Merchant") || !npc.getName().equals("Rewards")) {
						return;
					}
					if(configs.getMsgCfg().getInt("CooldownTime") != -1) {
						Map<Integer, Integer> time = new HashMap<Integer, Integer>();
						if(cooldownTimer.containsKey(p.getName())) {
							time = cooldownTimer.get(p.getName());
						}
						time.put(id, configs.getMsgCfg().getInt("CooldownTime") + 1);
						cooldownTimer.put(p.getName(), time);
						Map<Integer, BukkitRunnable> runnable = new HashMap<Integer, BukkitRunnable>();
						if(cooldownTask.containsKey(p.getName())) {
							runnable = cooldownTask.get(p.getName());
						}
						runnable.put(id, new BukkitRunnable() {
							Map<Integer, Integer> timer = cooldownTimer.get(p.getName());
							Integer i = timer.get(id);
                        
							public void run() {
								--i;
								timer.put(id, i);
								cooldownTimer.put(p.getName(), timer);
								if(p.isOnline()) {
									if(cooldownTimer.get(p.getName()).get(id) == 0) {
										final Map<Integer, Integer> timerRemoval = cooldownTimer.get(p.getName());
										timerRemoval.remove(id);
										cooldownTimer.put(p.getName(), timerRemoval);
										final Map<Integer, BukkitRunnable> runnableRemoval = cooldownTask.get(p.getName());
										runnableRemoval.remove(id);
										cooldownTask.put(p.getName(), runnableRemoval);
										cancel();
									}
								}
								else {
									cancel();
								}
							}
						});
						cooldownTask.put(p.getName(), runnable);
						cooldownTask.get(p.getName()).get(id).runTaskTimer(core, 0L, 20L);
					}
				}
				else {
					p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("NPC.Events.CooldownMessage").replace("%Time%", new StringBuilder().append(cooldownTimer.get(p.getName()).get(id)).toString()).replace("%NPCName%", configs.getNPCCfg().getString("NPCS." + id + ".Name"))));
				}
            }
        }
    }
    
    @SuppressWarnings("deprecation")
	@EventHandler
    public void npcClick(NPCRightClickEvent e) {
    	final Player p = e.getClicker().getPlayer();
		final NPC npc = e.getNPC();
		final int id = npc.getId();
		final ItemStack item = p.getItemInHand();
		final Inventory inv = p.getInventory();
		
		if(CitizensAPI.getNPCRegistry().getById(id) != null) {
			if(configs.getNPCCfg().contains("NPCS." + id)) {
				if(!cooldownTask.containsKey(p.getName()) || (cooldownTask.containsKey(p.getName()) && !cooldownTask.get(p.getName()).containsKey(id))) {
					if(npc.getName().equals("Merchant")) {
						if(item.isSimilar(flaremethods.Tier1())) {
							p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("NPC.Events.OpeningFlare").replace("%FlareName%", flaremethods.Tier1().getItemMeta().getDisplayName())));
				  			inv.removeItem(flaremethods.Tier1());
				  			p.openInventory(invmethods.getTier1WinningsMenu());
				  			winningsmethods.startGenericPicker("tier1", invmethods.getTier1WinningsMenu(), p);
						}
						else if(item.isSimilar(flaremethods.Tier2())) {
							p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("NPC.Events.OpeningFlare").replace("%FlareName%", flaremethods.Tier2().getItemMeta().getDisplayName())));
			  				inv.removeItem(flaremethods.Tier2());
			  				p.openInventory(invmethods.getTier2WinningsMenu());
			  				winningsmethods.startGenericPicker("tier2", invmethods.getTier2WinningsMenu(), p);
						}
						else if(item.isSimilar(flaremethods.Tier3())) {
							p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("NPC.Events.OpeningFlare").replace("%FlareName%", flaremethods.Tier3().getItemMeta().getDisplayName())));
			  				inv.removeItem(flaremethods.Tier3());
			  				p.openInventory(invmethods.getTier3WinningsMenu());
			  				winningsmethods.startGenericPicker("tier3", invmethods.getTier3WinningsMenu(), p);
						}
						else if(item.isSimilar(flaremethods.Tier4())) {
							p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("NPC.Events.OpeningFlare").replace("%FlareName%", flaremethods.Tier4().getItemMeta().getDisplayName())));
			  				inv.removeItem(flaremethods.Tier4());
			  				p.openInventory(invmethods.getTier4WinningsMenu());
			  				winningsmethods.startGenericPicker("tier4", invmethods.getTier4WinningsMenu(), p);
						}
						else if(item.isSimilar(flaremethods.Tier5())) {
							p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("NPC.Events.OpeningFlare").replace("%FlareName%", flaremethods.Tier5().getItemMeta().getDisplayName())));
			  				inv.removeItem(flaremethods.Tier5());
			  				p.openInventory(invmethods.getTier5WinningsMenu());
			  				winningsmethods.startGenericPicker("tier5", invmethods.getTier5WinningsMenu(), p);
						}
						else if(item.getType() == Material.STICK && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().contains(utils.format("&6&lMiningFlares Rod")) && item.getItemMeta().hasLore() && item.getItemMeta().getLore().toString().contains(utils.format("&7* Shift to change between NPC and Crate mode!, &7* Right click to switch between create and delete mode!, &7* Left click on a block to create/delete a NPC/Crate!"))) {
							
						}
						else {
							p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("NPC.Events.EmptyHand")));
						}
					}
					if(npc.getName().equals("Rewards")) {
						if(item.getType() == Material.STICK && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().contains(utils.format("&6&lMiningFlares Rod")) && item.getItemMeta().hasLore() && item.getItemMeta().getLore().toString().contains(utils.format("&7* Shift to change between NPC and Crate mode!, &7* Right click to switch between create and delete mode!, &7* Left click on a block to create/delete a NPC/Crate!"))) {
							
						}
						else {
							p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Rewards.MenuOpening")));
							utils.openInv(p, invmethods.getRewardsMenu());
						}
					}
					if(!npc.getName().equals("Merchant") || !npc.getName().equals("Rewards")) {
						return;
					}
					if(configs.getMsgCfg().getInt("CooldownTime") != -1) {
						Map<Integer, Integer> time = new HashMap<Integer, Integer>();
						if(cooldownTimer.containsKey(p.getName())) {
							time = cooldownTimer.get(p.getName());
						}
						time.put(id, configs.getMsgCfg().getInt("CooldownTime") + 1);
						cooldownTimer.put(p.getName(), time);
						Map<Integer, BukkitRunnable> runnable = new HashMap<Integer, BukkitRunnable>();
						if(cooldownTask.containsKey(p.getName())) {
							runnable = cooldownTask.get(p.getName());
						}
						runnable.put(id, new BukkitRunnable() {
							Map<Integer, Integer> timer = cooldownTimer.get(p.getName());
							Integer i = timer.get(id);
                        
							public void run() {
								--i;
								timer.put(id, i);
								cooldownTimer.put(p.getName(), timer);
								if(p.isOnline()) {
									if(cooldownTimer.get(p.getName()).get(id) == 0) {
										final Map<Integer, Integer> timerRemoval = cooldownTimer.get(p.getName());
										timerRemoval.remove(id);
										cooldownTimer.put(p.getName(), timerRemoval);
										final Map<Integer, BukkitRunnable> runnableRemoval = cooldownTask.get(p.getName());
										runnableRemoval.remove(id);
										cooldownTask.put(p.getName(), runnableRemoval);
										cancel();
									}
								}
								else {
									cancel();
								}
							}
						});
						cooldownTask.put(p.getName(), runnable);
						cooldownTask.get(p.getName()).get(id).runTaskTimer(core, 0L, 20L);
					}
				}
				else {
					p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("NPC.Events.CooldownMessage").replace("%Time%", new StringBuilder().append(cooldownTimer.get(p.getName()).get(id)).toString()).replace("%NPCName%", configs.getNPCCfg().getString("NPCS." + id + ".Name"))));
				}
            }
        }
    }
    
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        
        if(cooldownTask.containsKey(p.getName())) {
            cooldownTask.remove(p.getName());
        }
        if(cooldownTimer.containsKey(p.getName())) {
            cooldownTimer.remove(p.getName());
        }
    }

}
