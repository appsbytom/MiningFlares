package me.Tom.MiningFlares;

//All methods linked with editing rewards

public class LootMethods {

	private PluginCore core;
	private ConfigManager configs;
	public LootMethods(PluginCore pl) {
		core = pl;
		configs = core.getConfigs();
		tier1lootID = 0;
		tier1totalpercentage = 0.0;
		tier2lootID = 0;
		tier2totalpercentage = 0.0;
		tier3lootID = 0;
		tier3totalpercentage = 0.0;
		tier4lootID = 0;
		tier4totalpercentage = 0.0;
		tier5lootID = 0;
		tier5totalpercentage = 0.0;
	}
	
	public void setup() {
		if(configs.getTier1Cfg().getConfigurationSection("FlareRewards") != null) {
			for (final String st : configs.getTier1Cfg().getConfigurationSection("FlareRewards").getKeys(false)) {
				final int id = Integer.parseInt(st);
				if(id > getTier1LootID()) {
					setTier1LootID(id);
				}
			}
		}
		if(configs.getTier2Cfg().getConfigurationSection("FlareRewards") != null) {
			for (final String st : configs.getTier2Cfg().getConfigurationSection("FlareRewards").getKeys(false)) {
				final int id = Integer.parseInt(st);
				if(id > getTier2LootID()) {
					setTier2LootID(id);
				}
			}
		}
		if(configs.getTier3Cfg().getConfigurationSection("FlareRewards") != null) {
			for (final String st : configs.getTier3Cfg().getConfigurationSection("FlareRewards").getKeys(false)) {
				final int id = Integer.parseInt(st);
				if(id > getTier3LootID()) {
					setTier3LootID(id);
				}
			}
		}
		if(configs.getTier4Cfg().getConfigurationSection("FlareRewards") != null) {
			for (final String st : configs.getTier4Cfg().getConfigurationSection("FlareRewards").getKeys(false)) {
				final int id = Integer.parseInt(st);
				if(id > getTier4LootID()) {
					setTier4LootID(id);
				}
			}
		}
		if(configs.getTier5Cfg().getConfigurationSection("FlareRewards") != null) {
			for (final String st : configs.getTier5Cfg().getConfigurationSection("FlareRewards").getKeys(false)) {
				final int id = Integer.parseInt(st);
				if(id > getTier5LootID()) {
					setTier5LootID(id);
				}
			}
		}
	}
	
	private int tier1lootID;
	public Integer getTier1LootID() {
		return tier1lootID;
	}
	public Integer setTier1LootID(int id) {
		return tier1lootID = id;
	}
	
	private int tier2lootID;
	public Integer getTier2LootID() {
		return tier2lootID;
	}
	public Integer setTier2LootID(int id) {
		return tier2lootID = id;
	}
	
	private int tier3lootID;
	public Integer getTier3LootID() {
		return tier3lootID;
	}
	public Integer setTier3LootID(int id) {
		return tier3lootID = id;
	}
	
	private int tier4lootID;
	public Integer getTier4LootID() {
		return tier4lootID;
	}
	public Integer setTier4LootID(int id) {
		return tier4lootID = id;
	}
	
	private int tier5lootID;
	public Integer getTier5LootID() {
		return tier5lootID;
	}
	public Integer setTier5LootID(int id) {
		return tier5lootID = id;
	}
	
	private double tier1totalpercentage;
	public Double getTier1Percent() {
		return tier1totalpercentage;
	}
	public Double setTier1Percent(double per) {
		return tier1totalpercentage = per;
	}
	public Double addTier1Percents(double per) {
		return tier1totalpercentage += per;
	}
	
	private double tier2totalpercentage;
	public Double getTier2Percent() {
		return tier2totalpercentage;
	}
	public Double setTier2Percent(double per) {
		return tier2totalpercentage = per;
	}
	public Double addTier2Percents(double per) {
		return tier2totalpercentage += per;
	}
	
	private double tier3totalpercentage;
	public Double getTier3Percent() {
		return tier3totalpercentage;
	}
	public Double setTier3Percent(double per) {
		return tier3totalpercentage = per;
	}
	public Double addTier3Percents(double per) {
		return tier3totalpercentage += per;
	}
	
	private double tier4totalpercentage;
	public Double getTier4Percent() {
		return tier4totalpercentage;
	}
	public Double setTier4Percent(double per) {
		return tier4totalpercentage = per;
	}
	public Double addTier4Percents(double per) {
		return tier4totalpercentage += per;
	}
	
	private double tier5totalpercentage;
	public Double getTier5Percent() {
		return tier5totalpercentage;
	}
	public Double setTier5Percent(double per) {
		return tier5totalpercentage = per;
	}
	public Double addTier5Percents(double per) {
		return tier5totalpercentage += per;
	}
}
