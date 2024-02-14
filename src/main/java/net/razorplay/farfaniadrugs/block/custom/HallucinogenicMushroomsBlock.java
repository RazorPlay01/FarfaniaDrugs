package net.razorplay.farfaniadrugs.block.custom;

import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.razorplay.farfaniadrugs.effect.ModEffects;

import java.util.*;

public class HallucinogenicMushroomsBlock extends BlockItem {
    private List<EffectInstance> firstEffectsList = new ArrayList<>();
    private int timer = 120;

    public HallucinogenicMushroomsBlock(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }


    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        firstEffectsList.add(new EffectInstance(Effects.LUCK, 20 * timer, 1));
        firstEffectsList.add(new EffectInstance(Effects.SPEED, 20 * timer, 0));
        firstEffectsList.add(new EffectInstance(ModEffects.HALLUCINOGENIC_MUSHROOMS_EFFECT.get(), 20 * timer, 0));
        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entityLiving;
            player.addPotionEffect(new EffectInstance(Effects.LUCK, 20 * timer, 1));
            player.addPotionEffect(new EffectInstance(Effects.SPEED, 20 * timer, 0));
            player.addPotionEffect(new EffectInstance(ModEffects.HALLUCINOGENIC_MUSHROOMS_EFFECT.get(), 20 * timer, 0));
            //firstEffectsList.forEach(player::addPotionEffect);
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