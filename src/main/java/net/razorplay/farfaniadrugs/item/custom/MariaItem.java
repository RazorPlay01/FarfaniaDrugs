package net.razorplay.farfaniadrugs.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.razorplay.farfaniadrugs.FarfaniaDrugs;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MariaItem extends Item {

    public MariaItem(Properties properties) {
        super(properties);
    }

    /*@Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        entityLiving.addPotionEffect(new EffectInstance(Effects.REGENERATION, 20 * 120, 1));

        // Esperamos 10 segundos antes de activar el otro efecto
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(() -> {
            entityLiving.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20 * 120, 0, false, false));
            entityLiving.addPotionEffect(new EffectInstance(Effects.HUNGER, 20 * 120, 0, false, false));
            scheduler.shutdown();
        }, 10, TimeUnit.SECONDS);

        stack.damageItem(1, entityLiving, player -> player.sendBreakAnimation(entityLiving.getActiveHand()));

        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }*/

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        FarfaniaDrugs.loadShader("phosphor.json", false);
        ItemStack stack = playerIn.getHeldItem(handIn);

        // Aplicamos regeneración durante 10 segundos
        playerIn.addPotionEffect(new EffectInstance(Effects.REGENERATION, 20 * 120, 1));

        // Esperamos 10 segundos antes de activar el otro efecto
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(() -> {
            FarfaniaDrugs.loadShader((String)null, true);

            playerIn.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20 * 120, 0, false, false));
            playerIn.addPotionEffect(new EffectInstance(Effects.HUNGER, 20 * 120, 0, false, false));
            scheduler.shutdown();
        }, 10, TimeUnit.SECONDS);

        stack.shrink(1);
        return ActionResult.func_233538_a_(stack, worldIn.isRemote());
    }
}
