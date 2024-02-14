package net.razorplay.farfaniadrugs.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.razorplay.farfaniadrugs.effect.ModEffects;
import net.razorplay.farfaniadrugs.item.ModItems;
import net.razorplay.farfaniadrugs.util.PlayerUtil;


public class MarijuanaCigaretteItem extends Item {
    private int timer = 240;

    public MarijuanaCigaretteItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        // Verificar si el jugador puede consumir el ítem basándose en el estado del efecto de poción
        if (PlayerUtil.canPlayerConsumeItem(playerIn, PlayerUtil.ModEffect.CIGARETTE_EFFECT)) {
            if (stack.getItem() == ModItems.MARIJUANA_CIGARETTE.get()) {
                stack.shrink(1);
                ItemStack newstack = new ItemStack(ModItems.MARIJUANA_CIGARETTE_1.get(), 1);
                if (!playerIn.inventory.addItemStackToInventory(newstack)) {
                    playerIn.dropItem(newstack, false);
                }
            } else if (stack.getItem() == ModItems.MARIJUANA_CIGARETTE_1.get()) {
                stack.shrink(1);
                ItemStack newstack = new ItemStack(ModItems.MARIJUANA_CIGARETTE_2.get(), 1);
                if (!playerIn.inventory.addItemStackToInventory(newstack)) {
                    playerIn.dropItem(newstack, false);
                }
            } else if (stack.getItem() == ModItems.MARIJUANA_CIGARETTE_2.get()) {
                stack.shrink(1);
                ItemStack newstack = new ItemStack(ModItems.MARIJUANA_CIGARETTE_3.get(), 1);
                if (!playerIn.inventory.addItemStackToInventory(newstack)) {
                    playerIn.dropItem(newstack, false);
                }
            } else if (stack.getItem() == ModItems.MARIJUANA_CIGARETTE_3.get()) {
                stack.shrink(1);
            }


            playerIn.addPotionEffect(new EffectInstance(Effects.REGENERATION, 20 * timer, 1));
            playerIn.addPotionEffect(new EffectInstance(ModEffects.MARIJUANA_EFFECT.get(), 20 * timer));

            spawnCigarretePaticles(playerIn);
            return super.onItemRightClick(worldIn, playerIn, handIn);
        } else {
            // El jugador no puede consumir el ítem debido al efecto de poción activo
            playerIn.sendStatusMessage(new TranslationTextComponent("item.consume.error"),true);
            return new ActionResult<>(ActionResultType.FAIL, playerIn.getHeldItem(handIn));
        }
    }

    private static void spawnCigarretePaticles(PlayerEntity playerIn) {
        double x = playerIn.getPosX();
        double y = playerIn.getPosY() + playerIn.getEyeHeight();
        double z = playerIn.getPosZ();
        World world = playerIn.getEntityWorld();
        for (int i = 0; i < 10; i++) {
            double motionX = world.getRandom().nextGaussian() * 0.02D;
            double motionY = world.getRandom().nextGaussian() * 0.02D;
            double motionZ = world.getRandom().nextGaussian() * 0.02D;

            double speed = 0.5D;  // La velocidad de dispersión de las partículas

            world.addParticle(ParticleTypes.LARGE_SMOKE,
                    x, y, z, motionX * speed, motionY * speed, motionZ * speed);
        }
    }
}
