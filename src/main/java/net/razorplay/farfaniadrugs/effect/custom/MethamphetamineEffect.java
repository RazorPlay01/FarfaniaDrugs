package net.razorplay.farfaniadrugs.effect.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.razorplay.farfaniadrugs.FarfaniaDrugs;

import java.util.ArrayList;
import java.util.List;

public class MethamphetamineEffect extends Effect {
    private ResourceLocation shader = new ResourceLocation("farfaniadrugs:shaders/post/blur.json");
    private boolean effectApplied = false;
    private int timer;

    public MethamphetamineEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        if (entityLivingBaseIn.isPotionActive(this)) {
            if (!effectApplied) {
                FarfaniaDrugs.loadCustomShader(shader);
                effectApplied = true;
            }
        }
    }

    @Override
    public void removeAttributesModifiersFromEntity(LivingEntity entityLivingBaseIn, AttributeModifierManager attributeMapIn, int amplifier) {
        FarfaniaDrugs.loadDefaultShader();
        PlayerEntity player = (PlayerEntity) entityLivingBaseIn;
        player.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 2 * timer, 1));
        effectApplied = false;
        super.removeAttributesModifiersFromEntity(entityLivingBaseIn, attributeMapIn, amplifier);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        if (!effectApplied) {
            timer = duration;
        }
        return true;
    }
}
