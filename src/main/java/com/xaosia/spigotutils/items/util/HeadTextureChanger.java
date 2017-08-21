/*
 * Copyright 2015 Marvin Schäfer (inventivetalent). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice, this list of
 *       conditions and the following disclaimer.
 *
 *    2. Redistributions in binary form must reproduce the above copyright notice, this list
 *       of conditions and the following disclaimer in the documentation and/or other materials
 *       provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ''AS IS'' AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those of the
 * authors and contributors and should not be interpreted as representing official policies,
 * either expressed or implied, of anybody else.
 */

package com.xaosia.spigotutils.items.util;

import java.util.Base64;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.block.Skull;
import org.bukkit.inventory.meta.SkullMeta;

import com.xaosia.spigotutils.items.Reflection;

public class HeadTextureChanger {

	public static String encodeBase64(byte[] bytes) {
		return Base64.getEncoder().encodeToString(bytes);
	}

	public static String buildResourceLocation(String url) {
		String format = "{textures:{SKIN:{url:\"%s\"}}}";
		return String.format(format, url);
	}

	public static Object createProfile(String data) {
		try {
			Object profile = NMUClass.com_mojang_authlib_GameProfile.getConstructor(UUID.class, String.class).newInstance(UUID.randomUUID(), "CustomBlock");
			Object propertyMap = AccessUtil.setAccessible(NMUClass.com_mojang_authlib_GameProfile.getDeclaredField("properties")).get(profile);
			Object property = NMUClass.com_mojang_authlib_properties_Property.getConstructor(String.class, String.class).newInstance("textures", data);
			NMUClass.com_google_common_collect_ForwardingMultimap.getDeclaredMethod("put", Object.class, Object.class).invoke(propertyMap, "textures", property);

			return profile;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object createProfile(String value, String signature) {
		if (signature == null) { return createProfile(value); }
		try {
			Object profile = NMUClass.com_mojang_authlib_GameProfile.getConstructor(UUID.class, String.class).newInstance(UUID.randomUUID(), "CustomBlock");
			Object propertyMap = AccessUtil.setAccessible(NMUClass.com_mojang_authlib_GameProfile.getDeclaredField("properties")).get(profile);
			Object property = NMUClass.com_mojang_authlib_properties_Property.getConstructor(String.class, String.class, String.class).newInstance("textures", value, signature);
			NMUClass.com_google_common_collect_ForwardingMultimap.getDeclaredMethod("put", Object.class, Object.class).invoke(propertyMap, "textures", property);

			return profile;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void applyTextureToSkull(Skull skull, Object profile) throws Exception {
		Location loc = skull.getLocation();
		Object world = Reflection.getHandle(loc.getWorld());
		Object tileEntity = NMSClass.WorldServer.getDeclaredMethod("getTileEntity", int.class, int.class, int.class).invoke(world, loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
		AccessUtil.setAccessible(NMSClass.TileEntitySkull.getDeclaredField("j")).set(tileEntity, profile);
		NMSClass.World.getDeclaredMethod("notify", int.class, int.class, int.class).invoke(world, loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
	}

	public static SkullMeta applyTextureToMeta(SkullMeta meta, Object profile) throws Exception {
		if (meta == null) { throw new IllegalArgumentException("meta cannot be null"); }
		if (profile == null) { throw new IllegalArgumentException("profile cannot be null"); }
		Object baseNBTTag = NMSClass.NBTTagCompound.newInstance();
		Object ownerNBTTag = NMSClass.NBTTagCompound.newInstance();

		NMSClass.GameProfileSerializer.getDeclaredMethod("serialize", NMSClass.NBTTagCompound, NMUClass.com_mojang_authlib_GameProfile).invoke(null, ownerNBTTag, profile);

		NMSClass.NBTTagCompound.getDeclaredMethod("set", String.class, NMSClass.NBTBase).invoke(baseNBTTag, "SkullOwner", ownerNBTTag);

		SkullMeta newMeta = (SkullMeta) AccessUtil.setAccessible(OBCClass.inventory_CraftMetaSkull.getDeclaredConstructor(NMSClass.NBTTagCompound)).newInstance(baseNBTTag);

		AccessUtil.setAccessible(OBCClass.inventory_CraftMetaSkull.getDeclaredField("profile")).set(meta, profile);
		AccessUtil.setAccessible(OBCClass.inventory_CraftMetaSkull.getDeclaredField("profile")).set(newMeta, profile);

		return newMeta;
	}

}