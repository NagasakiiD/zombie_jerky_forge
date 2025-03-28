package net.zjf.zombiejerkyforge.item;

import com.stereowalker.survive.needs.IRealisticEntity;
import com.stereowalker.survive.needs.WaterData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class CamelpackItem extends ArmorItem {
	private static final int MAX_DRINKS = 3;
	private static final int MAX_DURATION = 6000;
	private static final int TICK_INTERVAL = 20;

	public CamelpackItem(ArmorItem.Type type, Item.Properties properties) {
		super(new ArmorMaterial() {
			public int getDurabilityForType(ArmorItem.Type type) { return 100; }
			public int getDefenseForType(ArmorItem.Type type) { return 2; }
			public int getEnchantmentValue() { return 9; }
			public SoundEvent getEquipSound() { return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_generic")); }
			public Ingredient getRepairIngredient() { return Ingredient.of(); }
			public String getName() { return "camelpack"; }
			public float getToughness() { return 0.25F; }
			public float getKnockbackResistance() { return 0.0F; }
		}, type, properties);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public int getDrinksLeft(ItemStack stack) {
		return stack.getOrCreateTag().getInt("DrinksLeft");
	}

	public void setDrinksLeft(ItemStack stack, int drinks) {
		stack.getOrCreateTag().putInt("DrinksLeft", Math.min(drinks, MAX_DRINKS));
	}

	public void decrementDrinks(ItemStack stack) {
		setDrinksLeft(stack, Math.max(getDrinksLeft(stack) - 1, 0));
	}

	@SubscribeEvent
	public static void onPlayerTick(LivingEvent.LivingTickEvent event) {
		LivingEntity entity = event.getEntity();
		if (entity instanceof Player player) {
			ItemStack chestItem = player.getItemBySlot(EquipmentSlot.CHEST);
			if (chestItem.getItem() instanceof CamelpackItem) {
				CompoundTag tag = chestItem.getOrCreateTag();
				int drinks = tag.getInt("DrinksLeft");
				int hydrationTime = tag.getInt("HydrationTime");

				if (drinks > 0 && hydrationTime == 0) {
					hydrationTime = (int) ((float) drinks / MAX_DRINKS * MAX_DURATION);
					tag.putInt("HydrationTime", hydrationTime);
				}

				if (hydrationTime > 0) {
					if (player.tickCount % TICK_INTERVAL == 0) {
						hydrationTime -= TICK_INTERVAL;
						tag.putInt("HydrationTime", hydrationTime);

						if (player instanceof IRealisticEntity realisticPlayer) {
							WaterData waterData = realisticPlayer.getWaterData();
							if (waterData.getWaterLevel() < 40) {
								waterData.drink(1, 1.0F, 0, false);
								waterData.save((LivingEntity) realisticPlayer);
							}
						}
					}
					if (hydrationTime <= 0 && drinks > 0) {
						tag.putInt("DrinksLeft", drinks - 1);
						tag.putInt("HydrationTime", (int) ((float) drinks / MAX_DRINKS * MAX_DURATION));
					}
				}
			}
		}
	}

	public boolean fillWithPurifiedWater(Player player, ItemStack stack, ItemStack bottle) {
		if (bottle.getItem() == Items.POTION) {
			Potion potion = PotionUtils.getPotion(bottle);
			ResourceLocation potionRegistryName = ForgeRegistries.POTIONS.getKey(potion);
			if (potionRegistryName != null && potionRegistryName.equals(new ResourceLocation("survive", "purified_water"))) {
				CompoundTag tag = stack.getOrCreateTag();
				int drinks = tag.getInt("DrinksLeft");
				if (drinks < MAX_DRINKS) {
					setDrinksLeft(stack, drinks + 1);
					PotionUtils.setPotion(stack, potion);
					if (!player.isCreative()) {
						player.setItemInHand(player.getUsedItemHand(), new ItemStack(Items.GLASS_BOTTLE));
					}
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean isDamageable(ItemStack stack) {
		return false;
	}

	public static class Chestplate extends CamelpackItem {
		public Chestplate() {
			super(Type.CHESTPLATE, new Item.Properties().durability(100));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "extra_food_items:textures/entities/camelpacklayer.png";
		}
	}
}
