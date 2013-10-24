package net.sparkzz.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;

public class Register {
	
	public void initCommand(String command, CommandExecutor executor) {
		Bukkit.getPluginCommand(command).setExecutor(executor);
	}
	
	static Register instance = new Register();
	
	public static Register getRegister() {
		return instance;
	}
}
