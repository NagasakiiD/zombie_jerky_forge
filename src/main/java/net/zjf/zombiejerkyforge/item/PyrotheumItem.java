
package net.zjf.zombiejerkyforge.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BucketItem;

import net.zjf.zombiejerkyforge.init.ExtraFoodItemsModFluids;

public class PyrotheumItem extends BucketItem {
	public PyrotheumItem() {
		super(ExtraFoodItemsModFluids.PYROTHEUM, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1).rarity(Rarity.COMMON));
	}
}
