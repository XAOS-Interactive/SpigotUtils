package com.xaosia.spigotutils;

import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;

import com.google.common.collect.Sets;

public class Blocks {
	
	public static Set<Block> findNearbyBlocks(Location location, int radius, Set<Material> filterBy){
		
		Set<Block> blocks = Sets.newHashSet();
		
		int x = location.getBlockX();
		int y = location.getBlockY();
		int z = location.getBlockZ();
		
		World world = location.getWorld();
		
		for(int i=-radius ; i<= radius ; i++){
			for(int j=-radius ; j<=radius ; j++){
				for(int k=-radius ; k<= radius ; k++){
					Block block = world.getBlockAt(x+i, y+j, z+k);
					if(filterBy == null || filterBy.contains(block.getType())){
						blocks.add(block);
					}
				}
			}
		}
		
		return blocks;
	}
	public static Set<BlockState> findNearbyBlockStates(Location location, int radius, Set<Material> filterBy){
		
		Set<BlockState> blocks = Sets.newHashSet();
		
		int x = location.getBlockX();
		int y = location.getBlockY();
		int z = location.getBlockZ();
		
		World world = location.getWorld();
		
		for(int i=-radius ; i<= radius ; i++){
			for(int j=-radius ; j<=radius ; j++){
				for(int k=-radius ; k<= radius ; k++){
					Block block = world.getBlockAt(x+i, y+j, z+k);
					if(filterBy == null || filterBy.contains(block.getType())){
						blocks.add(block.getState());
					}
				}
			}
		}
		
		return blocks;
	}
	
	public static short chatColorToWoolColor(ChatColor color){
		switch(color){
			case AQUA:
				return (short) 3;
			case BLACK:
				return (short) 15;
			case BLUE:
				return (short) 11;
			case DARK_BLUE:
			case DARK_AQUA:
				return (short) 9;
			case DARK_GRAY:
				return (short) 7;
			case GREEN:
			case DARK_GREEN:
				return (short) 13;
			case DARK_PURPLE:
				return (short) 10;
			case RED:
			case DARK_RED:
				return (short) 14;
			case GOLD:
				return (short) 1;
			case GRAY:
				return (short) 8;
			case LIGHT_PURPLE:
				return (short) 6;
			case YELLOW:
				return (short) 4;
			default:
			case WHITE:
			case MAGIC:
			case STRIKETHROUGH:
			case UNDERLINE:
			case RESET:
			case ITALIC:
			case BOLD:
				return (short) 0;
		}
	}
	
}
