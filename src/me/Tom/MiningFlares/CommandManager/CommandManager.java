package me.Tom.MiningFlares.CommandManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.Tom.MiningFlares.ConfigManager;
import me.Tom.MiningFlares.PluginCore;
import me.Tom.MiningFlares.Utils;
import me.Tom.MiningFlares.CommandManager.Commands.Crates;
import me.Tom.MiningFlares.CommandManager.Commands.EditLoot;
import me.Tom.MiningFlares.CommandManager.Commands.GiveFlares;
import me.Tom.MiningFlares.CommandManager.Commands.Help;
import me.Tom.MiningFlares.CommandManager.Commands.NPCs;
import me.Tom.MiningFlares.CommandManager.Commands.ReloadConfigs;
import me.Tom.MiningFlares.CommandManager.Commands.RewardsGUI;
import me.Tom.MiningFlares.CommandManager.Commands.Rod;

public class CommandManager implements CommandExecutor {

    private PluginCore core;
    private ConfigManager configs;
    private Utils<?> utils;
    private List<SubCommands> commands;
    public CommandManager(PluginCore pl) {
    	core = pl;
    	configs = core.getConfigs();
    	utils = core.getUtils();
    	commands = new ArrayList<SubCommands>();
    }

    //Sub Commands
    private String main = "mflare";
    public String give = "give";
    public String rewards = "rewards";
    public String reload = "reload";
    public String help = "help";
    public String npc = "npc";
    public String editloot = "loot";
    public String crates = "crate";
    public String rod = "rod";

    public void setup() {
        core.getCommand(main).setExecutor(this);
        commands.add(new ReloadConfigs(core));
        commands.add(new RewardsGUI(core));
        commands.add(new Help(core));
        commands.add(new GiveFlares(core));
        commands.add(new EditLoot(core));
        if(core.isHologramsEnabled()) {
        	if(configs.getFlaresCfg().getBoolean("Redeeming.NPC") || configs.getFlaresCfg().getBoolean("Redeeming.Crates")) {
        		commands.add(new Rod(core));
        	}
    		if(configs.getFlaresCfg().getBoolean("Redeeming.NPC")) {
    			commands.add(new NPCs(core));
    		}	
    		if(configs.getFlaresCfg().getBoolean("Redeeming.Crates")) {
    			commands.add(new Crates(core));
    		}
        }
    }

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (command.getName().equalsIgnoreCase(main)) {
            if (args.length == 0) {
            	sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.NoCmd"))); 
            	return true;
            }

            SubCommands target = get(args[0]);

            if (target == null) {
            	sender.sendMessage(utils.addPrefix() + utils.format(configs.getMsgCfg().getString("Errors.NoCmd")));
            	return true;
            }

            List<String> arrayList = new ArrayList<String>();

            arrayList.addAll(Arrays.asList(args));
            arrayList.remove(0);

            try{
                target.onCommand(sender,args);
            }catch (Exception e){
            	sender.sendMessage(utils.addPrefix() + utils.format("&cAn error has occurred"));

                e.printStackTrace();
            }
        }

        return true;
    }

    private SubCommands get(String name) {
        Iterator<SubCommands> subcommands = commands.iterator();

        while (subcommands.hasNext()) {
        	SubCommands sc = (SubCommands) subcommands.next();

            if (sc.name().equalsIgnoreCase(name)) {
                return sc;
            }

            String[] aliases;
            int length = (aliases = sc.aliases()).length;

            for (int var5 = 0; var5 < length; ++var5) {
                String alias = aliases[var5];
                if (name.equalsIgnoreCase(alias)) {
                    return sc;
                }

            }
        }
        return null;
    }
}
