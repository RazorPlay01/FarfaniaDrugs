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

@Mod.EventBusSubscriber(modid = FarfaniaDrugs.MOD_ID, value = Dist.CLIENT)
public class PlayerDeathHandler {

}
