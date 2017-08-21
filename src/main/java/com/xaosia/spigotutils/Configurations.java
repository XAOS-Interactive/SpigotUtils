package com.xaosia.spigotutils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Configurations {

	public static File saveConfigDefaultsToFile(FileConfiguration cfg, File dest){
		
		try {
			dest.getParentFile().mkdirs();
			dest.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileConfiguration lang = YamlConfiguration.loadConfiguration(dest);
		
		lang.setDefaults(cfg);
		lang.options().copyDefaults(true);
		try {
			lang.save(dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dest;
	}
	
	public static FileConfiguration getConfigFromResource(JavaPlugin plugin, String path){
		return YamlConfiguration.loadConfiguration(new InputStreamReader(plugin.getResource(path), Charset.forName("UTF-8")));
	}
	
}
