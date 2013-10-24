package net.sparkzz.event;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class Manager {
	
	public void registerEvent(Listener listener, Plugin plugin) {
		Bukkit.getPluginManager().registerEvents(listener, plugin);
	}
	
	static Manager instance = new Manager();
	
	public static Manager getManager() {
		return instance;
	}
}