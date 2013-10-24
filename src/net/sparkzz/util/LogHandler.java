package net.sparkzz.util;

import java.util.logging.Level;

import org.bukkit.plugin.Plugin;

public class LogHandler {
	
	public void info(Plugin plugin, String message) {
		plugin.getLogger().info(message);
	}
	
	public void warn(Plugin plugin, String message) {
		plugin.getLogger().warning(message);
	}
	
	public void severe(Plugin plugin, String message) {
		plugin.getLogger().warning(message);
	}
	
	public void log(Plugin plugin, Level level, String message) {
		plugin.getLogger().log(level, message);
	}
	
	private LogHandler() {
	}
	
	static LogHandler instance = new LogHandler();

	public static LogHandler getInstance() {
		return instance;
	}
}