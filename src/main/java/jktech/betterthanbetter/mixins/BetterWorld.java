package jktech.betterthanbetter.mixins;

import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.world.Dimension;
import net.minecraft.core.world.LevelListener;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.chunk.Chunk;
import net.minecraft.core.world.save.LevelStorage;
import net.minecraft.core.world.type.WorldType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;
import java.util.List;

@Mixin(World.class)
public abstract class BetterWorld{
	@Shadow
	public List<LevelListener> listeners;

	public void updateEntityChunkAndSendToPlayer(int i, int j, int k, Entity tileentity) {
	if (this.isBlockLoaded(i, j, k)) {
		this.getChunkFromBlockCoords(i, k).setChunkModified();
	}
	for (int l = 0; l < this.listeners.size(); ++l) {
		this.listeners.get(l).blockChanged(i, j, k);
	}
	}
	@Shadow
	public abstract boolean isBlockLoaded(int x, int y, int z);
	@Shadow
	public abstract Chunk getChunkFromBlockCoords(int x, int z);
}
