package net.razorplay.farfaniadrugs.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.razorplay.farfaniadrugs.FarfaniaDrugs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DefaultUtil {
    public static void ShaderCooldown(int delay, PlayerEntity player){
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(() -> {
            FarfaniaDrugs.loadShader((String) null, true);
            scheduler.shutdown();
        }, delay, TimeUnit.SECONDS);
    }


    public static void playerApplyDrugsEffect(List<EffectInstance> firstEffectsList, String firstShader,List<EffectInstance> secondEffectsList, String secondShader,boolean isDefaultShader, int delay, PlayerEntity player) {
        FarfaniaDrugs.loadShader(firstShader, false);
        firstEffectsList.forEach(effect -> player.addPotionEffect(effect));

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(() -> {
            FarfaniaDrugs.loadShader((String) secondShader, isDefaultShader);

            secondEffectsList.forEach(effect -> player.addPotionEffect(effect));
            scheduler.shutdown();
        }, delay, TimeUnit.SECONDS);
    }
}

