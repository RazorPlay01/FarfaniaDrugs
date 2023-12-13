package net.razorplay.farfaniadrugs.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.razorplay.farfaniadrugs.effect.ModEffects;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PlayerUtil {
    public enum ModEffect {
        FENTANYL_EFFECT,
        BLACK_WIDOW_POISON_EFFECT,
        COCAINE_EFFECT,
        MARIJUANA_EFFECT,
        METHAMPHETAMINE_EFFECT,
        HALLUCINOGENIC_MUSHROOMS_EFFECT,
        CIGARETTE_EFFECT
    }

    public static boolean canPlayerConsumeItem(PlayerEntity player, ModEffect effect) {
        if (effect == ModEffect.CIGARETTE_EFFECT) {
            List<Effect> potionEffects = Arrays.asList(ModEffects.FENTANYL_EFFECT.get(), ModEffects.BLACK_WIDOW_POISON_EFFECT.get(),
                    ModEffects.COCAINE_EFFECT.get(), ModEffects.MARIJUANA_EFFECT.get(), ModEffects.METHAMPHETAMINE_EFFECT.get(), ModEffects.HALLUCINOGENIC_MUSHROOMS_EFFECT.get());
            return !hasPotionEffect(player, potionEffects);
        } else if (effect == ModEffect.COCAINE_EFFECT) {
            List<Effect> potionEffects = Arrays.asList(ModEffects.FENTANYL_EFFECT.get(), ModEffects.BLACK_WIDOW_POISON_EFFECT.get(),
                    ModEffects.CIGARETTE_EFFECT.get(), ModEffects.MARIJUANA_EFFECT.get(), ModEffects.METHAMPHETAMINE_EFFECT.get(), ModEffects.HALLUCINOGENIC_MUSHROOMS_EFFECT.get());
            return !hasPotionEffect(player, potionEffects);
        } else if (effect == ModEffect.FENTANYL_EFFECT) {
            List<Effect> potionEffects = Arrays.asList(ModEffects.CIGARETTE_EFFECT.get(), ModEffects.BLACK_WIDOW_POISON_EFFECT.get(),
                    ModEffects.COCAINE_EFFECT.get(), ModEffects.MARIJUANA_EFFECT.get(), ModEffects.METHAMPHETAMINE_EFFECT.get(), ModEffects.HALLUCINOGENIC_MUSHROOMS_EFFECT.get());
            return !hasPotionEffect(player, potionEffects);
        } else if (effect == ModEffect.MARIJUANA_EFFECT) {
            List<Effect> potionEffects = Arrays.asList(ModEffects.FENTANYL_EFFECT.get(), ModEffects.BLACK_WIDOW_POISON_EFFECT.get(),
                    ModEffects.COCAINE_EFFECT.get(), ModEffects.CIGARETTE_EFFECT.get(), ModEffects.METHAMPHETAMINE_EFFECT.get(), ModEffects.HALLUCINOGENIC_MUSHROOMS_EFFECT.get());
            return !hasPotionEffect(player, potionEffects);
        } else if (effect == ModEffect.METHAMPHETAMINE_EFFECT) {
            List<Effect> potionEffects = Arrays.asList(ModEffects.FENTANYL_EFFECT.get(), ModEffects.BLACK_WIDOW_POISON_EFFECT.get(),
                    ModEffects.COCAINE_EFFECT.get(), ModEffects.MARIJUANA_EFFECT.get(), ModEffects.CIGARETTE_EFFECT.get(), ModEffects.HALLUCINOGENIC_MUSHROOMS_EFFECT.get());
            return !hasPotionEffect(player, potionEffects);
        } else if (effect == ModEffect.HALLUCINOGENIC_MUSHROOMS_EFFECT) {
            List<Effect> potionEffects = Arrays.asList(ModEffects.FENTANYL_EFFECT.get(), ModEffects.BLACK_WIDOW_POISON_EFFECT.get(),
                    ModEffects.COCAINE_EFFECT.get(), ModEffects.MARIJUANA_EFFECT.get(), ModEffects.METHAMPHETAMINE_EFFECT.get(), ModEffects.CIGARETTE_EFFECT.get());
            return !hasPotionEffect(player, potionEffects);
        } else if (effect == ModEffect.BLACK_WIDOW_POISON_EFFECT) {
            List<Effect> potionEffects = Arrays.asList(ModEffects.FENTANYL_EFFECT.get(), ModEffects.CIGARETTE_EFFECT.get(),
                    ModEffects.COCAINE_EFFECT.get(), ModEffects.MARIJUANA_EFFECT.get(), ModEffects.METHAMPHETAMINE_EFFECT.get(), ModEffects.HALLUCINOGENIC_MUSHROOMS_EFFECT.get());
            return !hasPotionEffect(player, potionEffects);
        }
        return false;
    }

    private static boolean hasPotionEffect(PlayerEntity player, List<Effect> potionEffects) {
        for (Effect effect : potionEffects) {
            if (player.getActivePotionEffect(Objects.requireNonNull(effect)) != null) {
                return true;
            }
        }
        return false;
    }
}

