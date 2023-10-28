package jktech.betterthanbetter.blocks;

import jktech.betterthanbetter.Registry;
import jktech.betterthanbetter.blocks.blockis.DryBlock;
import jktech.betterthanbetter.items.items;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.block.ItemBlock;
import net.minecraft.core.item.tool.ItemToolAxe;
import net.minecraft.core.item.tool.ItemToolPickaxe;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.RecipeHelper;
import turniplabs.halplibe.helper.RenderHelper;

import static jktech.betterthanbetter.btb.MOD_ID;

public class Blocks {
	public static final Block sugarcaneblock = new BlockBuilder(MOD_ID)
		.setHardness(1.0f)
		.setResistance(1.0f)
		.setTextures("sugarcaneblock.png")
		.setTags(BlockTags.MINEABLE_BY_AXE)
		//.setItemBlock((BlockBuilder.BlockLambda<ItemBlock>) items.sugarcaneblockitem)
		.build(new Block(
			Registry.getModKey("sugarcaneblock"),
			Registry.getNewBlockId(),
			Material.wood)
		);
	public static final DryBlock drysugarcaneblock = (DryBlock) ((DryBlock) (new BlockBuilder(MOD_ID)
		.setHardness(1.0f)
		.setResistance(1.0f)
		.setTextures("drysugarcaneblock.png")
		.setTags(BlockTags.MINEABLE_BY_AXE)
		//.setItemBlock((BlockBuilder.BlockLambda<ItemBlock>) items.drysugarcaneblockitem)
		.build(new DryBlock(
			Registry.getModKey("drysugarcaneblock"),
			Registry.getNewBlockId(),
			Material.wood)
		))).withWet(sugarcaneblock).setTickOnLoad(true);

	public static final Block[] WOODY_BLOCKS = {sugarcaneblock,drysugarcaneblock};
	final static String[] BLOCK_RECIPE = {
		" AA",
		" AA"
	};

	public static void initializeCraftingRecipes() {
		RecipeHelper.Crafting.createRecipe(new ItemStack(sugarcaneblock),new Object[]{BLOCK_RECIPE,'A',Item.sugarcane});
		RecipeHelper.smeltingManager.addSmelting(sugarcaneblock.asItem().id,new ItemStack(drysugarcaneblock));
	}
}
