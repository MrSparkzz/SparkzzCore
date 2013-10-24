package net.sparkzz.util;

import java.io.File;
import java.io.IOException;

import net.sparkzz.core.Main;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class FileManager {
	
	LogHandler log = LogHandler.getInstance();
	Main main = Main.getInstance();
	
	File file, configFile;
	
	FileConfiguration data, config;
	
	public void setup(Plugin plugin) {
		configFile = new File(plugin.getDataFolder(), "config.yml");
		config = plugin.getConfig();
		config.options().copyDefaults(true);
		
		if (!configFile.exists()) {
			saveConfig();
		}
	}
	
	public void createYML(Plugin plugin, String fileName) {
		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}
		
		file = new File(plugin.getDataFolder(), fileName + ".yml");
		
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				log.severe(main, "Could not create " + fileName + ".yml!");
			}
		}
		
		data = YamlConfiguration.loadConfiguration(file);
	}
	
	public void saveData() {
		try {
			data.save(file);
		} catch (IOException e) {
			log.severe(main, "Could not save config file!");
		}
	}
	
	public FileConfiguration getData() {
		return data;
	}
	
	public FileConfiguration getConfig() {
		return config;
	}
	
	public void saveConfig() {
		try {
			config.save(configFile);
		} catch (IOException e) {
			log.severe(main, "Could not save config file!");
		}
	}
	
	public void reloadConfig() {
		config = YamlConfiguration.loadConfiguration(configFile);
	}
}
