package me.Tom.MiningFlares.ControlRod;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.Tom.MiningFlares.PluginCore;
import me.Tom.MiningFlares.Utils;

//All methods linked with rods

public class RodMethods {
	
	private PluginCore core;
	private Utils<?> utils;
	public RodMethods(PluginCore pl) {
		core = pl;
		utils = core.getUtils();
		rod = new ArrayList<UUID>();
		npc = new ArrayList<UUID>();
		crate = new ArrayList<UUID>();
		create = new ArrayList<UUID>();
		delete = new ArrayList<UUID>();
	}
	
	private List<UUID> rod;
	public List<UUID> getRod() {
		return rod;
	}
	
	private List<UUID> npc;
	public List<UUID> getNPCList() {
		return npc;
	}
	
	private List<UUID> crate;
	public List<UUID> getCrateList() {
		return crate;
	}
	
	private List<UUID> create;
	public List<UUID> getCreateList() {
		return create;
	}
	
	private List<UUID> delete;
	public List<UUID> getDeleteList() {
		return delete;
	}
	
	public ItemStack npcRod() {
		List<String> lore = new ArrayList<String>();
		lore.add(utils.format("&7* Shift to change between NPC and Crate mode!"));
		lore.add(utils.format("&7* Right click to switch between create and delete mode!"));
		lore.add(utils.format("&7* Left click on a block to create/delete a NPC/Crate!"));
		lore.add("");
		lore.add(utils.format("&7NPC: &aEnabled"));
		lore.add(utils.format("&7Crate: &cDisabled"));
		return utils.addHiddenGlow(utils.makeItem(Material.STICK, 1, 0, utils.format("&6&lMiningFlares Rod &7[&aCreate&7]"), lore));
	}
}
