
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.zjf.zombiejerkyforge.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.zjf.zombiejerkyforge.ExtraFoodItemsMod;

public class ExtraFoodItemsModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ExtraFoodItemsMod.MODID);
	public static final RegistryObject<CreativeModeTab> NAGASAKIISADDITIONS = REGISTRY.register("nagasakiisadditions",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.extra_food_items.nagasakiisadditions")).icon(() -> new ItemStack(ExtraFoodItemsModItems.ZOMBIE_JERKY.get())).displayItems((parameters, tabData) -> {
				tabData.accept(ExtraFoodItemsModItems.ZOMBIE_JERKY.get());
				tabData.accept(ExtraFoodItemsModItems.SPIDEREYESOUP.get());
				tabData.accept(ExtraFoodItemsModItems.INVARSHEARS.get());
				tabData.accept(ExtraFoodItemsModItems.WOODEN_HELMET.get());
				tabData.accept(ExtraFoodItemsModItems.WOODEN_CHESTPLATE.get());
				tabData.accept(ExtraFoodItemsModItems.WOODEN_LEGGINGS.get());
				tabData.accept(ExtraFoodItemsModItems.WOODEN_BOOTS.get());
				tabData.accept(ExtraFoodItemsModItems.PYROTHEUM_BUCKET.get());
				tabData.accept(ExtraFoodItemsModItems.MONSTER_JERKY.get());
				tabData.accept(ExtraFoodItemsModItems.STRINGARMOR_HELMET.get());
				tabData.accept(ExtraFoodItemsModItems.STRINGARMOR_CHESTPLATE.get());
				tabData.accept(ExtraFoodItemsModItems.STRINGARMOR_LEGGINGS.get());
				tabData.accept(ExtraFoodItemsModItems.STRINGARMOR_BOOTS.get());
				tabData.accept(ExtraFoodItemsModItems.CAMELPACK.get());
			}).build());
}
