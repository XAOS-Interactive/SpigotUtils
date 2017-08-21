package com.xaosia.spigotutils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class Inventories {
	
	public static boolean containsAtLeast(PlayerInventory inventory, ItemStack compareTo, int amount, ItemCompareOption option){
		int amountFound = 0;
		amountFound += countOccurrences(inventory.getArmorContents(),compareTo,option);
		amountFound += countOccurrences(inventory.getContents(),compareTo,option);
		Logger.debug("amount found = "+amountFound);
		return amountFound >= amount;
	}
	
	public static List<ItemStack> find(PlayerInventory inventory, ItemStack compareTo, ItemCompareOption option){
		List<ItemStack> found = new ArrayList<ItemStack>();
		found.addAll(find(inventory.getArmorContents(),compareTo,option));
		found.addAll(find(inventory.getContents(),compareTo,option));
		return found;
	}
	
	public static void remove(PlayerInventory inventory, ItemStack toRemove, ItemCompareOption option){
		int leftToRemove = toRemove.getAmount();
		
		ItemStack[] inv = inventory.getContents();		
		for(int i=0 ; i< inv.length ; i++){
			ItemStack item = inv[i];
			if(leftToRemove > 0 && item != null && itemEquals(item, toRemove, option)){
				if(leftToRemove >= item.getAmount()){
					leftToRemove -= item.getAmount();
					inv[i] = null;
				}else{
					item.setAmount(item.getAmount()-leftToRemove);
					leftToRemove = 0;
				}
			}
		}
		inventory.setContents(inv);
		
		ItemStack[] armor = inventory.getArmorContents();		
		for(int i=0 ; i< armor.length ; i++){
			ItemStack item = armor[i];
			if(leftToRemove > 0 && item != null && itemEquals(item, toRemove, option)){
				if(leftToRemove >= item.getAmount()){
					leftToRemove -= item.getAmount();
					armor[i] = new ItemStack(Material.AIR);
				}else{
					item.setAmount(item.getAmount()-leftToRemove);
					leftToRemove = 0;
				}
			}
		}
		inventory.setArmorContents(armor);
	}

	
	public static int countOccurrences(ItemStack[] contents, ItemStack compareTo, ItemCompareOption option){
		int found = 0;
		for(ItemStack item : contents){
			if(itemEquals(item, compareTo, option)){
				found += item.getAmount();
			}
		}
		return found;
	}
	

	public static List<ItemStack> find(ItemStack[] contents, ItemStack compareTo, ItemCompareOption option) {
		List<ItemStack> found = new ArrayList<ItemStack>();
		for(ItemStack item : contents){
			if(itemEquals(item, compareTo, option)){
				found.add(item.clone());
			}
		}
		return found;
	}
	
	public static boolean itemEquals(ItemStack one, ItemStack two, ItemCompareOption option){
		boolean equals = true;
		
		if(one == null && two == null){
			Logger.debug("one==null && two==null");
			return true;
		}
		
		if( (one == null && two != null) || (one != null && two == null) ){
			Logger.debug("one==null && two!=null || one!=null && two==null");
			return false;
		}
		
		Logger.debug("comparing one="+one.getType().toString()+" to two="+two.getType().toString());
		
		if(option.isMaterial()){
			equals = one.getType().equals(two.getType());
			Logger.debug("compare material="+equals);
		}
		
		if(equals && option.isAmount()){
			equals = one.getAmount() == two.getAmount();
			Logger.debug("compare amount="+equals);
		}
		
		if(equals && option.isDamageValue()){
			equals = one.getDurability() == two.getDurability();
			Logger.debug("compare durability="+equals);
		}
		
		if(equals && option.isDisplayName()){
			if(!one.hasItemMeta() && !two.hasItemMeta()){
				equals = true;
			}else if(one.hasItemMeta() && two.hasItemMeta()){
				String oneName = one.getItemMeta().getDisplayName();
				String twoName = two.getItemMeta().getDisplayName();
				if( (oneName == null && twoName == null) || (oneName != null && twoName != null && oneName.equals(twoName))){
					equals = true;
				}else{
					equals = false;
				}
			}else{
				equals = false;
			}
			Logger.debug("compare display name="+equals);
		}
		
		if(equals && option.isLore()){
			if(!one.hasItemMeta() && !two.hasItemMeta()){
				equals = true;
			}else if(one.hasItemMeta() && two.hasItemMeta()){
				List<String> oneLore = one.getItemMeta().getLore();
				List<String> twoLore = two.getItemMeta().getLore();
				if(oneLore == null && twoLore == null){
					equals = true;
				}else if (oneLore != null && twoLore != null){
					if(oneLore.size() == twoLore.size()){
						for(int i=0 ; i< oneLore.size() ; i++){
							if(!oneLore.get(i).equals(twoLore.get(i))){
								equals = false;
								break;
							}
						}
					}else{
						equals = false;
					}
				}else{
					equals = false;
				}
			}else{
				equals = false;
			}
			Logger.debug("compare lore="+equals);
		}
		
		if(equals && option.isEnchantments()){
			Map<Enchantment, Integer> oneEnch = one.getEnchantments();
			Map<Enchantment, Integer> twoEnch = two.getEnchantments();
			
			if(oneEnch == null && twoEnch == null){
				equals = true;
			}else if(oneEnch != null && twoEnch != null){
				if(oneEnch.size() == twoEnch.size()){
					equals = true;
					for(Entry<Enchantment,Integer> oneEnchEntry : oneEnch.entrySet()){
						Integer twoEnchLevel = twoEnch.get(oneEnchEntry.getKey());
						if(twoEnchLevel == null || twoEnchLevel != oneEnchEntry.getValue()){
							equals = false;
							break;
						}
					}
				}else{
					equals = false;
				}
			}else{
				equals = false;
			}
			Logger.debug("compare enchantments="+equals);
		}
		
		if(equals && option.isUnbreakable()){
			if(!one.hasItemMeta() && !two.hasItemMeta()){
				equals = true;
			}else if(one.hasItemMeta() && two.hasItemMeta()){
				equals = one.getItemMeta().spigot().isUnbreakable() == two.getItemMeta().spigot().isUnbreakable();
			}else{
				equals = false;
			}
			Logger.debug("compare unbreakable="+equals);
		}
		
		if(equals && option.isColor()){
			if(!one.hasItemMeta() && !two.hasItemMeta()){
				equals = true;
			}else if(one.hasItemMeta() && two.hasItemMeta() && one.getItemMeta() instanceof LeatherArmorMeta && two.getItemMeta() instanceof LeatherArmorMeta){
				LeatherArmorMeta oneMeta = (LeatherArmorMeta) one.getItemMeta();
				LeatherArmorMeta twoMeta = (LeatherArmorMeta) two.getItemMeta();
				if(oneMeta.getColor() == null && twoMeta.getColor() == null){
					equals = true;
				}else if(oneMeta.getColor() != null && twoMeta.getColor() != null && oneMeta.getColor().equals(twoMeta.getColor())){
					equals = true;
				}else{
					equals = false;
				}
			}else{
				equals = false;
			}
			Logger.debug("compare color="+equals);
		}	

		if(option.isSkullOwner()){
			if(!one.hasItemMeta() && !two.hasItemMeta()){
				equals = true;
			}else if(one.hasItemMeta() && two.hasItemMeta() && one.getItemMeta() instanceof SkullMeta && two.getItemMeta() instanceof SkullMeta){
				SkullMeta oneMeta = (SkullMeta) one.getItemMeta();
				SkullMeta twoMeta = (SkullMeta) two.getItemMeta();
				if(oneMeta.getOwner() == null && twoMeta.getOwner() == null){
					equals = true;
				}else if(oneMeta.getOwner() != null && twoMeta.getOwner() != null && oneMeta.getOwner().equals(twoMeta.getOwner())){
					equals = true;
				}else{
					equals = false;
				}
			}else{
				equals = false;
			}
			Logger.debug("compare color="+equals);
		}	
		

		Logger.debug("=> compare ="+equals);
		
		return equals;
	}
	
	
	public static void clear(Player player) {
		player.getInventory().clear();
		
		//clear player armor
		ItemStack[] emptyArmor = new ItemStack[4];
		for(int i=0 ; i<emptyArmor.length ; i++){
			emptyArmor[i] = new ItemStack(Material.AIR);
		}
		player.getInventory().setArmorContents(emptyArmor);
		
	}

	public static void give(Player player, List<ItemStack> items) {
		for(ItemStack item: items){
			give(player,item);
		}
	}
	
	public static void give(Player player, ItemStack item) {
		int maxStackSize = item.getMaxStackSize();
		if(maxStackSize != -1 && item.getAmount() > maxStackSize){

			ItemStack fullStack = item.clone();
			fullStack.setAmount(maxStackSize);
			
			ItemStack itemReduced = item.clone();
			itemReduced.setAmount(item.getAmount() - maxStackSize);

			give(player, fullStack);
			give(player, itemReduced);
		}else{
			player.getInventory().addItem(item.clone());
		}
	}

	public static void setArmor(Player player, List<ItemStack> armor) {
		if(armor.size() >= 4){
			PlayerInventory inv = player.getInventory();
			inv.setHelmet(armor.get(0).clone());
			inv.setChestplate(armor.get(1).clone());
			inv.setLeggings(armor.get(2).clone());
			inv.setBoots(armor.get(3).clone());
		}
	}
	
	
}
