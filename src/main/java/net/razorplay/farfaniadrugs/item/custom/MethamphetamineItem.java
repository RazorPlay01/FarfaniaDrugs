package net.razorplay.farfaniadrugs.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.razorplay.farfaniadrugs.effect.ModEffects;
import net.razorplay.farfaniadrugs.util.PlayerUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MethamphetamineItem extends Item {
    private int timer = 20;

    public MethamphetamineItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity player, Hand handIn) {
        ItemStack stack = player.getHeldItem(handIn);
        if (PlayerUtil.canPlayerConsumeItem(player, PlayerUtil.ModEffect.METHAMPHETAMINE_EFFECT)) {
            player.addPotionEffect(new EffectInstance(Effects.STRENGTH, 20 * timer));
            player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 20 * timer));
            player.addPotionEffect(new EffectInstance(ModEffects.METHAMPHETAMINE_EFFECT.get(), 20 * timer));

            stack.shrink(1);
            //return ActionResult.func_233538_a_(stack, worldIn.isRemote());
            return super.onItemRightClick(worldIn, player, handIn);
        } else {
            player.sendStatusMessage(new TranslationTextComponent("item.consume.error"),true);
            return new ActionResult<>(ActionResultType.FAIL, player.getHeldItem(handIn));
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        /*if (Screen.hasShiftDown()||Screen.hasAltDown()||Screen.hasControlDown()){
            tooltip.add(new TranslationTextComponent("tooltip.farfaniadrugs.methamphetamine_test"));
        }else {
            tooltip.add(new TranslationTextComponent("tooltip.farfaniadrugs.methamphetamine"));
        }*/

        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
