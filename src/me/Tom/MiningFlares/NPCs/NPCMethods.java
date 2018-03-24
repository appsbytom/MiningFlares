package me.Tom.MiningFlares.NPCs;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;

import me.Tom.MiningFlares.ConfigManager;
import me.Tom.MiningFlares.PluginCore;
import me.Tom.MiningFlares.Utils;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.DespawnReason;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.npc.skin.SkinnableEntity;

//All methods linked with NPCs

public class NPCMethods {
	
	private PluginCore core;
    private ConfigManager configs;
    private Utils<?> utils;
    public NPCMethods(PluginCore pl) {
    	core = pl;
    	configs = core.getConfigs();
    	utils = core.getUtils();
    	getHolo = new HashMap<Integer, Hologram>();
    }
    
    private int maxID;
    public Integer getMaxID() {
    	return maxID;
    }
    public Integer setMaxID(int id) {
    	return maxID = id;
    }
    
    private Map<Integer, Hologram> getHolo;
    public Map<Integer, Hologram> getHolos() {
    	return getHolo;
    }
    
    public final Location getNPCCfgLocation(final String string) {
        double d1 = 0.0;
        double d2 = 0.0;
        double d3 = 0.0;
        float d4 = 0.0f;
        float d5 = 0.0f;
        final String[] cfgloc = configs.getNPCCfg().getString(string).split(",");
        final World world = core.getServer().getWorld(cfgloc[0]);
        d1 = Double.parseDouble(cfgloc[1]);
        d2 = Double.parseDouble(cfgloc[2]);
        d3 = Double.parseDouble(cfgloc[3]);
        d4 = Float.parseFloat(cfgloc[4]);
        d5 = Float.parseFloat(cfgloc[5]);
        final Location loc = new Location(world, d1, d2, d3);
        loc.setYaw(d4);
        loc.setPitch(d5);
        return loc;
    }
    
    public Location getNPCCfgHoloLocation(final String string) {
        double d1 = 0.0;
        double d2 = 0.0;
        double d3 = 0.0;
        final String[] cfgloc = configs.getNPCCfg().getString(string).split(",");
        final World world = core.getServer().getWorld(cfgloc[0]);
        d1 = Double.parseDouble(cfgloc[1]);
        d2 = Double.parseDouble(cfgloc[2]) + 2.5;
        d3 = Double.parseDouble(cfgloc[3]);
        return new Location(world, d1, d2, d3);
    }
    
    public void loadNPCHolograms() {
        if (configs.getNPCCfg().getConfigurationSection("NPCS") != null) {
            if (configs.getNPCCfg().getConfigurationSection("NPCS").getKeys(false) == null) {
                return;
            }
            for (final String id : configs.getNPCCfg().getConfigurationSection("NPCS").getKeys(false)) {
                if (configs.getNPCCfg().contains("NPCS." + id + ".Location")) {
                    final Hologram holo = HologramsAPI.createHologram(core, getNPCCfgHoloLocation("NPCS." + id + ".Location"));
                    for (final String sr : configs.getNPCCfg().getStringList("Holograms.Text")) {
                        holo.appendTextLine(utils.format(sr).replaceAll("%NPCName%", utils.format(configs.getNPCCfg().getString("NPCS." + id + ".Name"))));
                        holo.setAllowPlaceholders(true);
                    }
                    getHolo.put(Integer.valueOf(id), holo);
                }
            }
        }
    }
    
    public void removeHolograms() {
        if (!HologramsAPI.getHolograms(core).isEmpty()) {
            for (final Hologram holo : HologramsAPI.getHolograms(core)) {
                holo.delete();
            }
        }
    }
    
    public void unloadCitizenNPCS() {
    	if(configs.getNPCCfg().getConfigurationSection("NPCS") != null) {
	    	for(String id : configs.getNPCCfg().getConfigurationSection("NPCS").getKeys(false)) {
	    		new BukkitRunnable() {
					
					@Override
					public void run() {
						if(CitizensAPI.getNPCRegistry().getById(Integer.parseInt(id)) != null && configs.getNPCCfg().contains("NPCS." + id)) {
			    			NPC npc = CitizensAPI.getNPCRegistry().getById(Integer.parseInt(id));
							npc.despawn(DespawnReason.PENDING_RESPAWN);
			    		}
					}
				}.runTaskLater(core, 5L);
	    	}
    	}
    }
    
    public void loadCitizenNPCS() {
    	new BukkitRunnable() {
			
			@Override
			public void run() {
				if (configs.getNPCCfg().getConfigurationSection("NPCS") != null) {
		    		for(String id : configs.getNPCCfg().getConfigurationSection("NPCS").getKeys(false)) {
						NPC npcs = CitizensAPI.getNPCRegistry().getById(Integer.parseInt(id));
						npcs.setName(configs.getNPCCfg().getString("NPCS." + id + ".Name"));
						npcs.spawn(npcs.getStoredLocation());
						SkinnableEntity skinnable = (npcs.getEntity() instanceof SkinnableEntity) ? (SkinnableEntity)npcs.getEntity() : null;
						skinnable.setSkinName(configs.getNPCCfg().getString("NPCS." + id + ".Skin"));
		    		}
				}
			}
		}.runTaskLater(core, 10L);
    }
}
