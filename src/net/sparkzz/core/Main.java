package net.sparkzz.core;

import net.sparkzz.command.Commands;
import net.sparkzz.util.LogHandler;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	LogHandler logger = LogHandler.getInstance();
	Commands commands = Commands.getCommands();
	
	@Override
	public void onDisable() {
		logger.info(this, "Disabled");
	}
	
	@Override
	public void onEnable() {
		commands.initCommands();
		
		logger.info(this, "Enabled");
	}
	
	static Main instance = new Main();
	
	public static Main getInstance() {
		return instance;
	}
}
