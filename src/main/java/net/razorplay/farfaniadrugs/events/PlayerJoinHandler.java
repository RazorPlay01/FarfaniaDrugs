package net.razorplay.farfaniadrugs.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.razorplay.farfaniadrugs.FarfaniaDrugs;


@Mod.EventBusSubscriber(modid = FarfaniaDrugs.MOD_ID, value = Dist.CLIENT)
public class PlayerJoinHandler {
    @SubscribeEvent
    public static void onPlayerLogin(ClientPlayerNetworkEvent.LoggedInEvent event) {
        ClientPlayerEntity player = Minecraft.getInstance().player;
        if (player != null) {
            // Your logic to reapply the shader to the player upon logging in
            FarfaniaDrugs.loadCustomShader(FarfaniaDrugs.localJarName);

        }
    }
}