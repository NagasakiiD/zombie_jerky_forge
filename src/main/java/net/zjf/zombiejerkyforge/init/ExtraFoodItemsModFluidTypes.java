
/*
 * MCreator note: This file will be REGENERATED on each build.
 */
package net.zjf.zombiejerkyforge.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fluids.FluidType;

import net.zjf.zombiejerkyforge.fluid.types.PyrotheumFluidType;
import net.zjf.zombiejerkyforge.ExtraFoodItemsMod;

public class ExtraFoodItemsModFluidTypes {
	public static final DeferredRegister<FluidType> REGISTRY = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, ExtraFoodItemsMod.MODID);
	public static final RegistryObject<FluidType> PYROTHEUM_TYPE = REGISTRY.register("pyrotheum", () -> new PyrotheumFluidType());
}
