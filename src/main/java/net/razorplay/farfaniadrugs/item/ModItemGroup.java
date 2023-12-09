package net.razorplay.farfaniadrugs.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.fml.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class ModItemGroup {
    public static final ItemGroup FARFADRUGS_GROUP = new ItemGroup("farfadrugs") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.METHAMPHETAMINE.get());
        }
    };
}
