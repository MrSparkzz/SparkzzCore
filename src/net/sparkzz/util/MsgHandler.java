package net.sparkzz.util;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MsgHandler {
	
	private MsgHandler() {
	}
	
	static MsgHandler instance = new MsgHandler();
	
	public static MsgHandler getInstance() {
		return instance;
	}
	
	Colorizer color = Colorizer.getInstance();
	FileManager files = new FileManager();
	
	public void deny(CommandSender sender) {
		sender.sendMessage(color.RED + "You are not permitted to perform this action!");
	}
	
	public void args(CommandSender sender, int caseNum) {
		switch (caseNum) {
			case 1: 
				sender.sendMessage(color.RED + "Too few arguments!");
				break;
			case 2:
				sender.sendMessage(color.RED + "Too many arguments!");
				break;
			default:
				sender.sendMessage(color.RED + "Invalid arguments!");
				break;
		}
	}
	
	public String buildStringRaw(int start, String[] args) {
		StringBuilder str = new StringBuilder();
	
		for (int i = start; i < args.length; i++) {
			str.append(args[i] + " ");
		}
	
		String raw_msg = str.toString();
		
		return raw_msg;
	}
	
	public String buildString(int start, String[] args) {
		StringBuilder str = new StringBuilder();
	
		for (int i = start; i < args.length; i++) {
			str.append(args[i] + " ");
		}
	
		String raw_msg = str.toString();
		String message = translateColor(raw_msg);
		
		return message;
	}
	
	public void broadcast(String message) {
		Bukkit.broadcastMessage(translateColor(message));
	}
	
	public void send(Player player, String message) {
		player.sendMessage(translateColor(message));

	}

	public void send(CommandSender sender, String message) {
		sender.sendMessage(translateColor(message));
	}
	
	public void sendRaw(Player player, String message) {
		player.sendRawMessage(message);
	}
	
	public void send(String name, String message) {
		Player player = Bukkit.getPlayer(name);

		if	(player != null) {
			player.sendMessage(translateColor(message));
		} 
	}
	
	public void targetNotFound(CommandSender sender, String name) {
		sender.sendMessage(color.RED + "Specified player " + color.GOLD + name + color.RED + " is not online!");
	}
	
	public void massSend(String[] playerNameList, String message) {
		for(Player player : Bukkit.getOnlinePlayers()) {
			String playerName = player.getName();
			send(playerNameList[playerName.indexOf(playerName)], message);
		}
	}
 
	public String translateColor(String input) {
		return input = color.tranlsateColors(input);
	}
	
	public String info(String message) {
		return color.GRAY + "" + color.ITALIC + message;
	}

	public String warn(String message) {
		return color.RED + message;
	}
	
	public String severe(String message) {
		return color.BOLD + "" + color.DARK_RED + message;
	}
}