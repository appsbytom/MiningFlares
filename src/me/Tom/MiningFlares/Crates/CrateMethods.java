package me.Tom.MiningFlares.Crates;

import org.bukkit.Location;
import org.bukkit.World;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;

import me.Tom.MiningFlares.ConfigManager;
import me.Tom.MiningFlares.PluginCore;
import me.Tom.MiningFlares.Utils;

//All methods linked with Crates

public class CrateMethods {
	
	private PluginCore core;
	private ConfigManager configs;
	private Utils<?> utils;
	public CrateMethods(PluginCore pl) {
		core = pl;
		configs = core.getConfigs();
		utils = core.getUtils();
	}
	
	private Location tier1loc;
	public Location getTier1Loc() {
		return tier1loc;
	}
	public Location setTier1Loc() {
		String[] loc = configs.getCratesCfg().getString("Crates.Tier1.Loc").split(",");
		World world = core.getServer().getWorld(loc[0]);
		Double x = Double.parseDouble(loc[1]);
		Double y = Double.parseDouble(loc[2]);
		Double z = Double.parseDouble(loc[3]);
		tier1loc = new Location(world, x, y, z);
		return tier1loc;
	}
	
	private Location tier2loc;
	public Location getTier2Loc() {
		return tier2loc;
	}
	public Location setTier2Loc() {
		String[] loc = configs.getCratesCfg().getString("Crates.Tier2.Loc").split(",");
		World world = core.getServer().getWorld(loc[0]);
		Double x = Double.parseDouble(loc[1]);
		Double y = Double.parseDouble(loc[2]);
		Double z = Double.parseDouble(loc[3]);
		tier2loc = new Location(world, x, y, z);
		return tier2loc;
	}
	
	private Location tier3loc;
	public Location getTier3Loc() {
		return tier3loc;
	}
	public Location setTier3Loc() {
		String[] loc = configs.getCratesCfg().getString("Crates.Tier3.Loc").split(",");
		World world = core.getServer().getWorld(loc[0]);
		Double x = Double.parseDouble(loc[1]);
		Double y = Double.parseDouble(loc[2]);
		Double z = Double.parseDouble(loc[3]);
		tier3loc = new Location(world, x, y, z);
		return tier3loc;
	}
	
	private Location tier4loc;
	public Location getTier4Loc() {
		return tier4loc;
	}
	public Location setTier4Loc() {
		String[] loc = configs.getCratesCfg().getString("Crates.Tier4.Loc").split(",");
		World world = core.getServer().getWorld(loc[0]);
		Double x = Double.parseDouble(loc[1]);
		Double y = Double.parseDouble(loc[2]);
		Double z = Double.parseDouble(loc[3]);
		tier4loc = new Location(world, x, y, z);
		return tier4loc;
	}
	
	private Location tier5loc;
	public Location getTier5Loc() {
		return tier5loc;
	}
	public Location setTier5Loc() {
		String[] loc = configs.getCratesCfg().getString("Crates.Tier5.Loc").split(",");
		World world = core.getServer().getWorld(loc[0]);
		Double x = Double.parseDouble(loc[1]);
		Double y = Double.parseDouble(loc[2]);
		Double z = Double.parseDouble(loc[3]);
		tier5loc = new Location(world, x, y, z);
		return tier5loc;
	}
	
	public void removeHolograms() {
        if (!HologramsAPI.getHolograms(core).isEmpty()) {
            for (final Hologram holo : HologramsAPI.getHolograms(core)) {
                holo.delete();
            }
        }
    }
	
	public Location getCratesCfgHoloLocation(final String string) {
        double d1 = 0.0;
        double d2 = 0.0;
        double d3 = 0.0;
        final String[] arrayOfString = configs.getCratesCfg().getString(string).split(",");
        final World localWorld = core.getServer().getWorld(arrayOfString[0]);
        d1 = Double.parseDouble(arrayOfString[1]) + 0.5;
        d2 = Double.parseDouble(arrayOfString[2]) + 2.05;
        d3 = Double.parseDouble(arrayOfString[3]) + 0.5;
        return new Location(localWorld, d1, d2, d3);
    }
	
	public void loadCrateHolograms() {
        if(configs.getCratesCfg().contains("Crates.Tier1.Loc")) {
        	final Hologram holo = HologramsAPI.createHologram(core, getCratesCfgHoloLocation("Crates.Tier1.Loc"));
        	for (final String sr : configs.getCratesCfg().getStringList("Holograms.Text")) {
        		holo.appendTextLine(utils.format(sr).replaceAll("%CrateName%", utils.format(configs.getCratesCfg().getString("Crates.Tier1.Name"))));
                holo.setAllowPlaceholders(true);
        	}
        }
        if(configs.getCratesCfg().contains("Crates.Tier2.Loc")) {
        	final Hologram holo = HologramsAPI.createHologram(core, getCratesCfgHoloLocation("Crates.Tier2.Loc"));
        	for (final String sr : configs.getCratesCfg().getStringList("Holograms.Text")) {
        		holo.appendTextLine(utils.format(sr).replaceAll("%CrateName%", utils.format(configs.getCratesCfg().getString("Crates.Tier2.Name"))));
                holo.setAllowPlaceholders(true);
            }
        }
        if(configs.getCratesCfg().contains("Crates.Tier3.Loc")) {
        	final Hologram holo = HologramsAPI.createHologram(core, getCratesCfgHoloLocation("Crates.Tier3.Loc"));
        	for (final String sr : configs.getCratesCfg().getStringList("Holograms.Text")) {
                holo.appendTextLine(utils.format(sr).replaceAll("%CrateName%", utils.format(configs.getCratesCfg().getString("Crates.Tier3.Name"))));
                holo.setAllowPlaceholders(true);
            }
        }
        if(configs.getCratesCfg().contains("Crates.Tier4.Loc")) {
        	final Hologram holo = HologramsAPI.createHologram(core, getCratesCfgHoloLocation("Crates.Tier4.Loc"));
        	for (final String sr : configs.getCratesCfg().getStringList("Holograms.Text")) {
                holo.appendTextLine(utils.format(sr).replaceAll("%CrateName%", utils.format(configs.getCratesCfg().getString("Crates.Tier4.Name"))));
                holo.setAllowPlaceholders(true);
            }
        }
        if(configs.getCratesCfg().contains("Crates.Tier5.Loc")) {
        	final Hologram holo = HologramsAPI.createHologram(core, getCratesCfgHoloLocation("Crates.Tier5.Loc"));
        	for (final String sr : configs.getCratesCfg().getStringList("Holograms.Text")) {
                holo.appendTextLine(utils.format(sr).replaceAll("%CrateName%", utils.format(configs.getCratesCfg().getString("Crates.Tier5.Name"))));
                holo.setAllowPlaceholders(true);
        	}
        }
    }

}
