package jktech.betterthanbetter;
import jktech.betterthanbetter.blocks.Blocks;
import jktech.betterthanbetter.items.items;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.Item;
import turniplabs.halplibe.helper.BlockHelper;
import turniplabs.halplibe.helper.ItemHelper;
import turniplabs.halplibe.helper.RegistryHelper;

import java.io.File;

import static jktech.betterthanbetter.btb.MOD_ID;

public class Registry {

	private static int newBlockId = Block.highestBlockId;
	private static int newItemId = Item.highestItemId;

	public static void onInitialize() {

		// Procedure required to get valid blockIds and itemIds.
		File halplibe_config = new File(FabricLoader.getInstance().getConfigDir() + "/config/bta-halplibe.properties");
		RegistryHelper.scheduleRegistry(halplibe_config.exists(), ()-> {
			newBlockId = BlockHelper.findOpenIds(Blocks.WOODY_BLOCKS.length);
			//newItemId = ItemHelper.findOpenIds(items.ITEM_BLOCKS.length);
		});

	}

	public static int getNewBlockId() {
		newBlockId++;
		return newBlockId;
	}
	public static int getNewItemId() {
		newItemId++;
		return newItemId;
	}

	public static String getModKey(String name) {
		return MOD_ID + "." + name;
	}

	public static String getModPath(String name) {
		return MOD_ID + "/" + name;
	}

}
