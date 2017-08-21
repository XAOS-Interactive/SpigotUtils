package com.xaosia.spigotutils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;


public class Parser {
	
	public static <E extends Enum<E>> Enum<E> parseEnum(Class<E> enumClass, String toParse, Enum<E> defaultValue, String errorLog){
	 
		Enum<E> e;
		try{
			e = Enum.valueOf(enumClass, toParse.toUpperCase());
		}catch(IllegalArgumentException ex){
			Logger.warnC(errorLog);
			e = defaultValue;
		}
		
		return e;
	}
	
	
	
	public static Location parseLocation(String toParse){
		try{
			String[] arr = toParse.split(" ");
			World world = Bukkit.getWorld(arr[0]);
			if(world == null)
				world = Bukkit.getWorlds().get(0);
			return new Location(
					world,
					Double.parseDouble(arr[1]),
					Double.parseDouble(arr[2]),
					Double.parseDouble(arr[3]),
					Float.parseFloat(arr[4]),
					Float.parseFloat(arr[5])
					);
		}catch(Exception e){
			Logger.warnC("couldn't parse location "+toParse);
			return parseLocation("%default% 0 60 0 0 0");
		}
	}
	
	public static Vector parseVector(String toParse){
		try{
			String[] arr = toParse.split(" ");
			return new Vector(
					Double.parseDouble(arr[0]),
					Double.parseDouble(arr[1]),
					Double.parseDouble(arr[2])
					);
		}catch(Exception e){
			Logger.warnC(e.getMessage());
			return new Vector(0, 0, 0);
		}
	}
	
	public static Location parseLocation(World world,String toParse){
		try{
			String[] arr = toParse.split(" ");
			if(world == null)
				world = Bukkit.getWorlds().get(0);
			return new Location(
					world,
					Double.parseDouble(arr[0]),
					Double.parseDouble(arr[1]),
					Double.parseDouble(arr[2]),
					Float.parseFloat(arr[3]),
					Float.parseFloat(arr[4])
			);
		}catch(Exception e){
			Logger.warnC("Couldn't parse location, error: "+e.getMessage()+", defaulting to world's spawn location");
			return parseLocation(world,world.getSpawnLocation().getBlockX()+" "+world.getSpawnLocation().getBlockY()+" "+world.getSpawnLocation().getBlockZ()+" 0 0");
		}
	}
	
}
