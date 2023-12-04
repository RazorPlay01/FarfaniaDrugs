package net.razorplay.farfaniadrugs.item;

import net.minecraft.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.razorplay.farfaniadrugs.FarfaniaDrugs;
import net.razorplay.farfaniadrugs.item.custom.*;

public class ModItems {
    private static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FarfaniaDrugs.MOD_ID);

    public static final RegistryObject<Item> VIUDA_NEGRA =
            ITEMS.register("viuda_negra", () -> new ViudaNegraItem(
                    new Item.Properties().group(ModItemGroup.FARFADRUGS_GROUP)));
    public static final RegistryObject<Item> COCAINE =
            ITEMS.register("cocaine", () -> new CocaItem(
                    new Item.Properties().group(ModItemGroup.FARFADRUGS_GROUP)));
    public static final RegistryObject<Item> MARIA =
            ITEMS.register("marihuana", () -> new MariaItem(
                    new Item.Properties().group(ModItemGroup.FARFADRUGS_GROUP)));

    public static final RegistryObject<Item> META =
            ITEMS.register("meta", () -> new MetaItem(
                    new Item.Properties().group(ModItemGroup.FARFADRUGS_GROUP)));
    public static final RegistryObject<Item> FENTANILO =
            ITEMS.register("fentanilo",
                    () -> new FentaniloItem(
                            new Item.Properties().group(ModItemGroup.FARFADRUGS_GROUP)));
    public static final RegistryObject<Item> HONGOS_ALUCINOGENOS =
            ITEMS.register("hongos_alucinogenos",
                    () -> new HongosAlucinogenos(
                            new Item.Properties().group(ModItemGroup.FARFADRUGS_GROUP)));

    public static final RegistryObject<Item> CIGARETTE =
            ITEMS.register("cigarette",
                    () -> new CigaretteItem(
                            new Item.Properties().group(ModItemGroup.FARFADRUGS_GROUP).maxStackSize(20)));

    public static final RegistryObject<Item> CIGARETTE_1 =
            ITEMS.register("cigarette_1",
                    () -> new CigaretteItem(
                            new Item.Properties().maxStackSize(20)));

    public static final RegistryObject<Item> CIGARETTE_2 =
            ITEMS.register("cigarette_2",
                    () -> new CigaretteItem(
                            new Item.Properties().maxStackSize(20)));

    public static final RegistryObject<Item> CIGARETTE_3 =
            ITEMS.register("cigarette_3",
                    () -> new CigaretteItem(
                            new Item.Properties().maxStackSize(20)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
