package net.razorplay.farfaniadrugs.effect.custom;

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

public class MarijuanaEffect extends Effect {
    private final ResourceLocation shader = new ResourceLocation("farfaniadrugs:shaders/post/phosphor.json");
    private static boolean isEffectApplied = false;
    private int timer;
    public MarijuanaEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        if (entityLivingBaseIn.isPotionActive(this)) {
            CompoundNBT playerData = entityLivingBaseIn.getPersistentData();
            playerData.putBoolean("effectApplied", true);
            playerData.putString("customShader", shader.getPath());
        }
    }

    @Override
    public void removeAttributesModifiersFromEntity(LivingEntity entityLivingBaseIn, AttributeModifierManager attributeMapIn, int amplifier) {
        PlayerEntity player = (PlayerEntity) entityLivingBaseIn;
        CompoundNBT playerData = player.getPersistentData();
        player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 2 * timer, 0));
        player.addPotionEffect(new EffectInstance(Effects.HUNGER, 2 * timer, 0));
        playerData.putBoolean("effectApplied", false);
        playerData.putString("customShader", FarfaniaDrugs.DEFAULT_SHADER.getPath());
        isEffectApplied = false;
        super.removeAttributesModifiersFromEntity(entityLivingBaseIn, attributeMapIn, amplifier);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        if (!isEffectApplied) {
            timer = duration;
            isEffectApplied = true;
        }
        return true;
    }

    public static void resetEffectApplied(PlayerEntity player) {
        CompoundNBT playerD = player.getPersistentData();
        playerD.putBoolean("effectApplied", false);
    }
}
