package net.razorplay.farfaniadrugs.item.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.razorplay.farfaniadrugs.FarfaniaDrugs;
import net.razorplay.farfaniadrugs.effect.ModEffects;
import net.razorplay.farfaniadrugs.item.ModItemGroup;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HallucinogenicMushroomsItem extends Item {
    private List<EffectInstance> firstEffectsList = new ArrayList<>();
    private List<EffectInstance> secondEffectsList = new ArrayList<>();

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
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        firstEffectsList.add(new EffectInstance(Effects.LUCK, 20 * timer, 1));
        firstEffectsList.add(new EffectInstance(Effects.SPEED, 20 * timer, 0));
        firstEffectsList.add(new EffectInstance(ModEffects.HALLUCINOGENIC_MUSHROOMS_EFFECT.get(), 20 * timer, 0));
        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entityLiving;
            firstEffectsList.forEach(player::addPotionEffect);
        }
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