package me.Tom.MiningFlares.EventManager;

import org.bukkit.plugin.PluginManager;

import me.Tom.MiningFlares.ConfigManager;
import me.Tom.MiningFlares.PluginCore;
import me.Tom.MiningFlares.ControlRod.RodEvents;
import me.Tom.MiningFlares.ControlRod.RodEvents_1_9_Plus;
import me.Tom.MiningFlares.Crates.CrateMenuClick;
import me.Tom.MiningFlares.Crates.UseCrate;
import me.Tom.MiningFlares.Crates.UseCrate_1_9_Higher;
import me.Tom.MiningFlares.Flares.BlockBreak;
import me.Tom.MiningFlares.Flares.ClickFlare;
import me.Tom.MiningFlares.Flares.ClickFlare_1_9_Higher;
import me.Tom.MiningFlares.Flares.RewardsMenuClick;
import me.Tom.MiningFlares.Flares.WorldGuard_BlockBreak;
import me.Tom.MiningFlares.NPCs.CitizensListeners;

public class EventManager {

	private PluginCore core;
	private ConfigManager configs;
	private PluginManager pm;
	public EventManager(PluginCore pl) {
		core = pl;
		configs = core.getConfigs();
		pm = core.getServer().getPluginManager();
	}
	
	public void setup() {
		pm.registerEvents(new RewardsMenuClick(core), core);
		if(configs.getFlaresCfg().getBoolean("Redeeming.Click")) {
			if(core.getVersion().equals("v1_8_R1") || core.getVersion().equals("v1_8_R2") || core.getVersion().equals("v1_8_R3")) {
				pm.registerEvents(new ClickFlare(core), core);
			}
			else {
				pm.registerEvents(new ClickFlare_1_9_Higher(core), core);
			}
		}
		if(core.isWGEnabled()) {
			pm.registerEvents(new WorldGuard_BlockBreak(core), core);
		}
		else {
			pm.registerEvents(new BlockBreak(core), core);
		}
		if(core.isHologramsEnabled()) {
			if(configs.getFlaresCfg().getBoolean("Redeeming.NPC") || configs.getFlaresCfg().getBoolean("Redeeming.Crates")) {
				pm.registerEvents(new CrateMenuClick(core), core);
				if(core.getVersion().equals("v1_8_R1") || core.getVersion().equals("v1_8_R2") || core.getVersion().equals("v1_8_R3")) {
					pm.registerEvents(new RodEvents(core), core);
				}
				else {
					pm.registerEvents(new RodEvents_1_9_Plus(core), core);
				}
			}
			if(configs.getFlaresCfg().getBoolean("Redeeming.NPC")) {
				if(core.isCitizensEnabled()) {
					pm.registerEvents(new CitizensListeners(core), core);
				}
			}
			if(configs.getFlaresCfg().getBoolean("Redeeming.Crates")) {
				if(core.getVersion().equals("v1_8_R1") || core.getVersion().equals("v1_8_R2") || core.getVersion().equals("v1_8_R3")) {
					pm.registerEvents(new UseCrate(core), core);
				}
				else {
					pm.registerEvents(new UseCrate_1_9_Higher(core), core);
				}
			}
		}
	}
	
}
