package com.xaosia.spigotutils;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Logger {
	
	private static java.util.logging.Logger logger;
	private static ConsoleCommandSender console;
	private static String strippedPrefix = "[SpigotUtils] ";
	private static String coloredPrefix = "[SpigotUtils] ";
	private static boolean debug = false;

	static{
		logger = Bukkit.getLogger();
		console = Bukkit.getServer().getConsoleSender();
	}
	/**
	 * Warn in white in the console
	 * @param message
	 */
	public static void warn(String message){
		logger.warning(ChatColor.stripColor(getPrefix()+message));
	}
	
	/**
	 * Warn in orange color in the console
	 * @param message
	 */
	public static void warnC(String message){
		coloredLog(ChatColor.GOLD,message);
	}
	
	/**
	 * Info in the console
	 * @param message
	 */
	public static void info(String message){
		logger.info(ChatColor.stripColor(getPrefix()+message));
	}
	
	/**
	 * Info in green in the console
	 * @param message
	 */
	public static void infoC(String message){
		coloredLog(ChatColor.GREEN,message);
	}
	
	/**
	 * Severe in the console
	 * @param message
	 */
	public static void severe(String message){
		logger.severe(ChatColor.stripColor(getPrefix()+message));;
	}
	
	/**
	 * Severe in red in the console
	 * @param message
	 */
	public static void severeC(String message){
		coloredLog(ChatColor.RED,message);
	}
	
	/**
	 * Debug to console if debug enabled
	 * @param message
	 */
	public static void debug(String message){
		if(debug){
			logger.info(ChatColor.stripColor(getPrefix()+"[DEBUG] "+message));
		}
	}
	
	/**
	 * Broadcast a message to all players and log to console
	 * @param message
	 */
	public static void broadcast(String message){
		Bukkit.getServer().broadcastMessage(getPrefix()+message);
	}
	
	
	private static void coloredLog(ChatColor color,String message){
		console.sendMessage(color+getStrippedPrefix()+message);
	}
	
	
	private static String getPrefix(){
		return coloredPrefix;
	}
	
	private static String getStrippedPrefix(){
		return strippedPrefix;
	}
	
	public static void setStrippedPrefix(String prefix){
		strippedPrefix = prefix;
	}
	
	public static void setColoredPrefix(String prefix){
		coloredPrefix = prefix;
	}
	
	public static void setDebug(boolean state){
		debug = state;
	}

	public static void sendMessage(Player player, String message) {
		player.sendMessage(getPrefix()+message);
	}
}
