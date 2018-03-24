package me.Tom.MiningFlares.Crates;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.Tom.MiningFlares.InventoryMethods;
import me.Tom.MiningFlares.PluginCore;

public class CrateMenuClick implements Listener {
	
	private PluginCore core;
	private InventoryMethods invmethods;
	public CrateMenuClick(PluginCore pl) {
		core = pl;
		invmethods = core.getInvMethods();
	}
	
	@EventHandler
	public void invClick(InventoryClickEvent event) {
		Inventory inventory = event.getClickedInventory();
		ItemStack clicked = event.getCurrentItem();
		
		if(inventory != null) {
			if(inventory.equals(invmethods.getTier1WinningsMenu())) {
				if(clicked != null) {
					event.setCancelled(true);
				}
			}
			if(inventory.equals(invmethods.getTier2WinningsMenu())) {
				if(clicked != null) {
					event.setCancelled(true);
				}
			}
			if(inventory.equals(invmethods.getTier3WinningsMenu())) {
				if(clicked != null) {
					event.setCancelled(true);
				}
			}
			if(inventory.equals(invmethods.getTier4WinningsMenu())) {
				if(clicked != null) {
					event.setCancelled(true);
				}
			}
			if(inventory.equals(invmethods.getTier5WinningsMenu())) {
				if(clicked != null) {
					event.setCancelled(true);
				}
			}
		}
	}
	
	public void invClose(InventoryCloseEvent event) {
		Inventory inventory = event.getInventory();
		
		if(inventory.equals(invmethods.getTier1WinningsMenu())) {
			event.getPlayer().openInventory(invmethods.getTier1WinningsMenu());
		}
		if(inventory.equals(invmethods.getTier2WinningsMenu())) {
			event.getPlayer().openInventory(invmethods.getTier2WinningsMenu());
		}
		if(inventory.equals(invmethods.getTier3WinningsMenu())) {
			event.getPlayer().openInventory(invmethods.getTier3WinningsMenu());
		}
		if(inventory.equals(invmethods.getTier4WinningsMenu())) {
			event.getPlayer().openInventory(invmethods.getTier4WinningsMenu());
		}
		if(inventory.equals(invmethods.getTier5WinningsMenu())) {
			event.getPlayer().openInventory(invmethods.getTier5WinningsMenu());
		}
	}
	
}
