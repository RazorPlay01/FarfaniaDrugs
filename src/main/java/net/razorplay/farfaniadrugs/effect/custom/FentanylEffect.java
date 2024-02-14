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

public class FentanylEffect extends Effect {
    private final ResourceLocation shader = new ResourceLocation("farfaniadrugs:shaders/post/deconverge.json");
    public FentanylEffect(EffectType typeIn, int liquidColorIn) {
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
        playerData.putBoolean("effectApplied", false);
        playerData.putString("customShader", FarfaniaDrugs.DEFAULT_SHADER.getPath());
        player.addPotionEffect(new EffectInstance(Effects.WITHER,20 * 15));
        super.removeAttributesModifiersFromEntity(entityLivingBaseIn, attributeMapIn, amplifier);
    }

    public static void resetEffectApplied(PlayerEntity player) {
        CompoundNBT playerD = player.getPersistentData();
        playerD.putBoolean("effectApplied", false);
    }
}
