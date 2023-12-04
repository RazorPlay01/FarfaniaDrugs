package net.razorplay.farfaniadrugs.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.razorplay.farfaniadrugs.FarfaniaDrugs;

import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MetaItem extends Item {
    public MetaItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        FarfaniaDrugs.loadShader("blur.json", false);
        ItemStack stack = playerIn.getHeldItem(handIn);

        // Aplicamos regeneración durante 10 segundos
        playerIn.addPotionEffect(new EffectInstance(Effects.STRENGTH, 20 * 120, 1));
        playerIn.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 20 * 120, 1));

        // Esperamos 10 segundos antes de activar el otro efecto
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(() -> {
            FarfaniaDrugs.loadShader((String)null, true);

            playerIn.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 20 * 120, 0, false, false));
            scheduler.shutdown();
        }, 10, TimeUnit.SECONDS);

        stack.shrink(1);
        return ActionResult.func_233538_a_(stack, worldIn.isRemote());
    }
}
