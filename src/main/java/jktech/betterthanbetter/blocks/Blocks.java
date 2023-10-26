package jktech.betterthanbetter.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import turniplabs.halplibe.helper.BlockBuilder;

import static jktech.betterthanbetter.ExampleMod.MOD_ID;

public class Blocks {
	public static BlockBuilder builder= new BlockBuilder(MOD_ID);
	public static final Block sugarcaneblock=new Block("caneblock",999, Material.wood);
	public static final Block drysugarcaneblock=new Block("drycaneblock",1000, Material.wood);

	public static void reg(){

	}
}
