package net.sparkzz.util;

import org.bukkit.ChatColor;

public class Colorizer {

	// colors
	public ChatColor
		BLACK = ChatColor.BLACK,
		DARK_GRAY = ChatColor.DARK_GRAY,
		GRAY = ChatColor.GRAY,
		WHITE = ChatColor.WHITE,
		DARK_RED = ChatColor.DARK_RED,
		RED = ChatColor.RED,
		BLUE = ChatColor.DARK_BLUE,
		LIGHT_BLUE = ChatColor.BLUE,
		CYAN = ChatColor.DARK_AQUA,
		AQUA = ChatColor.AQUA,
		GREEN = ChatColor.DARK_GREEN,
		LIGHT_GREEN = ChatColor.GREEN,
		PURPLE = ChatColor.DARK_PURPLE,
		PINK = ChatColor.LIGHT_PURPLE,
		GOLD = ChatColor.GOLD,
		YELLOW = ChatColor.YELLOW;
		
	// formats
	public ChatColor
		BOLD = ChatColor.BOLD,
		ITALIC = ChatColor.ITALIC,
		UNDERLINE = ChatColor.UNDERLINE,
		RESET = ChatColor.RESET;
	
	public String tranlsateColors(String message) {
        return message = ChatColor.translateAlternateColorCodes('&', message);
	}
	
	static Colorizer instance = new Colorizer();
	
	public static Colorizer getInstance() {
		return instance;
	}
}
