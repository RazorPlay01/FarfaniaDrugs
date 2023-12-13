package net.razorplay.farfaniadrugs.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.razorplay.farfaniadrugs.effect.ModEffects;
import net.razorplay.farfaniadrugs.util.PlayerUtil;

import java.util.ArrayList;
import java.util.List;

public class MarijuanaItem extends Item {
    private int timer = 20;

    public MarijuanaItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity player, Hand handIn) {
        if (PlayerUtil.canPlayerConsumeItem(player, PlayerUtil.ModEffect.MARIJUANA_EFFECT)) {
            ItemStack stack = player.getHeldItem(handIn);
            player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 20 * timer, 1));
            player.addPotionEffect(new EffectInstance(ModEffects.MARIJUANA_EFFECT.get(), 20 * timer));

            stack.shrink(1);
            //return ActionResult.func_233538_a_(stack, worldIn.isRemote());
            return super.onItemRightClick(worldIn, player, handIn);
        } else {
            return new ActionResult<>(ActionResultType.FAIL, player.getHeldItem(handIn));
        }
    }
}
