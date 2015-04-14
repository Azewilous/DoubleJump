package me.azewilous.doublejump;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * @author Azewilous
 */

public class DoubleJump extends JavaPlugin implements Listener {
	public final Logger azelogs = Logger.getLogger("Minecraft");
	
	Plugin plugin;
	FileConfiguration config;
    File cFile;
	
    List<String> djtoggle = new ArrayList<String>();
    
	@Override
	public void onEnable(){
		PluginDescriptionFile pdfFile = this.getDescription();
        this.azelogs.info(pdfFile.getName() + " Version " + pdfFile.getVersion());
		  getServer().getPluginManager().registerEvents(this, this);
		
		reloadConfig();
		config = getConfig();
		config.options().copyDefaults(true);
		cFile = new File(getDataFolder(), "config.yml");
		
		config.addDefault("DoubleJump.Distance", 2.0);
		config.addDefault("DoubleJump.Velocity", 1);
		config.addDefault("DoubleJump.DisabledWorld1", "");
		config.addDefault("DoubleJump.DisabledWorld2", "");
		config.addDefault("DoubleJump.DisabledWorld3", "");
		config.addDefault("DoubleJump.DisabledWorld4", "");
		config.addDefault("DoubleJump.DisabledWorld5", "");
		saveConfig();
		
		registerEvents(this, new Jump(this));
		this.getCommand("djtoggle").setExecutor(new DJToggle(this));
		this.getCommand("djreload").setExecutor(new Reload(this));
		
	}

	public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }
	
}
