package net.razorplay.farfaniadrugs.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.razorplay.farfaniadrugs.FarfaniaDrugs;
import net.razorplay.farfaniadrugs.effect.ModEffects;
import net.razorplay.farfaniadrugs.effect.custom.*;

import static net.razorplay.farfaniadrugs.FarfaniaDrugs.*;

@Mod.EventBusSubscriber(modid = FarfaniaDrugs.MOD_ID, value = Dist.CLIENT)
public class PlayerDeathHandler {
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onPlayerCloneEvent(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            loadDefaultShader();
            resetEffects(event.getPlayer());
        } else {
            if (shaderActive) {
                loadCustomShader(currentShader);
            } else {
                loadDefaultShader();
            }
        }
    }
    public static void resetEffects(PlayerEntity player){
        BlackWidowPoisonEffect.resetEffectApplied(player);
        CigaretteEffect.resetEffectApplied(player);
        CocaineEffect.resetEffectApplied(player);
        FentanylEffect.resetEffectApplied(player);
        HallucinogenicMushroomsEffect.resetEffectApplied(player);
        MarijuanaEffect.resetEffectApplied(player);
        MethamphetamineEffect.resetEffectApplied(player);
    }
}
