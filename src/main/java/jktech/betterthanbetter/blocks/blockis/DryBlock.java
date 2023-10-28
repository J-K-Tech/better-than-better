package jktech.betterthanbetter.blocks.blockis;

import jktech.betterthanbetter.blocks.Blocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.item.ItemBucketIceCream;
import net.minecraft.core.item.ItemFood;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

import java.util.Random;

public class DryBlock extends Block {

	public Block wet;
	public DryBlock(String key, int id, Material material) {
		super(key, id, material);
	}

	public Block withWet(Block W){
		wet=W;
		return this;
	}
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		boolean flag;
		flag = false;
		if (flag || world.getBlockMaterial(x, y, z - 1) == Material.water) {
			flag = true;
		}
		else if (flag || world.getBlockMaterial(x, y, z + 1) == Material.water) {
			flag = true;
		}
		else if (flag || world.getBlockMaterial(x - 1, y, z) == Material.water) {
			flag = true;
		}
		else if (flag || world.getBlockMaterial(x + 1, y, z) == Material.water) {
			flag = true;
		}
		else if (flag || world.getBlockMaterial(x, y + 1, z) == Material.water) {
			flag = true;
		}
		if (flag) {
			world.setBlockWithNotify(x, y, z, this.wet.id);
		}
		else{
		world.scheduleBlockUpdate(x, y, z, this.id, this.tickRate());}
	}

	@Override
	public void onBlockPlaced(World world, int x, int y, int z, Side side, EntityLiving entity, double sideHeight) {
		super.onBlockPlaced(world, x, y, z, side, entity, sideHeight);
		world.scheduleBlockUpdate(x, y, z, this.id, this.tickRate());
	}

	@Override
	public int tickRate() {
		return 20;
	}
}
