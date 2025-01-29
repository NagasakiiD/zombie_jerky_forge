package net.zjf.zombiejerkyforge.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraft.world.level.block.Block;
import net.zjf.zombiejerkyforge.block.PyrotheumBlock;
import net.zjf.zombiejerkyforge.ExtraFoodItemsMod;

public class ExtraFoodItemsModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, ExtraFoodItemsMod.MODID);
	public static final RegistryObject<Block> PYROTHEUM = REGISTRY.register("pyrotheum", () -> new PyrotheumBlock());
	// Start of user code block custom blocks
	// End of user code block custom blocks
}
