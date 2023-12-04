package net.razorplay.farfaniadrugs.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.razorplay.farfaniadrugs.item.ModItems;
import net.razorplay.farfaniadrugs.util.DefaultUtil;


public class CigaretteItem extends Item {

    public CigaretteItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);

        if (stack.getItem() == ModItems.CIGARETTE.get()) {
            stack.shrink(1);
            ItemStack newstack = new ItemStack(ModItems.CIGARETTE_1.get(), 1);
            if (!playerIn.inventory.addItemStackToInventory(newstack)) {
                playerIn.dropItem(newstack, false);
            }
        } else if (stack.getItem() == ModItems.CIGARETTE_1.get()) {
            stack.shrink(1);
            ItemStack newstack = new ItemStack(ModItems.CIGARETTE_2.get(), 1);
            if (!playerIn.inventory.addItemStackToInventory(newstack)) {
                playerIn.dropItem(newstack, false);
            }
        } else if (stack.getItem() == ModItems.CIGARETTE_2.get()) {
            stack.shrink(1);
            ItemStack newstack = new ItemStack(ModItems.CIGARETTE_3.get(), 1);
            if (!playerIn.inventory.addItemStackToInventory(newstack)) {
                playerIn.dropItem(newstack, false);
            }
        } else if (stack.getItem() == ModItems.CIGARETTE_3.get()) {
            stack.shrink(1);
        }

        DefaultUtil.ShaderCooldown("bumpy.json", 10, playerIn);


        spawnCigarretePaticles(playerIn);

        return super.onItemRightClick(worldIn, playerIn, handIn);
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
