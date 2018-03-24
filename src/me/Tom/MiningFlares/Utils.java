package me.Tom.MiningFlares;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedSet;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.Command;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredListener;


public class Utils<K> extends HashMap<K,Double> {
	
	private PluginCore core;
	private int total;
	public Utils(PluginCore pl) {
		core = pl;
	}
	
	private static final long serialVersionUID = 2160140027152719742L;

	@SuppressWarnings({ "unchecked", "unlikely-arg-type" })
	public void unload(Plugin plugin) {
    	PluginManager pm = Bukkit.getPluginManager();
        Plugin target = pm.getPlugin(plugin.getName());
        String pluginName = target.getName() + "_" + target.getDescription().getVersion();
        SimpleCommandMap commandMap = null;
        List<Plugin> plugins = new ArrayList<Plugin>();
        Map<String, Plugin> names = new HashMap<String, Plugin>();
        Map<String, Command> commands = new HashMap<String, Command>();
        Map<Event, SortedSet<RegisteredListener>> listeners = new HashMap<Event, SortedSet<RegisteredListener>>();
        boolean reloadListeners = true;
        if (pm != null) {
            pm.disablePlugin(target);
            try {
                Field pluginsField = Bukkit.getPluginManager().getClass().getDeclaredField("plugins");
                pluginsField.setAccessible(true);
                plugins = (List<Plugin>) pluginsField.get(pm);
                Field lookupNamesField = Bukkit.getPluginManager().getClass().getDeclaredField("lookupNames");
                lookupNamesField.setAccessible(true);
                names = (Map<String, Plugin>) lookupNamesField.get(pm);
                try {
                    Field listenersField = Bukkit.getPluginManager().getClass().getDeclaredField("listeners");
                    listenersField.setAccessible(true);
                    listeners = (Map<Event, SortedSet<RegisteredListener>>) listenersField.get(pm);
                } catch (Exception e) {
                    reloadListeners = false;
                }
                Field commandMapField = Bukkit.getPluginManager().getClass().getDeclaredField("commandMap");
                commandMapField.setAccessible(true);
                commandMap = (SimpleCommandMap) commandMapField.get(pm);
                Field knownCommandsField = SimpleCommandMap.class.getDeclaredField("knownCommands");
                knownCommandsField.setAccessible(true);
                commands = (Map<String, Command>) knownCommandsField.get(commandMap);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                Bukkit.getConsoleSender().sendMessage(addPrefix() + format(""));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                Bukkit.getConsoleSender().sendMessage(addPrefix() + format(""));
            }
        }
        pm.disablePlugin(target);
        if (plugins != null && plugins.contains(pluginName))
            plugins.remove(pluginName);
        if (names != null && names.containsKey(pluginName))
            names.remove(pluginName);
        if (listeners != null && reloadListeners) {
            for (SortedSet<RegisteredListener> set : listeners.values()) {
                for (Iterator<RegisteredListener> it = set.iterator(); it.hasNext(); ) {
                    RegisteredListener value = it.next();
                    if (value.getPlugin() == target) {
                        it.remove();
                    }
                }
            }
        }
        if (commandMap != null) {
            for (Iterator<Map.Entry<String, Command>> it = commands.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<String, Command> entry = it.next();
                if (entry.getValue() instanceof PluginCommand) {
                    PluginCommand c = (PluginCommand) entry.getValue();
                    if (c.getPlugin() == target) {
                        c.unregister(commandMap);
                        it.remove();
                    }
                }
            }
        }
        ClassLoader cl = pluginName.getClass().getClassLoader();
        if (cl instanceof URLClassLoader) {
            try {
                Field pluginField = cl.getClass().getDeclaredField("plugin");
                pluginField.setAccessible(true);
                pluginField.set(cl, null);
                Field pluginInitField = cl.getClass().getDeclaredField("pluginInit");
                pluginInitField.setAccessible(true);
                pluginInitField.set(cl, null);
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger("");
            }
            try {
                ((URLClassLoader) cl).close();
            } catch (IOException ex) {
            	Logger.getLogger("");
            }
        }
        System.gc();
    }
	
	@Override
    public Double put(K a, Double b){
        Double i=super.put(a,b);
        total=0;
        for(Double in:values()){
            total+=in;
        }
        return i;
    }
 
    public K get(Random rand){
        if (total<=0)return null;
        int i=rand.nextInt(total);
        for(Map.Entry<K, Double> entry:entrySet()){
            i-=entry.getValue();
            if (i<0)return entry.getKey();
        }
        return null;
    }
    
    public void runCommand(Player player, String type, String cmd) {
        if (type == "player") {
            Bukkit.getServer().getScheduler().runTask(core, new Runnable() {
                public void run() {
                    player.chat("/" + cmd);
                }
            });
        }
        if (type == "console") {
            Bukkit.getServer().getScheduler().runTask(core, new Runnable() {
                public void run() {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                }
            });
        }
    }
    
