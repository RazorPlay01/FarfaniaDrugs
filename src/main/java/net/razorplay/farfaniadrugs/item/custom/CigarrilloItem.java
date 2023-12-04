package net.razorplay.farfaniadrugs.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.network.play.server.SSetSlotPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class CigarrilloItem extends Item {
    private final Map<Integer, String> durabilityModelMap;

    public CigarrilloItem(Properties properties) {
        super(properties);
        durabilityModelMap = new HashMap<>();
        durabilityModelMap.put(4, "cigarro");
        durabilityModelMap.put(3, "cigarro_1");
        durabilityModelMap.put(2, "cigarro_2");
        durabilityModelMap.put(1, "cigarro_3");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);

        // Resto del código para el manejo de la durabilidad y el modelo del ítem
        int maxUses = stack.getMaxDamage(); // Obtiene el máximo de usos
        int remainingUses = maxUses - stack.getDamage(); // Calcula los usos restantes
        String modelName = "cigarro"; // Nombre de modelo predeterminado

        if (durabilityModelMap.containsKey(remainingUses)) {
            modelName = durabilityModelMap.get(remainingUses);
        }

        // Construye la ruta completa del modelo
        String modelPath = "farfaniadrugs:item/" + modelName;

        // Lógica para cambiar el modelo del ítem utilizando modelPath
        // Ejemplo: envío de paquete de cambio de modelo en el lado del servidor
        if (playerIn instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) playerIn;
            ItemStack stackCopy = stack.copy(); // Hacer una copia del ItemStack modificado
            serverPlayer.connection.sendPacket(new SSetSlotPacket(0, serverPlayer.inventory.currentItem, stackCopy));
        }

        stack.damageItem(1, playerIn, player -> player.sendBreakAnimation(handIn));

        //System.out.println("Damange" + stack.getDamage());
        //System.out.println("modelPath" + modelPath);

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }


  /*  @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {11
        ItemStack stack = playerIn.getHeldItem(handIn);

        stack.damageItem(1, playerIn, player -> {
            int maxUses = 4; // Máximo de usos
            int remainingUses = maxUses - stack.getDamage(); // Calcular usos restantes

            // Seleccionar el modelo dependiendo de los usos restantes
            String modelName = "farfaniadrugs:item/cigarrillo_" + remainingUses;

            if (player instanceof ServerPlayerEntity) {
                ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
                ItemStack stackCopy = stack.copy(); // Hacer una copia del ItemStack modificado
                serverPlayer.connection.sendPacket(new SSetSlotPacket(0, serverPlayer.inventory.currentItem, stackCopy));
            }
            System.out.println(modelName);

            // Aplicar el modelo al ítem
            //((ServerPlayerEntity) player).connection.sendPacket(new SSetSlotPacket(0, 36 + player.inventory.currentItem, stack));

            player.sendBreakAnimation(handIn);
        });

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
*/

}
