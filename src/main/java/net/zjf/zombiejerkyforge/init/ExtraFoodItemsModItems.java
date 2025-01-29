
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.zjf.zombiejerkyforge.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.Item;

import net.zjf.zombiejerkyforge.item.*;
import net.zjf.zombiejerkyforge.ExtraFoodItemsMod;

public class ExtraFoodItemsModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, ExtraFoodItemsMod.MODID);
	public static final RegistryObject<Item> ZOMBIE_JERKY = REGISTRY.register("zombie_jerky", () -> new ZombieJerkyItem());
	public static final RegistryObject<Item> SPIDEREYESOUP = REGISTRY.register("spidereyesoup", () -> new SpidereyesoupItem());
	public static final RegistryObject<Item> INVARSHEARS = REGISTRY.register("invarshears", () -> new InvarshearsItem());
	public static final RegistryObject<Item> WOODEN_HELMET = REGISTRY.register("wooden_helmet", () -> new WoodenItem.Helmet());
	public static final RegistryObject<Item> WOODEN_CHESTPLATE = REGISTRY.register("wooden_chestplate", () -> new WoodenItem.Chestplate());
	public static final RegistryObject<Item> WOODEN_LEGGINGS = REGISTRY.register("wooden_leggings", () -> new WoodenItem.Leggings());
	public static final RegistryObject<Item> WOODEN_BOOTS = REGISTRY.register("wooden_boots", () -> new WoodenItem.Boots());
	public static final RegistryObject<Item> PYROTHEUM_BUCKET = REGISTRY.register("pyrotheum_bucket", () -> new PyrotheumItem());
	public static final RegistryObject<Item> MONSTER_JERKY = REGISTRY.register("monster_jerky", () -> new MonsterJerkyItem());
	public static final RegistryObject<Item> STRINGARMOR_HELMET = REGISTRY.register("stringarmor_helmet", () -> new StringarmorItem.Helmet());
	public static final RegistryObject<Item> STRINGARMOR_CHESTPLATE = REGISTRY.register("stringarmor_chestplate", () -> new StringarmorItem.Chestplate());
	public static final RegistryObject<Item> STRINGARMOR_LEGGINGS = REGISTRY.register("stringarmor_leggings", () -> new StringarmorItem.Leggings());
	public static final RegistryObject<Item> STRINGARMOR_BOOTS = REGISTRY.register("stringarmor_boots", () -> new StringarmorItem.Boots());
	public static final RegistryObject<Item> CAMELPACK = REGISTRY.register("camelpack", () -> new CamelpackItem.Chestplate());
	// Start of user code block custom items
	// End of user code block custom items
}
