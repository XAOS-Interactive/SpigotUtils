package com.xaosia.spigotutils;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;


public class Colors {

	public static Color toColor(ChatColor color){
		switch(color){
			case AQUA:
				return Color.fromRGB(0,255,255);
			case BLACK:
				return Color.fromRGB(0,0,0);
			case BLUE:
				return Color.fromRGB(0,0,255);
			case DARK_AQUA:
				return Color.fromRGB(30,163,162);
			case DARK_BLUE:
				return Color.fromRGB(0,0,139);
			case DARK_GRAY:
				return Color.fromRGB(169,169,169);
			case DARK_GREEN:
				return Color.fromRGB(0,100,0);
			case DARK_PURPLE:
				return Color.fromRGB(148,0,211);
			case DARK_RED:
				return Color.fromRGB(139,0,0);
			case GOLD:
				return Color.fromRGB(255,115,0);
			case GRAY:
				return Color.fromRGB(128,128,128);
			case GREEN:
				return Color.fromRGB(0,128,0);
			case LIGHT_PURPLE:
				return Color.fromRGB(239,82,159);
			case RED:
				return Color.fromRGB(255,0,0);
			case YELLOW:
				return Color.fromRGB(255,255,0);
			case WHITE:
			default:
			case UNDERLINE:
			case STRIKETHROUGH:
			case RESET:
			case MAGIC:
			case ITALIC:
			case BOLD:
				return Color.fromRGB(255,255,255);
		}
	}
	
	public static DyeColor toDyeColor(ChatColor color){
		switch(color){
			case AQUA:
				return DyeColor.LIGHT_BLUE;
			case BLACK:
				return DyeColor.BLACK;
			case DARK_BLUE:
			case DARK_AQUA:
			case BLUE:
				return DyeColor.BLUE;
			case GRAY:
			case DARK_GRAY:
				return DyeColor.GRAY;
			case GREEN:
			case DARK_GREEN:
				return DyeColor.GREEN;
			case DARK_PURPLE:
				return DyeColor.PURPLE;
			case DARK_RED:
				return DyeColor.BROWN;
			case GOLD:
				return DyeColor.ORANGE;
			case LIGHT_PURPLE:
				return DyeColor.PINK;
			case RED:
				return DyeColor.RED;
			case YELLOW:
				return DyeColor.YELLOW;
			case WHITE:
			default:
			case UNDERLINE:
			case STRIKETHROUGH:
			case RESET:
			case MAGIC:
			case ITALIC:
			case BOLD:
				return DyeColor.WHITE;
		}
	}
	
	
	
}
