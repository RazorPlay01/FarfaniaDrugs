package net.razorplay.farfaniadrugs.events;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.server.command.ConfigCommand;
import net.razorplay.farfaniadrugs.FarfaniaDrugs;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FarfaniaDrugs.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event){
        //new SetDrugEffect(event.getDispatcher());
        //new SetDrugShader(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }
}
