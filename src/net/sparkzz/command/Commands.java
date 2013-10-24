package net.sparkzz.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class Commands implements CommandExecutor {

	String cmd;
	
	Register register = Register.getRegister();
	PluginManager pm = Bukkit.getPluginManager();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		cmd = command.getName();
			
			// disable command
			if (cmd.equalsIgnoreCase("disable")) {
				if (!sender.hasPermission("sparkzz.disable")) {
					sender.sendMessage(ChatColor.RED + "You are not permitted to perform this action!");
					return true;
				}
				
				if (args.length != 1) {	
					sender.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "Proper Usage: /disable <PLUGIN>");
					return true;
				}
				
				Plugin plugin = pm.getPlugin(args[0]);
					
				if (plugin == null) {
					sender.sendMessage(ChatColor.RED + "Specified Plugin " + ChatColor.GOLD + args[0] + ChatColor.RED + " could not be found!");
					return true;
				}

				if (!(plugin.isEnabled())) {
					sender.sendMessage(ChatColor.RED + "Specified Plugin" + ChatColor.GOLD + args[0] + ChatColor.RED + " is already disabled!");
					return true;
				}
				
				if (sender instanceof Player) 
					sender.sendMessage(ChatColor.YELLOW + "Disabling Plugin " + ChatColor.GOLD + plugin + ChatColor.YELLOW + "!");
				
				pm.disablePlugin(plugin);
				
				if (sender instanceof Player)
					sender.sendMessage(ChatColor.GREEN + "Successfully Disabled " + ChatColor.GOLD + plugin + ChatColor.GREEN + "!");
				
				return true;
			}
			// disable command
			
			// enable command
			if (cmd.equalsIgnoreCase("enable")) {
				if (!sender.hasPermission("sparkzz.enable")) {
					sender.sendMessage(ChatColor.RED + "You are not permitted to perform this action!");
					return true;
				}
				
				if (args.length != 1) {
					sender.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "Proper Usage: /enable <PLUGIN>");
					return true;
				}
				
				Plugin plugin = pm.getPlugin(args[0]);
				
				if (plugin == null) {
					sender.sendMessage(ChatColor.RED + "Specified Plugin " + ChatColor.GOLD + args[0] + ChatColor.RED + " could not be found!");
					return true;
				}
				
				if (plugin.isEnabled()) {
					sender.sendMessage(ChatColor.RED + "Specified Plugin " + ChatColor.GOLD + args[0] + ChatColor.RED + " is already enabled!");
					return true;
				}
				
				if (sender instanceof Player) 
					sender.sendMessage(ChatColor.YELLOW + "Enabling Plugin " + ChatColor.GOLD + plugin + ChatColor.YELLOW + "!");
				
				pm.enablePlugin(plugin);
				
				if (sender instanceof Player)
					sender.sendMessage(ChatColor.GREEN + "Successfully Enabled " + ChatColor.GOLD + plugin + ChatColor.GREEN + "!");
				
				return true;
			}
			// enable command
			
			// refresh command
			if (cmd.equalsIgnoreCase("refresh")) {				
				if (!sender.hasPermission("sparkzz.refresh")) {
					sender.sendMessage(ChatColor.RED + "You are not permitted to perform this action!");
					return true;
				}
				
				if (args.length != 1) {
					sender.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "Proper Usage: /refresh <PLUGIN>");
					return true;
				}
				
				Plugin plugin = pm.getPlugin(args[0]);
				
				if (plugin == null) {
					sender.sendMessage(ChatColor.RED + "Specified Plugin " + ChatColor.GOLD + args[0] + ChatColor.RED + " could not be found!");
					return true;
				}
				
				if (!plugin.isEnabled()) {
					sender.sendMessage(ChatColor.GOLD + args[0] + ChatColor.YELLOW + " is disabled, so we will enable it for you!");
					
					if (sender instanceof Player)
						sender.sendMessage(ChatColor.GREEN + "Successfully Refreshed " + ChatColor.GOLD + plugin + ChatColor.GREEN + "!");
					
					pm.enablePlugin(plugin);
					
					if (sender instanceof Player)
						sender.sendMessage(ChatColor.GREEN + "Successfully Refreshed " + ChatColor.GOLD + plugin + ChatColor.GREEN + "!");
					
					return true;
				}
				
				if (sender instanceof Player) 
					sender.sendMessage(ChatColor.YELLOW + "Reloading Plugin " + ChatColor.GOLD + plugin + ChatColor.YELLOW + "!");
				
				try {
					pm.disablePlugin(plugin);
					
					Thread.sleep(5000);
					
					pm.enablePlugin(plugin);
				} catch (InterruptedException e) {
					e.printStackTrace();
					return true;
				}
				
				if (sender instanceof Player)
					sender.sendMessage(ChatColor.GREEN + "Successfully Refreshed " + ChatColor.GOLD + plugin + ChatColor.GREEN + "!");
				
				return true;
			}
			// refresh command
		
		return false;
	}
	
	public void initCommands() {
		register.initCommand("enable", this);
		register.initCommand("disable", this);
		register.initCommand("refresh", this);
	}
	
	static Commands instance = new Commands();
	
	public static Commands getCommands() {
		return instance;
	}
}
