package net.razorplay.farfaniadrugs.commands;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.nio.ByteBuffer;
import java.util.function.Supplier;

public class ExampleC2SPacket {
    public ExampleC2SPacket() {

    }

    public ExampleC2SPacket(PacketBuffer buf) {

    }

    public void toBytes(PacketBuffer buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!
            ServerPlayerEntity player = context.getSender();
            ServerWorld level = player.getServerWorld();

            EntityType.COW.spawn(level, null, null, player.getPosition(),
                    SpawnReason.COMMAND, true, false);
        });
        return true;
    }

}