    public void openInv(Player player, Inventory name) {
    	player.openInventory(name);
    }
	
	public String format(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
	public String addPrefix() {
    	return ChatColor.translateAlternateColorCodes('&', core.getConfigs().getFlaresCfg().getString("Plugin.Prefix"));
    }
	
	@SuppressWarnings("deprecation")
	public List<String> handLore(Player player) {
		List<String> itemlores = new ArrayList<>();
		for (String lore : player.getItemInHand().getItemMeta().getLore()) {
			itemlores.add(lore.replaceAll("\u00a7", "&"));
		}
		return itemlores;
	}
	
	public ItemStack addHiddenGlow(ItemStack item) {
	    ItemStack it = item.clone();
	    try {
	      if (item != null) {
	        if ((item.hasItemMeta()) && (item.getItemMeta().hasEnchants())) {
	          return item;
	        }
	        item.addUnsafeEnchantment(Enchantment.LUCK, 1);
	        ItemMeta meta = item.getItemMeta();
	        meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
	        item.setItemMeta(meta);
	      }
	      return item;
	    }
	    catch (NoClassDefFoundError e) {}
	    return it;
	  }
	
	@SuppressWarnings("deprecation")
	public ItemStack makeHead(String owner, int amount, String name, List<String> lore, Map<Enchantment, Integer> enchants) {
		ItemStack item = new ItemStack(Material.SKULL_ITEM, amount, (short) SkullType.PLAYER.ordinal());
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setOwner(owner);
		meta.setDisplayName(name);
		meta.setLore(lore);
		item.setItemMeta(meta);
		item.addUnsafeEnchantments(enchants);
		return item;
	}
	
	@SuppressWarnings("deprecation")
	public ItemStack makeHead(String owner, int amount, String name, List<String> lore) {
		ItemStack item = new ItemStack(Material.SKULL_ITEM, amount, (short) SkullType.PLAYER.ordinal());
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setOwner(owner);
		meta.setDisplayName(name);
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	public ItemStack makeItem(Material material) {
		ItemStack item = new ItemStack(material);
		return item;
	}
	
	public ItemStack makeItem(Material material, int amount, int type, String name) {
		ItemStack item = new ItemStack(material, amount, (short)type);
		ItemMeta m = item.getItemMeta();
		m.setDisplayName(name);
		item.setItemMeta(m);
		return item;
	}
	
	public ItemStack makeItem(Material material, int amount, int type, String name, List<String> lore) {
		ItemStack item = new ItemStack(material, amount, (short)type);
		ItemMeta m = item.getItemMeta();
		m.setDisplayName(name);
		m.setLore(lore);
		item.setItemMeta(m);
		return item;
	}
	
	public ItemStack makeItem(Material material, int amount, int type, String name, List<String> lore, ItemFlag flag) {
		ItemStack item = new ItemStack(material, amount, (short)type);
		ItemMeta m = item.getItemMeta();
		m.setDisplayName(name);
		m.setLore(lore);
		m.addItemFlags(flag);
		item.setItemMeta(m);
		return item;
	}
	
	public ItemStack makeItem(Material material, int amount, int type, String name, List<String> lore, Map<Enchantment, Integer> enchants) {
	    ItemStack item = new ItemStack(material, amount, (short)type);
	    ItemMeta m = item.getItemMeta();
	    m.setDisplayName(name);
	    m.setLore(lore);
	    item.setItemMeta(m);
	    item.addUnsafeEnchantments(enchants);
	    return item;
	  }
	
	public ItemStack makeItem(Material material, int amount, short type, String name, List<String> lore, Map<Enchantment, Integer> enchants, ItemFlag flag) {
	    ItemStack item = new ItemStack(material, amount, (short)type);
	    ItemMeta m = item.getItemMeta();
	    m.setDisplayName(name);
	    m.setLore(lore);
	    m.addItemFlags(flag);
	    item.setItemMeta(m);
	    item.addUnsafeEnchantments(enchants);
	    return item;
	  }
	
	public ItemStack makeItem(Material material, int amount, short type, String name, List<String> lore, Map<Enchantment, Integer> enchants) {
	    ItemStack item = new ItemStack(material, amount, (short)type);
	    ItemMeta m = item.getItemMeta();
	    m.setDisplayName(name);
	    m.setLore(lore);
	    item.setItemMeta(m);
	    item.addUnsafeEnchantments(enchants);
	    return item;
	  }
	
	public ItemStack giveItem(ItemStack item, Integer amount) {
		item.setAmount(amount);
		return item;
	}

	public boolean isInt(String s) {
	    try {
	        Integer.parseInt(s);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	public boolean isDouble(String s) {
		try {
			Double.parseDouble(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
