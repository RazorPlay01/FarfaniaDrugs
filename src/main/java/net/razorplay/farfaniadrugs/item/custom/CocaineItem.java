package net.razorplay.farfaniadrugs.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.razorplay.farfaniadrugs.FarfaniaDrugs;
import net.razorplay.farfaniadrugs.effect.ModEffects;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CocaineItem extends Item {
    private List<EffectInstance> firstEffectsList = new ArrayList<>();
    private int timer = 20;

    public CocaineItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity player, Hand handIn) {
        firstEffectsList.add(new EffectInstance(Effects.SPEED, 20 * timer, 1));
        firstEffectsList.add(new EffectInstance(Effects.HASTE, 20 * timer, 1));
        firstEffectsList.add(new EffectInstance(ModEffects.COCAINE_EFFECT.get(), 20 * timer));
        ItemStack stack = player.getHeldItem(handIn);

        firstEffectsList.forEach(player::addPotionEffect);

        stack.shrink(1);
        return ActionResult.func_233538_a_(stack, worldIn.isRemote());
    }

}