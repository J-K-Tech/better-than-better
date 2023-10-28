package jktech.betterthanbetter;

import jktech.betterthanbetter.blocks.Blocks;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class btb implements ModInitializer {
    public static final String MOD_ID = "better-than-better";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);



    @Override
    public void onInitialize() {
        LOGGER.info("now BTA is better than better");
		Registry.onInitialize();
		Blocks.initializeCraftingRecipes();
    }
}
