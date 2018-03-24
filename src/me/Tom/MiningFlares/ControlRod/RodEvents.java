package me.Tom.MiningFlares.ControlRod;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;

import me.Tom.MiningFlares.ConfigManager;
import me.Tom.MiningFlares.PluginCore;
import me.Tom.MiningFlares.Utils;
import me.Tom.MiningFlares.Crates.CrateMethods;

@SuppressWarnings("deprecation")
public class RodEvents implements Listener {
	
	private PluginCore core;
	private ConfigManager configs;
	private Utils<?> utils;
	private RodMethods rodmethods;
	private CrateMethods cratemethods;
	private List<UUID> check;
	private Location loc;
	public RodEvents(PluginCore pl) {
		core = pl;
		configs = core.getConfigs();
		utils = core.getUtils();
		rodmethods = core.getRodMethods();
		cratemethods = core.getCrateMethods();
		check = new ArrayList<UUID>();
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		UUID id = p.getUniqueId();
		
		if(check.contains(id)) {
			check.remove(id);
		}
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity().getPlayer();
		UUID id = p.getUniqueId();
		
		if(rodmethods.getRod().contains(id)) {
			rodmethods.getNPCList().remove(id);
			rodmethods.getCrateList().remove(id);
			rodmethods.getCreateList().remove(id);
			rodmethods.getDeleteList().remove(id);
			rodmethods.getRod().remove(id);
			check.remove(id);
			Iterator<ItemStack> iterator = e.getDrops().iterator();
			while(iterator.hasNext()) {
				ItemStack i = iterator.next();
				if(i != null && i.getType() != Material.AIR && i.getType() == Material.STICK && i.hasItemMeta() && i.getItemMeta().hasDisplayName() && i.getItemMeta().hasLore()) {
					ItemMeta meta = i.getItemMeta();
					if(meta.getDisplayName().equalsIgnoreCase(utils.format("&6&lMiningFlares Rod &7[&aCreate&7]")) && meta.getLore().toString().contains(utils.format("&7* Shift to change between NPC and Crate mode!, &7* Right click to switch between create and delete mode!, &7* Left click on a block to create/delete a NPC/Crate!"))) {
						iterator.remove();
					}
					if(meta.getDisplayName().equalsIgnoreCase(utils.format("&6&lMiningFlares Rod &7[&cDelete&7]")) && meta.getLore().toString().contains(utils.format("&7* Shift to change between NPC and Crate mode!, &7* Right click to switch between create and delete mode!, &7* Left click on a block to create/delete a NPC/Crate!"))) {
						iterator.remove();
					}
				}
			}
		}
	}
	
