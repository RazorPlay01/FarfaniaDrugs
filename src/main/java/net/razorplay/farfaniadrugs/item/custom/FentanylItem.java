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
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.moddiscovery.ModInfo;
import net.razorplay.farfaniadrugs.FarfaniaDrugs;
import net.razorplay.farfaniadrugs.effect.ModEffects;
import net.razorplay.farfaniadrugs.item.ModItemGroup;
import net.razorplay.farfaniadrugs.util.PlayerUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FentanylItem extends Item {
    private int timer = 20;

    public FentanylItem(Properties properties) {
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
        if (PlayerUtil.canPlayerConsumeItem(playerIn, PlayerUtil.ModEffect.FENTANYL_EFFECT)) {
            return super.onItemRightClick(worldIn, playerIn, handIn);
        } else {
            playerIn.sendStatusMessage(new TranslationTextComponent("item.consume.error"),true);
            return new ActionResult<>(ActionResultType.FAIL, playerIn.getHeldItem(handIn));
        }
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        entityLiving.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20 * timer, 3));
        entityLiving.addPotionEffect(new EffectInstance(ModEffects.FENTANYL_EFFECT.get(), 20 * timer));
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