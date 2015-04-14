package me.azewilous.doublejump;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
 * @author Azewilous
 */

public class DJToggle implements CommandExecutor {

DoubleJump plugin;
	public DJToggle(DoubleJump doubleJump) {
		this.plugin = doubleJump;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
			String[] args) {
		
	if(Bukkit.getServer().getWorld((String) plugin.getConfig().get("DoubleJump.DisabledWorld1")) != null){
		sender.sendMessage("This Command is disabled in this world");
		return false;
	}
	if(Bukkit.getServer().getWorld((String) plugin.getConfig().get("DoubleJump.DisabledWorld2")) != null){
		sender.sendMessage("This Command is disabled in this world");
		return false;
	}
	if(Bukkit.getServer().getWorld((String) plugin.getConfig().get("DoubleJump.DisabledWorld3")) != null){
		sender.sendMessage("This Command is disabled in this world");
		return false;
	}
	if(Bukkit.getServer().getWorld((String) plugin.getConfig().get("DoubleJump.DisabledWorld4")) != null){
		sender.sendMessage("This Command is disabled in this world");
		return false;
	}
	if(Bukkit.getServer().getWorld((String) plugin.getConfig().get("DoubleJump.DisabledWorld5")) != null){
		sender.sendMessage("This Command is disabled in this world");
		return false;
	}
		if(!(sender instanceof Player)){
			sender.sendMessage("Can Only Be Used By A Player");
			return false;
		}
			Player player = (Player) sender;
		 if(player.hasPermission("dj.toggle") || player.isOp()){
			if(plugin.djtoggle.contains(player.getName())){
				player.sendMessage(ChatColor.DARK_PURPLE + "Toggled DoubleJump Off!");
				plugin.djtoggle.remove(player.getName());
				return true;
			} else 
				if(!(plugin.djtoggle.contains(player.getName()))){
					player.sendMessage(ChatColor.GREEN + "Toggled DoubleJump On");
					plugin.djtoggle.add(player.getName());
					return true;
				}
			 return false;
		 } else
			 player.sendMessage(ChatColor.RED + "You Need The Permission" + ChatColor.BLUE + " dj.toggle " + ChatColor.RED + "To Use This Command");
		return true;
	}

}
