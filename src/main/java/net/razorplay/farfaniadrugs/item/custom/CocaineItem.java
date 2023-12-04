package net.razorplay.farfaniadrugs.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.razorplay.farfaniadrugs.util.DefaultUtil;

import java.util.ArrayList;
import java.util.List;

public class CocaineItem extends Item {

    public CocaineItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);

        List<EffectInstance> firstEffectsList = new ArrayList<>();
        firstEffectsList.add(new EffectInstance(Effects.SPEED, 20 * 120, 2));
        firstEffectsList.add(new EffectInstance(Effects.HASTE, 20 * 120, 1));
        List<EffectInstance> secondEffectsList = new ArrayList<>();
        secondEffectsList.add(new EffectInstance(Effects.SLOWNESS, 20 * 120, 0));
        secondEffectsList.add(new EffectInstance(Effects.MINING_FATIGUE, 20 * 120, 0));

        DefaultUtil.playerApplyDrugsEffect(firstEffectsList, "art.json", secondEffectsList, null, true, 120, playerIn);

        stack.shrink(1);
        return ActionResult.func_233538_a_(stack, worldIn.isRemote());
    }
}