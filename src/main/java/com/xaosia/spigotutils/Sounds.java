package com.xaosia.spigotutils;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Sounds {
	


	public static void playAll(Sound sound) {
		for(Player player : Bukkit.getOnlinePlayers()){
			play(player,sound,1,1);
		}
	}
	
	public static void playAll(Location location, Sound sound) {
		for(Player player : Bukkit.getOnlinePlayers()){
			play(player,location,sound,1,1);
		}
	}
	
	public static void playAll(Collection<Player> players, Sound sound, float volume, float pitch) {
		for(Player player : players){
			play(player,sound,volume,pitch);
		}
	}
	
	public static void playAll(Sound sound, float volume, float pitch) {
		for(Player player : Bukkit.getOnlinePlayers()){
			play(player,sound,volume,pitch);
		}
	}
	
	public static void playAll(Location location, Sound sound, float volume, float pitch) {
		for(Player player : Bukkit.getOnlinePlayers()){
			play(player,location,sound,volume,pitch);
		}
	}
	
	public void play(Player player, Sound sound) {
		play(player,sound,1,1);
	}

	public static void play(Player player, Sound sound, float volume, float pitch){
		play(player, player.getLocation(), sound, volume, pitch);
	}
	
	public static void play(Player player, Location location, Sound sound, float volume, float pitch){
		player.playSound(location,sound,volume,pitch);
	}
}
