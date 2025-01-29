package net.zjf.zombiejerkyforge.fluid;

import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.ParticleOptions;
import net.zjf.zombiejerkyforge.init.ExtraFoodItemsModItems;
import net.zjf.zombiejerkyforge.init.ExtraFoodItemsModFluids;
import net.zjf.zombiejerkyforge.init.ExtraFoodItemsModFluidTypes;
import net.zjf.zombiejerkyforge.init.ExtraFoodItemsModBlocks;
import net.minecraft.world.level.block.Blocks;

public abstract class PyrotheumFluid extends ForgeFlowingFluid {
	public static final ForgeFlowingFluid.Properties PROPERTIES = new ForgeFlowingFluid.Properties(
			() -> ExtraFoodItemsModFluidTypes.PYROTHEUM_TYPE.get(),
			() -> ExtraFoodItemsModFluids.PYROTHEUM.get(),
			() -> ExtraFoodItemsModFluids.FLOWING_PYROTHEUM.get())
			.explosionResistance(100f)
			.tickRate(3)
			.levelDecreasePerBlock(2)
			.slopeFindDistance(10)
			.bucket(() -> ExtraFoodItemsModItems.PYROTHEUM_BUCKET.get())
			.block(() -> (LiquidBlock) ExtraFoodItemsModBlocks.PYROTHEUM.get());

	private PyrotheumFluid() {
		super(PROPERTIES);
	}

	@Override
	public ParticleOptions getDripParticle() {
		return ParticleTypes.DRIPPING_LAVA;
	}

	@Override
	public void tick(Level level, BlockPos pos, FluidState state) {
		super.tick(level, pos, state);

		// Add fire around the Pyrotheum fluid
		for (Direction direction : Direction.values()) {
			BlockPos adjacentPos = pos.relative(direction);
			BlockState adjacentState = level.getBlockState(adjacentPos);

			// Only place fire on air blocks or valid surfaces for fire (like stone, dirt, etc.)
			if (adjacentState.isAir() && level.getBlockState(adjacentPos.below()).isSolid()) {
				level.setBlockAndUpdate(adjacentPos, Blocks.FIRE.defaultBlockState());
			}
		}
	}

	public static class Source extends PyrotheumFluid {
		public int getAmount(FluidState state) {
			return 8;
		}

		public boolean isSource(FluidState state) {
			return true;
		}
	}

	public static class Flowing extends PyrotheumFluid {
		protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
			super.createFluidStateDefinition(builder);
			builder.add(LEVEL);
		}

		public int getAmount(FluidState state) {
			return state.getValue(LEVEL);
		}

		public boolean isSource(FluidState state) {
			return false;
		}
	}
}
