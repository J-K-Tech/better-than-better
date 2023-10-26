package jktech.betterthanbetter.mixins;

import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.world.Dimension;
import net.minecraft.core.world.World;
import net.minecraft.core.world.save.LevelStorage;
import net.minecraft.core.world.type.WorldType;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(World.class)
public class BetterWorld extends World {
	public BetterWorld(LevelStorage saveHandler, String name, long seed, Dimension dimension, WorldType worldType) {
		super(saveHandler, name, seed, dimension, worldType);
	}

	public BetterWorld(World world, Dimension dimension) {
		super(world, dimension);
	}

	public BetterWorld(LevelStorage saveHandler, String name, Dimension dimension, WorldType worldType, long seed) {
		super(saveHandler, name, dimension, worldType, seed);
	}

	public void updateEntityChunkAndSendToPlayer(int i, int j, int k, Entity tileentity) {
	if (this.isBlockLoaded(i, j, k)) {
		this.getChunkFromBlockCoords(i, k).setChunkModified();
	}
	for (int l = 0; l < this.listeners.size(); ++l) {
		this.listeners.get(l).blockChanged(i, j, k);
	}
}
}
