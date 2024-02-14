package net.razorplay.farfaniadrugs.item;

import net.minecraft.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.razorplay.farfaniadrugs.FarfaniaDrugs;
import net.razorplay.farfaniadrugs.block.ModBlocks;
import net.razorplay.farfaniadrugs.block.custom.HallucinogenicMushroomsBlock;
import net.razorplay.farfaniadrugs.item.custom.*;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FarfaniaDrugs.MOD_ID);

    //MARIJUANA
    public static final RegistryObject<Item> MARIJUANA_SEED = ITEMS.register("marijuana_seed",
            () -> new BlockItem(ModBlocks.MARIJUANA_CROP.get(), new Item.Properties()
                    .group(ModItemGroup.FARFADRUGS_GROUP)));
    public static final RegistryObject<Item> MARIJUANA = ITEMS.register("marijuana",
            () -> new MarijuanaItem(new Item.Properties().group(ModItemGroup.FARFADRUGS_GROUP)));
    public static final RegistryObject<Item> MARIJUANA_CIGARETTE = ITEMS.register("marijuana_cigarette",
            () -> new MarijuanaCigaretteItem(new Item.Properties().group(ModItemGroup.FARFADRUGS_GROUP).maxStackSize(10)));
    public static final RegistryObject<Item> MARIJUANA_CIGARETTE_1 = ITEMS.register("marijuana_cigarette_1",
            () -> new MarijuanaCigaretteItem(new Item.Properties().maxStackSize(10)));

    public static final RegistryObject<Item> MARIJUANA_CIGARETTE_2 = ITEMS.register("marijuana_cigarette_2",
            () -> new MarijuanaCigaretteItem(new Item.Properties().maxStackSize(10)));

    public static final RegistryObject<Item> MARIJUANA_CIGARETTE_3 = ITEMS.register("marijuana_cigarette_3",
            () -> new MarijuanaCigaretteItem(new Item.Properties().maxStackSize(10)));

    //COCAINE
    public static final RegistryObject<Item> COCAINE_SEED = ITEMS.register("cocaine_seed",
            () -> new BlockItem(ModBlocks.COCAINE_CROP.get(), new Item.Properties()
                    .group(ModItemGroup.FARFADRUGS_GROUP)));
    public static final RegistryObject<Item> COCAINE_LEAF = ITEMS.register("cocaine_leaf",
            () -> new Item(new Item.Properties().group(ModItemGroup.FARFADRUGS_GROUP)));
    public static final RegistryObject<Item> COCAINE = ITEMS.register("cocaine",
            () -> new CocaineItem(new Item.Properties().group(ModItemGroup.FARFADRUGS_GROUP)));

    //HALLUCINOGENIC_MUSHROOMS
    public static final RegistryObject<Item> HALLUCINOGENIC_MUSHROOMS = ITEMS.register("hallucinogenic_mushrooms",
            () -> new HallucinogenicMushroomsBlock(ModBlocks.HALLUCINOGENIC_MUSHROOMS_CROP.get(), new Item.Properties()
                    .food(new Food.Builder().hunger(1).saturation(0.2f).setAlwaysEdible().build())
                    .group(ModItemGroup.FARFADRUGS_GROUP)));
    public static final RegistryObject<Item> COOKED_HALLUCINOGENIC_MUSHROOMS = ITEMS.register("cooked_hallucinogenic_mushrooms",
            () -> new HallucinogenicMushroomsItem(new Item.Properties().group(ModItemGroup.FARFADRUGS_GROUP)));

    //TOBACCO
    public static final RegistryObject<Item> TOBACCO_SEED = ITEMS.register("tobacco_seed",
            () -> new BlockItem(ModBlocks.TOBACCO_CROP.get(), new Item.Properties()
                    .group(ModItemGroup.FARFADRUGS_GROUP)));
    public static final RegistryObject<Item> TOBACCO_LEAF = ITEMS.register("tobacco_leaf",
            () -> new Item(new Item.Properties().group(ModItemGroup.FARFADRUGS_GROUP)));
    public static final RegistryObject<Item> DRIED_TOBACC0_LEAF = ITEMS.register("dried_tobacco_leaf",
            () -> new Item(new Item.Properties().group(ModItemGroup.FARFADRUGS_GROUP)));
    public static final RegistryObject<Item> CIGARETTE = ITEMS.register("cigarette",
            () -> new CigaretteItem(new Item.Properties().group(ModItemGroup.FARFADRUGS_GROUP).maxStackSize(20)));

    public static final RegistryObject<Item> CIGARETTE_1 = ITEMS.register("cigarette_1",
            () -> new CigaretteItem(new Item.Properties().maxStackSize(20)));

    public static final RegistryObject<Item> CIGARETTE_2 = ITEMS.register("cigarette_2",
            () -> new CigaretteItem(new Item.Properties().maxStackSize(20)));

    public static final RegistryObject<Item> CIGARETTE_3 = ITEMS.register("cigarette_3",
            () -> new CigaretteItem(new Item.Properties().maxStackSize(20)));

    //METHAMPHETAMINE
    public static final RegistryObject<Item> METHAMPHETAMINE = ITEMS.register("methamphetamine",
            () -> new MethamphetamineItem(new Item.Properties().group(ModItemGroup.FARFADRUGS_GROUP)));

    //BLACK_WIDOW_POISON
    public static final RegistryObject<Item> BLACK_WIDOW_POISON = ITEMS.register("black_widow_poison",
            () -> new BlackWidowPoisonItem(new Item.Properties().group(ModItemGroup.FARFADRUGS_GROUP)));

    //FENTANYL
    public static final RegistryObject<Item> FENTANYL = ITEMS.register("fentanyl",
            () -> new FentanylItem(new Item.Properties().group(ModItemGroup.FARFADRUGS_GROUP)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
