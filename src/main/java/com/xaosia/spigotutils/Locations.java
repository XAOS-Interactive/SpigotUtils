package com.xaosia.spigotutils;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class Locations {

	
	public static String printLocation(Location loc){
		return loc.getWorld().getName()+" "+loc.getX()+" "+loc.getY()+" "+loc.getZ();
	}
	
	public static Location getNearbySurfaceLocation(Location loc, double searchOffset2D){
		double xOffset = Randoms.randomDouble(0, searchOffset2D);
		double zOffset = Randoms.randomDouble(0, searchOffset2D);
		Location safeLocation = loc.clone().add(xOffset, 0, zOffset);
		safeLocation = safeLocation.getWorld().getHighestBlockAt(safeLocation).getLocation();
		safeLocation.setYaw(loc.getYaw());
		safeLocation.setPitch(loc.getPitch());
		return safeLocation;
	}
	
	public static class LocationBounds{
		private Location min;
		private Location max;
		public LocationBounds(Location min, Location max) {
			super();
			
			Location min_ = min.clone();
			Location max_ = max.clone();
			
			double minX = Math.min(min_.getX(), max_.getX());
			double minY = Math.min(min_.getY(), max_.getY());
			double minZ = Math.min(min_.getZ(), max_.getZ());
			
			double maxX = Math.max(min_.getX(), max_.getX());
			double maxY = Math.max(min_.getY(), max_.getY());
			double maxZ = Math.max(min_.getZ(), max_.getZ());
			
			World world = min.getWorld();
			
			this.min = new Location(world,minX,minY,minZ);
			this.max = new Location(world,maxX,maxY,maxZ);
		}
		
		public Location getMin() {
			return min;
		}
		public Location getMax() {
			return max;
		}
		public boolean contains(Location location){
			if(!min.getWorld().equals(location.getWorld()))
				return false;
			if(min == null || max == null)
				return true;
			
			return location.getX() >= min.getX() && location.getX() <= max.getX()
				&& location.getY() >= min.getY() && location.getY() <= max.getY()
				&& location.getZ() >= min.getZ() && location.getZ() <= max.getZ();
		}

		public World getWorld() {
			if(min != null)
				return min.getWorld();
			return null;
		}
		
		public void shift(Vector vector){
			min.add(vector);
			max.add(vector);
		}
	}

	public static Location getFirstFreeLocationOnTop(Location location) {
		Location free = location.clone();
		while(free.getY() < 255){
			if(free.getBlock().isEmpty() && free.clone().add(0, 1, 0).getBlock().isEmpty()){
				return free;
			}else{
				free.add(0, 1, 0);
			}
		}
		return free;
	}
	
	public static Location getSurfaceLocation(Location location) {
		return location.getWorld().getHighestBlockAt(location).getLocation().clone();
	}
	
	public static void safeTeleport(JavaPlugin plugin, Player player, Location location){
		Chunk chunk = location.getWorld().getChunkAt(location);
		chunk.load();
		if(chunk.isLoaded()){
			player.teleport(location);
		}else{
			Bukkit.getScheduler().runTaskLater(plugin, new Runnable(){

				@Override
				public void run() {
					Chunk chunk = location.getWorld().getChunkAt(location);
					if(chunk.isLoaded()){
						player.teleport(location);
					}else{
						Bukkit.getScheduler().runTaskLater(plugin, this, 5);
					}
				}
				
			}, 5);
		}
		
		
		
	}
	
	public static boolean blockEquals(Location a, Location b){
		return a.getWorld() != null 
			&& b.getWorld() != null 
			&& a.getWorld().equals(b.getWorld())
			&& a.getBlockX() == b.getBlockX()
			&& a.getBlockY() == b.getBlockY()
			&& a.getBlockZ() == b.getBlockZ();
	}
	
	public static Location toBlockLoc(Location location){
		Location asBlock = location.clone();
		asBlock.setX(asBlock.getBlockX());
		asBlock.setY(asBlock.getBlockY());
		asBlock.setZ(asBlock.getBlockZ());
		asBlock.setPitch(0);
		asBlock.setYaw(0);
		return asBlock;
	}
	
	public static Location toBlockLoc(Location location, boolean keepRotation){
		Location asBlock = location.clone();
		asBlock.setX(location.getBlockX());
		asBlock.setY(location.getBlockY());
		asBlock.setZ(location.getBlockZ());
		if(keepRotation){
			asBlock.setPitch(location.getPitch());
			asBlock.setYaw(location.getYaw());
		}else{
			asBlock.setPitch(0);
			asBlock.setYaw(0);
		}
		return asBlock;
	}
	
}
