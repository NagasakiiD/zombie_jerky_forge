package net.zjf.zombiejerkyforge.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class WaterAddedPyroProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z) {
        BlockPos[] neighbors = {
            new BlockPos((int) x + 1, (int) y, (int) z),
            new BlockPos((int) x - 1, (int) y, (int) z),
            new BlockPos((int) x, (int) y + 1, (int) z),
            new BlockPos((int) x, (int) y - 1, (int) z),
            new BlockPos((int) x, (int) y, (int) z + 1),
            new BlockPos((int) x, (int) y, (int) z - 1)
        };

        for (BlockPos pos : neighbors) {
            BlockState state = world.getBlockState(pos);

            // Check if the neighbor block is water
            if (state.getBlock() == Blocks.WATER) {
                // Replace the water block with cobblestone
                world.setBlock(pos, Blocks.STONE.defaultBlockState(), 3);
            }
        }
    }
}
