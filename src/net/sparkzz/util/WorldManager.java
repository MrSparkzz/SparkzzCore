package net.sparkzz.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

/*
 * WorldManager class
 * 
 * Created by: ThunderWaffeMC
 * Modified & Assembled by: MrSparkzz
 */

public class WorldManager {

	public boolean isWorld(World world) {
		if (!world.equals(null)) return true;
		else return false;
	}
	
	public void unloadWorld(World world) {
		Bukkit.getServer().unloadWorld(world, true);
	}
	
	public void copyWorld(String name) {
		World source = Bukkit.getWorld(name);
		File sourceFolder = source.getWorldFolder();
		
		World target = Bukkit.getWorld(name);
		
		if (isWorld(target)) {
			int i = 0;
			
			do {
				++i;
				
				target = Bukkit.getWorld(name + "_copy_" + i);
			} while (isWorld(target));
		}
		
		File targetFolder = target.getWorldFolder();
		
		copyWorldFiles(sourceFolder, targetFolder);
	}
	
	public void copyWorldFiles(File source, File target) {
		try {
			ArrayList<String> ignore = new ArrayList<String>(Arrays.asList("uid.dat", "session.dat"));
			
			if (!ignore.contains(source.getName())) {
				if (source.isDirectory()) {
					if (!target.exists())
						target.mkdirs();
					
					String files[] = source.list();
					
					for (String file : files) {
						File srcFile = new File(source, file);
						File destFile = new File(target, file);
						copyWorldFiles(srcFile, destFile);
					}
				} else {
					InputStream in = new FileInputStream(source);
					OutputStream out = new FileOutputStream(target);
					byte[] buffer = new byte[1024];
					int length;
					while ((length = in.read(buffer)) > 0)
						out.write(buffer, 0, length);
					in.close();
					out.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteWorld(World world) {
		if (!isWorld(world)) return;
		
		File path = world.getWorldFolder();
		
		for (Player player : world.getPlayers()) {
			player.kickPlayer("Current world is being deleted!");
		}
		
		unloadWorld(world);

		deleteWorldFiles(path);
		return;
	}
	
	private boolean deleteWorldFiles(File path) {
		if (path.exists()) {
			File files[] = path.listFiles();
			
			for (int i = 0 ; i < files.length ; i++) {
				if (files[i].isDirectory()) {
					deleteWorldFiles(files[i]);
				} else {
					files[i].delete();
				}
			}
		}
		return (path.delete());
	}
	
	static WorldManager worlds = new WorldManager();
	
	public static WorldManager getWorlds() {
		return worlds;
	}
}
