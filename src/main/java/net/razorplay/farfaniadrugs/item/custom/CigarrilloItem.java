package net.razorplay.farfaniadrugs.item.custom;

import net.minecraft.client.particle.Particle;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.razorplay.farfaniadrugs.FarfaniaDrugs;
import net.razorplay.farfaniadrugs.util.DefaultUtil;


public class CigarrilloItem extends Item {

    public CigarrilloItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);

        FarfaniaDrugs.loadShader("bumpy.json", false);

        DefaultUtil.ShaderCooldown(10, playerIn);

        //World world = playerIn.getEntityWorld();
        //world.addParticle(ParticleTypes.LARGE_SMOKE,playerIn.getPosX(),playerIn.getPosY(),playerIn.getPosZ(),2,2,2);

        stack.damageItem(1, playerIn, player -> player.sendBreakAnimation(handIn));

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
