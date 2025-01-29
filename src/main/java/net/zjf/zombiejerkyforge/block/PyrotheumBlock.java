package net.zjf.zombiejerkyforge.block;

import net.zjf.zombiejerkyforge.init.ExtraFoodItemsModFluids;
import net.zjf.zombiejerkyforge.procedures.PyrotheumMobplayerCollidesBlockProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.NotNull;

public class PyrotheumBlock extends LiquidBlock implements PyrotheumBlocktick {
	public PyrotheumBlock() {
		super(ExtraFoodItemsModFluids.PYROTHEUM,
				BlockBehaviour.Properties.of().mapColor(MapColor.FIRE).strength(100f).lightLevel(s -> 15).noCollission().noLootTable().liquid().pushReaction(PushReaction.DESTROY).sound(SoundType.EMPTY).replaceable());
	}

	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 100;
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 50;
	}

	@Override
	public void entityInside(@NotNull BlockState blockstate, @NotNull Level world, @NotNull BlockPos pos, @NotNull Entity entity) {
		super.entityInside(blockstate, world, pos, entity);
		PyrotheumMobplayerCollidesBlockProcedure.execute(world, entity);
	}

	public void randomTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		super.randomTick(state, (ServerLevel) level, pos, random);

		// Check for water in neighboring blocks during the random tick
		for (Direction dir : Direction.values()) {
			BlockPos neighborPos = pos.relative(dir);
			BlockState neighborState = level.getBlockState(neighborPos);

			// If water is found next to this block, convert it to stone and remove water
			if (neighborState.is(Blocks.WATER)) {
				level.setBlockAndUpdate(pos, Blocks.STONE.defaultBlockState());  // Change PyrotheumBlock to stone
				level.setBlockAndUpdate(neighborPos, Blocks.AIR.defaultBlockState());  // Remove water block
				return; // Exit after updating (only do it once)
			}
		}

		// Fire spawning logic (same as before)
		for (int x = -4; x <= 4; x++) {
			for (int y = 0; y <= 0; y++) { // Stay on the same Y-level as the fluid block
				for (int z = -4; z <= 4; z++) {
					BlockPos targetPos = pos.offset(x, y, z); // Check positions on the same level
					BlockState targetState = level.getBlockState(targetPos);

					// Only consider solid blocks (using isFaceSturdy instead of isSolid)
					if (targetState.isFaceSturdy(level, targetPos, Direction.UP) && !targetState.is(state.getBlock())) {
						BlockPos abovePos = targetPos.above();
						BlockState aboveState = level.getBlockState(abovePos);

						// Place fire only if the block above is air
						if (aboveState.isAir() && random.nextFloat() < 0.1f) { // Adjust spawn chance as needed
							level.setBlockAndUpdate(abovePos, Blocks.FIRE.defaultBlockState());
						}
					}
				}
			}
		}
	}
}
