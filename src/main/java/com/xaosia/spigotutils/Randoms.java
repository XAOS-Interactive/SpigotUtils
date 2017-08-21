package com.xaosia.spigotutils;

import java.util.Random;

import org.bukkit.block.BlockFace;

public class Randoms {
	private static Random r = new Random();
	
	public static int randomInteger(int min, int max){
		int realMin = Math.min(min, max);
		int realMax = Math.max(min, max);
		int exclusiveSize = realMax-realMin;
		return r.nextInt(exclusiveSize+1)+realMin;
	}
	
	public static double randomDouble(double min, double max){
		double realMin = Math.min(min, max);
		double realMax = Math.max(min, max);
		double exclusiveSize = realMax-realMin;
		
		return r.nextDouble()*exclusiveSize+realMin;
	}
	
	public static BlockFace randomAdjacentFace(){
		BlockFace[] faces = new BlockFace[]{
			BlockFace.DOWN,
			BlockFace.UP,
			BlockFace.EAST,
			BlockFace.WEST,
			BlockFace.NORTH,
			BlockFace.SOUTH
		};
		return faces[randomInteger(0,faces.length-1)];
	}
	
}
