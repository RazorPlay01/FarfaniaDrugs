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
import net.razorplay.farfaniadrugs.util.DefaultUtil;

import java.util.ArrayList;
import java.util.List;
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
        ItemStack stack = playerIn.getHeldItem(handIn);

        List<EffectInstance> firstEffectsList = new ArrayList<>();
        firstEffectsList.add(new EffectInstance(Effects.STRENGTH, 20 * 120, 1));
        firstEffectsList.add(new EffectInstance(Effects.RESISTANCE, 20 * 120, 1));
        List<EffectInstance> secondEffectsList = new ArrayList<>();
        secondEffectsList.add(new EffectInstance(Effects.MINING_FATIGUE, 20 * 120, 0));

        DefaultUtil.playerApplyDrugsEffect(firstEffectsList, "blur.json",
                secondEffectsList, null, true, 120, playerIn);

        stack.shrink(1);
        return ActionResult.func_233538_a_(stack, worldIn.isRemote());
    }
}
