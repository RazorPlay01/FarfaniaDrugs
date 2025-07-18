package net.razorplay.farfaniadrugs.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.razorplay.farfaniadrugs.effect.ModEffects;
import net.razorplay.farfaniadrugs.item.ModItemGroup;
import net.razorplay.farfaniadrugs.util.PlayerUtil;

import java.util.*;

public class BlackWidowPoisonItem extends Item {
    private int timer = 120;

    public BlackWidowPoisonItem(Properties group) {
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
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32;  // Duración del efecto en ticks (32 ticks ≈ 1.6 segundos)
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (PlayerUtil.canPlayerConsumeItem(playerIn, PlayerUtil.ModEffect.BLACK_WIDOW_POISON_EFFECT)) {
            return super.onItemRightClick(worldIn, playerIn, handIn);
        } else {
            playerIn.sendStatusMessage(new TranslationTextComponent("item.consume.error"),true);
            return new ActionResult<>(ActionResultType.FAIL, playerIn.getHeldItem(handIn));
        }
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        entityLiving.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 20 * timer, 1));
        entityLiving.addPotionEffect(new EffectInstance(ModEffects.BLACK_WIDOW_POISON_EFFECT.get(), 20 * timer));
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
}

