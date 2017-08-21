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

package com.xaosia.spigotutils.items;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.xaosia.spigotutils.items.util.HeadTextureChanger;

public class SkullMetaBuilder extends MetaBuilder {

	public SkullMetaBuilder() {
	}

	public SkullMetaBuilder(ItemStack itemStack) {
		super(itemStack);
	}

	public SkullMetaBuilder(ItemBuilder itemBuilder) {
		super(itemBuilder);
	}

	protected SkullMetaBuilder(MetaBuilder builder) {
		super();
		this.meta = builder.meta;
		this.itemBuilder = builder.itemBuilder;
	}

	private SkullMeta getMeta() {
		return (SkullMeta) this.meta;
	}

	@Override
	public SkullMetaBuilder fromConfig(ConfigurationSection section, boolean translateColors) {
		super.fromConfig(section, translateColors);

		if (section.contains("owner")) {
			withOwner(format(section.getString("owner")));
		}
		if (section.contains("texture")) {
			withTexture(section.getString("texture"));
		}

		return this;
	}

	@Override
	public ConfigurationSection toConfig(ConfigurationSection section) {
		section = super.toConfig(section);

		section.set("owner", getMeta().getOwner());
		//TODO: Set texture

		return section;
	}

	/**
	 * Change the owner of the skull
	 *
	 * @param owner Name of the skull owner
	 * @return the SkullMetaBuilder
	 */
	public SkullMetaBuilder withOwner(String owner) {
		validateInit();
		getMeta().setOwner(owner);
		return this;
	}

	/**
	 * Change the displayed texture of the skull
	 *
	 * @param texture {@link java.util.Base64}-Encoded skin texture
	 * @return the SkullMetaBuilder
	 */
	public SkullMetaBuilder withTexture(String texture) {
		validateInit();
		getMeta().setOwner("MHF_ItemBuilder");
		try {
			HeadTextureChanger.applyTextureToMeta(getMeta(), HeadTextureChanger.createProfile(texture));
		} catch (Throwable e) {
			throw new RuntimeException("Failed to apply custom texture", e);
		}
		return this;
	}

	/**
	 * @return The built {@link SkullMeta}
	 */
	@Override
	public SkullMeta build() {
		return (SkullMeta) super.build();
	}

}
