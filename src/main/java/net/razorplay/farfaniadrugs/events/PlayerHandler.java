package net.razorplay.farfaniadrugs.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.razorplay.farfaniadrugs.FarfaniaDrugs;
import net.razorplay.farfaniadrugs.effect.ModEffects;
import net.razorplay.farfaniadrugs.effect.custom.*;
import net.razorplay.farfaniadrugs.util.PlayerUtil;

import java.util.Arrays;
import java.util.List;

import static net.razorplay.farfaniadrugs.FarfaniaDrugs.*;

@Mod.EventBusSubscriber(modid = FarfaniaDrugs.MOD_ID, value = Dist.CLIENT)
public class PlayerHandler {
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onPlayerLogin(ClientPlayerNetworkEvent.LoggedInEvent event) {
        ClientPlayerEntity player = Minecraft.getInstance().player;
        List<Effect> potionEffects = Arrays.asList(ModEffects.FENTANYL_EFFECT.get(), ModEffects.BLACK_WIDOW_POISON_EFFECT.get(),
                ModEffects.COCAINE_EFFECT.get(), ModEffects.MARIJUANA_EFFECT.get(), ModEffects.METHAMPHETAMINE_EFFECT.get(),
                ModEffects.HALLUCINOGENIC_MUSHROOMS_EFFECT.get(), ModEffects.CIGARETTE_EFFECT.get());
        boolean isModEffectActive = PlayerUtil.hasPotionEffect(player, potionEffects);
        if (player != null) {
            CompoundNBT playerData = player.getPersistentData();

            if (isModEffectActive) {
                playerData.putString("customShader", playerData.getString("currentShader"));
                playerData.putBoolean("shaderActive", false);
            }


            if (player.getPersistentData().getString("currentShader").isEmpty()) {
                playerData.putString("customShader", FarfaniaDrugs.DEFAULT_SHADER.getPath());
                playerData.putBoolean("shaderActive", false);
            } else {
                playerData.putString("customShader", playerData.getString("currentShader"));
                playerData.putBoolean("shaderActive", false);
            }
        }
    }


    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onPlayerCloneEvent(PlayerEvent.Clone event) {
        PlayerEntity originalplayer = event.getOriginal();
        CompoundNBT originalplayerData = originalplayer.getPersistentData();
        PlayerEntity player = event.getPlayer();
        CompoundNBT playerData = player.getPersistentData();
        if (!originalplayer.getEntityWorld().isRemote) {
            playerData.putBoolean("shaderActive", originalplayerData.getBoolean("shaderActive"));
            playerData.putBoolean("effectApplied", originalplayerData.getBoolean("effectApplied"));
            playerData.putString("customShader", originalplayerData.getString("customShader"));
            playerData.putString("currentShader", originalplayerData.getString("currentShader"));
        }

        if (event.isWasDeath()) {
            playerData.putString("customShader", FarfaniaDrugs.DEFAULT_SHADER.getPath());
            playerData.putBoolean("shaderActive", false);
            resetEffects(event.getPlayer());
        } else {
            if (playerData.getBoolean("shaderActive")) {
                playerData.putString("customShader", playerData.getString("currentShader"));
                playerData.putBoolean("shaderActive", false);
            } else {
                playerData.putString("customShader", FarfaniaDrugs.DEFAULT_SHADER.getPath());
                playerData.putBoolean("shaderActive", false);
                resetEffects(event.getPlayer());
            }
        }
    }

    public static void resetEffects(PlayerEntity player) {
        BlackWidowPoisonEffect.resetEffectApplied(player);
        CigaretteEffect.resetEffectApplied(player);
        CocaineEffect.resetEffectApplied(player);
        FentanylEffect.resetEffectApplied(player);
        HallucinogenicMushroomsEffect.resetEffectApplied(player);
        MarijuanaEffect.resetEffectApplied(player);
        MethamphetamineEffect.resetEffectApplied(player);
    }
}
