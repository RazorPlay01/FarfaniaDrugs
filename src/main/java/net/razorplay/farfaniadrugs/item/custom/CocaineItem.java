package net.razorplay.farfaniadrugs.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.razorplay.farfaniadrugs.FarfaniaDrugs;
import net.razorplay.farfaniadrugs.effect.ModEffects;
import net.razorplay.farfaniadrugs.util.PlayerUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CocaineItem extends Item {
    private int timer = 120;

    public CocaineItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity player, Hand handIn) {
        if (PlayerUtil.canPlayerConsumeItem(player, PlayerUtil.ModEffect.COCAINE_EFFECT)) {
            player.addPotionEffect(new EffectInstance(Effects.SPEED, 20 * timer, 1));
            player.addPotionEffect(new EffectInstance(Effects.HASTE, 20 * timer, 1));
            player.addPotionEffect(new EffectInstance(ModEffects.COCAINE_EFFECT.get(), 20 * timer));
            ItemStack stack = player.getHeldItem(handIn);

            stack.shrink(1);
            //return ActionResult.func_233538_a_(stack, worldIn.isRemote());
            return super.onItemRightClick(worldIn, player, handIn);
        } else {
            player.sendStatusMessage(new TranslationTextComponent("item.consume.error"),true);
            return new ActionResult<>(ActionResultType.FAIL, player.getHeldItem(handIn));
        }
    }

}