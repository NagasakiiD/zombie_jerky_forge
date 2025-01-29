
/*
 * MCreator note: This file will be REGENERATED on each build.
 */
package net.zjf.zombiejerkyforge.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;

import net.zjf.zombiejerkyforge.fluid.PyrotheumFluid;
import net.zjf.zombiejerkyforge.ExtraFoodItemsMod;

public class ExtraFoodItemsModFluids {
	public static final DeferredRegister<Fluid> REGISTRY = DeferredRegister.create(ForgeRegistries.FLUIDS, ExtraFoodItemsMod.MODID);
	public static final RegistryObject<FlowingFluid> PYROTHEUM = REGISTRY.register("pyrotheum", () -> new PyrotheumFluid.Source());
	public static final RegistryObject<FlowingFluid> FLOWING_PYROTHEUM = REGISTRY.register("flowing_pyrotheum", () -> new PyrotheumFluid.Flowing());

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class FluidsClientSideHandler {
		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {
			ItemBlockRenderTypes.setRenderLayer(PYROTHEUM.get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(FLOWING_PYROTHEUM.get(), RenderType.translucent());
		}
	}
}
