package net.zjf.zombiejerkyforge.item;

import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.tags.BlockTags;

public class InvarshearsItem extends ShearsItem {
    public InvarshearsItem() {
        super(new Item.Properties().durability(100));
    }

    @Override
    public int getEnchantmentValue() {
        return 2;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState blockstate) {
        // Use the minecraft:leaves tag to check for leaves
        if (blockstate.is(BlockTags.LEAVES)) {
            return 15.0f; // Match the destroy speed of vanilla shears
        }
        return 1f; // Default speed for other blocks
    }
}
