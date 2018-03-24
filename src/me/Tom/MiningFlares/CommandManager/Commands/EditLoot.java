package me.Tom.MiningFlares.CommandManager.Commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.Tom.MiningFlares.ConfigManager;
import me.Tom.MiningFlares.LootMethods;
import me.Tom.MiningFlares.PluginCore;
import me.Tom.MiningFlares.Utils;
import me.Tom.MiningFlares.CommandManager.SubCommands;
import me.Tom.MiningFlares.Flares.FlareMethods;

public class EditLoot extends SubCommands {
	
	private PluginCore core;
	private ConfigManager configs;
	private Utils<?> utils;
	private FlareMethods flaremethods;
	private LootMethods lootmethods;
	public EditLoot(PluginCore pl) {
		core = pl;
		configs = core.getConfigs();
		utils = core.getUtils();
		flaremethods = core.getFlareMethods();
		lootmethods = core.getLootMethods();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onCommand(CommandSender sender, String[] args) {
		if(sender.hasPermission("mflare.editloot")) {
			if(args.length == 1) {
				sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.WrongFormat")));
			}
			else {
				if(args[1].equalsIgnoreCase("add")) {
					if(sender instanceof Player) {
						Player p = (Player) sender;
						if(args.length >= 4) {
							ItemStack hand = p.getItemInHand();
							if(args[2].equalsIgnoreCase("tier1")) {
								if(p.getItemInHand() != null) {
									if(utils.isDouble(args[3])) {
										double Chance = Double.parseDouble(args[3]);
										lootmethods.setTier1LootID(lootmethods.getTier1LootID() + 1);
										configs.getTier1Cfg().set("FlareRewards." + lootmethods.getTier1LootID() + ".Item", hand.getTypeId());
										configs.getTier1Cfg().set("FlareRewards." + lootmethods.getTier1LootID() + ".Data", hand.getDurability());
										if(hand.getItemMeta().hasDisplayName()) {
											configs.getTier1Cfg().set("FlareRewards." + lootmethods.getTier1LootID() + ".Name", hand.getItemMeta().getDisplayName().replaceAll("\u00a7", "&"));
										}
										else {
											configs.getTier1Cfg().set("FlareRewards." + lootmethods.getTier1LootID() + ".Name", "");
										}
										if(hand.getItemMeta().hasLore()) {
											configs.getTier1Cfg().set("FlareRewards." + lootmethods.getTier1LootID() + ".Lore", utils.handLore(p));
										}
										else {
											configs.getTier1Cfg().set("FlareRewards." + lootmethods.getTier1LootID() + ".Lore", null);
										}
										if(hand.getItemMeta().hasEnchants()) {
											Map<Enchantment, Integer> enchants = hand.getEnchantments();
											List<String> enchantlist = new ArrayList<String>();
											for (Enchantment e : enchants.keySet()) {
												int level = enchants.get(e);
												enchantlist.add(e.getName() + ";" + level);
												configs.getTier1Cfg().set("FlareRewards." + lootmethods.getTier1LootID() + ".Enchants", enchantlist);
											}
										}
										else {
											configs.getTier1Cfg().set("FlareRewards." + lootmethods.getTier1LootID() + ".Enchants", null);
										}
										configs.getTier1Cfg().set("FlareRewards." + lootmethods.getTier1LootID() + ".Chance", Chance);
										configs.saveTier1Cfg();
										configs.reloadMethod(core, p, "tier1", false);
										p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.ItemAdded").replace("%Tier%", flaremethods.Tier1().getItemMeta().getDisplayName())).replace("%lootID%", String.valueOf(lootmethods.getTier1LootID())));
									}
									else {
										p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
									}
								}
								else {
									p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.EmptyHand")));
								}
							}
							else if(args[2].equalsIgnoreCase("tier2")) {
								if(p.getItemInHand() != null) {
									if(utils.isDouble(args[3])) {
										double Chance = Double.parseDouble(args[3]);
										lootmethods.setTier2LootID(lootmethods.getTier2LootID() + 1);
										configs.getTier2Cfg().set("FlareRewards." + lootmethods.getTier2LootID() + ".Item", hand.getTypeId());
										configs.getTier2Cfg().set("FlareRewards." + lootmethods.getTier2LootID() + ".Data", hand.getDurability());
										if(hand.getItemMeta().hasDisplayName()) {
											configs.getTier2Cfg().set("FlareRewards." + lootmethods.getTier2LootID() + ".Name", hand.getItemMeta().getDisplayName().replaceAll("\u00a7", "&"));
										}
										else {
											configs.getTier2Cfg().set("FlareRewards." + lootmethods.getTier2LootID() + ".Name", "");
										}
										if(hand.getItemMeta().hasLore()) {
											configs.getTier2Cfg().set("FlareRewards." + lootmethods.getTier2LootID() + ".Lore", utils.handLore(p));
										}
										else {
											configs.getTier2Cfg().set("FlareRewards." + lootmethods.getTier2LootID() + ".Lore", null);
										}
										if(hand.getItemMeta().hasEnchants()) {
											Map<Enchantment, Integer> enchants = hand.getEnchantments();
											List<String> enchantlist = new ArrayList<String>();
											for (Enchantment e : enchants.keySet()) {
												int level = enchants.get(e);
												enchantlist.add(e.getName() + ";" + level);
												configs.getTier2Cfg().set("FlareRewards." + lootmethods.getTier2LootID() + ".Enchants", enchantlist);
											}
										}
										else {
											configs.getTier2Cfg().set("FlareRewards." + lootmethods.getTier2LootID() + ".Enchants", null);
										}
										configs.getTier2Cfg().set("FlareRewards." + lootmethods.getTier2LootID() + ".Chance", Chance);
										configs.saveTier2Cfg();
										configs.reloadMethod(core, p, "tier2", false);
										p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.ItemAdded").replace("%Tier%", flaremethods.Tier2().getItemMeta().getDisplayName())).replace("%lootID%", String.valueOf(lootmethods.getTier2LootID())));
									}
									else {
										p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
									}
								}
								else {
									p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.EmptyHand")));
								}
							}
							else if(args[2].equalsIgnoreCase("tier3")) {
								if(p.getItemInHand() != null) {
									if(utils.isDouble(args[3])) {
										double Chance = Double.parseDouble(args[3]);
										lootmethods.setTier3LootID(lootmethods.getTier3LootID() + 1);
										configs.getTier3Cfg().set("FlareRewards." + lootmethods.getTier3LootID() + ".Item", hand.getTypeId());
										configs.getTier3Cfg().set("FlareRewards." + lootmethods.getTier3LootID() + ".Data", hand.getDurability());
										if(hand.getItemMeta().hasDisplayName()) {
											configs.getTier3Cfg().set("FlareRewards." + lootmethods.getTier3LootID() + ".Name", hand.getItemMeta().getDisplayName().replaceAll("\u00a7", "&"));
										}
										else {
											configs.getTier3Cfg().set("FlareRewards." + lootmethods.getTier3LootID() + ".Name", "");
										}
										if(hand.getItemMeta().hasLore()) {
											configs.getTier3Cfg().set("FlareRewards." + lootmethods.getTier3LootID() + ".Lore", utils.handLore(p));
										}
										else {
											configs.getTier3Cfg().set("FlareRewards." + lootmethods.getTier3LootID() + ".Lore", null);
										}
										if(hand.getItemMeta().hasEnchants()) {
											Map<Enchantment, Integer> enchants = hand.getEnchantments();
											List<String> enchantlist = new ArrayList<String>();
											for (Enchantment e : enchants.keySet()) {
												int level = enchants.get(e);
												enchantlist.add(e.getName() + ";" + level);
												configs.getTier3Cfg().set("FlareRewards." + lootmethods.getTier3LootID() + ".Enchants", enchantlist);
											}
										}
										else {
											configs.getTier3Cfg().set("FlareRewards." + lootmethods.getTier3LootID() + ".Enchants", null);
										}
										configs.getTier3Cfg().set("FlareRewards." + lootmethods.getTier3LootID() + ".Chance", Chance);
										configs.saveTier3Cfg();
										configs.reloadMethod(core, p, "tier3", false);
										p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.ItemAdded").replace("%Tier%", flaremethods.Tier3().getItemMeta().getDisplayName())).replace("%lootID%", String.valueOf(lootmethods.getTier3LootID())));
									}
									else {
										p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
									}
								}
								else {
									p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.EmptyHand")));
								}
							}
							else if(args[2].equalsIgnoreCase("tier4")) {
								if(p.getItemInHand() != null) {
									if(utils.isDouble(args[3])) {
										double Chance = Double.parseDouble(args[3]);
										lootmethods.setTier4LootID(lootmethods.getTier4LootID() + 1);
										configs.getTier4Cfg().set("FlareRewards." + lootmethods.getTier4LootID() + ".Item", hand.getTypeId());
										configs.getTier4Cfg().set("FlareRewards." + lootmethods.getTier4LootID() + ".Data", hand.getDurability());
										if(hand.getItemMeta().hasDisplayName()) {
											configs.getTier4Cfg().set("FlareRewards." + lootmethods.getTier4LootID() + ".Name", hand.getItemMeta().getDisplayName().replaceAll("\u00a7", "&"));
										}
										else {
											configs.getTier4Cfg().set("FlareRewards." + lootmethods.getTier4LootID() + ".Name", "");
										}
										if(hand.getItemMeta().hasLore()) {
											configs.getTier4Cfg().set("FlareRewards." + lootmethods.getTier4LootID() + ".Lore", utils.handLore(p));
										}
										else {
											configs.getTier4Cfg().set("FlareRewards." + lootmethods.getTier4LootID() + ".Lore", null);
										}
										if(hand.getItemMeta().hasEnchants()) {
											Map<Enchantment, Integer> enchants = hand.getEnchantments();
											List<String> enchantlist = new ArrayList<String>();
											for (Enchantment e : enchants.keySet()) {
												int level = enchants.get(e);
												enchantlist.add(e.getName() + ";" + level);
												configs.getTier4Cfg().set("FlareRewards." + lootmethods.getTier4LootID() + ".Enchants", enchantlist);
											}
										}
										else {
											configs.getTier4Cfg().set("FlareRewards." + lootmethods.getTier4LootID() + ".Enchants", null);
										}
										configs.getTier4Cfg().set("FlareRewards." + lootmethods.getTier4LootID() + ".Chance", Chance);
										configs.saveTier4Cfg();
										configs.reloadMethod(core, p, "tier4", false);
										p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.ItemAdded").replace("%Tier%", flaremethods.Tier4().getItemMeta().getDisplayName())).replace("%lootID%", String.valueOf(lootmethods.getTier4LootID())));
									}
									else {
										p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
									}
								}
								else {
									p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.EmptyHand")));
								}
							}
							else if(args[2].equalsIgnoreCase("tier5")) {
								if(p.getItemInHand() != null) {
									if(utils.isDouble(args[3])) {
										double Chance = Double.parseDouble(args[3]);
										lootmethods.setTier1LootID(lootmethods.getTier5LootID() + 1);
										configs.getTier5Cfg().set("FlareRewards." + lootmethods.getTier5LootID() + ".Item", hand.getTypeId());
										configs.getTier5Cfg().set("FlareRewards." + lootmethods.getTier5LootID() + ".Data", hand.getDurability());
										if(hand.getItemMeta().hasDisplayName()) {
											configs.getTier5Cfg().set("FlareRewards." + lootmethods.getTier5LootID() + ".Name", hand.getItemMeta().getDisplayName().replaceAll("\u00a7", "&"));
										}
										else {
											configs.getTier5Cfg().set("FlareRewards." + lootmethods.getTier5LootID() + ".Name", "");
										}
										if(hand.getItemMeta().hasLore()) {
											configs.getTier5Cfg().set("FlareRewards." + lootmethods.getTier5LootID() + ".Lore", utils.handLore(p));
										}
										else {
											configs.getTier5Cfg().set("FlareRewards." + lootmethods.getTier5LootID() + ".Lore", null);
										}
										if(hand.getItemMeta().hasEnchants()) {
											Map<Enchantment, Integer> enchants = hand.getEnchantments();
											List<String> enchantlist = new ArrayList<String>();
											for (Enchantment e : enchants.keySet()) {
												int level = enchants.get(e);
												enchantlist.add(e.getName() + ";" + level);
												configs.getTier5Cfg().set("FlareRewards." + lootmethods.getTier5LootID() + ".Enchants", enchantlist);
											}
										}
										else {
											configs.getTier5Cfg().set("FlareRewards." + lootmethods.getTier5LootID() + ".Enchants", null);
										}
										configs.getTier5Cfg().set("FlareRewards." + lootmethods.getTier5LootID() + ".Chance", Chance);
										configs.saveTier5Cfg();
										configs.reloadMethod(core, p, "tier5", false);
										p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.ItemAdded").replace("%Tier%", flaremethods.Tier5().getItemMeta().getDisplayName())).replace("%lootID%", String.valueOf(lootmethods.getTier5LootID())));
									}
									else {
										p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
									}
								}
								else {
									p.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.EmptyHand")));
								}
							}
							else {
								sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.WrongFormat")));
							}
						}
						else {
							sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.WrongFormat")));
						}
					}
					else {
						sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.WrongUser")));
					}
				}
				else if(args[1].equalsIgnoreCase("delete")) {
					if(args.length >= 4) {
						if(args[2].equalsIgnoreCase("tier1")) {
							if(utils.isInt(args[3])) {
								int lootID = Integer.parseInt(args[3]);
								if(configs.getTier1Cfg().contains("FlareRewards." + lootID)) {
									configs.getTier1Cfg().set("FlareRewards." + lootID, null);
									configs.saveTier1Cfg();
									configs.reloadMethod(core, sender, "tier1", false);
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.LootDeleted").replace("%lootID%", String.valueOf(lootID)).replace("%Tier%", flaremethods.Tier1().getItemMeta().getDisplayName())));
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
								}
							}
							else {
								sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
							}
						}
						else if(args[2].equalsIgnoreCase("tier2")) {
							if(utils.isInt(args[3])) {
								int lootID = Integer.parseInt(args[3]);
								if(configs.getTier2Cfg().contains("FlareRewards." + lootID)) {
									configs.getTier2Cfg().set("FlareRewards." + lootID, null);
									configs.saveTier2Cfg();
									configs.reloadMethod(core, sender, "tier2", false);
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.LootDeleted").replace("%lootID%", String.valueOf(lootID)).replace("%Tier%", flaremethods.Tier2().getItemMeta().getDisplayName())));
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
								}
							}
							else {
								sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
							}
						}
						else if(args[2].equalsIgnoreCase("tier3")) {
							if(utils.isInt(args[3])) {
								int lootID = Integer.parseInt(args[3]);
								if(configs.getTier3Cfg().contains("FlareRewards." + lootID)) {
									configs.getTier3Cfg().set("FlareRewards." + lootID, null);
									configs.saveTier3Cfg();
									configs.reloadMethod(core, sender, "tier3", false);
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.LootDeleted").replace("%lootID%", String.valueOf(lootID)).replace("%Tier%", flaremethods.Tier3().getItemMeta().getDisplayName())));
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
								}
							}
							else {
								sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
							}
						}
						else if(args[2].equalsIgnoreCase("tier4")) {
							if(utils.isInt(args[3])) {
								int lootID = Integer.parseInt(args[3]);
								if(configs.getTier4Cfg().contains("FlareRewards." + lootID)) {
									configs.getTier4Cfg().set("FlareRewards." + lootID, null);
									configs.saveTier4Cfg();
									configs.reloadMethod(core, sender, "tier4", false);
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.LootDeleted").replace("%lootID%", String.valueOf(lootID)).replace("%Tier%", flaremethods.Tier4().getItemMeta().getDisplayName())));
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
								}
							}
							else {
								sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
							}
						}
						else if(args[2].equalsIgnoreCase("tier5")) {
							if(utils.isInt(args[3])) {
								int lootID = Integer.parseInt(args[3]);
								if(configs.getTier5Cfg().contains("FlareRewards." + lootID)) {
									configs.getTier5Cfg().set("FlareRewards." + lootID, null);
									configs.saveTier5Cfg();
									configs.reloadMethod(core, sender, "tier5", false);
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.LootDeleted").replace("%lootID%", String.valueOf(lootID)).replace("%Tier%", flaremethods.Tier5().getItemMeta().getDisplayName())));
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
								}
							}
							else {
								sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
							}
						}
						else {
							sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.WrongFormat")));
						}
					}
					else {
						sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.WrongFormat")));	
					}
				}
				else if(args[1].equalsIgnoreCase("info")) {
					if(args.length >= 4) {
						if(args[2].equalsIgnoreCase("tier1")) {
							if(utils.isInt(args[3])) {
								int lootID = Integer.parseInt(args[3]);
								if(configs.getTier1Cfg().contains("FlareRewards." + lootID)) {
									sender.sendMessage(utils.format(configs.getMsgCfg().getStringList("Loot.InfoLayout.Start").toString().replace(", ", "\n").replace("[", "").replace("]", "")));
									sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Tier").replace("%Tier%", flaremethods.Tier1().getItemMeta().getDisplayName())));
									sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.LootID").replace("%lootID%", String.valueOf(lootID))));
									sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.ItemID").replace("%ItemID%", String.valueOf(configs.getTier1Cfg().getInt("FlareRewards." + lootID + ".Item")))));
									sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.DataID").replace("%DataID%", String.valueOf(configs.getTier1Cfg().getInt("FlareRewards." + lootID + ".Data")))));
									if(!configs.getTier1Cfg().getString("FlareRewards." + lootID + ".Name").isEmpty()) {
										sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Name").replace("%ItemName%", configs.getTier1Cfg().getString("FlareRewards." + lootID + ".Name"))));
									}
									if(configs.getTier1Cfg().getString("FlareRewards." + lootID + ".Lore") != null) {
										sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Lore")));
										sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Lores").replace("%Lore%", configs.getTier1Cfg().getStringList("FlareRewards." + lootID + ".Lore").toString().replace("[", "").replace("]", "").replace(",", "\n  &e-&7"))));
									}
									if(configs.getTier1Cfg().getString("FlareRewards." + lootID + ".Enchants") != null) {
										sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Enchant")));
										sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Enchants").replace("%Enchants%", configs.getTier1Cfg().getStringList("FlareRewards." + lootID + ".Enchants").toString().replace("[", "").replace("]", "").replace(",", "\n  &e-&7"))));
									}
									sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Chance").replace("%Chance%", String.valueOf(configs.getTier1Cfg().getDouble("FlareRewards." + lootID + ".Chance")))));
									sender.sendMessage(utils.format(configs.getMsgCfg().getStringList("Loot.InfoLayout.End").toString().replace(", ", "\n").replace("[", "").replace("]", " ")));
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
								}
							}
							else {
								sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
							}
						}
						else if(args[2].equalsIgnoreCase("tier2")) {
							if(utils.isInt(args[3])) {
								int lootID = Integer.parseInt(args[3]);
								if(configs.getTier2Cfg().contains("FlareRewards." + lootID)) {
									sender.sendMessage(utils.format(configs.getMsgCfg().getStringList("Loot.InfoLayout.Start").toString().replace(", ", "\n").replace("[", "").replace("]", "")));
									sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Tier").replace("%Tier%", flaremethods.Tier2().getItemMeta().getDisplayName())));
									sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.LootID").replace("%lootID%", String.valueOf(lootID))));
									sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.ItemID").replace("%ItemID%", String.valueOf(configs.getTier2Cfg().getInt("FlareRewards." + lootID + ".Item")))));
									sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.DataID").replace("%DataID%", String.valueOf(configs.getTier2Cfg().getInt("FlareRewards." + lootID + ".Data")))));
									if(!configs.getTier1Cfg().getString("FlareRewards." + lootID + ".Name").isEmpty()) {
										sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Name").replace("%ItemName%", configs.getTier2Cfg().getString("FlareRewards." + lootID + ".Name"))));
									}
									if(configs.getTier1Cfg().getString("FlareRewards." + lootID + ".Lore") != null) {
										sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Lore")));
										sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Lores").replace("%Lore%", configs.getTier2Cfg().getStringList("FlareRewards." + lootID + ".Lore").toString().replace("[", "").replace("]", "").replace(",", "\n  &e-&7"))));
									}
									if(configs.getTier1Cfg().getString("FlareRewards." + lootID + ".Enchants") != null) {
										sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Enchant")));
										sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Enchants").replace("%Enchants%", configs.getTier2Cfg().getStringList("FlareRewards." + lootID + ".Enchants").toString().replace("[", "").replace("]", "").replace(",", "\n  &e-&7"))));
									}
									sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Chance").replace("%Chance%", String.valueOf(configs.getTier2Cfg().getDouble("FlareRewards." + lootID + ".Chance")))));
									sender.sendMessage(utils.format(configs.getMsgCfg().getStringList("Loot.InfoLayout.End").toString().replace(", ", "\n").replace("[", "").replace("]", " ")));
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
								}
							}
							else {
								sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
							}
						}
						else if(args[2].equalsIgnoreCase("tier3")) {
							if(utils.isInt(args[3])) {
								int lootID = Integer.parseInt(args[3]);
								if(configs.getTier3Cfg().contains("FlareRewards." + lootID)) {
									sender.sendMessage(utils.format(configs.getMsgCfg().getStringList("Loot.InfoLayout.Start").toString().replace(", ", "\n").replace("[", "").replace("]", "")));
									sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Tier").replace("%Tier%", flaremethods.Tier3().getItemMeta().getDisplayName())));
									sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.LootID").replace("%lootID%", String.valueOf(lootID))));
									sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.ItemID").replace("%ItemID%", String.valueOf(configs.getTier3Cfg().getInt("FlareRewards." + lootID + ".Item")))));
									sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.DataID").replace("%DataID%", String.valueOf(configs.getTier3Cfg().getInt("FlareRewards." + lootID + ".Data")))));
									if(!configs.getTier1Cfg().getString("FlareRewards." + lootID + ".Name").isEmpty()) {
										sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Name").replace("%ItemName%", configs.getTier3Cfg().getString("FlareRewards." + lootID + ".Name"))));
									}
									if(configs.getTier1Cfg().getString("FlareRewards." + lootID + ".Lore") != null) {
										sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Lore")));
										sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Lores").replace("%Lore%", configs.getTier3Cfg().getStringList("FlareRewards." + lootID + ".Lore").toString().replace("[", "").replace("]", "").replace(",", "\n  &e-&7"))));
									}
									if(configs.getTier1Cfg().getString("FlareRewards." + lootID + ".Enchants") != null) {
										sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Enchant")));
										sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Enchants").replace("%Enchants%", configs.getTier3Cfg().getStringList("FlareRewards." + lootID + ".Enchants").toString().replace("[", "").replace("]", "").replace(",", "\n  &e-&7"))));
									}
									sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Chance").replace("%Chance%", String.valueOf(configs.getTier3Cfg().getDouble("FlareRewards." + lootID + ".Chance")))));
									sender.sendMessage(utils.format(configs.getMsgCfg().getStringList("Loot.InfoLayout.End").toString().replace(", ", "\n").replace("[", "").replace("]", " ")));
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
								}
							}
							else {
								sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
							}
						}
						else if(args[2].equalsIgnoreCase("tier4")) {
							if(utils.isInt(args[3])) {
								int lootID = Integer.parseInt(args[3]);
								if(configs.getTier4Cfg().contains("FlareRewards." + lootID)) {
									sender.sendMessage(utils.format(configs.getMsgCfg().getStringList("Loot.InfoLayout.Start").toString().replace(", ", "\n").replace("[", "").replace("]", "")));
									sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Tier").replace("%Tier%", flaremethods.Tier4().getItemMeta().getDisplayName())));
									sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.LootID").replace("%lootID%", String.valueOf(lootID))));
									sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.ItemID").replace("%ItemID%", String.valueOf(configs.getTier4Cfg().getInt("FlareRewards." + lootID + ".Item")))));
									sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.DataID").replace("%DataID%", String.valueOf(configs.getTier4Cfg().getInt("FlareRewards." + lootID + ".Data")))));
									if(!configs.getTier1Cfg().getString("FlareRewards." + lootID + ".Name").isEmpty()) {
										sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Name").replace("%ItemName%", configs.getTier4Cfg().getString("FlareRewards." + lootID + ".Name"))));
									}
									if(configs.getTier1Cfg().getString("FlareRewards." + lootID + ".Lore") != null) {
										sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Lore")));
										sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Lores").replace("%Lore%", configs.getTier4Cfg().getStringList("FlareRewards." + lootID + ".Lore").toString().replace("[", "").replace("]", "").replace(",", "\n  &e-&7"))));
									}
									if(configs.getTier1Cfg().getString("FlareRewards." + lootID + ".Enchants") != null) {
										sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Enchant")));
										sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Enchants").replace("%Enchants%", configs.getTier4Cfg().getStringList("FlareRewards." + lootID + ".Enchants").toString().replace("[", "").replace("]", "").replace(",", "\n  &e-&7"))));
									}
									sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Chance").replace("%Chance%", String.valueOf(configs.getTier4Cfg().getDouble("FlareRewards." + lootID + ".Chance")))));
									sender.sendMessage(utils.format(configs.getMsgCfg().getStringList("Loot.InfoLayout.End").toString().replace(", ", "\n").replace("[", "").replace("]", " ")));
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
								}
							}
							else {
								sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
							}
						}
						else if(args[2].equalsIgnoreCase("tier5")) {
							if(utils.isInt(args[3])) {
								int lootID = Integer.parseInt(args[3]);
								if(configs.getTier5Cfg().contains("FlareRewards." + lootID)) {
									sender.sendMessage(utils.format(configs.getMsgCfg().getStringList("Loot.InfoLayout.Start").toString().replace(", ", "\n").replace("[", "").replace("]", "")));
									sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Tier").replace("%Tier%", flaremethods.Tier5().getItemMeta().getDisplayName())));
									sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.LootID").replace("%lootID%", String.valueOf(lootID))));
									sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.ItemID").replace("%ItemID%", String.valueOf(configs.getTier5Cfg().getInt("FlareRewards." + lootID + ".Item")))));
									sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.DataID").replace("%DataID%", String.valueOf(configs.getTier5Cfg().getInt("FlareRewards." + lootID + ".Data")))));
									if(!configs.getTier1Cfg().getString("FlareRewards." + lootID + ".Name").isEmpty()) {
										sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Name").replace("%ItemName%", configs.getTier5Cfg().getString("FlareRewards." + lootID + ".Name"))));
									}
									if(configs.getTier1Cfg().getString("FlareRewards." + lootID + ".Lore") != null) {
										sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Lore")));
										sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Lores").replace("%Lore%", configs.getTier5Cfg().getStringList("FlareRewards." + lootID + ".Lore").toString().replace("[", "").replace("]", "").replace(",", "\n  &e-&7"))));
									}
									if(configs.getTier1Cfg().getString("FlareRewards." + lootID + ".Enchants") != null) {
										sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Enchant")));
										sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Enchants").replace("%Enchants%", configs.getTier5Cfg().getStringList("FlareRewards." + lootID + ".Enchants").toString().replace("[", "").replace("]", "").replace(",", "\n  &e-&7"))));
									}
									sender.sendMessage(utils.format(configs.getMsgCfg().getString("Loot.InfoLayout.Chance").replace("%Chance%", String.valueOf(configs.getTier5Cfg().getDouble("FlareRewards." + lootID + ".Chance")))));
									sender.sendMessage(utils.format(configs.getMsgCfg().getStringList("Loot.InfoLayout.End").toString().replace(", ", "\n").replace("[", "").replace("]", " ")));
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
								}
							}
							else {
								sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
							}
						}
						else {
							sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.WrongFormat")));
						}
					}
					else {
						sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.WrongFormat")));	
					}
				}
				else if(args[1].equalsIgnoreCase("set")) {
					if(args.length >= 6) {
						if(args[2].equalsIgnoreCase("item")) {
							if(args[3].equalsIgnoreCase("tier1")) {
								if(utils.isInt(args[4])) {
									int lootID = Integer.parseInt(args[4]);
									if(utils.isInt(args[5])) {
										int newItemID = Integer.parseInt(args[5]);
										if(configs.getTier1Cfg().contains("FlareRewards." + lootID)) {
											configs.getTier1Cfg().set("FlareRewards." + lootID + ".Item", newItemID);
											configs.saveTier1Cfg();
											configs.reloadMethod(core, sender, "tier1", false);
											sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.SetItemID").replace("%lootID%", String.valueOf(lootID)).replace("%ItemID%", String.valueOf(newItemID)).replace("%Tier%", flaremethods.Tier1().getItemMeta().getDisplayName())));
										}
										else {
											sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
										}
									}
									else {
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
									}
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
								}
							}
							else if(args[3].equalsIgnoreCase("tier2")) {
								if(utils.isInt(args[4])) {
									int lootID = Integer.parseInt(args[4]);
									if(utils.isInt(args[5])) {
										int newItemID = Integer.parseInt(args[5]);
										if(configs.getTier2Cfg().contains("FlareRewards." + lootID)) {
											configs.getTier2Cfg().set("FlareRewards." + lootID + ".Item", newItemID);
											configs.saveTier2Cfg();
											configs.reloadMethod(core, sender, "tier2", false);
											sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.SetItemID").replace("%lootID%", String.valueOf(lootID)).replace("%ItemID%", String.valueOf(newItemID)).replace("%Tier%", flaremethods.Tier2().getItemMeta().getDisplayName())));
										}
										else {
											sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
										}
									}
									else {
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
									}
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
								}
							}
							else if(args[3].equalsIgnoreCase("tier3")) {
								if(utils.isInt(args[4])) {
									int lootID = Integer.parseInt(args[4]);
									if(utils.isInt(args[5])) {
										int newItemID = Integer.parseInt(args[5]);
										if(configs.getTier3Cfg().contains("FlareRewards." + lootID)) {
											configs.getTier3Cfg().set("FlareRewards." + lootID + ".Item", newItemID);
											configs.saveTier3Cfg();
											configs.reloadMethod(core, sender, "tier3", false);
											sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.SetItemID").replace("%lootID%", String.valueOf(lootID)).replace("%ItemID%", String.valueOf(newItemID)).replace("%Tier%", flaremethods.Tier3().getItemMeta().getDisplayName())));
										}
										else {
											sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
										}
									}
									else {
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
									}
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
								}
							}
							else if(args[3].equalsIgnoreCase("tier4")) {
								if(utils.isInt(args[4])) {
									int lootID = Integer.parseInt(args[4]);
									if(utils.isInt(args[5])) {
										int newItemID = Integer.parseInt(args[5]);
										if(configs.getTier4Cfg().contains("FlareRewards." + lootID)) {
											configs.getTier4Cfg().set("FlareRewards." + lootID + ".Item", newItemID);
											configs.saveTier4Cfg();
											configs.reloadMethod(core, sender, "tier4", false);
											sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.SetItemID").replace("%lootID%", String.valueOf(lootID)).replace("%ItemID%", String.valueOf(newItemID)).replace("%Tier%", flaremethods.Tier4().getItemMeta().getDisplayName())));
										}
										else {
											sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
										}
									}
									else {
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
									}
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
								}
							}
							else if(args[3].equalsIgnoreCase("tier5")) {
								if(utils.isInt(args[4])) {
									int lootID = Integer.parseInt(args[4]);
									if(utils.isInt(args[5])) {
										int newItemID = Integer.parseInt(args[5]);
										if(configs.getTier5Cfg().contains("FlareRewards." + lootID)) {
											configs.getTier5Cfg().set("FlareRewards." + lootID + ".Item", newItemID);
											configs.saveTier5Cfg();
											configs.reloadMethod(core, sender, "tier5", false);
											sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.SetItemID").replace("%lootID%", String.valueOf(lootID)).replace("%ItemID%", String.valueOf(newItemID)).replace("%Tier%", flaremethods.Tier5().getItemMeta().getDisplayName())));
										}
										else {
											sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
										}
									}
									else {
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
									}
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
								}
							}
							else {
								sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.WrongFormat")));
							}
						}
						else if(args[2].equalsIgnoreCase("data")) {
							if(args[3].equalsIgnoreCase("tier1")) {
								if(utils.isInt(args[4])) {
									int lootID = Integer.parseInt(args[4]);
									if(utils.isInt(args[5])) {
										int newDataID = Integer.parseInt(args[5]);
										if(configs.getTier1Cfg().contains("FlareRewards." + lootID)) {
											configs.getTier1Cfg().set("FlareRewards." + lootID + ".Data", newDataID);
											configs.saveTier1Cfg();
											configs.reloadMethod(core, sender, "tier1", false);
											sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.SetDataID").replace("%lootID%", String.valueOf(lootID)).replace("%DataID%", String.valueOf(newDataID)).replace("%Tier%", flaremethods.Tier1().getItemMeta().getDisplayName())));
										}
										else {
											sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
										}
									}
									else {
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
									}
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
								}
							}
							else if(args[3].equalsIgnoreCase("tier2")) {
								if(utils.isInt(args[4])) {
									int lootID = Integer.parseInt(args[4]);
									if(utils.isInt(args[5])) {
										int newDataID = Integer.parseInt(args[5]);
										if(configs.getTier2Cfg().contains("FlareRewards." + lootID)) {
											configs.getTier2Cfg().set("FlareRewards." + lootID + ".Data", newDataID);
											configs.saveTier2Cfg();
											configs.reloadMethod(core, sender, "tier2", false);
											sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.SetDataID").replace("%lootID%", String.valueOf(lootID)).replace("%DataID%", String.valueOf(newDataID)).replace("%Tier%", flaremethods.Tier2().getItemMeta().getDisplayName())));
										}
										else {
											sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
										}
									}
									else {
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
									}
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
								}
							}
							else if(args[3].equalsIgnoreCase("tier3")) {
								if(utils.isInt(args[4])) {
									int lootID = Integer.parseInt(args[4]);
									if(utils.isInt(args[5])) {
										int newDataID = Integer.parseInt(args[5]);
										if(configs.getTier3Cfg().contains("FlareRewards." + lootID)) {
											configs.getTier3Cfg().set("FlareRewards." + lootID + ".Data", newDataID);
											configs.saveTier3Cfg();
											configs.reloadMethod(core, sender, "tier3", false);
											sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.SetDataID").replace("%lootID%", String.valueOf(lootID)).replace("%DataID%", String.valueOf(newDataID)).replace("%Tier%", flaremethods.Tier3().getItemMeta().getDisplayName())));
										}
										else {
											sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
										}
									}
									else {
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
									}
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
								}
							}
							else if(args[3].equalsIgnoreCase("tier4")) {
								if(utils.isInt(args[4])) {
									int lootID = Integer.parseInt(args[4]);
									if(utils.isInt(args[5])) {
										int newDataID = Integer.parseInt(args[5]);
										if(configs.getTier4Cfg().contains("FlareRewards." + lootID)) {
											configs.getTier4Cfg().set("FlareRewards." + lootID + ".Data", newDataID);
											configs.saveTier4Cfg();
											configs.reloadMethod(core, sender, "tier4", false);
											sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.SetDataID").replace("%lootID%", String.valueOf(lootID)).replace("%DataID%", String.valueOf(newDataID)).replace("%Tier%", flaremethods.Tier4().getItemMeta().getDisplayName())));
										}
										else {
											sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
										}
									}
									else {
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
									}
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
								}
							}
							else if(args[3].equalsIgnoreCase("tier5")) {
								if(utils.isInt(args[4])) {
									int lootID = Integer.parseInt(args[4]);
									if(utils.isInt(args[5])) {
										int newDataID = Integer.parseInt(args[5]);
										if(configs.getTier5Cfg().contains("FlareRewards." + lootID)) {
											configs.getTier5Cfg().set("FlareRewards." + lootID + ".Data", newDataID);
											configs.saveTier5Cfg();
											configs.reloadMethod(core, sender, "tier5", false);
											sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.SetDataID").replace("%lootID%", String.valueOf(lootID)).replace("%DataID%", String.valueOf(newDataID)).replace("%Tier%", flaremethods.Tier5().getItemMeta().getDisplayName())));
										}
										else {
											sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
										}
									}
									else {
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
									}
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
								}
							}
							else {
								sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.WrongFormat")));
							}
						}
						else if(args[2].equalsIgnoreCase("name")) {
							if(args[3].equalsIgnoreCase("tier1")) {
								if(utils.isInt(args[4])) {
									int lootID = Integer.parseInt(args[4]);
									String newName = args[5];
									if(configs.getTier1Cfg().contains("FlareRewards." + lootID)) {
										if(newName.equals("null")) {
											configs.getTier1Cfg().set("FlareRewards." + lootID + ".Name", "");
										}
										else {
											configs.getTier1Cfg().set("FlareRewards." + lootID + ".Name", newName.replace("_", " "));
										}
										configs.saveTier1Cfg();
										configs.reloadMethod(core, sender, "tier1", false);
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.SetName").replace("%ItemName%", configs.getTier1Cfg().getString("FlareRewards." + lootID + ".Name")).replace("%lootID%", String.valueOf(lootID)).replace("%Tier%", flaremethods.Tier1().getItemMeta().getDisplayName())));
									}
									else {
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
									}
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
								}
							}
							else if(args[3].equalsIgnoreCase("tier2")) {
								if(utils.isInt(args[4])) {
									int lootID = Integer.parseInt(args[4]);
									String newName = args[5];
									if(configs.getTier2Cfg().contains("FlareRewards." + lootID)) {
										if(newName.equals("null")) {
											configs.getTier2Cfg().set("FlareRewards." + lootID + ".Name", "");
										}
										else {
											configs.getTier2Cfg().set("FlareRewards." + lootID + ".Name", newName.replace("_", " "));
										}
										configs.saveTier2Cfg();
										configs.reloadMethod(core, sender, "tier2", false);
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.SetName").replace("%ItemName%", configs.getTier2Cfg().getString("FlareRewards." + lootID + ".Name")).replace("%lootID%", String.valueOf(lootID)).replace("%Tier%", flaremethods.Tier2().getItemMeta().getDisplayName())));
									}
									else {
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
									}
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
								}
							}
							else if(args[3].equalsIgnoreCase("tier3")) {
								if(utils.isInt(args[4])) {
									int lootID = Integer.parseInt(args[4]);
									String newName = args[5];
									if(configs.getTier3Cfg().contains("FlareRewards." + lootID)) {
										if(newName.equals("null")) {
											configs.getTier3Cfg().set("FlareRewards." + lootID + ".Name", "");
										}
										else {
											configs.getTier3Cfg().set("FlareRewards." + lootID + ".Name", newName.replace("_", " "));
										}
										configs.saveTier3Cfg();
										configs.reloadMethod(core, sender, "tier3", false);
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.SetName").replace("%ItemName%", configs.getTier3Cfg().getString("FlareRewards." + lootID + ".Name")).replace("%lootID%", String.valueOf(lootID)).replace("%Tier%", flaremethods.Tier3().getItemMeta().getDisplayName())));
									}
									else {
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
									}
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
								}
							}
							else if(args[3].equalsIgnoreCase("tier4")) {
								if(utils.isInt(args[4])) {
									int lootID = Integer.parseInt(args[4]);
									String newName = args[5];
									if(configs.getTier4Cfg().contains("FlareRewards." + lootID)) {
										if(newName.equals("null")) {
											configs.getTier4Cfg().set("FlareRewards." + lootID + ".Name", "");
										}
										else {
											configs.getTier4Cfg().set("FlareRewards." + lootID + ".Name", newName.replace("_", " "));
										}
										configs.saveTier4Cfg();
										configs.reloadMethod(core, sender, "tier4", false);
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.SetName").replace("%ItemName%", configs.getTier4Cfg().getString("FlareRewards." + lootID + ".Name")).replace("%lootID%", String.valueOf(lootID)).replace("%Tier%", flaremethods.Tier4().getItemMeta().getDisplayName())));
									}
									else {
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
									}
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
								}
							}
							else if(args[3].equalsIgnoreCase("tier5")) {
								if(utils.isInt(args[4])) {
									int lootID = Integer.parseInt(args[4]);
									String newName = args[5];
									if(configs.getTier5Cfg().contains("FlareRewards." + lootID)) {
										if(newName.equals("null")) {
											configs.getTier5Cfg().set("FlareRewards." + lootID + ".Name", "");
										}
										else {
											configs.getTier5Cfg().set("FlareRewards." + lootID + ".Name", newName.replace("_", " "));
										}
										configs.saveTier5Cfg();
										configs.reloadMethod(core, sender, "tier5", false);
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.SetName").replace("%ItemName%", configs.getTier5Cfg().getString("FlareRewards." + lootID + ".Name")).replace("%lootID%", String.valueOf(lootID)).replace("%Tier%", flaremethods.Tier5().getItemMeta().getDisplayName())));
									}
									else {
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
									}
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
								}
							}
							else {
								sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.WrongFormat")));
							}
						}
						else if(args[2].equalsIgnoreCase("lore")) {
							if(args[3].equalsIgnoreCase("tier1")) {
								if(utils.isInt(args[4])) {
									int lootID = Integer.parseInt(args[4]);
									String[] lore = args[5].replace("_", " ").split(",");
									if(configs.getTier1Cfg().contains("FlareRewards." + lootID)) {
										if(lore[0].equals("null")) {
											configs.getTier1Cfg().set("FlareRewards." + lootID + ".Lore", null);
										}
										else {
											configs.getTier1Cfg().set("FlareRewards." + lootID + ".Lore", lore);
										}
										configs.saveTier1Cfg();
										configs.reloadMethod(core, sender, "tier1", false);
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.SetLore").replace("%lootID%", String.valueOf(lootID)).replace("%Lore%", configs.getTier1Cfg().getStringList("FlareRewards." + lootID + ".Lore").toString().replace("[", "").replace("]", "").replace(",", "&7,")).replace("%Tier%", flaremethods.Tier1().getItemMeta().getDisplayName())));
									}
									else {
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
									}
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
								}
							}
							else if(args[3].equalsIgnoreCase("tier2")) {
								if(utils.isInt(args[4])) {
									int lootID = Integer.parseInt(args[4]);
									String[] lore = args[5].replace("_", " ").split(",");
									if(configs.getTier2Cfg().contains("FlareRewards." + lootID)) {
										if(lore[0].equals("null")) {
											configs.getTier2Cfg().set("FlareRewards." + lootID + ".Lore", null);
										}
										else {
											configs.getTier2Cfg().set("FlareRewards." + lootID + ".Lore", lore);
										}
										configs.saveTier2Cfg();
										configs.reloadMethod(core, sender, "tier2", false);
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.SetLore").replace("%lootID%", String.valueOf(lootID)).replace("%Lore%", configs.getTier2Cfg().getStringList("FlareRewards." + lootID + ".Lore").toString().replace("[", "").replace("]", "").replace(",", "&7,")).replace("%Tier%", flaremethods.Tier2().getItemMeta().getDisplayName())));
									}
									else {
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
									}
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
								}
							}
							else if(args[3].equalsIgnoreCase("tier3")) {
								if(utils.isInt(args[4])) {
									int lootID = Integer.parseInt(args[4]);
									String[] lore = args[5].replace("_", " ").split(",");
									if(configs.getTier3Cfg().contains("FlareRewards." + lootID)) {
										if(lore[0].equals("null")) {
											configs.getTier3Cfg().set("FlareRewards." + lootID + ".Lore", null);
										}
										else {
											configs.getTier3Cfg().set("FlareRewards." + lootID + ".Lore", lore);
										}
										configs.saveTier3Cfg();
										configs.reloadMethod(core, sender, "tier3", false);
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.SetLore").replace("%lootID%", String.valueOf(lootID)).replace("%Lore%", configs.getTier3Cfg().getStringList("FlareRewards." + lootID + ".Lore").toString().replace("[", "").replace("]", "").replace(",", "&7,")).replace("%Tier%", flaremethods.Tier3().getItemMeta().getDisplayName())));
									}
									else {
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
									}
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
								}
							}
							else if(args[3].equalsIgnoreCase("tier4")) {
								if(utils.isInt(args[4])) {
									int lootID = Integer.parseInt(args[4]);
									String[] lore = args[5].replace("_", " ").split(",");
									if(configs.getTier4Cfg().contains("FlareRewards." + lootID)) {
										if(lore[0].equals("null")) {
											configs.getTier4Cfg().set("FlareRewards." + lootID + ".Lore", null);
										}
										else {
											configs.getTier4Cfg().set("FlareRewards." + lootID + ".Lore", lore);
										}
										configs.saveTier4Cfg();
										configs.reloadMethod(core, sender, "tier4", false);
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.SetLore").replace("%lootID%", String.valueOf(lootID)).replace("%Lore%", configs.getTier4Cfg().getStringList("FlareRewards." + lootID + ".Lore").toString().replace("[", "").replace("]", "").replace(",", "&7,")).replace("%Tier%", flaremethods.Tier4().getItemMeta().getDisplayName())));
									}
									else {
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
									}
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
								}
							}
							else if(args[3].equalsIgnoreCase("tier5")) {
								if(utils.isInt(args[4])) {
									int lootID = Integer.parseInt(args[4]);
									String[] lore = args[5].replace("_", " ").split(",");
									if(configs.getTier5Cfg().contains("FlareRewards." + lootID)) {
										if(lore[0].equals("null")) {
											configs.getTier5Cfg().set("FlareRewards." + lootID + ".Lore", null);
										}
										else {
											configs.getTier5Cfg().set("FlareRewards." + lootID + ".Lore", lore);
										}
										configs.saveTier5Cfg();
										configs.reloadMethod(core, sender, "tier5", false);
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.SetLore").replace("%lootID%", String.valueOf(lootID)).replace("%Lore%", configs.getTier5Cfg().getStringList("FlareRewards." + lootID + ".Lore").toString().replace("[", "").replace("]", "").replace(",", "&7,")).replace("%Tier%", flaremethods.Tier5().getItemMeta().getDisplayName())));
									}
									else {
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
									}
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
								}
							}
							else {
								sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.WrongFormat")));
							}
						}
						else if(args[2].equalsIgnoreCase("enchant")) {
							if(args[3].equalsIgnoreCase("tier1")) {
								if(utils.isInt(args[4])) {
									int lootID = Integer.parseInt(args[4]);
									String[] allinputs = args[5].toUpperCase().split(",");
									List<String> enchantinfo = new ArrayList<String>();
									List<String> enchnames = new ArrayList<String>();
									List<Integer> enchlevels = new ArrayList<Integer>();
									List<String> allenchants = new ArrayList<String>();
									for(Enchantment e : Enchantment.values()) {
										allenchants.add(e.getName().toUpperCase());
									}
									for(String e : allinputs) {
										String encnames = e.split(";")[0];
										enchnames.add(encnames);
										if(e.split(";").length > 1) {
											if(utils.isInt(e.split(";")[1])) {
												String enclevels = e.split(";")[1];
												enchlevels.add(Integer.parseInt(enclevels));
												enchantinfo.add(e);
											}
										}
										else {
											if(enchnames.contains("null")) {
												if(configs.getTier1Cfg().contains("FlareRewards." + lootID)) {
													configs.getTier1Cfg().set("FlareRewards." + lootID + ".Enchants", null);
													configs.saveTier1Cfg();
													configs.reloadMethod(core, sender, "tier1", false);
													sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.SetEnchant").replace("%lootID%", String.valueOf(lootID)).replace("%Enchant%", "null").replace("%Tier%", flaremethods.Tier1().getItemMeta().getDisplayName())));
												}
											}
											else {
												sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.WrongEnchantFormat")));
											}
											return;
										}
									}
									if(allenchants.containsAll(enchnames)) {
										if(configs.getTier1Cfg().contains("FlareRewards." + lootID)) {
											configs.getTier1Cfg().set("FlareRewards." + lootID + ".Enchants", enchantinfo);
											configs.saveTier1Cfg();
											configs.reloadMethod(core, sender, "tier1", false);
											sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.SetEnchant").replace("%lootID%", String.valueOf(lootID)).replace("%Enchant%", enchantinfo.toString().replace("[", "").replace("]", "")).replace("%Tier%", flaremethods.Tier1().getItemMeta().getDisplayName())));
										}
										else {
											sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
										}
									}
									else {
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.UnusableEnchant").replace("%Enchant%", args[5])));
									}
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
								}
							}
							else if(args[3].equalsIgnoreCase("tier2")) {
								if(utils.isInt(args[4])) {
									int lootID = Integer.parseInt(args[4]);
									String[] allinputs = args[5].toUpperCase().split(",");
									List<String> enchantinfo = new ArrayList<String>();
									List<String> enchnames = new ArrayList<String>();
									List<Integer> enchlevels = new ArrayList<Integer>();
									List<String> allenchants = new ArrayList<String>();
									for(Enchantment e : Enchantment.values()) {
										allenchants.add(e.getName().toUpperCase());
									}
									for(String e : allinputs) {
										String encnames = e.split(";")[0];
										enchnames.add(encnames);
										if(e.split(";").length > 1) {
											if(utils.isInt(e.split(";")[1])) {
												String enclevels = e.split(";")[1];
												enchlevels.add(Integer.parseInt(enclevels));
												enchantinfo.add(e);
											}
										}
										else {
											if(enchnames.contains("null")) {
												if(configs.getTier2Cfg().contains("FlareRewards." + lootID)) {
													configs.getTier2Cfg().set("FlareRewards." + lootID + ".Enchants", null);
													configs.saveTier2Cfg();
													configs.reloadMethod(core, sender, "tier2", false);
													sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.SetEnchant").replace("%lootID%", String.valueOf(lootID)).replace("%Enchant%", "null").replace("%Tier%", flaremethods.Tier2().getItemMeta().getDisplayName())));
												}
												else {
													sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
												}
											}
											else {
												sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.WrongEnchantFormat")));
											}
											return;
										}
									}
									if(allenchants.containsAll(enchnames)) {
										if(configs.getTier2Cfg().contains("FlareRewards." + lootID)) {
											configs.getTier2Cfg().set("FlareRewards." + lootID + ".Enchants", enchantinfo);
											configs.saveTier2Cfg();
											configs.reloadMethod(core, sender, "tier2", false);
											sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.SetEnchant").replace("&lootID%", String.valueOf(lootID)).replace("%Enchant%", enchantinfo.toString().replace("[", "").replace("]", "")).replace("%Tier%", flaremethods.Tier2().getItemMeta().getDisplayName())));
										}
										else {
											sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
										}
									}
									else {
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.UnusableEnchant").replace("%Enchant%", args[5])));
									}
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
								}
							}
							else if(args[3].equalsIgnoreCase("tier3")) {
								if(utils.isInt(args[4])) {
									int lootID = Integer.parseInt(args[4]);
									String[] allinputs = args[5].toUpperCase().split(",");
									List<String> enchantinfo = new ArrayList<String>();
									List<String> enchnames = new ArrayList<String>();
									List<Integer> enchlevels = new ArrayList<Integer>();
									List<String> allenchants = new ArrayList<String>();
									for(Enchantment e : Enchantment.values()) {
										allenchants.add(e.getName().toUpperCase());
									}
									for(String e : allinputs) {
										String encnames = e.split(";")[0];
										enchnames.add(encnames);
										if(e.split(";").length > 1) {
											if(utils.isInt(e.split(";")[1])) {
												String enclevels = e.split(";")[1];
												enchlevels.add(Integer.parseInt(enclevels));
												enchantinfo.add(e);
											}
										}
										else {
											if(enchnames.contains("null")) {
												if(configs.getTier3Cfg().contains("FlareRewards." + lootID)) {
													configs.getTier3Cfg().set("FlareRewards." + lootID + ".Enchants", null);
													configs.saveTier3Cfg();
													configs.reloadMethod(core, sender, "tier3", false);
													sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.SetEnchant").replace("%lootID%", String.valueOf(lootID)).replace("%Enchant%", "null").replace("%Tier%", flaremethods.Tier3().getItemMeta().getDisplayName())));
												}
												else {
													sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
												}
											}
											else {
												sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.WrongEnchantFormat")));
											}
											return;
										}
									}
									if(allenchants.containsAll(enchnames)) {
										if(configs.getTier3Cfg().contains("FlareRewards." + lootID)) {
											configs.getTier3Cfg().set("FlareRewards." + lootID + ".Enchants", enchantinfo);
											configs.saveTier3Cfg();
											configs.reloadMethod(core, sender, "tier3", false);
											sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.SetEnchant").replace("&lootID%", String.valueOf(lootID)).replace("%Enchant%", enchantinfo.toString().replace("[", "").replace("]", "")).replace("%Tier%", flaremethods.Tier3().getItemMeta().getDisplayName())));
										}
										else {
											sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
										}
									}
									else {
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.UnusableEnchant").replace("%Enchant%", args[5])));
									}
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
								}
							}
							else if(args[3].equalsIgnoreCase("tier4")) {
								if(utils.isInt(args[4])) {
									int lootID = Integer.parseInt(args[4]);
									String[] allinputs = args[5].toUpperCase().split(",");
									List<String> enchantinfo = new ArrayList<String>();
									List<String> enchnames = new ArrayList<String>();
									List<Integer> enchlevels = new ArrayList<Integer>();
									List<String> allenchants = new ArrayList<String>();
									for(Enchantment e : Enchantment.values()) {
										allenchants.add(e.getName().toUpperCase());
									}
									for(String e : allinputs) {
										String encnames = e.split(";")[0];
										enchnames.add(encnames);
										if(e.split(";").length > 1) {
											if(utils.isInt(e.split(";")[1])) {
												String enclevels = e.split(";")[1];
												enchlevels.add(Integer.parseInt(enclevels));
												enchantinfo.add(e);
											}
										}
										else {
											if(enchnames.contains("null")) {
												if(configs.getTier4Cfg().contains("FlareRewards." + lootID)) {
													configs.getTier4Cfg().set("FlareRewards." + lootID + ".Enchants", null);
													configs.saveTier4Cfg();
													configs.reloadMethod(core, sender, "tier4", false);
													sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.SetEnchant").replace("%lootID%", String.valueOf(lootID)).replace("%Enchant%", "null").replace("%Tier%", flaremethods.Tier4().getItemMeta().getDisplayName())));
												}
												else {
													sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
												}
											}
											else {
												sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.WrongEnchantFormat")));
											}
											return;
										}
									}
									if(allenchants.containsAll(enchnames)) {
										if(configs.getTier4Cfg().contains("FlareRewards." + lootID)) {
											configs.getTier4Cfg().set("FlareRewards." + lootID + ".Enchants", enchantinfo);
											configs.saveTier4Cfg();
											configs.reloadMethod(core, sender, "tier4", false);
											sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.SetEnchant").replace("&lootID%", String.valueOf(lootID)).replace("%Enchant%", enchantinfo.toString().replace("[", "").replace("]", "")).replace("%Tier%", flaremethods.Tier4().getItemMeta().getDisplayName())));
										}
										else {
											sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
										}
									}
									else {
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.UnusableEnchant").replace("%Enchant%", args[5])));
									}
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
								}
							}
							else if(args[3].equalsIgnoreCase("tier5")) {
								if(utils.isInt(args[4])) {
									int lootID = Integer.parseInt(args[4]);
									String[] allinputs = args[5].toUpperCase().split(",");
									List<String> enchantinfo = new ArrayList<String>();
									List<String> enchnames = new ArrayList<String>();
									List<Integer> enchlevels = new ArrayList<Integer>();
									List<String> allenchants = new ArrayList<String>();
									for(Enchantment e : Enchantment.values()) {
										allenchants.add(e.getName().toUpperCase());
									}
									for(String e : allinputs) {
										String encnames = e.split(";")[0];
										enchnames.add(encnames);
										if(e.split(";").length > 1) {
											if(utils.isInt(e.split(";")[1])) {
												String enclevels = e.split(";")[1];
												enchlevels.add(Integer.parseInt(enclevels));
												enchantinfo.add(e);
											}
										}
										else {
											if(enchnames.contains("null")) {
												if(configs.getTier5Cfg().contains("FlareRewards." + lootID)) {
													configs.getTier5Cfg().set("FlareRewards." + lootID + ".Enchants", null);
													configs.saveTier5Cfg();
													configs.reloadMethod(core, sender, "tier5", false);
													sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.SetEnchant").replace("%lootID%", String.valueOf(lootID)).replace("%Enchant%", "null").replace("%Tier%", flaremethods.Tier5().getItemMeta().getDisplayName())));
												}
												else {
													sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
												}
											}
											else {
												sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.WrongEnchantFormat")));
											}
											return;
										}
									}
									if(allenchants.containsAll(enchnames)) {
										if(configs.getTier5Cfg().contains("FlareRewards." + lootID)) {
											configs.getTier5Cfg().set("FlareRewards." + lootID + ".Enchants", enchantinfo);
											configs.saveTier5Cfg();
											configs.reloadMethod(core, sender, "tier5", false);
											sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.SetEnchant").replace("&lootID%", String.valueOf(lootID)).replace("%Enchant%", enchantinfo.toString().replace("[", "").replace("]", "")).replace("%Tier%", flaremethods.Tier5().getItemMeta().getDisplayName())));
										}
										else {
											sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NoLoot").replace("%lootID%", String.valueOf(lootID))));
										}
									}
									else {
										sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.UnusableEnchant").replace("%Enchant%", args[5])));
									}
								}
								else {
									sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Loot.NotInteger")));
								}
							}
							else {
								sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.WrongFormat")));
							}
						}
						else {
							sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.WrongFormat")));
						}
					}
					else {
						sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.WrongFormat")));
					}
				}
				else {
					sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.WrongFormat")));
				}
			}
		}
	}

	@Override
	public String name() {
		return core.getCMDMgr().editloot;
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
