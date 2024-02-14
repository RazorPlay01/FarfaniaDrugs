package net.razorplay.farfaniadrugs.events;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.server.command.ConfigCommand;
import net.razorplay.farfaniadrugs.FarfaniaDrugs;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FarfaniaDrugs.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        //new SetDrugEffect(event.getDispatcher());
        //new SetDrugShader(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onCropGrow(BlockEvent.CropGrowEvent.Pre event) {
        if (!hasSkyAbove(event.getPos(), event.getWorld())) {
            event.setResult(Event.Result.DENY);
        }
    }

    private static boolean hasSkyAbove(BlockPos pos, IWorld world) {
        for (int y = 1; y <= 255; y++) {
            BlockPos checkPos = pos.up(y);
            if (!world.isAirBlock(checkPos)) {
                return false; // Hay un bloque encima del cultivo
            }
        }
        return true; // No hay bloques encima del cultivo
    }
}
