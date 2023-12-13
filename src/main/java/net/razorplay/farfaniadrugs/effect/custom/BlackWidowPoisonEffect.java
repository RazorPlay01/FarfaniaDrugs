package net.razorplay.farfaniadrugs.effect.custom;

import net.minecraft.command.arguments.NBTCompoundTagArgument;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.razorplay.farfaniadrugs.FarfaniaDrugs;

import java.util.ArrayList;
import java.util.List;

public class BlackWidowPoisonEffect extends Effect {
    private ResourceLocation shader = new ResourceLocation("farfaniadrugs:shaders/post/sobel.json");
    private static boolean effectApplied = false;
    private int timer;

    public BlackWidowPoisonEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        if (entityLivingBaseIn.isPotionActive(this)) {
            effectApplied = entityLivingBaseIn.getPersistentData().getBoolean("effectApplied");
            if (!effectApplied) {
                FarfaniaDrugs.loadCustomShader(shader);
                entityLivingBaseIn.getPersistentData().putBoolean("effectApplied", true);
                //effectApplied = true;
            }
        }
    }

    @Override
    public void removeAttributesModifiersFromEntity(LivingEntity entityLivingBaseIn, AttributeModifierManager attributeMapIn, int amplifier) {
        FarfaniaDrugs.loadDefaultShader();
        PlayerEntity player = (PlayerEntity) entityLivingBaseIn;
        player.addPotionEffect(new EffectInstance(Effects.POISON, timer * 2, 0));
        entityLivingBaseIn.getPersistentData().putBoolean("effectApplied", false);
        //effectApplied = false;
        super.removeAttributesModifiersFromEntity(entityLivingBaseIn, attributeMapIn, amplifier);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        if (!effectApplied) {
            timer = duration;
        }
        return true;
    }

    public static void resetEffectApplied(PlayerEntity player) {
        player.getPersistentData().putBoolean("effectApplied", false);
        //effectApplied = false;
    }
}
