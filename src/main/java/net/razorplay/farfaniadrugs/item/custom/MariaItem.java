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
import net.razorplay.farfaniadrugs.util.DefaultUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MariaItem extends Item {

    public MariaItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);

        List<EffectInstance> firstEffectsList = new ArrayList<>();
        firstEffectsList.add(new EffectInstance(Effects.REGENERATION, 20 * 120, 1));
        List<EffectInstance> secondEffectsList = new ArrayList<>();
        secondEffectsList.add(new EffectInstance(Effects.SLOWNESS, 20 * 240, 0));
        secondEffectsList.add(new EffectInstance(Effects.HUNGER, 20 * 240, 0));

        DefaultUtil.playerApplyDrugsEffect(firstEffectsList, "phosphor.json",
                secondEffectsList, null, true, 120, playerIn);

        stack.shrink(1);
        return ActionResult.func_233538_a_(stack, worldIn.isRemote());
    }
}