	@EventHandler
	public void gamemodeChange(PlayerGameModeChangeEvent e) {
		if(rodmethods.getRod().contains(e.getPlayer().getUniqueId())) {
			if(e.getNewGameMode() == GameMode.CREATIVE) {
				e.setCancelled(true);
				e.getPlayer().sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Rod.Events.CreativeMode")));
			}
		}
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		if(rodmethods.getRod().contains(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Rod.Events.DropItem")));
		}
	}
	
	@EventHandler
	public void invClick(InventoryClickEvent e) {
		if(rodmethods.getRod().contains(e.getWhoClicked().getUniqueId())) {
			if(e.getClickedInventory() != null) {
				e.setCancelled(true);
				e.getWhoClicked().sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Rod.Events.InventoryClick")));
			}
		}
	}
	
	@EventHandler
	public void rodClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		UUID id = p.getUniqueId();
		
		if(rodmethods.getRod().contains(id)) {
			ItemStack hand = p.getItemInHand();
			ItemMeta meta = hand.getItemMeta();
			if(hand.getType().equals(Material.STICK) && hand.hasItemMeta() && meta.hasDisplayName() && meta.getDisplayName().contains(utils.format("&6&lMiningFlares Rod")) && meta.hasLore() && meta.getLore().toString().contains(utils.format("&7* Shift to change between NPC and Crate mode!, &7* Right click to switch between create and delete mode!, &7* Left click on a block to create/delete a NPC/Crate!"))) {
				if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
					if(rodmethods.getCreateList().contains(id)) {
						rodmethods.getCreateList().remove(id);
						rodmethods.getDeleteList().add(id);
						meta.setDisplayName(utils.format("&6&lMiningFlares Rod &7[&cDelete&7]"));
						p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Rod.Events.DeleteEnable")));
					}
					else {
						rodmethods.getDeleteList().remove(id);
						rodmethods.getCreateList().add(id);
						meta.setDisplayName(utils.format("&6&lMiningFlares Rod &7[&aCreate&7]"));
						p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Rod.Events.CreateEnable")));
					}
					hand.setItemMeta(meta);
				}
				else if(e.getAction() == Action.LEFT_CLICK_BLOCK) {
					Location blockloc = e.getClickedBlock().getLocation();
					if(rodmethods.getCreateList().contains(id)) {
						if(rodmethods.getNPCList().contains(id)) {
							e.setCancelled(true);
							if(configs.getFlaresCfg().getBoolean("Redeeming.NPC")) {
								if(!check.contains(id)) {
									check.add(id);
									loc = blockloc;
									p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Rod.NPC.CreationSelection")));
								}
								else {
									p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Rod.NPC.CreationAlreadyBegun")));
								}
							}
							else {
								p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Rod.NPC.ConfigDisabled")));
							}
						}
						if(rodmethods.getCrateList().contains(id)) {
							e.setCancelled(true);
							if(configs.getFlaresCfg().getBoolean("Redeeming.Crates")) {
								if(blockloc.equals(cratemethods.getTier1Loc()) || blockloc.equals(cratemethods.getTier2Loc()) || blockloc.equals(cratemethods.getTier3Loc()) || blockloc.equals(cratemethods.getTier4Loc()) || blockloc.equals(cratemethods.getTier5Loc())) {
									p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Rod.Crate.AlreadyCrate")));
								}
								else {
									if(!check.contains(id)) {
										check.add(id);
										loc = blockloc;
										p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Rod.Crate.CreationSelection")));
									}
									else {
										p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Rod.Crate.CreationAlreadyBegun")));
									}
								}
							}
							else {
								p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Rod.Crate.ConfigDisabled")));
							}
						}
					}
					if(rodmethods.getDeleteList().contains(id)) {
						if(rodmethods.getCrateList().contains(id)) {
							if(configs.getFlaresCfg().getBoolean("Redeeming.Crates")) {
								if(blockloc.equals(cratemethods.getTier1Loc()) || blockloc.equals(cratemethods.getTier2Loc()) || blockloc.equals(cratemethods.getTier3Loc()) || blockloc.equals(cratemethods.getTier4Loc()) || blockloc.equals(cratemethods.getTier5Loc())) {
									for (Hologram hologram : HologramsAPI.getHolograms(core)) {
										Location hologramLoc = hologram.getLocation();
										if(hologramLoc.distance(blockloc) <= 2.5) {
											if(configs.getCratesCfg().contains("Crates.Tier1.Loc")) {
												if(hologramLoc.equals(cratemethods.getTier1Loc().clone().add(0.5, 2.05, 0.5))) {
													hologram.delete();
													configs.getCratesCfg().set("Crates.Tier1.Loc", null);
													cratemethods.getTier1Loc().zero();
													p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Events.CrateBroken").replace("%CrateName%", configs.getCratesCfg().getString("Crates.Tier1.Name"))));
												}
											}
											if(configs.getCratesCfg().contains("Crates.Tier2.Loc")) {
												if(hologramLoc.equals(cratemethods.getTier2Loc().clone().add(0.5, 2.05, 0.5))) {
													hologram.delete();
													configs.getCratesCfg().set("Crates.Tier2.Loc", null);
													cratemethods.getTier2Loc().zero();
													p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Events.CrateBroken").replace("%CrateName%", configs.getCratesCfg().getString("Crates.Tier2.Name"))));
												}
										    }
											if(configs.getCratesCfg().contains("Crates.Tier3.Loc")) {
												if(hologramLoc.equals(cratemethods.getTier3Loc().clone().add(0.5, 2.05, 0.5))) {
													hologram.delete();
													configs.getCratesCfg().set("Crates.Tier3.Loc", null);
													cratemethods.getTier3Loc().zero();
													p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Events.CrateBroken").replace("%CrateName%", configs.getCratesCfg().getString("Crates.Tier3.Name"))));
												}
										    }
											if(configs.getCratesCfg().contains("Crates.Tier4.Loc")) {
												if(hologramLoc.equals(cratemethods.getTier4Loc().clone().add(0.5, 2.05, 0.5))) {
													hologram.delete();
													configs.getCratesCfg().set("Crates.Tier4.Loc", null);
													cratemethods.getTier4Loc().zero();
													p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Events.CrateBroken").replace("%CrateName%", configs.getCratesCfg().getString("Crates.Tier4.Name"))));
												}
										    }
											if(configs.getCratesCfg().contains("Crates.Tier5.Loc")) {
												if(hologramLoc.equals(cratemethods.getTier5Loc().clone().add(0.5, 2.05, 0.5))) {
													hologram.delete();
													configs.getCratesCfg().set("Crates.Tier5.Loc", null);
													cratemethods.getTier5Loc().zero();
													p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Events.CrateBroken").replace("%CrateName%", configs.getCratesCfg().getString("Crates.Tier5.Name"))));
												}
										    }
											configs.saveCratesCfg();
											configs.reloadCratesCfg();
											cratemethods.removeHolograms();
										    cratemethods.loadCrateHolograms();
										}
									}
								}
								else {
									e.setCancelled(true);
									p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Rod.Crate.NotACrate")));
								}
							}
							else {
								p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Rod.Crate.ConfigDisabled")));
							}
						}
					}
				}
			}
		}
	}
	
	
	@EventHandler
	public void onShift(PlayerToggleSneakEvent e) {
		Player p = e.getPlayer();
		UUID id = p.getUniqueId();
		ItemStack hand = p.getItemInHand();
		ItemMeta meta = hand.getItemMeta();
		
		if(rodmethods.getRod().contains(id)) {
			if(!p.isSneaking()) {
				if(hand.getType().equals(Material.STICK) && hand.hasItemMeta() && meta.hasDisplayName() && meta.getDisplayName().contains(utils.format("&6&lMiningFlares Rod")) && meta.hasLore() && meta.getLore().toString().contains(utils.format("&7* Shift to change between NPC and Crate mode!, &7* Right click to switch between create and delete mode!, &7* Left click on a block to create/delete a NPC/Crate!"))) {
					List<String> lore = meta.getLore();
					if(rodmethods.getNPCList().contains(id)) {
						if(configs.getFlaresCfg().getBoolean("Redeeming.Crates")) {
							rodmethods.getNPCList().remove(id);
							rodmethods.getCrateList().add(id);
							lore.set(4, utils.format("&7NPC: &cDisabled"));
							lore.set(5, utils.format("&7Crate: &aEnabled"));
							p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Rod.Crate.Enable")));
						}
						else {
							p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Rod.Crate.ConfigDisabled")));
						}
					}
					else {
						if(configs.getFlaresCfg().getBoolean("Redeeming.NPC")) {
							rodmethods.getCrateList().remove(id);
							rodmethods.getNPCList().add(id);
							lore.set(4, utils.format("&7NPC: &aEnabled"));
							lore.set(5, utils.format("&7Crate: &cDisabled"));
							p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Rod.NPC.Enable")));
						}
						else {
							p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Rod.NPC.ConfigDisabled")));
						}
					}
					meta.setLore(lore);
					hand.setItemMeta(meta);
				}
			}
		}
	}
	
	@EventHandler
	public void onCrateChat(PlayerChatEvent e) {
		Player p = e.getPlayer();
		UUID id = p.getUniqueId();
		
		if(configs.getFlaresCfg().getBoolean("Redeeming.Crates")) {
			if(check.contains(id)) {
				e.setCancelled(true);
				if(rodmethods.getCrateList().contains(id)) {
					if(e.getMessage().equalsIgnoreCase("Tier1")) {
						if(configs.getCratesCfg().getString("Crates.Tier1.Loc") == null) {
							configs.getCratesCfg().set("Crates.Tier1.Loc", String.valueOf(loc.getWorld().getName()) + ", " + loc.getX() + ", " + loc.getY() + ", " + loc.getZ());
							final Hologram holo = HologramsAPI.createHologram(core, cratemethods.getCratesCfgHoloLocation("Crates.Tier1.Loc"));
							for (final String sr : configs.getCratesCfg().getStringList("Holograms.Text")) {
					            holo.appendTextLine(utils.format(sr).replace("%CrateName%", utils.format(configs.getCratesCfg().getString("Crates.Tier1.Name"))));
					            holo.setAllowPlaceholders(true);
					        }
							cratemethods.setTier1Loc();
							p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Events.CratePlaced").replace("%CrateName%", configs.getCratesCfg().getString("Crates.Tier1.Name"))));
						}
						else {
							p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Events.AlreadyPlaced").replace("%CrateName%", configs.getCratesCfg().getString("Crates.Tier1.Name"))));
						}
						configs.saveCratesCfg();
						configs.reloadCratesCfg();
						check.remove(id);
					}
					else if(e.getMessage().equalsIgnoreCase("Tier2")) {
						if(configs.getCratesCfg().getString("Crates.Tier2.Loc") == null) {
							configs.getCratesCfg().set("Crates.Tier2.Loc", String.valueOf(loc.getWorld().getName()) + ", " + loc.getX() + ", " + loc.getY() + ", " + loc.getZ());					
							final Hologram holo = HologramsAPI.createHologram(core, cratemethods.getCratesCfgHoloLocation("Crates.Tier2.Loc"));
							for (final String sr : configs.getCratesCfg().getStringList("Holograms.Text")) {
								holo.appendTextLine(utils.format(sr).replace("%CrateName%", utils.format(configs.getCratesCfg().getString("Crates.Tier2.Name"))));
								holo.setAllowPlaceholders(true);
							}
							cratemethods.setTier2Loc();
							p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Events.CratePlaced").replace("%CrateName%", configs.getCratesCfg().getString("Crates.Tier2.Name"))));
						}
						else {
							p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Events.AlreadyPlaced").replace("%CrateName%", configs.getCratesCfg().getString("Crates.Tier2.Name"))));
						}
						configs.saveCratesCfg();
						configs.reloadCratesCfg();
						check.remove(id);
					}
					else if(e.getMessage().equalsIgnoreCase("Tier3")) {
						if(configs.getCratesCfg().getString("Crates.Tier3.Loc") == null) {
							configs.getCratesCfg().set("Crates.Tier3.Loc", String.valueOf(loc.getWorld().getName()) + ", " + loc.getX() + ", " + loc.getY() + ", " + loc.getZ());					
							final Hologram holo = HologramsAPI.createHologram(core, cratemethods.getCratesCfgHoloLocation("Crates.Tier3.Loc"));
							for (final String sr : configs.getCratesCfg().getStringList("Holograms.Text")) {
								holo.appendTextLine(utils.format(sr).replace("%CrateName%", utils.format(configs.getCratesCfg().getString("Crates.Tier3.Name"))));
								holo.setAllowPlaceholders(true);
							}
							cratemethods.setTier3Loc();
							p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Events.CratePlaced").replace("%CrateName%", configs.getCratesCfg().getString("Crates.Tier3.Name"))));
						}
						else {
							p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Events.AlreadyPlaced").replace("%CrateName%", configs.getCratesCfg().getString("Crates.Tier3.Name"))));
						}
						configs.saveCratesCfg();
						configs.reloadCratesCfg();
						check.remove(id);
					}
					else if(e.getMessage().equalsIgnoreCase("Tier4")) {
						if(configs.getCratesCfg().getString("Crates.Tier4.Loc") == null) {
							configs.getCratesCfg().set("Crates.Tier4.Loc", String.valueOf(loc.getWorld().getName()) + ", " + loc.getX() + ", " + loc.getY() + ", " + loc.getZ());					
							final Hologram holo = HologramsAPI.createHologram(core, cratemethods.getCratesCfgHoloLocation("Crates.Tier4.Loc"));
							for (final String sr : configs.getCratesCfg().getStringList("Holograms.Text")) {
								holo.appendTextLine(utils.format(sr).replace("%CrateName%", utils.format(configs.getCratesCfg().getString("Crates.Tier4.Name"))));
								holo.setAllowPlaceholders(true);
							}
							cratemethods.setTier4Loc();
							p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Events.CratePlaced").replace("%CrateName%", configs.getCratesCfg().getString("Crates.Tier4.Name"))));
						}
						else {
							p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Events.AlreadyPlaced").replace("%CrateName%", configs.getCratesCfg().getString("Crates.Tier4.Name"))));
						}
						configs.saveCratesCfg();
						configs.reloadCratesCfg();
						check.remove(id);
					}
					else if(e.getMessage().equalsIgnoreCase("Tier5")) {
						if(configs.getCratesCfg().getString("Crates.Tier5.Loc") == null) {
							configs.getCratesCfg().set("Crates.Tier5.Loc", String.valueOf(loc.getWorld().getName()) + ", " + loc.getX() + ", " + loc.getY() + ", " + loc.getZ());					
							final Hologram holo = HologramsAPI.createHologram(core, cratemethods.getCratesCfgHoloLocation("Crates.Tier5.Loc"));
							for (final String sr : configs.getCratesCfg().getStringList("Holograms.Text")) {
								holo.appendTextLine(utils.format(sr).replace("%CrateName%", utils.format(configs.getCratesCfg().getString("Crates.Tier5.Name"))));
								holo.setAllowPlaceholders(true);
							}
							cratemethods.setTier5Loc();
							p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Events.CratePlaced").replace("%CrateName%", configs.getCratesCfg().getString("Crates.Tier5.Name"))));
						}
						else {
							p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Crates.Events.AlreadyPlaced").replace("%CrateName%", configs.getCratesCfg().getString("Crates.Tier5.Name"))));
						}
						configs.saveCratesCfg();
						configs.reloadCratesCfg();
						check.remove(id);
					}
					else if(e.getMessage().equalsIgnoreCase("cancel")) {
						p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Rod.Crate.CreationCancelled")));
						check.remove(id);
					}
					else {
						p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Rod.Crate.InvalidCrate").replace("%Message%", e.getMessage())));
					}
				}
			}
		}
	}
}
