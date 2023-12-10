package net.razorplay.farfaniadrugs.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.razorplay.farfaniadrugs.FarfaniaDrugs;


@Mod.EventBusSubscriber(modid = FarfaniaDrugs.MOD_ID, value = Dist.CLIENT)
public class PlayerJoinHandler {
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onPlayerLogin(ClientPlayerNetworkEvent.LoggedInEvent event) {
        ClientPlayerEntity player = Minecraft.getInstance().player;
        if (player != null) {
            FarfaniaDrugs.loadCustomShader(FarfaniaDrugs.currentShader);
        }
    }
}

