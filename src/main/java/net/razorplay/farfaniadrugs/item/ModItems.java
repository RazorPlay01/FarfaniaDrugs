package net.razorplay.farfaniadrugs.item;

import net.minecraft.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.razorplay.farfaniadrugs.FarfaniaDrugs;
import net.razorplay.farfaniadrugs.item.custom.*;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FarfaniaDrugs.MOD_ID);

    public static final RegistryObject<Item> BLACK_WIDOW_POISON =
            ITEMS.register("black_widow_poison", () -> new BlackWidowPoisonItem(
                    new Item.Properties().group(ModItemGroup.FARFADRUGS_GROUP)));
    public static final RegistryObject<Item> COCAINE =
            ITEMS.register("cocaine", () -> new CocaineItem(
                    new Item.Properties().group(ModItemGroup.FARFADRUGS_GROUP)));
    public static final RegistryObject<Item> MARIJUANA =
            ITEMS.register("marijuana", () -> new MarijuanaItem(
                    new Item.Properties().group(ModItemGroup.FARFADRUGS_GROUP)));

    public static final RegistryObject<Item> METHAMPHETAMINE =
            ITEMS.register("methamphetamine", () -> new MethamphetamineItem(
                    new Item.Properties().group(ModItemGroup.FARFADRUGS_GROUP)));
    public static final RegistryObject<Item> FENTANYL =
            ITEMS.register("fentanyl", () -> new FentanylItem(
                    new Item.Properties().group(ModItemGroup.FARFADRUGS_GROUP)));
    public static final RegistryObject<Item> HALLUCINOGENIC_MUSHROOMS =
            ITEMS.register("hallucinogenic_mushrooms", () -> new HallucinogenicMushroomsItem(
                    new Item.Properties().group(ModItemGroup.FARFADRUGS_GROUP)));

    public static final RegistryObject<Item> CIGARETTE =
            ITEMS.register("cigarette", () -> new CigaretteItem(
                    new Item.Properties().group(ModItemGroup.FARFADRUGS_GROUP).maxStackSize(20)));

    public static final RegistryObject<Item> CIGARETTE_1 =
            ITEMS.register("cigarette_1", () -> new CigaretteItem(
                    new Item.Properties().maxStackSize(20)));

    public static final RegistryObject<Item> CIGARETTE_2 =
            ITEMS.register("cigarette_2", () -> new CigaretteItem(
                    new Item.Properties().maxStackSize(20)));

    public static final RegistryObject<Item> CIGARETTE_3 =
            ITEMS.register("cigarette_3", () -> new CigaretteItem(
                    new Item.Properties().maxStackSize(20)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
