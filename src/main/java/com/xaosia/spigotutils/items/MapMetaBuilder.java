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

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;

public class MapMetaBuilder extends MetaBuilder {

	public MapMetaBuilder() {
	}

	public MapMetaBuilder(ItemStack itemStack) {
		super(itemStack);
	}

	public MapMetaBuilder(ItemBuilder itemBuilder) {
		super(itemBuilder);
	}

	protected MapMetaBuilder(MetaBuilder builder) {
		super();
		this.meta = builder.meta;
		this.itemBuilder = builder.itemBuilder;
	}

	private MapMeta getMeta() {
		return (MapMeta) this.meta;
	}

	/**
	 * Change if the map is scaled
	 *
	 * @param scaling
	 * @return the MapMetaBuilder
	 */
	public MapMetaBuilder withScaling(boolean scaling) {
		validateInit();
		getMeta().setScaling(scaling);
		return this;
	}

	/**
	 * Enable scaling for the map
	 *
	 * @return the MapMetaBuilder
	 */
	public MapMetaBuilder withScaling() {
		return withScaling(true);
	}

	/**
	 * @return the build {@link MapMeta}
	 */
	@Override
	public MapMeta build() {
		return (MapMeta) super.build();
	}
}
