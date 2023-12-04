package net.razorplay.farfaniadrugs.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {

    public static final ItemGroup FARFADRUGS_GROUP = new ItemGroup("farfadrugs") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.META.get());
        }
    };
}
