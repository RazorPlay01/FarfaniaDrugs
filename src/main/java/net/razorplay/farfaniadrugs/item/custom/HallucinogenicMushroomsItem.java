package net.razorplay.farfaniadrugs.item.custom;

import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.razorplay.farfaniadrugs.effect.ModEffects;
import net.razorplay.farfaniadrugs.item.ModItemGroup;
import net.razorplay.farfaniadrugs.util.PlayerUtil;

import java.util.ArrayList;
import java.util.List;

public class HallucinogenicMushroomsItem extends Item {
    private int timer = 20;

    public HallucinogenicMushroomsItem(Properties properties) {
        super(new Item.Properties()
                .group(ModItemGroup.FARFADRUGS_GROUP)
                .food(new Food.Builder()
                        .hunger(1)
                        .saturation(0.2f)
                        .setAlwaysEdible()
                        .build())
        );
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (PlayerUtil.canPlayerConsumeItem(playerIn, PlayerUtil.ModEffect.HALLUCINOGENIC_MUSHROOMS_EFFECT)) {
            return super.onItemRightClick(worldIn, playerIn, handIn);
        } else {
            return new ActionResult<>(ActionResultType.FAIL, playerIn.getHeldItem(handIn));
        }
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        entityLiving.addPotionEffect(new EffectInstance(Effects.LUCK, 20 * timer, 1));
        entityLiving.addPotionEffect(new EffectInstance(Effects.SPEED, 20 * timer, 0));
        entityLiving.addPotionEffect(new EffectInstance(ModEffects.HALLUCINOGENIC_MUSHROOMS_EFFECT.get(), 20 * timer, 0));
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.EAT;
    }
}