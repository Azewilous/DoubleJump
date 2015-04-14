package me.azewilous.doublejump;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
 * @author Azewilous
 */

public class Reload implements CommandExecutor {

DoubleJump plugin;
	
	public Reload(DoubleJump passedPlugin) {
		this.plugin = passedPlugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]){
		Player player = (Player) sender;
		if(player.hasPermission("dj.reload") || player.isOp() || sender.isOp()){
			if (args.length > 0) {
            sender.sendMessage(ChatColor.RED + "To many arguments.");
            return false;
			} else 
        		plugin.reloadConfig();
                plugin.getPluginLoader().disablePlugin(plugin);
                plugin.getPluginLoader().enablePlugin(plugin);
                sender.sendMessage(ChatColor.YELLOW + "Reloaded "+ ChatColor.GRAY + plugin.getDescription().getFullName());
		} else 
			player.sendMessage(ChatColor.RED + "You Need The Permission Node " + ChatColor.BLUE + " dj.reload "
                    + ChatColor.RED + "To Execute This Command");
        return true;	
	}
	
}
