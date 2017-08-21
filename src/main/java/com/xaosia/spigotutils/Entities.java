package com.xaosia.spigotutils;

import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class Entities {
	public static Entity get(World world, UUID uuid){
		for(Entity entity : world.getEntities()){
			if(entity.getUniqueId().equals(uuid)){
				return entity;
			}
		}
		return null;
	}

	public static void remove(World world, Set<EntityType> types) {
		Iterator<Entity> it = world.getEntities().iterator();
		while(it.hasNext()){
			if(types.contains(it.next().getType())){
				it.remove();
			}
		}
	}
}
