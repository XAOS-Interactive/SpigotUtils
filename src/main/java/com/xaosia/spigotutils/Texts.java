package com.xaosia.spigotutils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Texts {
	public static void tellraw(Player player, String json){
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tellraw "+player.getName()+" "+json);
	}
}
